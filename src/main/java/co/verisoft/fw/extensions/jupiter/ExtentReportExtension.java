/*
 * (C) Copyright 2023 VeriSoft (http://www.verisoft.co)
 *
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
package co.verisoft.fw.extensions.jupiter;

import co.verisoft.fw.ReportPortalObserver;
import co.verisoft.fw.extentreport.ExtentReportData;
import co.verisoft.fw.extentreport.ExtentReportReportObserver;
import co.verisoft.fw.extentreport.ReportManager;
import co.verisoft.fw.report.observer.Report;
import co.verisoft.fw.report.observer.ReportLevel;
import co.verisoft.fw.report.observer.ReportSource;
import co.verisoft.fw.store.StoreManager;
import co.verisoft.fw.store.StoreType;
import com.aventstack.extentreports.Status;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.plexus.util.ExceptionUtils;
import org.junit.jupiter.api.extension.*;

import java.util.*;

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
    public synchronized void beforeAll(ExtensionContext context) {

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

            // Register a report observer
            @SuppressWarnings("unused")
            ExtentReportReportObserver extentReportReportObserver = new ExtentReportReportObserver(ReportLevel.INFO);
            ReportPortalObserver reportPortalObserver = new ReportPortalObserver(ReportLevel.INFO);

            // Create an object to hold screenshots
            Map<String, List<String>> screenShots = new HashMap<>();
            StoreManager.getStore(StoreType.GLOBAL).putValueInStore("screenshots", screenShots);

            didRun = true;
        }


    }


    @Override
    public void beforeEach(ExtensionContext context) {
        /* no-op. Satisfy the interface */
    }


    /**
     * Callback that is invoked <em>before</em> an individual test.
     * Creates a new test instance using the method name
     *
     * @param context Junit 5 context object
     */
    @Override
    public void beforeTestExecution(ExtensionContext context) {

        // Create a UUID for test
        UUID uuid = UUID.randomUUID();
        StoreManager.getStore(StoreType.LOCAL_THREAD).putValueInStore("testId", uuid);

        // Get test name
        String testName = context.getDisplayName();

        // Create a new test
        ReportManager.getInstance().newTest(testName);

        Report.debug(ReportSource.REPORT, "Test Start. Test name: " + testName);
        Report.info(ReportSource.REPORT, "Test Name: " + testName);
        Report.info(ReportSource.REPORT, "KEY: testId  VALUE: " + uuid);
    }


    @Override
    public void afterTestExecution(ExtensionContext context) {
        /* no-op. Satisfy the interface */
    }


    /**
     * Callback that is invoked after an individual test is
     * Checks if the test have failed, and if so, it collects a screenshot
     *
     * @param context the current extension context; never {@code null}
     */
    @Override
    public void afterEach(ExtensionContext context) {

        if (context.getExecutionException().isPresent()) {
            String stackTrace = ExceptionUtils.getStackTrace(context.getExecutionException().get());
            String msg = "An Error occured during test.";
            Report.error(msg, ExtentReportData.builder().data(context.getExecutionException().get()).type(ExtentReportData.Type.THROWABLE).build());
        } else if (Objects.requireNonNull(ReportManager.getInstance().getCurrentTest()).getStatus() == Status.FAIL) {
            throw new AssertionError("Report fail caused test to fail");
        }

        // Find out if there are screenshots collected during the test
        Map<String, List<String>> screenShots = StoreManager.getStore(StoreType.GLOBAL)
                .getValueFromStore("screenshots");

        List<String> images = Objects.isNull(screenShots) ? null : screenShots.get(context.getDisplayName());

        if (!Objects.isNull(images))
            for (String image : images) {
                Report.error("Error Screenshot", ExtentReportData.builder().data(image).type(ExtentReportData.Type.SCREENSHOT).build());
            }

        // Get the test name
        String testName = context.getTestMethod().get().getName();

        // Finally, fail / pass the test
        if (context.getExecutionException().isPresent()) {
            ReportManager.getInstance().getCurrentTest().fail("Test Result - FAIL");
            Report.report(ReportSource.REPORT, ReportLevel.DEBUG, "Test Ended. Test name: " + testName +
                    "Test Result - FAIL");
        } else {
            ReportManager.getInstance().getCurrentTest().pass("Test Result - PASS");
            Report.report(ReportSource.REPORT, ReportLevel.DEBUG, "Test Ended. Test name: " + testName +
                    "Test Result - PASS");
        }
    }


    @Override
    public void afterAll(ExtensionContext context) {
        /* no-op. Satisfy the interface */
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
