package co.verisoft.fw.xray;
/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import co.verisoft.fw.utils.Builder;
import co.verisoft.fw.utils.JsonObject;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@ToString
public class XrayJsonTestInfoObject extends XrayJsonFormat implements JsonObject {

    private final Map<String, Object> fields;

    public @Nullable String getProjectKey() {
        return (String) fields.get("projectKey");
    }

    public @Nullable String getSummary() {
        return (String) fields.get("summary");
    }

    public @Nullable TestType getType() {
        return (TestType) fields.get("type");
    }

    public @Nullable String getTypeAsString() {
        return getType().toString();
    }

    public @Nullable List<String> getRequirementKeys() {
        return ((List<String>) fields.get("requirementKeys"));
    }

    public @Nullable String getLabels() {
        return (String) fields.get("labels");
    }

    public @Nullable List<XrayJsonStepDefObject> getSteps() {
        return ((List<XrayJsonStepDefObject>) fields.get("steps"));
    }

    public @Nullable String getScenario() {
        return (String) fields.get("scenario");
    }

    public @Nullable String getDefinition() {
        return ((String) fields.get("definition"));
    }

    private XrayJsonTestInfoObject(XrayJsonTestInfoObjectBuilder builder) {
        this.fields = builder.fields;
    }


    @SuppressWarnings("unchecked")
    @Override
    public JSONObject asJsonObject() {
        JSONObject obj = new JSONObject();

        if (this.getProjectKey() != null)
            obj.put("projectKey", getProjectKey());

        if (this.getSummary() != null)
            obj.put("summary", this.getSummary());

        if (this.getType() != null)
            obj.put("type", this.getType().toString());

        if (this.getRequirementKeys() != null) {
            JSONArray arr = new JSONArray();
            arr.addAll(getRequirementKeys());

            if (!arr.isEmpty()) {
                obj.put("requirementKeys", arr);
            }
        }

        if (this.getLabels() != null)
            obj.put("labels", this.getLabels());


        if (this.getSteps() != null) {
            JSONArray arr = new JSONArray();
            for (XrayJsonStepDefObject requirementKey : this.getSteps())
                arr.add(requirementKey.asJsonObject());

            if (!arr.isEmpty()) {
                obj.put("steps", arr);
            }
        }

        if (this.getScenario() != null)
            obj.put("scenario", this.getScenario());

        if (this.getDefinition() != null)
            obj.put("definition", this.getDefinition());

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
    @ToString
    @SuppressWarnings("rawtypes")
    public static class XrayJsonTestInfoObjectBuilder implements Builder {

        private final Map<String, Object> fields;

        public XrayJsonTestInfoObjectBuilder(XrayJsonTestInfoObject obj) {
            this.fields = obj.fields;
        }

        public XrayJsonTestInfoObjectBuilder() {
            this.fields = new HashMap<>();
        }

        public XrayJsonTestInfoObjectBuilder projectKey(String projectKey) {
            fields.put("projectKey", projectKey);
            return this;
        }

        public XrayJsonTestInfoObjectBuilder summary(String summary) {
            fields.put("summary", summary);
            return this;
        }

        public XrayJsonTestInfoObjectBuilder type(TestType type) {
            fields.put("type", type);
            return this;
        }

        public XrayJsonTestInfoObjectBuilder type(String type) {
            fields.put("type", TestType.toType(type));
            return this;
        }

        public XrayJsonTestInfoObjectBuilder requirementKeys(List<String> requirementKeys) {
            fields.put("requirementKeys", requirementKeys);
            return this;
        }

        public XrayJsonTestInfoObjectBuilder requirementKey(String requirementKey) {
            if (fields.get("requirementKeys") != null) {
                List<String> p = ((List<String>) fields.get("requirementKey"));
                p.add(requirementKey);
                fields.put("requirementKeys", p);
            } else {
                List<String> p = new ArrayList<>();
                p.add(requirementKey);
                this.requirementKeys(p);
            }
            return this;
        }

        public XrayJsonTestInfoObjectBuilder labels(String labels) {
            fields.put("labels", labels);
            return this;
        }

        public XrayJsonTestInfoObjectBuilder steps(List<XrayJsonStepDefObject> steps) {
            fields.put("steps", steps);
            return this;
        }

        public XrayJsonTestInfoObjectBuilder step(XrayJsonStepDefObject step) {
            if (fields.get("steps") != null) {
                List<XrayJsonStepDefObject> p = ((List<XrayJsonStepDefObject>) fields.get("steps"));
                p.add(step);
                fields.put("steps", p);
            } else {
                List<XrayJsonStepDefObject> p = new ArrayList<>();
                p.add(step);
                this.steps(p);
            }
            return this;
        }

        public XrayJsonTestInfoObjectBuilder scenario(String scenario) {
            fields.put("scenario", scenario);
            return this;
        }

        public XrayJsonTestInfoObjectBuilder definition(String definition) {
            fields.put("definition", definition);
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
