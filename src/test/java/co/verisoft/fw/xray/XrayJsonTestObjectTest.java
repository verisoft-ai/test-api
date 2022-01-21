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

import lombok.Synchronized;
import org.assertj.core.api.SoftAssertions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.ZonedDateTime;
import java.util.stream.Stream;

@SuppressWarnings("ALL")
@Execution(ExecutionMode.CONCURRENT)
public class XrayJsonTestObjectTest {

    @ParameterizedTest
    @MethodSource("getXrayTestObject")
    public void shouldBuildAllFieldsCorrectly(XrayJsonTestObject info) {

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(info.getTestKey()).isEqualTo("testKey");
        softAssertions.assertThat(info.getTestInfo().getDefinition()).isEqualTo("def1");
        softAssertions.assertThat(info.getStart()).isEqualTo("2022-01-06T11:21:11+02");
        softAssertions.assertThat(info.getFinish()).isEqualTo("2022-01-06T11:44:11+02");
        softAssertions.assertThat(info.getComment()).isEqualTo("comment");
        softAssertions.assertThat(info.getExecutedBy()).isEqualTo("executedBy");
        softAssertions.assertThat(info.getAssignee()).isEqualTo("assignee");
        softAssertions.assertThat(info.getStatus().toString()).isEqualTo("PASSED");
        softAssertions.assertThat(info.getSteps().get(0).getActualResult()).isEqualTo("actualResult");
        softAssertions.assertThat(info.getExamples()).isEqualTo("examples");
        softAssertions.assertThat(info.getIterations().get(0).getDuration()).isEqualTo("duration");
        softAssertions.assertThat(info.getDefects().get(0)).isEqualTo("defect1");
        softAssertions.assertThat(info.getEvidence().get(0).getFileName()).isEqualTo("fileName");
        softAssertions.assertThat(info.getCustomFields().get(0).getId()).isEqualTo("id");
        softAssertions.assertAll();
    }

    @ParameterizedTest
    @MethodSource("getXrayTestObject")
    public void shouldAllowToChangeValue(XrayJsonTestObject infoBase) {

        XrayJsonTestObject info = new XrayJsonTestObject.XrayJsonTestObjectBuilder(infoBase)
                .testKey("testKey2")
                .build();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(info.getTestKey()).isEqualTo("testKey2");
        softAssertions.assertThat(info.getTestInfo().getDefinition()).isEqualTo("def1");
        softAssertions.assertThat(info.getStart()).isEqualTo("2022-01-06T11:21:11+02");
        softAssertions.assertThat(info.getFinish()).isEqualTo("2022-01-06T11:44:11+02");
        softAssertions.assertThat(info.getComment()).isEqualTo("comment");
        softAssertions.assertThat(info.getExecutedBy()).isEqualTo("executedBy");
        softAssertions.assertThat(info.getAssignee()).isEqualTo("assignee");
        softAssertions.assertThat(info.getStatus().toString()).isEqualTo("PASSED");
        softAssertions.assertThat(info.getSteps().get(0).getActualResult()).isEqualTo("actualResult");
        softAssertions.assertThat(info.getExamples()).isEqualTo("examples");
        softAssertions.assertThat(info.getIterations().get(0).getDuration()).isEqualTo("duration");
        softAssertions.assertThat(info.getDefects().get(0)).isEqualTo("defect1");
        softAssertions.assertThat(info.getEvidence().get(0).getFileName()).isEqualTo("fileName");
        softAssertions.assertThat(info.getCustomFields().get(0).getId()).isEqualTo("id");
        softAssertions.assertAll();
    }

    @ParameterizedTest
    @MethodSource("getXrayTestObject")
    public void shouldCreateAJsonObject(XrayJsonTestObject info) {
        JSONObject obj = info.asJsonObject();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(obj.get("testKey")).isEqualTo(info.getTestKey());
        softAssertions.assertThat(((JSONObject) obj.get("testInfo")).get("projectKey")).isEqualTo(info.getTestInfo().getProjectKey());
        softAssertions.assertThat(obj.get("start")).isEqualTo(info.getStart());
        softAssertions.assertThat(obj.get("finish")).isEqualTo(info.getFinish());
        softAssertions.assertThat(obj.get("comment")).isEqualTo(info.getComment());
        softAssertions.assertThat(obj.get("executedBy")).isEqualTo(info.getExecutedBy());
        softAssertions.assertThat(obj.get("assignee")).isEqualTo(info.getAssignee());
        softAssertions.assertThat(obj.get("status").toString()).isEqualTo(info.getStatus().toString());
        softAssertions.assertThat(((JSONObject) ((JSONArray) obj.get("steps")).get(0))).isEqualTo(info.getSteps().get(0).asJsonObject());
        softAssertions.assertThat(obj.get("examples")).isEqualTo(info.getExamples());
        softAssertions.assertThat(((JSONObject) ((JSONArray) obj.get("iterations")).get(0)))
                .isEqualTo(info.getIterations().get(0).asJsonObject());
        softAssertions.assertThat(((String) ((JSONArray) obj.get("defects")).get(0)))
                .isEqualTo(info.getDefects().get(0));
         softAssertions.assertThat(((JSONObject) ((JSONArray) obj.get("evidences")).get(0)))
                 .isEqualTo(info.getEvidence().get(0).asJsonObject());
        softAssertions.assertThat(((JSONObject) ((JSONArray) obj.get("customFields")).get(0)))
                .isEqualTo(info.getCustomFields().get(0).asJsonObject());

        softAssertions.assertAll();

    }

    @ParameterizedTest
    @MethodSource("getXrayTestObject")
    public void shouldCreateAStringObject(XrayJsonTestObject info) throws ParseException {
        String objString = info.asString();
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(objString);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(obj.get("testKey")).isEqualTo(info.getTestKey());
        softAssertions.assertThat(obj.get("testInfo")).isEqualTo(info.getTestInfo().asJsonObject());
        softAssertions.assertThat(obj.get("start")).isEqualTo(info.getStart());
        softAssertions.assertThat(obj.get("finish")).isEqualTo(info.getFinish());
        softAssertions.assertThat(obj.get("comment")).isEqualTo(info.getComment());
        softAssertions.assertThat(obj.get("executedBy")).isEqualTo(info.getExecutedBy());
        softAssertions.assertThat(obj.get("assignee")).isEqualTo(info.getAssignee());
        softAssertions.assertThat(obj.get("status")).isEqualTo(info.getStatus().toString());
        softAssertions.assertThat(((JSONObject) ((JSONArray) obj.get("steps")).get(0)))
                .isEqualTo(info.getSteps().get(0).asJsonObject());
        softAssertions.assertThat(obj.get("examples")).isEqualTo(info.getExamples());
        softAssertions.assertThat(((JSONObject) ((JSONArray) obj.get("iterations")).get(0)))
                .isEqualTo(info.getIterations().get(0).asJsonObject());
        softAssertions.assertThat(((String) ((JSONArray) obj.get("defects")).get(0)))
                .isEqualTo(info.getDefects().get(0));
        softAssertions.assertThat(((JSONObject) ((JSONArray) obj.get("evidences")).get(0)))
                .isEqualTo(info.getEvidence().get(0).asJsonObject());
        softAssertions.assertThat(((JSONObject) ((JSONArray) obj.get("customFields")).get(0))).isEqualTo(info.getCustomFields().get(0).asJsonObject());

        softAssertions.assertAll();

    }


    @Synchronized
    private static Stream<XrayJsonTestObject> getXrayTestObject() {

        // Create Test info
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


        // Create step result
        XrayJsonEvidenceObject evidenc1 = new XrayJsonEvidenceObject.XrayJsonEvidenceObjectBuilder()
                .data("data1")
                .contentType("contentType1")
                .filename("fileName1")
                .build();

        XrayJsonEvidenceObject evidenc2 = new XrayJsonEvidenceObject.XrayJsonEvidenceObjectBuilder()
                .data("data2")
                .contentType("contentType2")
                .filename("fileName2")
                .build();

        XrayJsonStepResultObject stepResult = new XrayJsonStepResultObject.XrayJsonStepResultObjectBuilder()
                .status("passed")
                .comment("comment")
                .actualResult("actualResult")
                .evidence(evidenc1)
                .evidence(evidenc2)
                .defects("defects")
                .build();


        // Create iteration
        XrayJsonParameterObject param1 = new XrayJsonParameterObject.XrayJsonParameterObjectBuilder()
                .name("name1")
                .value("value1")
                .build();

        XrayJsonStepResultObject result1 = new XrayJsonStepResultObject.XrayJsonStepResultObjectBuilder()
                .comment("comment1")
                .status("passed")
                .actualResult("result1")
                .defects("defects1")
                .build();

        XrayJsonIterationObject iteration = new XrayJsonIterationObject.XrayJsonIterationObjectBuilder()
                .name("name")
                .parameter(param1)
                .log("log")
                .duration("duration")
                .status("passed")
                .step(result1)
                .build();

        // Create Evidence
        XrayJsonEvidenceObject evidence = new XrayJsonEvidenceObject.XrayJsonEvidenceObjectBuilder()
                .data("data")
                .filename("fileName")
                .contentType("contentType")
                .build();

        // Create custom field
        XrayJsonCustomFieldObject custField = new XrayJsonCustomFieldObject.XrayJsonCustomFieldObjectBuilder()
                .id("id")
                .name("name")
                .value("value")
                .build();

        XrayJsonTestObject infoBase = new XrayJsonTestObject.XrayJsonTestObjectBuilder()
                .testKey("testKey")
                .testInfo(testInfo)
                .start(ZonedDateTime.parse("2022-01-06T11:21:11+02"))
                .finish(ZonedDateTime.parse("2022-01-06T11:44:11+02"))
                .comment("comment")
                .executedBy("executedBy")
                .assignee("assignee")
                .status("passed")
                .step(stepResult)
                .examples("examples")
                .iteration(iteration)
                .defect("defect1")
                .evidence(evidence)
                .customField(custField)
                .build();
        return Stream.of(infoBase);
    }
}
