package co.verisoft.fw.xray;

import co.verisoft.fw.utils.Builder;
import co.verisoft.fw.utils.JsonObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Representation of Xray object - <b>Test Info</b>. This class follows the builder design pattern<br>
 *
 * <br><b>From Xray documentation:</b><br>
 * "testInfo" object - Creating Test issues <br>
 * It is possible to create new test issues when importing execution results using the Xray JSON format. For this,
 * a <b>testInfo</b> element must be provided in order for Xray to create the issues.
 * If it is the first time you are importing an execution with a <b>testInfo</b>, Xray will create the tests
 * automatically. Sub-sequent executions will reuse the same test issues.
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @see <a href="https://docs.getxray.app/display/XRAYCLOUD/Using+Xray+JSON+format+to+import+execution+results#UsingXrayJSONformattoimportexecutionresults-%22testInfo%22object-CreatingTestissues">
 * Using Xray JSON format to import execution results - Tets Info</a>
 * @since 0.0.2 (Jan 2022)
 */
public class XrayJsonTestInfoObject implements JsonObject {

    private final String projectKey;                    // The project key where the test issue will be created
    private final String summary;                       // The summary for the test issue
    private final TestType type;                        // The test type (e.g. Manual, Cucumber, Generic)
    private final List<String> requirementKeys;         // An array of requirement issue keys to associate with the test
    private final String labels;                        // The test issue labels
    private final List<XrayJsonStepDefObject> steps;    // An array of test steps (for Manual tests)
    private final String scenario;                      // The BDD scenario
    private final String definition;                    // The generic test definition

    public String getProjectKey() {
        return projectKey;
    }

    public String getSummary() {
        return summary;
    }

    public TestType getType() {
        return type;
    }

    public String getTypeAsString() {
        return type.toString();
    }

    public List<String> getRequirementKeys() {
        return requirementKeys;
    }

    public String getLabels() {
        return labels;
    }

    public List<XrayJsonStepDefObject> getSteps() {
        return steps;
    }

    public String getScenario() {
        return scenario;
    }

    public String getDefinition() {
        return definition;
    }

    private XrayJsonTestInfoObject(XrayJsonTestInfoObjectBuilder builder) {
        this.projectKey = builder.projectKey;
        this.summary = builder.summary;
        this.type = builder.type;
        this.requirementKeys = builder.requirementKeys;
        this.labels = builder.labels;
        this.steps = builder.steps;
        this.scenario = builder.scenario;
        this.definition = builder.definition;
    }


    @SuppressWarnings("unchecked")
    @Override
    public JSONObject asJsonObject() {
        JSONObject obj = new JSONObject();
        obj.put("projectKey", this.projectKey);
        obj.put("summary", this.summary);
        obj.put("type", this.type.toString());

        // requirementKeys
        JSONArray arr = new JSONArray();
        for (String requirementKey : requirementKeys)
            arr.add(requirementKey);

        if (!arr.isEmpty()) {
            obj.put("requirementKeys", arr);
        }

        obj.put("labels", this.labels);

        // Steps
        arr = new JSONArray(); // arr.clear reveales Json simple BUG!
        for (XrayJsonStepDefObject result : steps)
            arr.add(result.asJsonObject());

        if (!arr.isEmpty()) {
            obj.put("steps", arr);
        }

        obj.put("scenario", this.scenario);
        obj.put("definition", this.definition);

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
    public static class XrayJsonTestInfoObjectBuilder implements Builder {

        private String projectKey;
        private String summary;
        private TestType type;
        private List<String> requirementKeys;
        private String labels;
        private List<XrayJsonStepDefObject> steps;
        private String scenario;
        private String definition;


        public XrayJsonTestInfoObjectBuilder(XrayJsonTestInfoObject obj) {
            this.projectKey = obj.projectKey;
            this.summary = obj.summary;
            this.type = obj.type;
            this.requirementKeys = obj.requirementKeys;
            this.labels = obj.labels;
            this.steps = obj.steps;
            this.scenario = obj.scenario;
            this.definition = obj.definition;
        }

        public XrayJsonTestInfoObjectBuilder() {
            requirementKeys = new ArrayList<>();
            steps = new ArrayList<>();
        }

        public XrayJsonTestInfoObjectBuilder projectKey(String projectKey) {
            this.projectKey = projectKey;
            return this;
        }

        public XrayJsonTestInfoObjectBuilder summary(String summary) {
            this.summary = summary;
            return this;
        }

        public XrayJsonTestInfoObjectBuilder type(TestType type) {
            this.type = type;
            return this;
        }

        public XrayJsonTestInfoObjectBuilder type(String type) {
            this.type = TestType.toType(type);
            return this;
        }

        public XrayJsonTestInfoObjectBuilder requirementKeys(List<String> requirementKeys) {
            this.requirementKeys = requirementKeys;
            return this;
        }

        public XrayJsonTestInfoObjectBuilder requirementKey(String requirementKey) {
            this.requirementKeys.add(requirementKey);
            return this;
        }

        public XrayJsonTestInfoObjectBuilder labels(String labels) {
            this.labels = labels;
            return this;
        }

        public XrayJsonTestInfoObjectBuilder steps(List<XrayJsonStepDefObject> steps) {
            this.steps = steps;
            return this;
        }

        public XrayJsonTestInfoObjectBuilder step(XrayJsonStepDefObject step) {
            this.steps.add(step);
            return this;
        }

        public XrayJsonTestInfoObjectBuilder scenario(String scenario) {
            this.scenario = scenario;
            return this;
        }

        public XrayJsonTestInfoObjectBuilder definition(String definition) {
            this.definition = definition;
            return this;
        }



        public XrayJsonTestInfoObject build() {
            XrayJsonTestInfoObject info = new XrayJsonTestInfoObject(this);
            validateXrayTestInfoObject(info);
            return info;
        }

        private void validateXrayTestInfoObject(XrayJsonTestInfoObject obj) {
            // TOOD: some validation code here
        }
    }
}
