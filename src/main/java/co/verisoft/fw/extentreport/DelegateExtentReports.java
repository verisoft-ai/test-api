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

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.gherkin.model.IGherkinFormatterModel;
import com.aventstack.extentreports.model.ReportStats;
import com.aventstack.extentreports.observer.ExtentObserver;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

/**
 * Class delegates ExtentReport. This class is mainly for logging purposes. It synchronizes logging mechanism with
 * the report mechanism.
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @since 0.0.3 (Jan 2022)
 * @see DelegateExtentTest
 */
@NoArgsConstructor
@ToString
@Slf4j
public class DelegateExtentReports extends ExtentReports {
    
    @Override
    public void attachReporter(ExtentObserver... observer) {
        log.debug("Reporter attached");
        super.attachReporter(observer);
    }

    @Override
    public ExtentTest createTest(Class<? extends IGherkinFormatterModel> type, String name, String description) {
        log.debug("Creates new Test. type: " + type + ", name: " + name +
                ", description: " + description);
        return super.createTest(type, name, description);
    }

    @Override
    public ExtentTest createTest(Class<? extends IGherkinFormatterModel> type, String name) {
        log.debug("Creates new Test. type: " + type + ", name: " +
                name);
        return super.createTest(type, name);
    }

    @Override
    public ExtentTest createTest(GherkinKeyword gherkinKeyword, String name, String description) {
        log.debug("Creates new Test. GherkinKeyword: " + gherkinKeyword +
                ", name: " + name + ", description: " + description);
        return super.createTest(gherkinKeyword, name, description);
    }

    @Override
    public ExtentTest createTest(GherkinKeyword gherkinKeyword, String testName) {
        log.debug("Creates new Test. GherkinKeyword: " + gherkinKeyword +
                ", test name: " + testName);
        return super.createTest(gherkinKeyword, testName);
    }

    @Override
    public ExtentTest createTest(String name, String description) {
        log.debug("Creates new Test. Name: " + name +
                ", description: " + description);
        return super.createTest(name, description);
    }

    @Override
    public ExtentTest createTest(String name) {
        log.debug("Creates new Test. Name: " + name);
        return super.createTest(name);
    }

    @Override
    public void removeTest(ExtentTest test) {
        log.debug("Remove Test: " + test.toString());
        super.removeTest(test);
    }

    @Override
    public void removeTest(String name) {
        log.debug("Remove Test: " + name);
        super.removeTest(name);
    }

    @Override
    public void flush() {
        log.info("Flush report");
        super.flush();
    }

    @Override
    public void setSystemInfo(String k, String v) {
        log.debug("Setting system info " + k + " with value " + v);
        super.setSystemInfo(k, v);
    }

    @Override
    public void addTestRunnerOutput(List<String> reportLlog) {
        log.debug("Add test runner output " + Arrays.toString(reportLlog.toArray()));
        super.addTestRunnerOutput(reportLlog);
    }

    @Override
    public void addTestRunnerOutput(String reportLlog) {
        log.debug("Add test runner output " + reportLlog);
        super.addTestRunnerOutput(reportLlog);
    }

    @Override
    public ExtentReports tryResolveMediaPath(String[] path) {
        log.debug("Try resolve media path: " + Arrays.toString(path));
        return super.tryResolveMediaPath(path);
    }

    @Override
    public void createDomainFromJsonArchive(File jsonFile) throws IOException {
        log.debug("Create Domain From Json Archive: " + jsonFile.getAbsolutePath() + "/" + jsonFile.getName());
        super.createDomainFromJsonArchive(jsonFile);
    }

    @Override
    public void createDomainFromJsonArchive(String jsonFilePath) throws IOException {
        log.debug("Create Domain From Json Archive: " + jsonFilePath);
        super.createDomainFromJsonArchive(jsonFilePath);
    }

    @Override
    public void setReportUsesManualConfiguration(boolean useManualConfig) {
        log.debug("Set Report Uses Manual Configuration: " + useManualConfig);
        super.setReportUsesManualConfiguration(useManualConfig);
    }

    @Override
    public void setAnalysisStrategy(AnalysisStrategy strategy) {
        log.debug("Set Analysis Strategy: " + strategy.toString());
        super.setAnalysisStrategy(strategy);
    }

    @Override
    public void setGherkinDialect(String language) throws UnsupportedEncodingException {
        log.debug("Set Gherkin Dialect: " + language);
        super.setGherkinDialect(language);
    }

    @Override
    public ReportStats getStats() {
        log.debug("Call Get Stats");
        return super.getStats();
    }
}
