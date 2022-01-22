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
import org.json.simple.JSONObject;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Representation of Xray info object - Test execution issue. This class follows the builder design pattern<br>
 *
 * <br><b>From Xray documentation:</b><br>
 * You can specify which Test Execution issue to import the results by setting the test execution key on the
 * <b>testExecutionKey</b> property. Alternatively, you can create a new Test Execution issue for the execution results and
 * specify the issue fields within the <b>info</b> object.
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @see <a href="https://docs.getxray.app/display/XRAYCLOUD/Using+Xray+JSON+format+to+import+execution+results#UsingXrayJSONformattoimportexecutionresults-JSONformat">
 * Using Xray JSON format to import execution results - info</a>
 * @since 0.0.2 (Jan 2022)
 */
@ToString
public class XrayJsonInfoObject extends XrayJsonFormat implements JsonObject {

    private final Map<String, String> fields;

    private XrayJsonInfoObject(XrayInfoObjectBuilder builder) {
        this.fields = builder.fields;
    }

    public @Nullable String getProject() {
        return fields.get("project");
    }

    public @Nullable String getSummary() {
        return fields.get("summary");
    }

    public @Nullable String getDescription() {
        return fields.get("description");
    }

    public @Nullable String getVersion() {
        return fields.get("version");
    }

    public @Nullable String getRevision() {
        return fields.get("revision");
    }

    public @Nullable String getUser() {
        return fields.get("user");
    }

    public @Nullable String getStartDate() {
        return fields.get("startDate");
    }

    public @Nullable String getFinishDate() {
        return fields.get("finishDate");
    }

    public @Nullable String getTestPlanKey() {
        return fields.get("testPlanKey");
    }

    public @Nullable String getTestEnvironments() {
        return fields.get("testEnvironments");
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
    @ToString
    public static class XrayInfoObjectBuilder {
        private final Map<String, String> fields;


        public XrayInfoObjectBuilder(XrayJsonInfoObject obj) {
            this.fields = obj.fields;
        }

        public XrayInfoObjectBuilder() {
            this.fields = new HashMap<>();
        }

        public XrayInfoObjectBuilder project(String project) {
            this.fields.put("project", project);
            return this;
        }

        public XrayInfoObjectBuilder summary(String summary) {
            this.fields.put("summary", summary);
            return this;
        }

        public XrayInfoObjectBuilder description(String description) {
            this.fields.put("description", description);
            return this;
        }

        public XrayInfoObjectBuilder version(String version) {
            this.fields.put("version", version);
            return this;
        }

        public XrayInfoObjectBuilder revision(String revision) {
            this.fields.put("revision", revision);
            return this;
        }

        public XrayInfoObjectBuilder user(String user) {
            this.fields.put("user", user);
            return this;
        }

        public XrayInfoObjectBuilder startDate(ZonedDateTime startDate) {
            this.fields.put("startDate", asXrayDateTime(startDate));
            return this;
        }

        public XrayInfoObjectBuilder finishDate(ZonedDateTime finishDate) {
            this.fields.put("finishDate", asXrayDateTime(finishDate));
            return this;
        }

        public XrayInfoObjectBuilder testPlanKey(String testPlanKey) {
            this.fields.put("testPlanKey", testPlanKey);
            return this;
        }

        public XrayInfoObjectBuilder testEnvironments(String testEnvironments) {
            this.fields.put("testEnvironments", testEnvironments);
            return this;
        }

        public XrayJsonInfoObject build() {
            XrayJsonInfoObject info = new XrayJsonInfoObject(this);
            validateXrayInfoObject(info);
            return info;
        }

        private void validateXrayInfoObject(XrayJsonInfoObject obj) {
            // TOOD: some validation code here
        }
    }
}
