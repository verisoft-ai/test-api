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
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Representation of Xray object - <b>Custom Field</b>. This class follows the builder design pattern<br>
 *
 * <br><b>From Xray documentation:</b><br>
 * "customField" object - store test run custom fields <br>
 * It is possible to import test run custom field values into the Test Run object. Xray will use the "id" or "name"
 * to find the existing test run custom field in the project settings.
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @see <a href="https://docs.getxray.app/display/XRAYCLOUD/Using+Xray+JSON+format+to+import+execution+results#UsingXrayJSONformattoimportexecutionresults-customfield">
 * Using Xray JSON format to import execution results - Custom Field</a>
 * @since 0.0.2 (Jan 2022)
 */
public class XrayJsonCustomFieldObject extends XrayJsonFormat implements JsonObject {

    private final Map<String, String> fields;


    public @Nullable String getId() {
        return fields.get("id");
    }

    public @Nullable String getName() {
        return fields.get("name");
    }

    public @Nullable String getValue() {
        return fields.get("value");
    }

    private XrayJsonCustomFieldObject(XrayJsonCustomFieldObjectBuilder builder) {
        this.fields = builder.fields;
    }


    @SuppressWarnings("unchecked")
    @Override
    public JSONObject asJsonObject() {
        JSONObject obj = new JSONObject();
        for (Map.Entry<String, String> entry : fields.entrySet()) {
            if (entry.getValue() != null)
                obj.put(entry.getKey(), entry.getValue());
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
    public static class XrayJsonCustomFieldObjectBuilder implements Builder {

        private final Map<String, String> fields;

        public XrayJsonCustomFieldObjectBuilder(XrayJsonCustomFieldObject obj) {
            this.fields = obj.fields;
        }

        public XrayJsonCustomFieldObjectBuilder() {
            this.fields = new HashMap<>();
        }

        public XrayJsonCustomFieldObjectBuilder id(String id) {
            this.fields.put("id", id);
            return this;
        }

        public XrayJsonCustomFieldObjectBuilder name(String name) {
            this.fields.put("name", name);
            return this;
        }

        public XrayJsonCustomFieldObjectBuilder value(String value) {
            this.fields.put("value", value);
            return this;
        }


        public XrayJsonCustomFieldObject build() {
            XrayJsonCustomFieldObject info = new XrayJsonCustomFieldObject(this);
            validateXrayCustomFieldObject(info);
            return info;
        }

        private void validateXrayCustomFieldObject(XrayJsonCustomFieldObject obj) {
            // TOOD: some validation code here
        }
    }
}
