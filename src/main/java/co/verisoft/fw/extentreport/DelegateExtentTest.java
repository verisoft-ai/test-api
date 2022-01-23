package co.verisoft.fw.extentreport;

import co.verisoft.fw.utils.ExtendedLog;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.IGherkinFormatterModel;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.model.Test;
import lombok.ToString;
import org.slf4j.Logger;

import java.util.Arrays;

@ToString
public class DelegateExtentTest {

    private ExtentTest test;
    private static final Logger logger = new ExtendedLog(DelegateExtentTest.class);

    DelegateExtentTest(ExtentTest test) {
        synchronized (test) {
            this.test = test;
        }
    }


    public synchronized ExtentTest createNode(Class<? extends IGherkinFormatterModel> type, String name, String description) {
        logger.info("Report Log - create node name: " + name + " description: " + description + " type: " + type.toString());
        return test.createNode(type, name, description);
    }

    public synchronized ExtentTest createNode(String name, String description) {
        logger.info("Report Log - create node name: " + name + " description: " + description);
        return test.createNode(name, description);
    }

    public synchronized ExtentTest createNode(Class<? extends IGherkinFormatterModel> type, String name) {
        logger.info("Report Log - create node name: " + name + " type: " + type.toString());
        return test.createNode(type, name);
    }

    public synchronized ExtentTest createNode(GherkinKeyword gherkinKeyword, String name, String description) {
        logger.info("Report Log - create node name: " + name + " GherkinKeyword: " + gherkinKeyword.toString() + " description: " + description);
        return test.createNode(gherkinKeyword, name, description);
    }

    public synchronized ExtentTest createNode(GherkinKeyword gherkinKeyword, String name) {
        logger.info("Report Log - create node name: " + name + " GherkinKeyword: " + gherkinKeyword.toString());
        return test.createNode(gherkinKeyword, name);
    }

    public synchronized ExtentTest createNode(String name) {
        logger.info("Report Log - create node name: " + name);
        return test.createNode(name);
    }

    public ExtentTest generateLog(Status status, String details) {
        logger.info("Report Log - Generate log. Status: " + status + ", Details: " + details);
        return test.generateLog(status, details);
    }

    public ExtentTest generateLog(Status status, Markup markup) {
        logger.info("Report Log - Generate log. Status: " + status + ", Markup: " + markup.getMarkup());
        return test.generateLog(status, markup);
    }

    public ExtentTest log(Status status, String details, Throwable t, Media media) {
        logger.info(String.format("Report Log - Log. Status: %s, detils: %s, Throwable: %s, Media %s",
                status, details, t.getMessage(), media.getPath()));
        return test.log(status, details, t, media);
    }

    public ExtentTest log(Status status, String details, Media media) {
        logger.info(String.format("Report Log - Log. Status: %s, detils: %s, Media %s",
                status, details, media.getPath()));
        return test.log(status, details, media);
    }

    public ExtentTest log(Status status, Media media) {

        logger.info(String.format("Report Log - Log. Status: %s,  Media %s",
                status, media.getPath()));
        return test.log(status, media);
    }

    public ExtentTest log(Status status, String details) {

        logger.info(String.format("Report Log - Log. Status: %s, detils: %s",
                status, details));
        return test.log(status, details);
    }

    public ExtentTest log(Status status, Markup markup) {
        logger.info(String.format("Report Log - Log. Status: %s, Markup: %s",
                status, markup.getMarkup()));
        return test.log(status, markup);
    }

    public ExtentTest log(Status status, Throwable t, Media media) {

        logger.info(String.format("Report Log - Log. Status: %s, Throwable: %s, Media %s",
                status, t.getMessage(), media.getPath()));
        return test.log(status, t, media);
    }

    public ExtentTest log(Status status, Throwable t) {

        logger.info(String.format("Report Log - Log. Status: %s, Throwable: %s",
                status, t.getMessage()));
        return test.log(status, t);
    }

    public ExtentTest info(String details, Media media) {
        logger.info(String.format("Report Log - Info. Details: %s, Media: %s",
                details, media.getTitle()));
        return test.info(details, media);
    }

    public ExtentTest info(String details) {

        logger.info(String.format("Report Log - Info. Details: %s",
                details));
        return test.info(details);
    }

    public ExtentTest info(Throwable t, Media media) {
        logger.info(String.format("Report Log - Info. Throwable: %s, Meida: %s",
                t.getMessage(), media.getResolvedPath() + " " + media.getTitle()));
        return test.info(t, media);
    }

    public ExtentTest info(Throwable t) {
        logger.info(String.format("Report Log - Info. Throwable: %s",
                t.getMessage()));
        return test.info(t);
    }

    public ExtentTest info(Markup m) {
        logger.info(String.format("Report Log - Info. Markup: %s",
                m.getMarkup()));
        return test.info(m);
    }

    public ExtentTest info(Media media) {
        logger.info(String.format("Report Log - Info. Media: %s",
                media.getResolvedPath() + " " + media.getTitle()));
        return test.info(media);
    }

    public ExtentTest pass(String details, Media media) {
        logger.info(String.format("Report Log - Pass. Details: %s, Media: %s",
                details, media.getResolvedPath() + " " + media.getTitle()));
        return test.pass(details, media);
    }

    public ExtentTest pass(String details) {

        logger.info(String.format("Report Log - Pass. Details: %s",
                details));
        return test.pass(details);
    }

    public ExtentTest pass(Throwable t, Media media) {
        logger.info(String.format("Report Log - Pass. Throwable: %s, Media: %s",
                t.getMessage(), media.getResolvedPath() + " " + media.getTitle()));
        return test.pass(t, media);
    }

    public ExtentTest pass(Throwable t) {
        logger.info(String.format("Report Log - Pass. Throwable: %s",
                t.getMessage()));
        return test.pass(t);
    }

    public ExtentTest pass(Markup m) {
        logger.info(String.format("Report Log - Pass. Markup: %s",
                m.getMarkup()));
        return test.pass(m);
    }

    public ExtentTest pass(Media media) {
        logger.info(String.format("Report Log - Pass. Media: %s",
                media.getResolvedPath() + " " + media.getTitle()));
        return test.pass(media);
    }

    public ExtentTest fail(String details, Media media) {
        logger.info(String.format("Report Log - FAIL. Details: %s, Media: %s",
                details, media.getResolvedPath() + " " + media.getTitle()));
        return test.fail(details, media);
    }

    public ExtentTest fail(String details) {

        logger.info(String.format("Report Log - FAIL. Details: %s",
                details));
        return test.fail(details);
    }

    public ExtentTest fail(Throwable t, Media media) {
        logger.info(String.format("Report Log - FAIL. Throwable: %s, Media: %s",
                t.getMessage(), media.getResolvedPath() + " " + media.getTitle()));
        return test.fail(t, media);
    }

    public ExtentTest fail(Throwable t) {

        logger.info(String.format("Report Log - FAIL. Throwable: %s",
                t.getMessage()));
        return test.fail(t);
    }

    public ExtentTest fail(Markup m) {
        logger.info(String.format("Report Log - FAIL. Markup: %s",
                m.getMarkup()));
        return test.fail(m);
    }

    public ExtentTest fail(Media media) {
        logger.info(String.format("Report Log - FAIL. Media: %s",
                media.getResolvedPath() + " " + media.getTitle()));
        return test.fail(media);
    }

    public ExtentTest warning(String details, Media media) {
        logger.info(String.format("Report Log - WARNING. Details: %s, Media: %s",
                details, media.getResolvedPath() + " " + media.getTitle()));
        return test.warning(details, media);
    }

    public ExtentTest warning(String details) {

        logger.info(String.format("Report Log - WARNING. Details: %s",
                details));
        return test.warning(details);
    }

    public ExtentTest warning(Throwable t, Media media) {
        logger.info(String.format("Report Log - WARNING. Throwable: %s, Media: %s",
                t.getMessage(), media.getResolvedPath() + " " + media.getTitle()));
        return test.warning(t, media);
    }

    public ExtentTest warning(Throwable t) {

        logger.info(String.format("Report Log - WARNING. Throwable: %s",
                t.getMessage()));
        return test.warning(t);
    }

    public ExtentTest warning(Markup m) {
        logger.info(String.format("Report Log - WARNING. Markup: %s",
                m.getMarkup()));
        return test.warning(m);
    }

    public ExtentTest warning(Media media) {
        logger.info(String.format("Report Log - WARNING. Media: %s",
                media.getResolvedPath() + " " + media.getTitle()));
        return test.warning(media);
    }

    public ExtentTest skip(String details, Media media) {
        logger.info(String.format("Report Log - SKIP. Details: %s, Media: %s",
                details, media.getResolvedPath() + " " + media.getTitle()));
        return test.skip(details, media);
    }

    public ExtentTest skip(String details) {
        logger.info(String.format("Report Log - SKIP. Details: %s",
                details));
        return test.skip(details);
    }

    public ExtentTest skip(Throwable t, Media media) {
        logger.info(String.format("Report Log - SKIP. Throwable: %s, Media: %s",
                t.getMessage(), media.getResolvedPath() + " " + media.getTitle()));
        return test.skip(t, media);
    }

    public ExtentTest skip(Throwable t) {
        logger.info(String.format("Report Log - SKIP. Throwable: %s",
                t.getMessage()));
        return test.skip(t);
    }

    public ExtentTest skip(Markup m) {
        logger.info(String.format("Report Log - SKIP. Markup: %s",
                m.getMarkup()));
        return test.skip(m);
    }

    public ExtentTest skip(Media media) {
        logger.info(String.format("Report Log - SKIP. Media: %s",
                media.getResolvedPath() + " " + media.getTitle()));
        return test.skip(media);
    }

    public ExtentTest assignCategory(String... category) {
        logger.info(String.format("Report Log - Assign Category. category: %s",
                Arrays.toString(category)));
        return test.assignCategory(category);
    }

    public ExtentTest assignAuthor(String... author) {
        logger.info(String.format("Report Log - Assign Author. Author: %s",
                Arrays.toString(author)));
        return test.assignAuthor(author);
    }

    public ExtentTest assignDevice(String... device) {
        logger.info(String.format("Report Log - Assign Device. Device: %s",
                Arrays.toString(device)));
        return test.assignDevice(device);
    }

    public Status getStatus() {
        logger.info(String.format("Report Log - Get Status. Status: %s",
                test.getStatus().toString()));
        return test.getStatus();
    }

    public ExtentTest addScreenCaptureFromPath(String path, String title) {
        logger.info(String.format("Report Log - Add Screen Capture From Path. path: %s, title: %s",
                path, title));
        return test.addScreenCaptureFromPath(path, title);
    }

    public ExtentTest addScreenCaptureFromPath(String path) {
        logger.info(String.format("Report Log - Add Screen Capture From Path. path: %s",
                path));
        return test.addScreenCaptureFromPath(path);
    }

    public ExtentTest addScreenCaptureFromBase64String(String base64, String title) {
        logger.info(String.format("Report Log - Add Screen Capture From Base64 String. String: %s, title: %s",
                base64, title));
        return test.addScreenCaptureFromBase64String(base64, title);
    }


    public ExtentTest addScreenCaptureFromBase64String(String base64) {
        logger.info(String.format("Report Log - Add Screen Capture From Base64 String. String: %s",
                base64));
        return test.addScreenCaptureFromBase64String(base64);
    }

    public ExtentReports getExtent() {
        ExtentReports report = test.getExtent();
        logger.info(String.format("Report Log - Get Extent Report. Report: %s",
                report.toString()));
        return test.getExtent();
    }

    public Test getModel() {
        Test model = test.getModel();
        logger.info("Got model from test report. Model is: " + model.toString());
        return model;
    }
}
