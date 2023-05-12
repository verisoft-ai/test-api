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

import co.verisoft.fw.CustomReportPortalExtension;
import lombok.Synchronized;
import org.assertj.core.api.SoftAssertions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.ZonedDateTime;
import java.util.stream.Stream;

@SuppressWarnings("ALL")
@Execution(ExecutionMode.CONCURRENT)
@ExtendWith({CustomReportPortalExtension.class})
public class XrayJsonFormatObjectTest {

    @ParameterizedTest
    @MethodSource("getXrayFormatObject")
    public void shouldBuildAllFieldsCorrectly(XrayJsonFormatObject info) {

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(info.getTestExecutionKey()).isEqualTo("key");
        softAssertions.assertThat(info.getInfo().getDescription()).isEqualTo("description1");
        softAssertions.assertThat(info.getTests().get(0).getStatus()).isEqualTo(Status.PASSED);
        softAssertions.assertAll();
    }

    @ParameterizedTest
    @MethodSource("getXrayFormatObject")
    public void shouldAllowToChangeValue(XrayJsonFormatObject infoBase) {

        XrayJsonFormatObject info = new XrayJsonFormatObject.XrayJsonFormatObjectBuilder(infoBase)
                .testExecutionKey("testExecutionKey2")
                .build();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(info.getTestExecutionKey()).isEqualTo("testExecutionKey2");
        softAssertions.assertThat(info.getInfo().getDescription()).isEqualTo("description1");
        softAssertions.assertThat(info.getTests().get(0).getStatus().toString()).isEqualTo("PASSED");
        softAssertions.assertAll();
    }

    @ParameterizedTest
    @MethodSource("getXrayFormatObject")
    public void shouldCreateAJsonObject(XrayJsonFormatObject info) {
        JSONObject obj = info.asJsonObject();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(obj.get("testExecutionKey")).isEqualTo(info.getTestExecutionKey());
        softAssertions.assertThat(((JSONObject) obj.get("info")).get("summary")).isEqualTo(info.getInfo().getSummary());
        softAssertions.assertThat(((JSONObject) ((JSONObject) ((JSONArray) obj.get("tests")).get(0)).get("testInfo")).get("summary"))
                .isEqualTo(info.getTests().get(0).getTestInfo().getSummary());
        softAssertions.assertAll();

    }

    @ParameterizedTest
    @MethodSource("getXrayFormatObject")
    public void shouldCreateAStringObject(XrayJsonFormatObject info) throws ParseException {
        String objString = info.asString();
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(objString);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(obj.get("testExecutionKey")).isEqualTo(info.getTestExecutionKey());
        softAssertions.assertThat(((JSONObject) obj.get("info")).get("summary")).isEqualTo(info.getInfo().getSummary());
        softAssertions.assertThat(((JSONObject) ((JSONObject) ((JSONArray) obj.get("tests")).get(0)).get("testInfo")).get("summary"))
                .isEqualTo(info.getTests().get(0).getTestInfo().getSummary());
        softAssertions.assertAll();

    }


    @Synchronized
    private static Stream<XrayJsonFormatObject> getXrayFormatObject() {

        // Create JsonInfo
        XrayJsonInfoObject info = new XrayJsonInfoObject.XrayInfoObjectBuilder()
                .testEnvironments("env1")
                .testPlanKey("planKey1")
                .project("project1")
                .description("description1")
                .summary("summary1")
                .startDate(ZonedDateTime.parse("2022-01-06T11:44:11+02"))
                .finishDate(ZonedDateTime.parse("2022-01-06T11:44:11+02"))
                .user("user1")
                .version("version")
                .revision("revision1")
                .build();

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

        XrayJsonFormatObject obj = new XrayJsonFormatObject.XrayJsonFormatObjectBuilder()
                .test(infoBase)
                .info(info)
                .testExecutionKey("key")
                .build();

        return Stream.of(obj);
    }
}
