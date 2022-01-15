package co.verisoft.fw.xray;

import co.verisoft.fw.utils.JsonObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Representation of Xray info object - Test object. This class follows the builder design pattern<br>
 *
 * <br><b>From Xray documentation:</b><br>
 * "test" object - Test Run details <br>
 * The test run details object allows you to import any detail about the execution itself.
 * All Xray test types are supported. It is possible to import a <b>single</b> result (the test object itself with the
 * <b>"steps"</b> (Manual tests) or <b>"examples"</b> (BDD tests)) or <b>multiple</b> execution results into the same
 * Test Run (data-driven testing) using the <b>"iterations"</b> array.
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @since 0.0.2 (Jan 2022)
 *
 * @see <a href="https://docs.getxray.app/display/XRAYCLOUD/Using+Xray+JSON+format+to+import+execution+results#UsingXrayJSONformattoimportexecutionresults-%22test%22object-TestRundetails">
 *     Using Xray JSON format to import execution results - Test</a>
 */
public class XrayJsonTestObject implements JsonObject {

    private final String testKey;                               // The test issue key
    private final XrayJsonTestInfoObject testInfo;              // The testInfo element
    private final String start;                                 // The start date for the test run
    private final String finish;                                // The finish date for the test run
    private final String comment;                               // The comment for the test run
    private final String executedBy;                            // The user id who executed the test run
    private final String assignee;                              // The user id for the assignee of the test run
    private final Status status;            // The test run status (PASSED, FAILED, EXECUTING, custom statuses ...)
    private final List<XrayJsonStepResultObject> steps;         // The step results
    private final String examples;                              // The example results for BDD tests (link)
    private final List<XrayJsonIterationObject> iterations;     // The iteration containing data-driven test results
    private final List<String> defects;                         // An array of defect issue keys to associate with the test run
    private final List<XrayJsonEvidenceObject> evidence;        // An array of evidence items of the test run
    private final List<XrayJsonCustomFieldObject> customFields; // An array of custom fields for the test run


    private XrayJsonTestObject(XrayJsonTestObjectBuilder builder){
        this.testKey = builder.testKey;
        this.testInfo = builder.testInfo;
        this.start = builder.start;
        this.finish = builder.finish;
        this.comment = builder.comment;
        this.executedBy = builder.executedBy;
        this.assignee = builder.assignee;
        this.status = builder.status;
        this.steps = builder.steps;
        this.examples = builder.examples;
        this.iterations = builder.iterations;
        this.defects = builder.defects;
        this.evidence = builder.evidence;
        this.customFields = builder.customFields;
    }

    public String getTestKey() {
        return testKey;
    }

    public XrayJsonTestInfoObject getTestInfo() {
        return testInfo;
    }

    public String getStart() {
        return start;
    }

    public String getFinish() {
        return finish;
    }

    public String getComment() {
        return comment;
    }

    public String getExecutedBy() {
        return executedBy;
    }

    public String getAssignee() {
        return assignee;
    }

    public Status getStatus() {
        return status;
    }

    public String getStatusAsString() {
        return status.toString();
    }

    public List<XrayJsonStepResultObject> getSteps() {
        return steps;
    }

    public String getExamples() {
        return examples;
    }

    public List<XrayJsonIterationObject> getIterations() {
        return iterations;
    }

    public List<String> getDefects() {
        return defects;
    }

    public List<XrayJsonEvidenceObject> getEvidence() {
        return evidence;
    }

    public List<XrayJsonCustomFieldObject> getCustomFields() {
        return customFields;
    }

    @SuppressWarnings("unchecked")
    @Override
    public JSONObject asJsonObject() {
        JSONObject obj = new JSONObject();
        obj.put("testKey", this.testKey);
        obj.put("testInfo", this.testInfo.asJsonObject());
        obj.put("start", this.start);
        obj.put("finish", this.finish);
        obj.put("comment", this.comment);
        obj.put("executedBy", this.executedBy);
        obj.put("assignee", this.assignee);
        obj.put("status", this.status.toString());

        // Steps
        JSONArray arr = new JSONArray();
        for (XrayJsonStepResultObject step: steps) {
            arr.add(step.asJsonObject());
        }
        if (!arr.isEmpty())
            obj.put("steps", arr);

        obj.put("examples", this.examples);

        // Iterations
        arr = new JSONArray();
        for (XrayJsonIterationObject iteration: iterations) {
            arr.add(iteration.asJsonObject());
        }
        if (!arr.isEmpty())
            obj.put("iterations", arr);

        // Defects
        arr = new JSONArray();
        for (String defect: defects) {
            arr.add(defect);
        }
        if (!arr.isEmpty())
            obj.put("defects", arr);

        // Evidences
        arr = new JSONArray();
        for (XrayJsonEvidenceObject evidence: evidence) {
            arr.add(evidence.asJsonObject());
        }
        if (!arr.isEmpty())
            obj.put("evidences", arr);

        // Custom Fields
        arr = new JSONArray();
        for (XrayJsonCustomFieldObject cusField: customFields) {
            arr.add(cusField.asJsonObject());
        }
        if (!arr.isEmpty())
            obj.put("customFields", arr);

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
    public static class XrayJsonTestObjectBuilder{
        private String testKey;
        private XrayJsonTestInfoObject testInfo;
        private String start;
        private String finish;
        private String comment;
        private String executedBy;
        private String assignee;
        private Status status;
        private List<XrayJsonStepResultObject> steps;
        private String examples;
        private List<XrayJsonIterationObject> iterations;
        private List<String> defects;
        private List<XrayJsonEvidenceObject> evidence;
        private List<XrayJsonCustomFieldObject> customFields;



        public XrayJsonTestObjectBuilder(XrayJsonTestObject obj){
            this.testKey = obj.testKey;
            this.testInfo = obj.testInfo;
            this.start = obj.start;
            this.finish = obj.finish;
            this.comment = obj.comment;
            this.executedBy = obj.executedBy;
            this.assignee = obj.assignee;
            this.status = obj.status;
            this.steps = obj.steps;
            this.examples = obj.examples;
            this.iterations = obj.iterations;
            this.defects = obj.defects;
            this.evidence = obj.evidence;
            this.customFields = obj.customFields;
        }

        public XrayJsonTestObjectBuilder(){
            steps = new ArrayList<>();
            iterations = new ArrayList<>();
            defects = new ArrayList<>();
            evidence = new ArrayList<>();
            customFields = new ArrayList<>();
        }

        public XrayJsonTestObjectBuilder testKey(String testKey) {
            this.testKey = testKey;
            return this;
        }

        public XrayJsonTestObjectBuilder testInfo(XrayJsonTestInfoObject testInfo) {
            this.testInfo = testInfo;
            return this;
        }

        public XrayJsonTestObjectBuilder start(String start) {
            this.start = start;
            return this;
        }

        public XrayJsonTestObjectBuilder finish(String finish) {
            this.finish = finish;
            return this;
        }

        public XrayJsonTestObjectBuilder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public XrayJsonTestObjectBuilder executedBy(String executedBy) {
            this.executedBy = executedBy;
            return this;
        }

        public XrayJsonTestObjectBuilder assignee(String assignee) {
            this.assignee = assignee;
            return this;
        }

        public XrayJsonTestObjectBuilder status(Status status) {
            this.status = status;
            return this;
        }

        public XrayJsonTestObjectBuilder status(String status) {
            this.status = Status.toStatus(status);
            return this;
        }

        public XrayJsonTestObjectBuilder steps(List<XrayJsonStepResultObject> steps) {
            this.steps = steps;
            return this;
        }

        public XrayJsonTestObjectBuilder step(XrayJsonStepResultObject step) {
            this.steps.add(step);
            return this;
        }

        public XrayJsonTestObjectBuilder examples(String examples) {
            this.examples = examples;
            return this;
        }

        public XrayJsonTestObjectBuilder iterations(List<XrayJsonIterationObject> iterations) {
            this.iterations = iterations;
            return this;
        }

        public XrayJsonTestObjectBuilder iteration(XrayJsonIterationObject iteration) {
            this.iterations.add(iteration);
            return this;
        }

        public XrayJsonTestObjectBuilder defects(List<String> defects) {
            this.defects = defects;
            return this;
        }

        public XrayJsonTestObjectBuilder defect(String defect) {
            this.defects.add(defect);
            return this;
        }

        public XrayJsonTestObjectBuilder evidences(List<XrayJsonEvidenceObject> evidences) {
            this.evidence = evidences;
            return this;
        }

        public XrayJsonTestObjectBuilder evidence(XrayJsonEvidenceObject evidence) {
            this.evidence.add(evidence);
            return this;
        }

        public XrayJsonTestObjectBuilder customFields(List<XrayJsonCustomFieldObject> customFields) {
            this.customFields = customFields;
            return this;
        }

        public XrayJsonTestObjectBuilder customField(XrayJsonCustomFieldObject customField) {
            this.customFields.add(customField);
            return this;
        }

        public XrayJsonTestObject build(){
            XrayJsonTestObject info = new XrayJsonTestObject(this);
            validateXrayTestObject(info);
            return info;
        }

        private void validateXrayTestObject(XrayJsonTestObject obj) {
            // TOOD: some validation code here
        }
    }
}
