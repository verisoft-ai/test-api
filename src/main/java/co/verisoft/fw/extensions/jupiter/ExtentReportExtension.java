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

import co.verisoft.fw.extentreport.ReportManager;
import co.verisoft.fw.store.StoreManager;
import co.verisoft.fw.store.StoreType;
import com.aventstack.extentreports.Status;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.*;

import java.util.UUID;

import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.GLOBAL;

/**
 * VeriSoft extent report handling extension. It replaces It initializes the report at the beginning of all tests
 * and creates the HTML final report once at the end of all tests. <br>
 * Before each test it creates a new test instance in the report, and adds a UUID to the report and store<br>
 * Unlike Junit 5 beforeAll and afterAll, This extension will be invoked <b>exactly</b> once, even if there are
 * several test containers (in different classes).<br>
 * <b>Note!</b> <br>
 * 1. In order for the extension to work you need to register it at the class level. <br>
 * use <br> <pre> {@code @ExtendWith({ExtentReportExtension.class})} </pre>. <br>
 * 2.You may write several extension in th same line:<br> <pre> {@code @ExtendWith({ExtentReportExtension.class,
 * Extension2.class, Extension3.class})} </pre><br>
 * 3. Since Junit 5 does not guarantee which class will be invoked first, register this extension in all test methods,
 * or create a class which register the extension and other classes to derive from it.<br>
 * <p>
 * TODO: Figure out how to attach screenshots
 *
 * @author David Yehezkel, VeriSoft
 * @since 1.9.6
 */
@Slf4j
public class ExtentReportExtension implements BeforeAllCallback,
        BeforeEachCallback,
        BeforeTestExecutionCallback,
        AfterTestExecutionCallback,
        AfterEachCallback,
        AfterAllCallback,
        ExtensionContext.Store.CloseableResource {

    private static boolean didRun = false;

    /**
     * This method will be invoked <b>only once before all test executions</b>.
     * It does the following tasks:<br>
     * 1. Register a callback to be called once <b>all</b> tests are completed<br>
     * 2. Registers property variables to the system property - path to test results
     * (from property file into system property), additional test information if supplied etc..
     */
    @Override
    public synchronized void beforeAll(ExtensionContext context) throws Exception {

        if (!didRun) {
            // The following line registers a callback hook when the root test context is shut down
            context.getRoot().getStore(GLOBAL).put("Extent Report Callback", this);

            // Sets additional data if available
            if (System.getProperty("tester.name") != null)
                ReportManager.getInstance().getReport().setSystemInfo("Tester Name: ", System.getProperty("tester.name"));

            if (System.getProperty("test.suite.name") != null)
                ReportManager.getInstance().getReport().setSystemInfo("Test Suite Name: ", System.getProperty("test.suite.name"));

            if (System.getProperty("test.included.groups") != null)
                ReportManager.getInstance().getReport().setSystemInfo("Test Group Executed: ",
                        System.getProperty("test.included.groups"));

            didRun = true;
        }
    }


    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        /** no-op. Satisfy the interface**/
    }


    /**
     * Callback that is invoked <em>before</em> an individual test.
     * Creates a new test instance using the method name
     *
     * @param context Junit 5 context object
     */
    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {

        // Create a UUID for test
        UUID uuid = UUID.randomUUID();
        StoreManager.getStore(StoreType.LOCAL_THREAD).putValueInStore("testId", uuid);

        // Create a new test
        ReportManager.getInstance().newTest(context.getTestMethod().get().getName());

        ReportManager.getInstance().getCurrentTest().info("KEY: testId VALUE: " + uuid);
    }


    /**
     * Callback that is invoked <em>immediately after</em> an individual test is
     * executed but after any user-defined setup methods have been executed
     * for that test.
     * Checks if the test have failed, and if so, it collects a screenshot
     *
     * @param context the current extension context; never {@code null}
     */
    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        if (context.getExecutionException().isPresent()) {
            ReportManager.getInstance().getCurrentTest().fail("An Error occured. Reason: " +
                    context.getExecutionException().toString() + "  See logs for further details");
        } else if (ReportManager.getInstance().getCurrentTest().getStatus() == Status.FAIL) {
            throw new AssertionError("Report fail caused test to fail");
        }

        //TODO: Find a way to add screen shot without specifically using selenium or playwright
        // ReportManager.getInstance().currentTest().addScreenCaptureFromPath(file.getAbsolutePath(), "Error Screenshot");
    }


    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        /** no-op. Satisfy the interface **/
    }


    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        /** no-op. Satisfy the interface **/
    }


    /**
     * This method will be invoked <b>only once after all test executions</b>.
     * It creates the HTML report.
     */
    @Override
    public void close() {
        ReportManager.getInstance().flush();
    }
}
