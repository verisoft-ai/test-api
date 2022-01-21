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
 * Representation of Xray object - <b>Step Definition</b>. This class follows the builder design pattern<br>
 *
 * <br><b>From Xray documentation:</b><br>
 * "step def" object - step definition <br>
 * This object allows you to define the step fields for manual tests. Custom test step fields are also supported.
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @see <a href="https://docs.getxray.app/display/XRAYCLOUD/Using+Xray+JSON+format+to+import+execution+results#UsingXrayJSONformattoimportexecutionresults-%22stepdef%22object-stepdefinition">
 * Using Xray JSON format to import execution results - Step Definition</a>
 * @since 0.0.2 (Jan 2022)
 */
public class XrayJsonStepDefObject extends XrayJsonFormat implements JsonObject {

    private final Map<String, Object> fields;

    public @Nullable String getAction() {
        return (String) fields.get("action");
    }

    public @Nullable String getData() {
        return (String) fields.get("data");
    }

    public @Nullable String getResult() {
        return (String) fields.get("result");
    }

    public @Nullable List<XrayJsonCustomFieldObject> getCustomFields() {

        //noinspection unchecked
        return (List<XrayJsonCustomFieldObject>) fields.get("customFields");
    }

    public @Nullable XrayJsonCustomFieldObject getCustomField(String id) {
        List<XrayJsonCustomFieldObject> fields = this.getCustomFields();
        return fields.stream()
                .filter(custField -> custField.getId().equalsIgnoreCase(id))
                .findAny()
                .orElse(null);
    }


    private XrayJsonStepDefObject(XrayJsonStepDefObjectBuilder builder) {
        this.fields = builder.fields;
    }


    @SuppressWarnings("unchecked")
    @Override
    public JSONObject asJsonObject() {
        JSONObject obj = new JSONObject();

        if (this.getAction() != null)
            obj.put("action", this.getAction());

        if (this.getData() != null)
            obj.put("data", this.getData());

        if (this.getResult() != null)
            obj.put("result", this.getResult());

        if (this.getCustomFields() != null) {
            JSONArray arr = new JSONArray();
            for (XrayJsonCustomFieldObject custField : getCustomFields())
                arr.add(custField.asJsonObject());

            if (!arr.isEmpty()) {
                obj.put("customFields", arr);
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
    public static class XrayJsonStepDefObjectBuilder implements Builder {
        private final Map<String, Object> fields;


        public XrayJsonStepDefObjectBuilder(XrayJsonStepDefObject obj) {
            this.fields = obj.fields;
        }

        public XrayJsonStepDefObjectBuilder() {
            fields = new HashMap<>();
        }

        public XrayJsonStepDefObjectBuilder action(String action) {
            fields.put("action", action);
            return this;
        }

        public XrayJsonStepDefObjectBuilder data(String data) {
            fields.put("data", data);
            return this;
        }

        public XrayJsonStepDefObjectBuilder result(String result) {
            fields.put("result", result);
            return this;
        }

        public XrayJsonStepDefObjectBuilder customFields(List<XrayJsonCustomFieldObject> customFields) {
            fields.put("customFields", customFields);
            return this;
        }

        public XrayJsonStepDefObjectBuilder customField(XrayJsonCustomFieldObject customField) {
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


        public XrayJsonStepDefObject build() {
            XrayJsonStepDefObject info = new XrayJsonStepDefObject(this);
            validateXrayStepDefObject(info);
            return info;
        }

        private void validateXrayStepDefObject(XrayJsonStepDefObject obj) {
            // TOOD: some validation code here
        }
    }
}
