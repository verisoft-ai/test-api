package co.verisoft.fw.extentreport;
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

import co.verisoft.fw.utils.ExtendedLog;
import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.gherkin.model.IGherkinFormatterModel;
import com.aventstack.extentreports.model.ReportStats;
import com.aventstack.extentreports.observer.ExtentObserver;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.slf4j.Logger;

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
public class DelegateExtentReports extends ExtentReports {

    private static final Logger logger = new ExtendedLog(DelegateExtentReports.class);

    @Override
    public void attachReporter(ExtentObserver... observer) {
        logger.debug("Reporter attached");
        super.attachReporter(observer);
    }

    @Override
    public ExtentTest createTest(Class<? extends IGherkinFormatterModel> type, String name, String description) {
        logger.debug(new StringBuilder().append("Creates new Test. type: ").append(type).append(", name: ").append(name)
                .append(", description: ").append(description).toString());
        return super.createTest(type, name, description);
    }

    @Override
    public ExtentTest createTest(Class<? extends IGherkinFormatterModel> type, String name) {
        logger.debug(new StringBuilder().append("Creates new Test. type: ").append(type).append(", name: ")
                .append(name).toString());
        return super.createTest(type, name);
    }

    @Override
    public ExtentTest createTest(GherkinKeyword gherkinKeyword, String name, String description) {
        logger.debug(new StringBuilder().append("Creates new Test. GherkinKeyword: ").append(gherkinKeyword)
                .append(", name: ").append(name).append(", description: ").append(description).toString());
        return super.createTest(gherkinKeyword, name, description);
    }

    @Override
    public ExtentTest createTest(GherkinKeyword gherkinKeyword, String testName) {
        logger.debug(new StringBuilder().append("Creates new Test. GherkinKeyword: ").append(gherkinKeyword)
                .append(", test name: ").append(testName).toString());
        return super.createTest(gherkinKeyword, testName);
    }

    @Override
    public ExtentTest createTest(String name, String description) {
        logger.debug(new StringBuilder().append("Creates new Test. Name: ").append(name)
                .append(", description: ").append(description).toString());
        return super.createTest(name, description);
    }

    @Override
    public ExtentTest createTest(String name) {
        logger.debug(new StringBuilder().append("Creates new Test. Name: ").append(name).toString());
        return super.createTest(name);
    }

    @Override
    public void removeTest(ExtentTest test) {
        logger.debug("Remove Test: " + test.toString());
        super.removeTest(test);
    }

    @Override
    public void removeTest(String name) {
        logger.debug("Remove Test: " + name);
        super.removeTest(name);
    }

    @Override
    public void flush() {
        logger.info("Flush report");
        super.flush();
    }

    @Override
    public void setSystemInfo(String k, String v) {
        logger.debug("Setting system info " + k + " with value " + v);
        super.setSystemInfo(k, v);
    }

    @Override
    public void addTestRunnerOutput(List<String> log) {
        logger.debug("Add test runner output " + Arrays.toString(log.toArray()));
        super.addTestRunnerOutput(log);
    }

    @Override
    public void addTestRunnerOutput(String log) {
        logger.debug("Add test runner output " + log);
        super.addTestRunnerOutput(log);
    }

    @Override
    public ExtentReports tryResolveMediaPath(String[] path) {
        logger.debug("Try resolve media path: " + Arrays.toString(path));
        return super.tryResolveMediaPath(path);
    }

    @Override
    public void createDomainFromJsonArchive(File jsonFile) throws IOException {
        logger.debug("Create Domain From Json Archive: " + jsonFile.getAbsolutePath() + "/" + jsonFile.getName());
        super.createDomainFromJsonArchive(jsonFile);
    }

    @Override
    public void createDomainFromJsonArchive(String jsonFilePath) throws IOException {
        logger.debug("Create Domain From Json Archive: " + jsonFilePath);
        super.createDomainFromJsonArchive(jsonFilePath);
    }

    @Override
    public void setReportUsesManualConfiguration(boolean useManualConfig) {
        logger.debug("Set Report Uses Manual Configuration: " + useManualConfig);
        super.setReportUsesManualConfiguration(useManualConfig);
    }

    @Override
    public void setAnalysisStrategy(AnalysisStrategy strategy) {
        logger.debug("Set Analysis Strategy: " + strategy.toString());
        super.setAnalysisStrategy(strategy);
    }

    @Override
    public void setGherkinDialect(String language) throws UnsupportedEncodingException {
        logger.debug("Set Gherkin Dialect: " + language);
        super.setGherkinDialect(language);
    }

    @Override
    public ReportStats getStats() {
        logger.debug("Call Get Stats");
        return super.getStats();
    }
}