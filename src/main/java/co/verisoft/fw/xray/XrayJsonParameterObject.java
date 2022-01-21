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
 * Representation of Xray object - <b>Parameter</b>. This class follows the builder design pattern<br>
 *
 * <br><b>From Xray documentation:</b><br>
 * "parameter" object - parameters within iteration results <br>
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @see <a href="https://docs.getxray.app/display/XRAYCLOUD/Using+Xray+JSON+format+to+import+execution+results#UsingXrayJSONformattoimportexecutionresults-%22parameter%22object-parameterswithiniterationresults">
 * Using Xray JSON format to import execution results - Parameter</a>
 * @since 0.0.2 (Jan 2022)
 */
public class XrayJsonParameterObject extends XrayJsonFormat implements JsonObject {

    private final Map<String, String> fields;


    public @Nullable String getName() {
        return fields.get("name");
    }

    public @Nullable String getValue() {
        return fields.get("value");
    }

    private XrayJsonParameterObject(XrayJsonParameterObjectBuilder builder) {
        this.fields = builder.fields;
    }


    @SuppressWarnings("unchecked")
    @Override
    public JSONObject asJsonObject() {
        JSONObject obj = new JSONObject();

        if (this.getName() != null)
            obj.put("name", fields.get("name"));

        if (this.getValue() != null)
            obj.put("value", fields.get("value"));

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
    public static class XrayJsonParameterObjectBuilder implements Builder {

        private final Map<String, String> fields;

        public XrayJsonParameterObjectBuilder(XrayJsonParameterObject obj) {
            this.fields = obj.fields;
        }

        public XrayJsonParameterObjectBuilder() {
            this.fields = new HashMap<>();
        }

        public XrayJsonParameterObjectBuilder name(String name) {
            this.fields.put("name", name);
            return this;
        }

        public XrayJsonParameterObjectBuilder value(String value) {
            this.fields.put("value", value);

            return this;
        }


        public XrayJsonParameterObject build() {
            XrayJsonParameterObject info = new XrayJsonParameterObject(this);
            validateXrayParameterObject(info);
            return info;
        }

        private void validateXrayParameterObject(XrayJsonParameterObject obj) {
            // TOOD: some validation code here
        }
    }
}
