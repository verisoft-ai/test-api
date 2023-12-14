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
package co.verisoft.fw.extentreport;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 * Extent Report manager. This class is a singletone, and wraps the behavior of ExtentReport.<br>
 * ExtentTest is created automatically upon invoking getInstance();
 * </P>
 *
 * @author David Yehezkel, refactor by Nir Gallner
 * @see DelegateExtentReports
 * @see DelegateExtentTest
 * @since 1.9.6, updated 0.0.3 (Jan 2022)
 */
@ToString
@Slf4j
public final class ReportManager {

    private static ReportManager instance;
    static Map<Integer, DelegateExtentTest> tests = new HashMap<>();


    @Getter
    private final DelegateExtentReports report;
    @Getter
    private static String reportName;


    private ReportManager(String reportHtmlNameAndPath) {
        ReportManager.reportName = reportHtmlNameAndPath;
        report = new DelegateExtentReports();
        ExtentSparkReporter esr = new ExtentSparkReporter(reportName);
        esr.config().enableOfflineMode(true);
        report.attachReporter(esr);

    }


    /**
     * Return the instance of ReportManager. At the first call, it will create an instance. Afterwards, it will follow
     * the singletone design pattern
     *
     * @return ReportManager instance of the manager
     */
    public synchronized static ReportManager getInstance() {
        if (null == instance) {

            String directoryName = "Extent-Report";
            //Create a directory if does not exist
            File directory = new File(System.getProperty("user.dir") + "/target/" + directoryName + "/");
            if (!directory.exists()) {
                directory.mkdir();
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String currentDateAndTime = dateFormat.format(new Date());

            // Append the formatted date and time to the report filename
            reportName = (System.getProperty("user.dir") + "/target/" + directoryName + "/TestReport-" + currentDateAndTime + ".html");

            // Create new instance of the report manager
            instance = new ReportManager(reportName);

        }
        return instance;
    }


    /**
     * Returns the current test
     *
     * @return Current running test, or null
     */
    public synchronized @Nullable DelegateExtentTest getCurrentTest() {
        return tests.get((int) (Thread.currentThread().getId()));
    }


    /**
     * Creates a new test for the extent report
     *
     * @param name test name
     * @return new test instance
     */
    public synchronized DelegateExtentTest newTest(String name) {
        DelegateExtentTest test = new DelegateExtentTest(report.createTest(name));
        tests.put((int) (Thread.currentThread().getId()), test);
        return test;
    }


    /**
     * Creates a new test for the extent report with a description
     *
     * @param name test name
     * @param description test description
     * @return new test instance
     * @author Gili Eliach
     */
    public synchronized DelegateExtentTest newTest(String name,String description) {
        DelegateExtentTest test = new DelegateExtentTest(report.createTest(name,description));
        tests.put((int) (Thread.currentThread().getId()), test);
        return test;
    }

    /**
     * flush the file and creates the report html
     */
    public synchronized void flush() {
        this.report.flush();
    }
}
