package co.verisoft.fw.xray;

import co.verisoft.fw.utils.Builder;
import co.verisoft.fw.utils.JsonObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Representation of Xray Json format. This class follows the builder design pattern<br>
 *
 * <br><b>From Xray documentation:</b><br>
 * Xray provides a proprietary JSON format for importing execution results into Jira/Xray. <br>
 * Although Xray supports multiple report formats used by different testing frameworks/runners (e.g. JUnit, NUnit,
 * xUnit, TestNG, Cucumber, Robot Framework), there are scenarios where using these formats is not an option like:<br>
 * Using a testing framework report that is not supported by Xray <br>
 * Having your own testing framework<br>
 * Limited support of existing formats to import detailed execution results back into Jira<br>
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @since 0.0.2 (Jan 2022)
 *
 * @see <a href="https://docs.getxray.app/display/XRAYCLOUD/Using+Xray+JSON+format+to+import+execution+results#UsingXrayJSONformattoimportexecutionresults-Overview">
 *     Using Xray JSON format to import execution results - info</a>
 */
public class XrayJsonFormatObject implements JsonObject {

    private final String testExecutionKey;          // The test execution key where to import the execution results
    private final XrayJsonInfoObject info;          // The info object for creating new Test Execution issues
    private final List<XrayJsonTestObject> tests;   // The Test Run result details

    public String getTestExecutionKey() {
        return testExecutionKey;
    }

    public XrayJsonInfoObject getInfo() {
        return info;
    }

    public List<XrayJsonTestObject> getTests() {
        return tests;
    }

    private XrayJsonFormatObject(XrayJsonFormatObjectBuilder builder) {
        this.testExecutionKey = builder.testExecutionKey;
        this.info = builder.info;
        this.tests = builder.tests;
    }


    @SuppressWarnings("unchecked")
    @Override
    public JSONObject asJsonObject() {
        JSONObject obj = new JSONObject();
        obj.put("testExecutionKey", this.testExecutionKey);
        obj.put("info", this.info.asJsonObject());

        // Tests
        JSONArray arr = new JSONArray();
        for (XrayJsonTestObject test: tests) {
            arr.add(test.asJsonObject());
        }
        if (!arr.isEmpty())
            obj.put("tests", arr);

        return obj;
    }

    @Override
    public String asString() {
        return this.asJsonObject().toJSONString();
    }

    /**
     * Builder class for XrayInfoObject
     *
     * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
     * @since 0.0.2 (Jan 2022)
     */
    public static class XrayJsonFormatObjectBuilder implements Builder {

        private String testExecutionKey;        // The test execution key where to import the execution results
        private XrayJsonInfoObject info;        // The info object for creating new Test Execution issues
        private List<XrayJsonTestObject> tests; // The Test Run result details

        public XrayJsonFormatObjectBuilder(XrayJsonFormatObject obj) {
            this.testExecutionKey = obj.testExecutionKey;
            this.info = obj.info;
            this.tests = obj.tests;
        }

        public XrayJsonFormatObjectBuilder() {
            tests = new ArrayList<>();
        }

        public XrayJsonFormatObjectBuilder testExecutionKey(String testExecutionKey) {
            this.testExecutionKey = testExecutionKey;
            return this;
        }

        public XrayJsonFormatObjectBuilder info(XrayJsonInfoObject info) {
            this.info = info;
            return this;
        }

        public XrayJsonFormatObjectBuilder tests(List<XrayJsonTestObject> tests) {
            this.tests = tests;
            return this;
        }

        public XrayJsonFormatObjectBuilder test(XrayJsonTestObject test) {
            this.tests.add(test);
            return this;
        }


        public XrayJsonFormatObject build() {
            XrayJsonFormatObject info = new XrayJsonFormatObject(this);
            validateXrayFormatObject(info);
            return info;
        }

        private void validateXrayFormatObject(XrayJsonFormatObject obj) {
            // TOOD: some validation code here
        }
    }
}
