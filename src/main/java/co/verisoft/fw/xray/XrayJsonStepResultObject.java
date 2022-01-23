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
 * Representation of Xray object - <b>Step Result</b>. This class follows the builder design pattern<br>
 *
 * <br><b>From Xray documentation:</b><br>
 * "step result" object - step results <br>
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @see <a href="https://docs.getxray.app/display/XRAYCLOUD/Using+Xray+JSON+format+to+import+execution+results#UsingXrayJSONformattoimportexecutionresults-%22stepresult%22object-stepresults">
 * Using Xray JSON format to import execution results - Step Result</a>
 * @since 0.0.2 (Jan 2022)
 */
@ToString
public class XrayJsonStepResultObject extends XrayJsonFormat implements JsonObject {

    private final Map<String, Object> fields;

    public @Nullable Status getStatus() {
        return (Status) fields.get("status");
    }

    public @Nullable String getStatusAsString() {
        return getStatus() != null ? this.getStatus().toString() : null;
    }

    public @Nullable String getComment() {
        return (String) fields.get("comment");
    }

    public @Nullable String getActualResult() {
        return (String) fields.get("actualResult");
    }

    @SuppressWarnings("unchecked")
    public @Nullable List<XrayJsonEvidenceObject> getEvidences() {
        return (List<XrayJsonEvidenceObject>) fields.get("evidences");
    }

    public @Nullable String getDefects() {
        return (String) fields.get("defects");
    }


    private XrayJsonStepResultObject(XrayJsonStepResultObjectBuilder builder) {
        this.fields = builder.fields;
    }


    @SuppressWarnings("unchecked")
    @Override
    public JSONObject asJsonObject() {
        JSONObject obj = new JSONObject();

        if (this.getStatus() != null)
            obj.put("status", this.getStatusAsString());

        if (this.getComment() != null)
            obj.put("comment", this.getComment());

        if (this.getActualResult() != null)
            obj.put("actualResult", this.getActualResult());

        if (this.getDefects() != null)
            obj.put("defects", this.getDefects());

        if (this.getEvidences() != null) {
            JSONArray arr = new JSONArray();
            for (XrayJsonEvidenceObject evidence : getEvidences())
                arr.add(evidence.asJsonObject());

            if (!arr.isEmpty()) {
                obj.put("evidences", arr);
            }
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
    @SuppressWarnings("rawtypes")
    public static class XrayJsonStepResultObjectBuilder implements Builder {

        private final Map<String, Object> fields;

        public XrayJsonStepResultObjectBuilder(XrayJsonStepResultObject obj) {
            this.fields = obj.fields;
        }

        public XrayJsonStepResultObjectBuilder() {
            this.fields = new HashMap<>();
        }

        public XrayJsonStepResultObjectBuilder status(String status) {
            fields.put("status", Status.toStatus(status));
            return this;
        }

        public XrayJsonStepResultObjectBuilder status(Status status) {
            fields.put("status", status);
            return this;
        }

        public XrayJsonStepResultObjectBuilder comment(String comment) {
            fields.put("comment", comment);
            return this;
        }

        public XrayJsonStepResultObjectBuilder actualResult(String actualResult) {
            fields.put("actualResult", actualResult);
            return this;
        }

        public XrayJsonStepResultObjectBuilder evidence(List<XrayJsonEvidenceObject> evidences) {
            fields.put("evidences", evidences);
            return this;
        }

        public XrayJsonStepResultObjectBuilder evidence(XrayJsonEvidenceObject evidence) {
            if (fields.get("evidences") != null) {
                List<XrayJsonEvidenceObject> p = ((List<XrayJsonEvidenceObject>) fields.get("evidences"));
                p.add(evidence);
                fields.put("evidences", p);
            } else {
                List<XrayJsonEvidenceObject> p = new ArrayList<>();
                p.add(evidence);
                this.evidence(p);
            }
            return this;
        }

        public XrayJsonStepResultObjectBuilder defects(String defects) {
            fields.put("defects", defects);
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
