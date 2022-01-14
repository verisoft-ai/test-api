package co.verisoft.fw.xray;

import co.verisoft.fw.utils.JsonObject;
import org.json.simple.JSONObject;

/**
 * Representation of Xray info object - Test execution issue. This class follows the builder design pattern<br>
 *
 * <br><b>From Xray documentation:</b><br>
 * You can specify which Test Execution issue to import the results by setting the test execution key on the
 * <b>testExecutionKey</b> property. Alternatively, you can create a new Test Execution issue for the execution results and
 * specify the issue fields within the <b>info</b> object.
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @since 0.0.2 (Jan 2022)
 *
 * @see <a href="https://docs.getxray.app/display/XRAYCLOUD/Using+Xray+JSON+format+to+import+execution+results#UsingXrayJSONformattoimportexecutionresults-JSONformat">
 *     Using Xray JSON format to import execution results</a>
 */
public class XrayJsonTestObject implements JsonObject {

    private final String testKey;           // The test issue key
    private final String testInfo;          // The testInfo element
    private final String start;             // The start date for the test run
    private final String finish;            // The finish date for the test run
    private final String comment;           // The comment for the test run
    private final String executedBy;        // The user id who executed the test run
    private final String assignee;          // The user id for the assignee of the test run
    private final String status;            // The test run status (PASSED, FAILED, EXECUTING, TODO, custom statuses ...)
    private final String steps;             // The step results
    private final String examples;          // The example results for BDD tests (link)
    private final String iterations;        // The iteration containing data-driven test results
    private final String defects;           // An array of defect issue keys to associate with the test run
    private final String evidence;          // An array of evidence items of the test run
    private final String customFields;      // An array of custom fields for the test run


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

    public String getTestInfo() {
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

    public String getStatus() {
        return status;
    }

    public String getSteps() {
        return steps;
    }

    public String getExamples() {
        return examples;
    }

    public String getIterations() {
        return iterations;
    }

    public String getDefects() {
        return defects;
    }

    public String getEvidence() {
        return evidence;
    }

    public String getCustomFields() {
        return customFields;
    }

    @SuppressWarnings("unchecked")
    @Override
    public JSONObject asJsonObject() {
        JSONObject obj = new JSONObject();
        obj.put("testKey", this.testKey);
        obj.put("testInfo", this.testInfo);
        obj.put("start", this.start);
        obj.put("finish", this.finish);
        obj.put("comment", this.comment);
        obj.put("executedBy", this.executedBy);
        obj.put("assignee", this.assignee);
        obj.put("status", this.status);
        obj.put("steps", this.steps);
        obj.put("examples", this.examples);
        obj.put("iterations", this.iterations);
        obj.put("defects", this.defects);
        obj.put("evidence", this.evidence);
        obj.put("customFields", this.customFields);
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
        private String testKey;           // The test issue key
        private String testInfo;          // The testInfo element
        private String start;             // The start date for the test run
        private String finish;            // The finish date for the test run
        private String comment;           // The comment for the test run
        private String executedBy;        // The user id who executed the test run
        private String assignee;          // The user id for the assignee of the test run
        private String status;            // The test run status (PASSED, FAILED, EXECUTING, TODO, custom statuses ...)
        private String steps;             // The step results
        private String examples;          // The example results for BDD tests (link)
        private String iterations;        // The iteration containing data-driven test results
        private String defects;           // An array of defect issue keys to associate with the test run
        private String evidence;          // An array of evidence items of the test run
        private String customFields;      // An array of custom fields for the test run



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
            // No-Op. Basic builder option
        }

        public XrayJsonTestObjectBuilder testKey(String testKey) {
            this.testKey = testKey;
            return this;
        }

        public XrayJsonTestObjectBuilder testInfo(String testInfo) {
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

        public XrayJsonTestObjectBuilder status(String status) {
            this.status = status;
            return this;
        }

        public XrayJsonTestObjectBuilder steps(String steps) {
            this.steps = steps;
            return this;
        }

        public XrayJsonTestObjectBuilder examples(String examples) {
            this.examples = examples;
            return this;
        }

        public XrayJsonTestObjectBuilder iterations(String iterations) {
            this.iterations = iterations;
            return this;
        }

        public XrayJsonTestObjectBuilder defects(String defects) {
            this.defects = defects;
            return this;
        }

        public XrayJsonTestObjectBuilder evidence(String evidence) {
            this.evidence = evidence;
            return this;
        }

        public XrayJsonTestObjectBuilder customFields(String customFields) {
            this.customFields = customFields;
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
