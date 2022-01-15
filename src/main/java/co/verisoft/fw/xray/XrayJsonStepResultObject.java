package co.verisoft.fw.xray;

import co.verisoft.fw.utils.Builder;
import co.verisoft.fw.utils.JsonObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Locale;

/**
 * Representation of Xray object - <b>Step Result</b>. This class follows the builder design pattern<br>
 *
 * <br><b>From Xray documentation:</b><br>
 * "step result" object - step results <br>
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @since 0.0.2 (Jan 2022)
 *
 * @see <a href="https://docs.getxray.app/display/XRAYCLOUD/Using+Xray+JSON+format+to+import+execution+results#UsingXrayJSONformattoimportexecutionresults-%22stepresult%22object-stepresults">
 *     Using Xray JSON format to import execution results - Step Result</a>
 */
public class XrayJsonStepResultObject implements JsonObject {

    private final Status status;                                // The status for the test step (PASSED, FAILED, EXECUTING, custom statuses ...)
    private final String comment;                               // The comment for the step result
    private final String actualResult;                          // The actual result field for the step result
    private final List<XrayJsonEvidenceObject> evidences;      // An array of evidence items of the test run
    private final String defects;                              // An array of defect issue keys to associate with the test run

    public Status getStatus() {
        return status;
    }

    public String getStatusAsString() { return status.toString();}

    public String getComment() {
        return comment;
    }

    public String getActualResult() {
        return actualResult;
    }

    public List getEvidences() {
        return evidences;
    }

    public String getDefects() {
        return defects;
    }


    private XrayJsonStepResultObject(XrayJsonStepResultObjectBuilder builder) {
        this.status = builder.status;
        this.comment = builder.comment;
        this.actualResult = builder.actualResult;
        this.evidences = builder.evidences;
        this.defects = builder.defects;
    }


    @SuppressWarnings("unchecked")
    @Override
    public JSONObject asJsonObject() {
        JSONObject obj = new JSONObject();
        obj.put("status", this.status.toString());
        obj.put("comment", this.comment);
        obj.put("actualResult", this.actualResult);
        obj.put("defects", this.defects);

        // Write down the evidences
        JSONArray arr = new JSONArray();
        for (XrayJsonEvidenceObject evidence:evidences)
            arr.add(evidence.asJsonObject());

        if (!arr.isEmpty()){
            obj.put("evidences", arr);
        }

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

        private Status status;
        private String comment;
        private String actualResult;
        private List<XrayJsonEvidenceObject> evidences;
        private String defects;

        public XrayJsonStepResultObjectBuilder(XrayJsonStepResultObject obj) {
            this.status = obj.status;
            this.comment = obj.comment;
            this.actualResult = obj.actualResult;
            this.evidences = obj.evidences;
            this.defects = obj.defects;
        }

        public XrayJsonStepResultObjectBuilder() {
            this.evidences = new ArrayList<>();
        }

        public XrayJsonStepResultObjectBuilder status(String status) {
            this.status = Status.toStatus(status);
            return this;
        }

        public XrayJsonStepResultObjectBuilder status(Status status) {
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

        public XrayJsonStepResultObjectBuilder evidence(List<XrayJsonEvidenceObject> evidences) {
            this.evidences = evidences;
            return this;
        }

        public XrayJsonStepResultObjectBuilder evidence(XrayJsonEvidenceObject evidence) {
            this.evidences.add(evidence);
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
