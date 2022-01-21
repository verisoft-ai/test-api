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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Representation of Xray Json format. This class follows the builder design pattern<br>
 *
 * <br><b>From Xray documentation:</b><br>
 * Xray provides a proprietary JSON format for importing execution results into Jira/Xray. <br>
 * Although Xray supports multiple report formats used by different testing frameworks/runners (e.g. JUnit, NUnit,
 * xUnit, TestNG, Cucumber, Robot Framework), there are scenarios where using these formats is not an option like:<br>
 * Using a testing framework report that is not supported by Xray <br>
 * Having your own testing framework<br>
 * Limited support of existing formats to import detailed execution results back into Jira<br>
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @see <a href="https://docs.getxray.app/display/XRAYCLOUD/Using+Xray+JSON+format+to+import+execution+results#UsingXrayJSONformattoimportexecutionresults-Overview">
 * Using Xray JSON format to import execution results - info</a>
 * @since 0.0.2 (Jan 2022)
 */
public class XrayJsonFormatObject extends XrayJsonFormat implements JsonObject {

    private final Map<String, Object> fields;

    public @Nullable String getTestExecutionKey() {
        return (String) fields.get("testExecutionKey");
    }

    public @Nullable XrayJsonInfoObject getInfo() {
        return (XrayJsonInfoObject) fields.get("info");
    }

    public @Nullable List<XrayJsonTestObject> getTests() {
        return (List<XrayJsonTestObject>) fields.get("tests");
    }

    private XrayJsonFormatObject(XrayJsonFormatObjectBuilder builder) {
        this.fields = builder.fields;
    }


    @SuppressWarnings("unchecked")
    @Override
    public JSONObject asJsonObject() {
        JSONObject obj = new JSONObject();

        if (this.getTestExecutionKey() != null)
            obj.put("testExecutionKey", this.getTestExecutionKey());

        if (this.getInfo() != null)
            obj.put("info", this.getInfo().asJsonObject());

        if (this.getTests() != null) {
            JSONArray arr = new JSONArray();
            for (XrayJsonTestObject test : this.getTests()) {
                arr.add(test.asJsonObject());
            }
            if (!arr.isEmpty())
                obj.put("tests", arr);
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
    public static class XrayJsonFormatObjectBuilder implements Builder {

        private final Map<String, Object> fields;

        public XrayJsonFormatObjectBuilder(XrayJsonFormatObject obj) {
            this.fields = obj.fields;
        }

        public XrayJsonFormatObjectBuilder() {
            fields = new HashMap<>();
        }

        public XrayJsonFormatObjectBuilder testExecutionKey(String testExecutionKey) {
            fields.put("testExecutionKey", testExecutionKey);
            return this;
        }

        public XrayJsonFormatObjectBuilder info(XrayJsonInfoObject info) {
            fields.put("info", info);
            return this;
        }

        public XrayJsonFormatObjectBuilder tests(List<XrayJsonTestObject> tests) {
            fields.put("tests", tests);
            return this;
        }

        public XrayJsonFormatObjectBuilder test(XrayJsonTestObject test) {
            if (fields.get("tests") !=null){
                List<XrayJsonTestObject> testList = ((List<XrayJsonTestObject>) fields.get("tests"));
                testList.add(test);
                fields.put("tests", testList);
            }
            else{
                List<XrayJsonTestObject> testList = new ArrayList<>();
                testList.add(test);
                fields.put("tests", testList);
            }
            return this;
        }


        public XrayJsonFormatObject build() {
            XrayJsonFormatObject info = new XrayJsonFormatObject(this);
            validateXrayFormatObject(info);
            return info;
        }

        private void validateXrayFormatObject(XrayJsonFormatObject obj) {
            // TOOD: some validation code here
        }
    }
}
