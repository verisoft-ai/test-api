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

import co.verisoft.fw.utils.JsonObject;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * @see <a href="https://docs.getxray.app/display/XRAYCLOUD/Using+Xray+JSON+format+to+import+execution+results#UsingXrayJSONformattoimportexecutionresults-%22test%22object-TestRundetails">
 * Using Xray JSON format to import execution results - Test</a>
 * @since 0.0.2 (Jan 2022)
 */
@ToString
public class XrayJsonTestObject extends XrayJsonFormat implements JsonObject {

    private final Map<String, Object> fields;

    private XrayJsonTestObject(XrayJsonTestObjectBuilder builder) {
        this.fields = builder.fields;
    }

    public @Nullable String getTestKey() {
        return (String) fields.get("testKey");
    }

    public @Nullable XrayJsonTestInfoObject getTestInfo() {
        return (XrayJsonTestInfoObject) fields.get("testInfo");
    }

    public @Nullable String getStart() {
        return (String) fields.get("start");
    }

    public @Nullable String getFinish() {
        return (String) fields.get("finish");
    }

    public @Nullable String getComment() {
        return (String) fields.get("comment");
    }

    public @Nullable String getExecutedBy() {
        return (String) fields.get("executedBy");
    }

    public @Nullable String getAssignee() {
        return (String) fields.get("assignee");
    }

    public @Nullable Status getStatus() {
        return (Status) fields.get("status");
    }

    public @Nullable String getStatusAsString() {
        return fields.get("status").toString();
    }

    public @Nullable List<XrayJsonStepResultObject> getSteps() {
        return (List<XrayJsonStepResultObject>) fields.get("steps");
    }

    public @Nullable String getExamples() {
        return (String) fields.get("examples");
    }

    public @Nullable List<XrayJsonIterationObject> getIterations() {

        return (List<XrayJsonIterationObject>) fields.get("iterations");
    }

    public @Nullable List<String> getDefects() {
        return (List<String>) fields.get("defects");
    }

    public @Nullable List<XrayJsonEvidenceObject> getEvidence() {

        return (List<XrayJsonEvidenceObject>) fields.get("evidences");
    }

    public @Nullable List<XrayJsonCustomFieldObject> getCustomFields() {

        return (List<XrayJsonCustomFieldObject>) fields.get("customFields");
    }

    @SuppressWarnings("unchecked")
    @Override
    public JSONObject asJsonObject() {
        JSONObject obj = new JSONObject();

        if (getTestKey() != null)
            obj.put("testKey", this.getTestKey());

        if (getTestInfo() != null)
            obj.put("testInfo", this.getTestInfo().asJsonObject());

        if (getStart() != null)
            obj.put("start", this.getStart());

        if (this.getFinish() != null)
            obj.put("finish", this.getFinish());

        if (this.getComment() != null)
            obj.put("comment", this.getComment());

        if (this.getExecutedBy() != null)
            obj.put("executedBy", this.getExecutedBy());

        if (this.getAssignee() != null)
            obj.put("assignee", this.getAssignee());

        if (this.getStatus() != null)
            obj.put("status", this.getStatus().toString());

        if (this.getSteps() != null) {
            JSONArray arr = new JSONArray();
            for (XrayJsonStepResultObject step : getSteps()) {
                arr.add(step.asJsonObject());
            }
            if (!arr.isEmpty())
                obj.put("steps", arr);
        }

        if (this.getExamples() != null)
            obj.put("examples", this.getExamples());

        if (this.getIterations() != null) {
            JSONArray arr = new JSONArray();
            for (XrayJsonIterationObject iteration : getIterations()) {
                arr.add(iteration.asJsonObject());
            }
            if (!arr.isEmpty())
                obj.put("iterations", arr);
        }

        if (getDefects() != null) {
            JSONArray arr = new JSONArray();
            for (String defect : getDefects()) {
                arr.add(defect);
            }
            if (!arr.isEmpty())
                obj.put("defects", arr);
        }

        if (getEvidence() != null) {
            JSONArray arr = new JSONArray();
            for (XrayJsonEvidenceObject evidence : getEvidence()) {
                arr.add(evidence.asJsonObject());
            }
            if (!arr.isEmpty())
                obj.put("evidences", arr);
        }

        if (this.getCustomFields() != null) {
            JSONArray arr = new JSONArray();
            for (XrayJsonCustomFieldObject cusField : getCustomFields()) {
                arr.add(cusField.asJsonObject());
            }
            if (!arr.isEmpty())
                obj.put("customFields", arr);
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
    @ToString
    public static class XrayJsonTestObjectBuilder {
        private final Map<String, Object> fields;


        public XrayJsonTestObjectBuilder(XrayJsonTestObject obj) {
            this.fields = obj.fields;
        }

        public XrayJsonTestObjectBuilder() {
            fields = new HashMap<>();
        }

        public XrayJsonTestObjectBuilder testKey(String testKey) {
            fields.put("testKey", testKey);
            return this;
        }

        public XrayJsonTestObjectBuilder testInfo(XrayJsonTestInfoObject testInfo) {
            fields.put("testInfo", testInfo);
            return this;
        }

        public XrayJsonTestObjectBuilder start(ZonedDateTime start) {
            fields.put("start", asXrayDateTime(start));
            return this;
        }

        public XrayJsonTestObjectBuilder finish(ZonedDateTime finish) {
            fields.put("finish", asXrayDateTime(finish));
            return this;
        }

        public XrayJsonTestObjectBuilder comment(String comment) {
            fields.put("comment", comment);
            return this;
        }

        public XrayJsonTestObjectBuilder executedBy(String executedBy) {
            fields.put("executedBy", executedBy);
            return this;
        }

        public XrayJsonTestObjectBuilder assignee(String assignee) {
            fields.put("assignee", assignee);
            return this;
        }

        public XrayJsonTestObjectBuilder status(Status status) {
            fields.put("status", status);
            return this;
        }

        public XrayJsonTestObjectBuilder status(String status) {
            fields.put("status", Status.toStatus(status));
            return this;
        }

        public XrayJsonTestObjectBuilder steps(List<XrayJsonStepResultObject> steps) {
            fields.put("steps", steps);
            return this;
        }

        public XrayJsonTestObjectBuilder step(XrayJsonStepResultObject step) {
            if (fields.get("steps") != null) {
                List<XrayJsonStepResultObject> p = ((List<XrayJsonStepResultObject>) fields.get("steps"));
                p.add(step);
                fields.put("steps", p);
            } else {
                List<XrayJsonStepResultObject> p = new ArrayList<>();
                p.add(step);
                this.steps(p);
            }
            return this;
        }

        public XrayJsonTestObjectBuilder examples(String examples) {
            fields.put("examples", examples);
            return this;
        }

        public XrayJsonTestObjectBuilder iterations(List<XrayJsonIterationObject> iterations) {
            fields.put("iterations", iterations);
            return this;
        }

        public XrayJsonTestObjectBuilder iteration(XrayJsonIterationObject iteration) {
            if (fields.get("iterations") != null) {
                List<XrayJsonIterationObject> p = ((List<XrayJsonIterationObject>) fields.get("iterations"));
                p.add(iteration);
                fields.put("steps", p);
            } else {
                List<XrayJsonIterationObject> p = new ArrayList<>();
                p.add(iteration);
                this.iterations(p);
            }
            return this;
        }

        public XrayJsonTestObjectBuilder defects(List<String> defects) {
            fields.put("defects", defects);
            return this;
        }

        public XrayJsonTestObjectBuilder defect(String defect) {
            if (fields.get("defects") != null) {
                List<String> p = ((List<String>) fields.get("defects"));
                p.add(defect);
                fields.put("defects", p);
            } else {
                List<String> p = new ArrayList<>();
                p.add(defect);
                this.defects(p);
            }
            return this;
        }

        public XrayJsonTestObjectBuilder evidences(List<XrayJsonEvidenceObject> evidences) {
            fields.put("evidences", evidences);
            return this;
        }

        public XrayJsonTestObjectBuilder evidence(XrayJsonEvidenceObject evidence) {
            if (fields.get("evidences") != null) {
                List<XrayJsonEvidenceObject> p = ((List<XrayJsonEvidenceObject>) fields.get("evidences"));
                p.add(evidence);
                fields.put("evidences", p);
            } else {
                List<XrayJsonEvidenceObject> p = new ArrayList<>();
                p.add(evidence);
                this.evidences(p);
            }
            return this;
        }

        public XrayJsonTestObjectBuilder customFields(List<XrayJsonCustomFieldObject> customFields) {
            fields.put("customFields", customFields);
            return this;
        }

        public XrayJsonTestObjectBuilder customField(XrayJsonCustomFieldObject customField) {
            if (fields.get("customFields") != null) {
                List<XrayJsonCustomFieldObject> p = ((List<XrayJsonCustomFieldObject>) fields.get("customFields"));
                p.add(customField);
                fields.put("customFields", p);
            } else {
                List<XrayJsonCustomFieldObject> p = new ArrayList<>();
                p.add(customField);
                this.customFields(p);
            }
            return this;
        }

        public XrayJsonTestObject build() {
            XrayJsonTestObject info = new XrayJsonTestObject(this);
            validateXrayTestObject(info);
            return info;
        }

        private void validateXrayTestObject(XrayJsonTestObject obj) {
            // TOOD: some validation code here
        }
    }
}
