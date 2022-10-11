package co.verisoft.fw.extensions.jupiter;

/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import co.verisoft.fw.store.StoreManager;
import co.verisoft.fw.store.StoreType;
import co.verisoft.fw.utils.ExtendedLog;
import co.verisoft.fw.xray.Status;
import co.verisoft.fw.xray.XrayIdentifier;
import co.verisoft.fw.xray.XrayJsonTestObject;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import okhttp3.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Date;

/**
 * Extension to create Xray report.
 * Xray report is based on the XrayIdentifier annotation. If present, the test instance will be logged
 * and reported to XrayReporter. Test reports are stored in the global store space
 *
 * After each run the XrayResult json file imports automatically to Xray.
 * New Test Execution creates in jira board with all the current tests run results.
 * <b> for this feature you need: </b>
 * 1. know your jira type - cloud or server/DataCenter.
 * You can know your Jira type based on the URL. if Jira project URL contains 'atlassian.net' after the project name it means that you use Jira cloud.
 * otherwise, you use Jira server or DataCenter that behave the same.
 * 2. Create Personal Access Token for Authentication:
 * In jira cloud go to Settings -> Apps -> API Keys. and create API key.
 * In jira server/DataCenter go to User Profile -> Profile -> Personal Access Token. and Create token.
 * Another option for Authentication in Jira server/DataCenter is to create Applicative user with admin permissions and make Authentication based on userName and password.
 * 3. Go to xray-plugin.properties file and fill the properties according to the document in the file. -
 * first you need to fill the Jira type property - cloud, or server. in both server and DataCenter -  fill server. then, fill all the parameters you need like persona access token e.c.
 *
 * @author Nir Gallner
 * @since 0.0.2 (Jan 2022)
 */
@SuppressWarnings("unchecked")
@Slf4j
public class XrayPluginExtension implements AfterEachCallback, BeforeEachCallback,
        BeforeAllCallback, ExtensionContext.Store.CloseableResource {

    private static final Logger logger = new ExtendedLog(XrayPluginExtension.class);

    private static boolean executed = false;

    // Load xray properties from xray-plugin.properties file
    public static Properties appProps = getXrayPluginProperties();


    /**
     * One time in the test execution. Create a list of test objects and store it in the global space of the
     * VeriSoft store.
     *
     * @param extensionContext context from Junit
     */
    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        if (!executed) {

            // Set the callback for clase method at the end of the session
            extensionContext.getRoot().getStore(ExtensionContext.Namespace.GLOBAL).put("XrayCallback", this);

            Map<String, XrayJsonTestObject> xrayTestInstances = new HashMap<>();
            StoreManager.getStore(StoreType.GLOBAL).putValueInStore("tests", xrayTestInstances);

            executed = true;
        }

        log.debug("Registered " + this.getClass().getName() + " for class " + extensionContext.getRequiredTestClass().getName());
    }


    /**
     * Creates a new test object and put it in the XrayTestObject list, located on the global space of the store.
     * If there is no XrayIdentifier annotaion present, or annotation is present by there is no value, test object
     * will not be created nor reported
     *
     * @param extensionContext Junit 5 context
     */
    @Override
    public void beforeEach(ExtensionContext extensionContext) {

        // Validation - If there is no annotation, test should not be reported to jira - no need to continue
        if (!(extensionContext.getElement().isPresent() &&
                extensionContext.getElement().get().isAnnotationPresent(XrayIdentifier.class)))
            return;

        String[] xrayValues = extensionContext.getElement().get().getAnnotation(XrayIdentifier.class).value();

        // Validation - must contain a value
        if (xrayValues.length == 0)
            return;

        // Simple case - just one test id
        if (xrayValues.length == 1) {
            XrayJsonTestObject obj = new XrayJsonTestObject.XrayJsonTestObjectBuilder()
                    .testKey(xrayValues[0])
                    .start(ZonedDateTime.now())
                    .build();
            Map<String, XrayJsonTestObject> tests = StoreManager.getStore(StoreType.GLOBAL).getValueFromStore("tests");
            tests.put(xrayValues[0], obj);
        }

    }


    /**
     * Updates test object, which was created at the beginning of the test. If XrayIdentifier does not exist,
     * or exists with no value, do nothing. <br>
     * Note that this solution also works if there is a retry mechanism, since we use the HashMap object. So in a retry
     * the old value will be deleted and new values will be inserted.
     *
     * @param extensionContext Junit 5 extension
     */
    @Override
    public void afterEach(ExtensionContext extensionContext) {

        // Validation - If ther is no annotation, test should not be reported to jira - no need to continue
        if (!(extensionContext.getElement().isPresent() &&
                extensionContext.getElement().get().isAnnotationPresent(XrayIdentifier.class)))
            return;

        String[] xrayValues = extensionContext.getElement().get().getAnnotation(XrayIdentifier.class).value();

        // Validation - must contain a value
        if (xrayValues.length == 0)
            return;

        // Set the test status based on xray.type property value in Jira server/DC - set the status to PASS/FAIL, in cloud set the status to PASSED/FAILED
        Status status = extensionContext.getExecutionException().isPresent() ? appProps.getProperty("xray.type").equals("server") ? Status.FAIL : Status.FAILED : appProps.getProperty("xray.type").equals("server") ? Status.PASS : Status.PASSED;
        // Simple case - just one test id
        if (xrayValues.length == 1) {
            Map<String, XrayJsonTestObject> tests = StoreManager.getStore(StoreType.GLOBAL).getValueFromStore("tests");

            XrayJsonTestObject obj = tests.get(xrayValues[0]);

            // Validation
            if (obj == null)
                return;

            // Update test results
            obj = new XrayJsonTestObject.XrayJsonTestObjectBuilder(tests.get(xrayValues[0]))
                    .finish(ZonedDateTime.now())
                    .status(status)
                    .build();

            // Put it back. Not sure if necessary
            tests.put(xrayValues[0], obj);
        }
    }


    /**
     * load xray properties from xray-plugin-properties file
     * @return Properties - all xray properties
     */
    public static Properties getXrayPluginProperties(){
        // Load xray properties
        // TODO fix it! user.dir is not the framework path, maybe move this file to project?
        String xrayConfigPath = System.getProperty("user.dir") + "/src/test/resources/xray-plugin.properties";
        Properties appProps = new Properties();
        try {
            appProps.load(new FileInputStream(xrayConfigPath));
        } catch (Exception e) {
            // No property file found - rely on -D only
            log.warn("No property file were found: " + xrayConfigPath + ", will rely on injection (-D) only");
        }
        return appProps;
    }


    /**
     * Automatically import xray json result file to jira server/DataCenter
     * Creates a new Test Execution with the results in your Jira xray server/DC project board
     * @param reportFile - xray result file path
     * @return String - the API call response
     * @throws IOException
     */
    private String importJsonResultToJiraServerDC(String reportFile) throws IOException{

        // Defined the import file type
        final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json");

        // Initial necessary jira properties
        String jiraBaseUrl = System.getenv().getOrDefault("JIRA_BASE_URL", appProps.getProperty("JIRA_BASE_URL"));
        String jiraUsername = System.getenv().getOrDefault("JIRA_USERNAME", appProps.getProperty("JIRA_USERNAME"));
        String jiraPassword = System.getenv().getOrDefault("JIRA_PASSWORD", appProps.getProperty("JIRA_PASSWORD"));
        String jiraPersonalAccessToken = System.getenv().getOrDefault("JIRA_TOKEN", appProps.getProperty("JIRA_TOKEN"));
        logger.info("Importing a Xray JSON report to a Xray Server/Data Center instance");

        // Create authenticate token
        OkHttpClient client = new OkHttpClient();
        String credentials;

        // With PersonalAccessToken Authentication
        if (!jiraPersonalAccessToken.equals("null")) {
            credentials = "Bearer " + jiraPersonalAccessToken;
        }
        // With basic Authentication - userName and password
        else {
            credentials = Credentials.basic(jiraUsername, jiraPassword);
        }

        String endpointUrl = jiraBaseUrl + "/rest/raven/1.0/import/execution";
        RequestBody requestBody = null;
        try {
            // Get xray Json result file content
            String reportContent = new String ( Files.readAllBytes( Paths.get(reportFile) ) );
            requestBody = RequestBody.create(reportContent, MEDIA_TYPE_JSON);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        // Build the request
        Request request = new Request.Builder().url(endpointUrl).post(requestBody).addHeader("Authorization", credentials).build();
        Response response = null;
        try {
            // Execute import xray json result endpoint
            response = client.newCall(request).execute();
            // Get response
            String responseBody = response.body().string();

            if (response.isSuccessful()){
                logger.info("request done successfully, new created Test Execution: " + responseBody);
                return(responseBody);
            } else {
                logger.warn("request failed, logs: " + response.message());
                throw new IOException("Unexpected HTTP code ");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw(e);
        }
    }


    /**
     * Automatically import xray json result file to jira cloud
     * Creates a new Test Execution with tests results in your Jira xray cloud project board
     * @param reportFile - xray result file path
     * @return String - the API call response
     * @throws IOException
     */
    private String importJsonResultToJiraCloud(String reportFile) throws IOException {

        // Defined the import file type
        final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json");

        // Initial necessary jira properties
        String clientId = System.getenv().getOrDefault("CLIENT_ID", appProps.getProperty("CLIENT_ID"));
        String clientSecret = System.getenv().getOrDefault("CLIENT_SECRET", appProps.getProperty("CLIENT_SECRET"));
        String xrayCloudApiBaseUrl = appProps.getProperty("XRAY_CLOUD_API_BASE_URL");
        String authenticateUrl = xrayCloudApiBaseUrl + "/authenticate";

        logger.info("Importing a Xray JSON report to a Xray Cloud instance");

        // Create authenticate token
        OkHttpClient client = new OkHttpClient();
        String authenticationPayload = "{ \"client_id\": \"" + clientId +"\", \"client_secret\": \"" + clientSecret +"\" }";

        // Build create authenticate depends on jira properties
        RequestBody body = RequestBody.create(authenticationPayload, MEDIA_TYPE_JSON);
        Request request = new Request.Builder().url(authenticateUrl).post(body).build();

        Response response = null;
        String authToken = null;
        try {
            // Execute the generated authenticate request
            response = client.newCall(request).execute();
            // Get the response
            String responseBody = response.body().string();

            if (response.isSuccessful()){
                authToken = responseBody.replace("\"", "");
                logger.info("successfully generated authenticate token: " + authToken);
            } else {
                logger.warn("failed to authenticate " + response.message());
                throw new IOException("failed to authenticate " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String credentials = "Bearer " + authToken;
        String xrayCloudApiUploadUrl = "https://xray.cloud.getxray.app/api/v1";

        String endpointUrl =  xrayCloudApiUploadUrl + "/import/execution";
        RequestBody requestBody = null;
        try {
            // Get xray Json result file content
            String reportContent = new String ( Files.readAllBytes( Paths.get(reportFile) ) );
            requestBody = RequestBody.create(reportContent, MEDIA_TYPE_JSON);
        } catch (Exception e1) {
            e1.printStackTrace();
            throw e1;
        }

        // Build the auto import xray result file to jira request
        request = new Request.Builder().url(endpointUrl).post(requestBody).addHeader("Authorization", credentials).build();
        response = null;
        try {
            // Execute import xray json result endpoint
            response = client.newCall(request).execute();
            // Get response
            String responseBody = response.body().string();
            if (response.isSuccessful()){
                logger.info("request done successfully, new created Test Execution: " + responseBody);
                return(responseBody);
            } else {
                logger.warn("request failed, logs: " + response.message());
                throw new IOException("Unexpected HTTP code " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }


    /**
     * At the end of the execution, create the report and save it to the target/XrayReport directory
     * Import the report to your Jira instance - creates new Test Execution with the tests run results
     */
    @Override
    public void close() throws IOException {
        // Data definition for the new TestExecution
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.mm");

        String testExecutionSummary = "Tests Report " + dateFormat.format(new Date());
        String testExecutionDescription = "This report created Automatically after tests run";

        HashMap<String, String> infoJson = new HashMap<>();
        infoJson.put("summary", testExecutionSummary);
        infoJson.put("description", testExecutionDescription);

        // Get the object from store
        Map<String, XrayJsonTestObject> tests = StoreManager.getStore(StoreType.GLOBAL).getValueFromStore("tests");

        // Aggregate the results
        List<XrayJsonTestObject> testReuslts = new ArrayList<>();
        for (String key : tests.keySet())
            testReuslts.add(tests.get(key));

        // Create the Report Json object
        JSONObject obj = new JSONObject();
        // Info key creates a new TestExecution
        obj.put("info", infoJson);

        JSONArray arr = new JSONArray();
        for (val result : testReuslts)
            arr.add(result.asJsonObject());

        obj.put("tests", arr);

        // Create the directory
        String localPath = System.getProperty("user.dir") + "/target/XrayReport";

        File destDir = new File(localPath);
        if (!destDir.exists())
            //noinspection ResultOfMethodCallIgnored
            destDir.mkdir();

        if (!destDir.exists()) {
            log.error("Directory " + localPath + " was not created - terminating operation");
            return;
        }

        //Write the data to the file
        try {
            FileWriter file = new FileWriter(localPath + "/XrayResult.json");
            file.write(obj.toJSONString());
            file.flush();
            file.close();
        }
        catch (Exception e) {
            log.error("Could not create xray report. Error is " + e.getMessage());
        }
        // Import xrayResult Json file to Jira cloud or server/DC based on xray.type property value
        // Server/DataCenter
        if(appProps.getProperty("xray.type").equals("server")){
            importJsonResultToJiraServerDC(localPath + "/XrayResult.json");
        }
        // Cloud
        else if(appProps.getProperty("xray.type").equals("cloud")){
            importJsonResultToJiraCloud(localPath + "/XrayResult.json");
        }
        // If xray.type is empty or contains uncorrected value
        else{
            logger.warn("The Xray result json file created but not imported because of uncorrected xray type in properties file, fix it and fill server or cloud");
        }

    }
}
