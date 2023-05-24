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

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.IGherkinFormatterModel;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.model.Test;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * Class delegates ExtentTest. This class is mainly for logging purposes. It synchronizes logging mechanism with
 * the report mechanism.
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @since 0.0.3 (Jan 2022)
 * @see DelegateExtentReports
 */
@ToString
@Slf4j
@SuppressWarnings("unused")
public class DelegateExtentTest {

    private final ExtentTest test;

    DelegateExtentTest(ExtentTest test) {
        synchronized (test) {
            this.test = test;
        }
    }


    public synchronized ExtentTest createNode(Class<? extends IGherkinFormatterModel> type, String name, String description) {
        log.debug("Report Log - create node name: " + name + " description: " + description + " type: " + type.toString());
        return test.createNode(type, name, description);
    }

    public synchronized ExtentTest createNode(String name, String description) {
        log.debug("Report Log - create node name: " + name + " description: " + description);
        return test.createNode(name, description);
    }

    public synchronized ExtentTest createNode(Class<? extends IGherkinFormatterModel> type, String name) {
        log.debug("Report Log - create node name: " + name + " type: " + type.toString());
        return test.createNode(type, name);
    }

    public synchronized ExtentTest createNode(GherkinKeyword gherkinKeyword, String name, String description) {
        log.debug("Report Log - create node name: " + name + " GherkinKeyword: " + gherkinKeyword.toString() + " description: " + description);
        return test.createNode(gherkinKeyword, name, description);
    }

    public synchronized ExtentTest createNode(GherkinKeyword gherkinKeyword, String name) {
        log.debug("Report Log - create node name: " + name + " GherkinKeyword: " + gherkinKeyword.toString());
        return test.createNode(gherkinKeyword, name);
    }

    public synchronized ExtentTest createNode(String name) {
        log.debug("Report Log - create node name: " + name);
        return test.createNode(name);
    }

    public ExtentTest generateLog(Status status, String details) {
        log.info("Report Log - Generate log. Status: " + status + ", Details: " + details);
        return test.generateLog(status, details);
    }

    public ExtentTest generateLog(Status status, Markup markup) {
        log.info("Report Log - Generate log. Status: " + status + ", Markup: " + markup.getMarkup());
        return test.generateLog(status, markup);
    }

    public ExtentTest log(Status status, String details, Throwable t, Media media) {
        log.info(String.format("Report Log - Log. Status: %s, detils: %s, Throwable: %s",
                status, details, t.getMessage()));
        return test.log(status, details, t, media);
    }

    public ExtentTest log(Status status, String details, Media media) {
        log.info(String.format("Report Log - Log. Status: %s, detils: %s",
                status, details));
        return test.log(status, details, media);
    }

    public ExtentTest log(Status status, Media media) {
        log.info(String.format("Report Log - Log. Status: %s",
                status));
        return test.log(status, media);
    }

    public ExtentTest log(Status status, String details) {

        log.info(String.format("Report Log - Log. Status: %s, detils: %s",
                status, details));
        return test.log(status, details);
    }

    public ExtentTest log(Status status, Markup markup) {
        log.info(String.format("Report Log - Log. Status: %s, Markup: %s",
                status, markup.getMarkup()));
        return test.log(status, markup);
    }

    public ExtentTest log(Status status, Throwable t, Media media) {

        log.info(String.format("Report Log - Log. Status: %s, Throwable: %s",
                status, t.getMessage()));
        return test.log(status, t, media);
    }

    public ExtentTest log(Status status, Throwable t) {

        log.info(String.format("Report Log - Log. Status: %s, Throwable: %s",
                status, t.getMessage()));
        return test.log(status, t);
    }

    public ExtentTest info(String details, Media media) {
        log.info(String.format("Report Log - Info. Details: %s, Media: %s",
                details));
        return test.info(details, media);
    }

    public ExtentTest info(String details) {

        log.info(String.format("Report Log - Info. Details: %s",
                details));
        return test.info(details);
    }

    public ExtentTest info(Throwable t, Media media) {
        log.info(String.format("Report Log - Info. Throwable: %s",
                t.getMessage()));
        return test.info(t, media);
    }

    public ExtentTest info(Throwable t) {
        log.info(String.format("Report Log - Info. Throwable: %s",
                t.getMessage()));
        return test.info(t);
    }

    public ExtentTest info(Markup m) {
        log.info(String.format("Report Log - Info. Markup: %s",
                m.getMarkup()));
        return test.info(m);
    }

    public ExtentTest info(Media media) {
        log.info(String.format("Report Log - Info. Media attached"));
        return test.info(media);
    }

    public ExtentTest pass(String details, Media media) {
        log.info(String.format("Report Log - Pass. Details: %s",
                details));
        return test.pass(details, media);
    }

    public ExtentTest pass(String details) {

        log.info(String.format("Report Log - Pass. Details: %s",
                details));
        return test.pass(details);
    }

    public ExtentTest pass(Throwable t, Media media) {
        log.info(String.format("Report Log - Pass. Throwable: %s",
                t.getMessage()));
        return test.pass(t, media);
    }

    public ExtentTest pass(Throwable t) {
        log.info(String.format("Report Log - Pass. Throwable: %s",
                t.getMessage()));
        return test.pass(t);
    }

    public ExtentTest pass(Markup m) {
        log.info(String.format("Report Log - Pass. Markup: %s",
                m.getMarkup()));
        return test.pass(m);
    }

    public ExtentTest pass(Media media) {
        log.info(String.format("Report Log - Pass. Media attached"));
        return test.pass(media);
    }

    public ExtentTest fail(String details, Media media) {
        log.info(String.format("Report Log - FAIL. Details: %s",
                details));
        return test.fail(details, media);
    }

    public ExtentTest fail(String details) {

        log.info(String.format("Report Log - FAIL. Details: %s",
                details));
        return test.fail(details);
    }

    public ExtentTest fail(Throwable t, Media media) {
        log.info(String.format("Report Log - FAIL. Throwable: %s",
                t.getMessage()));
        return test.fail(t, media);
    }

    public ExtentTest fail(Throwable t) {

        log.info(String.format("Report Log - FAIL. Throwable: %s",
                t.getMessage()));
        return test.fail(t);
    }

    public ExtentTest fail(Markup m) {
        log.info(String.format("Report Log - FAIL. Markup: %s",
                m.getMarkup()));
        return test.fail(m);
    }

    public ExtentTest fail(Media media) {
        log.info(String.format("Report Log - FAIL. Media attached"));
        return test.fail(media);
    }

    public ExtentTest warning(String details, Media media) {
        log.info(String.format("Report Log - WARNING. Details: %s",
                details));
        return test.warning(details, media);
    }

    public ExtentTest warning(String details) {

        log.info(String.format("Report Log - WARNING. Details: %s",
                details));
        return test.warning(details);
    }

    public ExtentTest warning(Throwable t, Media media) {
        log.info(String.format("Report Log - WARNING. Throwable: %s",
                t.getMessage()));
        return test.warning(t, media);
    }

    public ExtentTest warning(Throwable t) {

        log.info(String.format("Report Log - WARNING. Throwable: %s",
                t.getMessage()));
        return test.warning(t);
    }

    public ExtentTest warning(Markup m) {
        log.info(String.format("Report Log - WARNING. Markup: %s",
                m.getMarkup()));
        return test.warning(m);
    }

    public ExtentTest warning(Media media) {
        log.info(String.format("Report Log - WARNING. Media attached"));
        return test.warning(media);
    }

    public ExtentTest skip(String details, Media media) {
        log.info(String.format("Report Log - SKIP. Details: %s",
                details));
        return test.skip(details, media);
    }

    public ExtentTest skip(String details) {
        log.info(String.format("Report Log - SKIP. Details: %s",
                details));
        return test.skip(details);
    }

    public ExtentTest skip(Throwable t, Media media) {
        log.info(String.format("Report Log - SKIP. Throwable: %s",
                t.getMessage()));
        return test.skip(t, media);
    }

    public ExtentTest skip(Throwable t) {
        log.info(String.format("Report Log - SKIP. Throwable: %s",
                t.getMessage()));
        return test.skip(t);
    }

    public ExtentTest skip(Markup m) {
        log.info(String.format("Report Log - SKIP. Markup: %s",
                m.getMarkup()));
        return test.skip(m);
    }

    public ExtentTest skip(Media media) {
        log.info(String.format("Report Log - SKIP. Media attached"));
        return test.skip(media);
    }

    public ExtentTest assignCategory(String... category) {
        log.info(String.format("Report Log - Assign Category. category: %s",
                Arrays.toString(category)));
        return test.assignCategory(category);
    }

    public ExtentTest assignAuthor(String... author) {
        log.info(String.format("Report Log - Assign Author. Author: %s",
                Arrays.toString(author)));
        return test.assignAuthor(author);
    }

    public ExtentTest assignDevice(String... device) {
        log.info(String.format("Report Log - Assign Device. Device: %s",
                Arrays.toString(device)));
        return test.assignDevice(device);
    }

    public Status getStatus() {
        log.info(String.format("Report Log - Get Status. Status: %s",
                test.getStatus().toString()));
        return test.getStatus();
    }

    public ExtentTest addScreenCaptureFromPath(String path, String title) {
        log.info(String.format("Report Log - Add Screen Capture From Path. path: %s, title: %s",
                path, title));
        return test.addScreenCaptureFromPath(path, title);
    }

    public ExtentTest addScreenCaptureFromPath(String path) {
        log.info(String.format("Report Log - Add Screen Capture From Path. path: %s",
                path));
        return test.addScreenCaptureFromPath(path);
    }

    public ExtentTest addScreenCaptureFromBase64String(String base64, String title) {
        log.info(String.format("Report Log - Add Screen Capture From Base64 String. String: %s, title: %s",
                base64, title));
        return test.addScreenCaptureFromBase64String(base64, title);
    }


    public ExtentTest addScreenCaptureFromBase64String(String base64) {
        log.info(String.format("Report Log - Add Screen Capture From Base64 String. String: %s",
                base64));
        return test.addScreenCaptureFromBase64String(base64);
    }

    public ExtentReports getExtent() {
        ExtentReports report = test.getExtent();
        log.info(String.format("Report Log - Get Extent Report. Report: %s",
                report.toString()));
        return test.getExtent();
    }

    public Test getModel() {
        Test model = test.getModel();
        log.info("Got model from test report. Model is: " + model.toString());
        return model;
    }
}
