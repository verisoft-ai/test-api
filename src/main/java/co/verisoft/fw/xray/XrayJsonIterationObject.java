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
import org.jetbrains.annotations.Nullable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

/**
 * Representation of Xray object - <b>Iteration</b>. This class follows the builder design pattern<br>
 *
 * <br><b>From Xray documentation:</b><br>
 * "iteration" object - Data-driven test results <br>
 * If you need to import data-driven test results you need to use the iteration object.
 * Xray will store all iterations within the same Test Run object.
 * It is also possible to import iteration results with parameters.
 * <b>Currently, this is only supported for manual tests.</b>
 * In this case, Xray will create a dataset automatically within the Test Run object.
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @see <a href="https://docs.getxray.app/display/XRAYCLOUD/Using+Xray+JSON+format+to+import+execution+results#UsingXrayJSONformattoimportexecutionresults-%22iteration%22object-Data-driventestresults">
 * Using Xray JSON format to import execution results - Iteration</a>
 * @since 0.0.2 (Jan 2022)
 */
public class XrayJsonIterationObject extends XrayJsonFormat implements JsonObject {

    private final Map<String, Object> fields;

    public @Nullable String getName() {
        return (String) fields.get("name");
    }

    public @Nullable List<XrayJsonParameterObject> getParameters() {

        return ((List<XrayJsonParameterObject>) fields.get("parameters"));
    }

    public @Nullable String getLog() {
        return ((String) fields.get("log"));
    }

    public @Nullable String getDuration() {

        return ((String) fields.get("duration"));
    }

    public @Nullable Status getStatus() {
        return ((Status) fields.get("status"));
    }

    public @Nullable String getStatusAsString() {
        return getStatus().toString();
    }


    public @Nullable List<XrayJsonStepResultObject> getSteps() {

        return ((List<XrayJsonStepResultObject>) fields.get("steps"));
    }


    private XrayJsonIterationObject(XrayJsonIterationObjectBuilder builder) {
        this.fields = builder.fields;
    }


    @SuppressWarnings("unchecked")
    @Override
    public JSONObject asJsonObject() {
        JSONObject obj = new JSONObject();

        if (this.getName() != null)
            obj.put("name", this.getName());

        if (this.getLog() != null)
            obj.put("log", this.getLog());

        if (this.getDuration() != null)
            obj.put("duration", this.getDuration());

        if (this.getStatus() != null)
            obj.put("status", this.getStatusAsString());

        if (this.getParameters() != null) {
            JSONArray arr = new JSONArray();
            for (XrayJsonParameterObject parameter : this.getParameters())
                arr.add(parameter.asJsonObject());

            if (!arr.isEmpty()) {
                obj.put("parameters", arr);
            }
        }

        if (this.getSteps() != null) {
            JSONArray arr = new JSONArray(); // arr.clear reveales Json simple BUG!
            for (XrayJsonStepResultObject result : this.getSteps())
                arr.add(result.asJsonObject());

            if (!arr.isEmpty()) {
                obj.put("steps", arr);
            }
            return obj;
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
    public static class XrayJsonIterationObjectBuilder implements Builder {

        private final Map<String, Object> fields;


        public XrayJsonIterationObjectBuilder(XrayJsonIterationObject obj) {
            this.fields = obj.fields;
        }

        public XrayJsonIterationObjectBuilder() {
            fields = new HashMap<>();
        }

        public XrayJsonIterationObjectBuilder name(String name) {
            fields.put("name", name);
            return this;
        }

        public XrayJsonIterationObjectBuilder parameters(List<XrayJsonParameterObject> parameters) {
            fields.put("parameters", parameters);
            return this;
        }

        public XrayJsonIterationObjectBuilder parameter(XrayJsonParameterObject parameter) {
            if (fields.get("parameters") !=null){
                List<XrayJsonParameterObject> p = ((List<XrayJsonParameterObject>) fields.get("parameters"));
                p.add(parameter);
                fields.put("parameters", p);
            }
            else{
                List<XrayJsonParameterObject> p = new ArrayList<>();
                p.add(parameter);
                this.parameters(p);
            }
            return this;
        }

        public XrayJsonIterationObjectBuilder log(String log) {
            fields.put("log", log);
            return this;
        }

        public XrayJsonIterationObjectBuilder duration(String duration) {
            fields.put("duration", duration);
            return this;
        }

        public XrayJsonIterationObjectBuilder status(Status status) {
            fields.put("status", status.toString());
            return this;
        }

        public XrayJsonIterationObjectBuilder status(String status) {
            fields.put("status", Status.toStatus(status));
            return this;
        }

        public XrayJsonIterationObjectBuilder steps(List<XrayJsonStepResultObject> steps) {
            fields.put("steps", steps);
            return this;
        }

        public XrayJsonIterationObjectBuilder step(XrayJsonStepResultObject step) {
            if (fields.get("steps") !=null){
                List<XrayJsonStepResultObject> p = ((List<XrayJsonStepResultObject>) fields.get("steps"));
                p.add(step);
                fields.put("steps", p);
            }
            else{
                List<XrayJsonStepResultObject> p = new ArrayList<>();
                p.add(step);
                this.steps(p);
            }
            return this;
        }


        public XrayJsonIterationObject build() {
            XrayJsonIterationObject info = new XrayJsonIterationObject(this);
            validateXrayIterationObject(info);
            return info;
        }

        private void validateXrayIterationObject(XrayJsonIterationObject obj) {
            // TOOD: some validation code here
        }
    }
}
