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

import org.assertj.core.api.SoftAssertions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@SuppressWarnings("ALL")
@Execution(ExecutionMode.CONCURRENT)
public class XrayJsonTestInfoObjectTest {

    @ParameterizedTest
    @MethodSource("getXrayTestInfoObject")
    public void shouldBuildAllFieldsCorrectly(XrayJsonTestInfoObject info) {

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(info.getProjectKey()).isEqualTo("projectKey1");
        softAssertions.assertThat(info.getSummary()).isEqualTo("summary1");
        softAssertions.assertThat(info.getType().toString()).isEqualTo(TestType.GENERIC.toString());
        softAssertions.assertThat(info.getType()).isEqualTo(TestType.GENERIC);
        softAssertions.assertThat(info.getRequirementKeys().get(0)).isEqualTo("key1");
        softAssertions.assertThat(info.getLabels()).isEqualTo("label1");
        softAssertions.assertThat(info.getSteps().get(0).getResult()).isEqualTo("result1");
        softAssertions.assertThat(info.getSteps().get(0).getCustomFields().get(0).getId()).isEqualTo("custId1");
        softAssertions.assertThat(info.getScenario()).isEqualTo("scenario1");
        softAssertions.assertThat(info.getDefinition()).isEqualTo("def1");

        softAssertions.assertAll();
    }

    @ParameterizedTest
    @MethodSource("getXrayTestInfoObject")
    public void shouldAllowToChangeValue(XrayJsonTestInfoObject infoBase) {

        XrayJsonTestInfoObject info = new XrayJsonTestInfoObject.XrayJsonTestInfoObjectBuilder(infoBase)
                .projectKey("projectKey2")
                .build();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(info.getProjectKey()).isEqualTo("projectKey2");
        softAssertions.assertThat(info.getSummary()).isEqualTo("summary1");
        softAssertions.assertThat(info.getType().toString()).isEqualTo(TestType.GENERIC.toString());
        softAssertions.assertThat(info.getType()).isEqualTo(TestType.GENERIC);
        softAssertions.assertThat(info.getRequirementKeys().get(0)).isEqualTo("key1");
        softAssertions.assertThat(info.getLabels()).isEqualTo("label1");
        softAssertions.assertThat(info.getSteps().get(0).getResult()).isEqualTo("result1");
        softAssertions.assertThat(info.getSteps().get(0).getCustomFields().get(0).getId()).isEqualTo("custId1");
        softAssertions.assertThat(info.getScenario()).isEqualTo("scenario1");
        softAssertions.assertThat(info.getDefinition()).isEqualTo("def1");

        softAssertions.assertAll();
    }

    @ParameterizedTest
    @MethodSource("getXrayTestInfoObject")
    public void shouldCreateAJsonObject(XrayJsonTestInfoObject info) {
        JSONObject obj = info.asJsonObject();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(obj.get("projectKey")).isEqualTo(info.getProjectKey());
        softAssertions.assertThat(obj.get("summary")).isEqualTo(info.getSummary());
        softAssertions.assertThat(obj.get("type")).isEqualTo(info.getTypeAsString());
        softAssertions.assertThat(((JSONArray) obj.get("requirementKeys")).get(0)).isEqualTo("key1");
        softAssertions.assertThat(obj.get("labels")).isEqualTo(info.getLabels());
        softAssertions.assertThat(((JSONObject) ((JSONArray) obj.get("steps")).get(0)).get("result")).isEqualTo("result1");
        softAssertions.assertThat(obj.get("scenario")).isEqualTo(info.getScenario());
        softAssertions.assertThat(obj.get("definition")).isEqualTo(info.getDefinition());

        softAssertions.assertAll();
    }

    @ParameterizedTest
    @MethodSource("getXrayTestInfoObject")
    public void shouldCreateAStringObject(XrayJsonTestInfoObject info) throws ParseException {
        String objString = info.asString();
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(objString);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(obj.get("projectKey")).isEqualTo(info.getProjectKey());
        softAssertions.assertThat(obj.get("summary")).isEqualTo(info.getSummary());
        softAssertions.assertThat(obj.get("type")).isEqualTo(info.getTypeAsString());
        softAssertions.assertThat(((JSONArray) obj.get("requirementKeys")).get(0)).isEqualTo("key1");
        softAssertions.assertThat(obj.get("labels")).isEqualTo(info.getLabels());
        softAssertions.assertThat(((JSONObject) ((JSONArray) obj.get("steps")).get(0)).get("result")).isEqualTo("result1");
        softAssertions.assertThat(obj.get("scenario")).isEqualTo(info.getScenario());
        softAssertions.assertThat(obj.get("definition")).isEqualTo(info.getDefinition());

        softAssertions.assertAll();

    }

    private static Stream<XrayJsonTestInfoObject> getXrayTestInfoObject() {

        XrayJsonCustomFieldObject cust1 = new XrayJsonCustomFieldObject.XrayJsonCustomFieldObjectBuilder()
                .id("custId1")
                .name("custName1")
                .value("custValue1")
                .build();

        XrayJsonStepDefObject step1 = new XrayJsonStepDefObject.XrayJsonStepDefObjectBuilder()
                .action("action1")
                .result("result1")
                .customField(cust1)
                .data("data1")
                .build();

        XrayJsonTestInfoObject testInfo = new XrayJsonTestInfoObject.XrayJsonTestInfoObjectBuilder()
                .projectKey("projectKey1")
                .summary("summary1")
                .type("generic")
                .requirementKey("key1")
                .labels("label1")
                .step(step1)
                .scenario("scenario1")
                .definition("def1")
                .build();

        return Stream.of(testInfo);
    }
}
