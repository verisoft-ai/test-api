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
 *     Using Xray JSON format to import execution results - info</a>
 */
public class XrayJsonInfoObject implements JsonObject {

    private final String project;         // The project key where the test execution will be created
    private final String summary;         // The summary for the test execution issue
    private final String description;     // The description for the test execution issue
    private final String version;         // The version name for the Fix Version field of the test execution issue
    private final String revision;        // A revision for the revision custom field
    private final String user;            // The userid for the Jira user who executed the tests
    private final String startDate;       // The start date for the test execution issue
    private final String finishDate;      // The finish date for the test execution issue
    private final String testPlanKey;      // The test plan key for associating the test execution issue
    private final String testEnvironments; // The test environments for the test execution issue


    private XrayJsonInfoObject(XrayInfoObjectBuilder builder){
        this.project = builder.project;
        this.summary = builder.summary;
        this.description = builder.description;
        this.version = builder.version;
        this.revision = builder.revision;
        this.user = builder.user;
        this.startDate = builder.startDate;
        this.finishDate = builder.finishDate;
        this.testPlanKey = builder.testPlanKey;
        this.testEnvironments = builder.testEnvironments;
    }

    public String getProject() {
        return project;
    }

    public String getSummary() {
        return summary;
    }

    public String getDescription() {
        return description;
    }

    public String getVersion() {
        return version;
    }

    public String getRevision() {
        return revision;
    }

    public String getUser() {
        return user;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public String getTestPlanKey() {
        return testPlanKey;
    }

    public String getTestEnvironments() {
        return testEnvironments;
    }


    @SuppressWarnings("unchecked")
    @Override
    public JSONObject asJsonObject() {
        JSONObject obj = new JSONObject();
        obj.put("project", this.project);
        obj.put("summary", this.summary);
        obj.put("description", this.description);
        obj.put("version", this.version);
        obj.put("revision", this.revision);
        obj.put("user", this.user);
        obj.put("startDate", this.startDate);
        obj.put("finishDate", this.finishDate);
        obj.put("testPlanKey", this.testPlanKey);
        obj.put("testEnvironments", this.testEnvironments);
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
    public static class XrayInfoObjectBuilder{
        private String project;         // The project key where the test execution will be created
        private String summary;         // The summary for the test execution issue
        private String description;     // The description for the test execution issue
        private String version;         // The version name for the Fix Version field of the test execution issue
        private String revision;        // A revision for the revision custom field
        private String user;            // The userid for the Jira user who executed the tests
        private String startDate;       // The start date for the test execution issue
        private String finishDate;      // The finish date for the test execution issue
        public String testPlanKey;      // The test plan key for associating the test execution issue
        public String testEnvironments; // The test environments for the test execution issue



        public XrayInfoObjectBuilder(XrayJsonInfoObject obj){
            this.project = obj.project;
            this.summary = obj.summary;
            this.description = obj.description;
            this.version = obj.version;
            this.revision = obj.revision;
            this.user = obj.user;
            this.startDate = obj.startDate;
            this.finishDate = obj.finishDate;
            this.testPlanKey = obj.testPlanKey;
            this.testEnvironments = obj.testEnvironments;
        }

        public XrayInfoObjectBuilder(){
            // No-Op. Basic builder option
        }

        public XrayInfoObjectBuilder project(String project) {
            this.project = project;
            return this;
        }

        public XrayInfoObjectBuilder summary(String summary) {
            this.summary = summary;
            return this;
        }

        public XrayInfoObjectBuilder description(String description) {
            this.description = description;
            return this;
        }

        public XrayInfoObjectBuilder version(String version) {
            this.version = version;
            return this;
        }

        public XrayInfoObjectBuilder revision(String revision) {
            this.revision = revision;
            return this;
        }

        public XrayInfoObjectBuilder user(String user) {
            this.user = user;
            return this;
        }

        public XrayInfoObjectBuilder startDate(String startDate) {
            this.startDate = startDate;
            return this;
        }

        public XrayInfoObjectBuilder finishDate(String finishDate) {
            this.finishDate = finishDate;
            return this;
        }

        public XrayInfoObjectBuilder testPlanKey(String testPlanKey) {
            this.testPlanKey = testPlanKey;
            return this;
        }

        public XrayInfoObjectBuilder testEnvironments(String testEnvironments) {
            this.testEnvironments = testEnvironments;
            return this;
        }

        public XrayJsonInfoObject build(){
            XrayJsonInfoObject info = new XrayJsonInfoObject(this);
            validateXrayInfoObject(info);
            return info;
        }

        private void validateXrayInfoObject(XrayJsonInfoObject obj) {
            // TOOD: some validation code here
        }
    }
}
