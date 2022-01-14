package co.verisoft.fw.xray;

import co.verisoft.fw.utils.Builder;
import co.verisoft.fw.utils.JsonObject;
import org.json.simple.JSONObject;

/**
 *
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @since 0.0.2 (Jan 2022)
 *
 * @see <a href="https://docs.getxray.app/display/XRAYCLOUD/Using+Xray+JSON+format+to+import+execution+results#UsingXrayJSONformattoimportexecutionresults-stepdef">
 *     "step def" object - step definition</a>
 */
public class XrayJsonFormatObject implements JsonObject {

    private final String testExecutionKey;      // The test execution key where to import the execution results
    private final String info;                  // The info object for creating new Test Execution issues
    private final String tests;                 // The Test Run result details

    public String getTestExecutionKey() {
        return testExecutionKey;
    }

    public String getInfo() {
        return info;
    }

    public String getTests() {
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
        obj.put("info", this.info);
        obj.put("tests", this.tests);

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

        private String testExecutionKey;      // The test execution key where to import the execution results
        private String info;                  // The info object for creating new Test Execution issues
        private String tests;                 // The Test Run result details

        public XrayJsonFormatObjectBuilder(XrayJsonFormatObject obj) {
            this.testExecutionKey = obj.testExecutionKey;
            this.info = obj.info;
            this.tests = obj.tests;
        }

        public XrayJsonFormatObjectBuilder() {
            // No op
        }

        public XrayJsonFormatObjectBuilder testExecutionKey(String testExecutionKey) {
            this.testExecutionKey = testExecutionKey;
            return this;
        }

        public XrayJsonFormatObjectBuilder info(String info) {
            this.info = info;
            return this;
        }

        public XrayJsonFormatObjectBuilder tests(String tests) {
            this.tests = tests;
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
