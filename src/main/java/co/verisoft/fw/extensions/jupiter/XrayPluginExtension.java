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
import co.verisoft.fw.xray.Status;
import co.verisoft.fw.xray.XrayIdentifier;
import co.verisoft.fw.xray.XrayJsonTestObject;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.File;
import java.io.FileWriter;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Extension to create Xray report.
 * Xray report is based on the XrayIdentifier annotation. If present, the test instance will be logged
 * and reported to XrayReporter. Test reports are stored in the global store space
 *
 * @author Nir Gallner
 * @since 0.0.2 (Jan 2022)
 */
@SuppressWarnings("unchecked")
@Slf4j
public class XrayPluginExtension implements AfterEachCallback, BeforeEachCallback,
        BeforeAllCallback, ExtensionContext.Store.CloseableResource {

    private static boolean executed = false;


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

        // Decide if test has failed
        Status status = extensionContext.getExecutionException().isPresent() ? Status.FAILED : Status.PASSED;

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
     * At the end of the execution, create the report and save it to the target/XrayReport directory
     */
    @Override
    public void close() {
        // Get the object from store
        Map<String, XrayJsonTestObject> tests = StoreManager.getStore(StoreType.GLOBAL).getValueFromStore("tests");

        // Aggregate the results
        List<XrayJsonTestObject> testReuslts = new ArrayList<>();
        for (String key : tests.keySet())
            testReuslts.add(tests.get(key));

        // Create the Report Json object
        JSONObject obj = new JSONObject();
        obj.put("testExecutionKey", System.getProperty("xray.testExecution.key"));

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
        } catch (Exception e) {
            log.error("Could not create xray report. Error is " + e.getMessage());
        }
    }
}
