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
public class XrayJsonStepResultObject implements JsonObject {

    private final String status;        // The status for the test step (PASSED, FAILED, EXECUTING, TODO, custom statuses ...)
    private final String comment;       // The comment for the step result
    private final String actualResult;  // The actual result field for the step result
    private final String evidence;      // An array of evidence items of the test run
    private final String defects;       // An array of defect issue keys to associate with the test run

    public String getStatus() {
        return status;
    }

    public String getComment() {
        return comment;
    }

    public String getActualResult() {
        return actualResult;
    }

    public String getEvidence() {
        return evidence;
    }

    public String getDefects() {
        return defects;
    }


    private XrayJsonStepResultObject(XrayJsonStepResultObjectBuilder builder) {
        this.status = builder.status;
        this.comment = builder.comment;
        this.actualResult = builder.actualResult;
        this.evidence = builder.evidence;
        this.defects = builder.defects;
    }


    @SuppressWarnings("unchecked")
    @Override
    public JSONObject asJsonObject() {
        JSONObject obj = new JSONObject();
        obj.put("status", this.status);
        obj.put("comment", this.comment);
        obj.put("actualResult", this.actualResult);
        obj.put("evidence", this.evidence);
        obj.put("defects", this.defects);

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
    public static class XrayJsonStepResultObjectBuilder implements Builder {

        private String status;        // The status for the test step (PASSED, FAILED, EXECUTING, TODO, custom statuses ...)
        private String comment;       // The comment for the step result
        private String actualResult;  // The actual result field for the step result
        private String evidence;      // An array of evidence items of the test run
        private String defects;       // An array of defect issue keys to associate with the test run

        public XrayJsonStepResultObjectBuilder(XrayJsonStepResultObject obj) {
            this.status = obj.status;
            this.comment = obj.comment;
            this.actualResult = obj.actualResult;
            this.evidence = obj.evidence;
            this.defects = obj.defects;
        }

        public XrayJsonStepResultObjectBuilder() {
            // No op
        }

        public XrayJsonStepResultObjectBuilder status(String status) {
            this.status = status;
            return this;
        }

        public XrayJsonStepResultObjectBuilder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public XrayJsonStepResultObjectBuilder actualResult(String actualResult) {
            this.actualResult = actualResult;
            return this;
        }

        public XrayJsonStepResultObjectBuilder evidence(String evidence) {
            this.evidence = evidence;
            return this;
        }

        public XrayJsonStepResultObjectBuilder defects(String defects) {
            this.defects = defects;
            return this;
        }


        public XrayJsonStepResultObject build() {
            XrayJsonStepResultObject info = new XrayJsonStepResultObject(this);
            validateXrayStepResultObject(info);
            return info;
        }

        private void validateXrayStepResultObject(XrayJsonStepResultObject obj) {
            // TOOD: some validation code here
        }
    }
}
