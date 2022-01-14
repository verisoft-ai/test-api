package co.verisoft.fw.xray;

import co.verisoft.selenium.framework.inf.ExtendedLog;
import org.apache.xmlbeans.SystemProperties;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;

/**
 * Representation of a report for x-ray class
 * This class is a singletone, and holds the results of all test instances, in a JSONObject format.
 * During the test executions, test objects are added to the reporter, and at the end of the test execution (of all
 * test classes, in all threads) it generates a json object, compatible with x-ray report.
 * https://docs.getxray.app/display/XRAY/Import+Execution+Results+-+REST
 * @author Nir Gallner
 * @since Jan 2022
 */
public class XrayReporter {
    private static final Logger logger = new ExtendedLog(XrayReporter.class);

    private static XrayReporter reporter;
    private JSONArray tests;


    private XrayReporter() {
        tests = new JSONArray();
    }


    public static XrayReporter getInstance() {
        if (reporter == null)
            reporter = new XrayReporter();

        return reporter;
    }


    /**
     * Generates a single JSONObject with all the results, which were collected this far
     *
     * @return The report object
     */
    public JSONObject generateReport() {
        JSONObject finalReport = new JSONObject();
        finalReport.put("testExecutionKey", SystemProperties.getProperty("xray.execution.key"));
        finalReport.put("tests", this.tests);
        return finalReport;
    }


    /**
     * Generates a single String representaion of a json object with all the results, which were collected this far
     *
     * @return The report object, string format
     */
    public String generateReportAsString() {
        return generateReport().toJSONString();
    }


    /**
     * Adds a test to the test list
     *
     * @param test a test object, to extract the data as json object and add to the list
     */
    public void addTest(XrayTestObject test) {
        this.tests.add(test.asJsonObject());
    }
}
