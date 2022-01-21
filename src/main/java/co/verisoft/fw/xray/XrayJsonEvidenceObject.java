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

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Representation of Xray object - <b>Evidence</b>. This class follows the builder design pattern<br>
 *
 * <br><b>From Xray documentation:</b><br>
 * "evidence" object - embedded attachments <br>
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @see <a href="https://docs.getxray.app/display/XRAYCLOUD/Using+Xray+JSON+format+to+import+execution+results#UsingXrayJSONformattoimportexecutionresults-%22evidence%22object-embeddedattachments">
 * Using Xray JSON format to import execution results - Evidence</a>
 * @since 0.0.2 (Jan 2022)
 */
public class XrayJsonEvidenceObject extends XrayJsonFormat implements JsonObject {

    private final Map<String, String> fields;


    public @Nullable String getData() {
        return fields.get("data");
    }

    public @Nullable String getEncodedData() {
        String data = fields.get("data");
        return Base64.getEncoder().encodeToString(data.getBytes());
    }

    public @Nullable String getFileName() {
        return fields.get("filename");
    }

    public @Nullable String getContentType() {
        return fields.get("contentType");
    }

    private XrayJsonEvidenceObject(XrayJsonEvidenceObjectBuilder builder) {
        this.fields = builder.fields;
    }


    @SuppressWarnings("unchecked")
    @Override
    public JSONObject asJsonObject() {
        JSONObject obj = new JSONObject();
        if (this.getData() != null)
            obj.put("data", Base64.getEncoder().encodeToString(this.getData().getBytes()));

        if (this.getFileName() != null)
            obj.put("filename", this.getFileName());

        if (this.getContentType() != null)
            obj.put("contentType", this.getContentType());

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
    public static class XrayJsonEvidenceObjectBuilder implements Builder {

        private final Map<String, String> fields;

        public XrayJsonEvidenceObjectBuilder(XrayJsonEvidenceObject obj) {
            this.fields = obj.fields;
        }

        public XrayJsonEvidenceObjectBuilder() {
            this.fields = new HashMap<>();
        }

        public XrayJsonEvidenceObjectBuilder data(String data) {
            this.fields.put("data", data);
            return this;
        }

        public XrayJsonEvidenceObjectBuilder filename(String filename) {
            this.fields.put("filename", filename);
            return this;
        }

        public XrayJsonEvidenceObjectBuilder contentType(String contentType) {
            this.fields.put("contentType", contentType);
            return this;
        }


        public XrayJsonEvidenceObject build() {
            XrayJsonEvidenceObject info = new XrayJsonEvidenceObject(this);
            validateXrayEvidenceObject(info);
            return info;
        }

        private void validateXrayEvidenceObject(XrayJsonEvidenceObject obj) {
            // TOOD: some validation code here
        }
    }
}
