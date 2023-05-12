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

import java.util.stream.Stream;

@SuppressWarnings("ALL")
@Execution(ExecutionMode.CONCURRENT)
@ExtendWith({CustomReportPortalExtension.class})
public class XrayJsonIterationObjectTest {

    @ParameterizedTest
    @MethodSource("getXrayIterationObject")
    public void shouldBuildAllFieldsCorrectly(XrayJsonIterationObject info) {

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(info.getName()).isEqualTo("name");
        softAssertions.assertThat(info.getParameters().get(0).getName()).isEqualTo("name1");
        softAssertions.assertThat(info.getLog()).isEqualTo("log");
        softAssertions.assertThat(info.getDuration()).isEqualTo("duration");
        softAssertions.assertThat(info.getStatusAsString()).isEqualTo("PASSED");
        softAssertions.assertThat(info.getSteps().get(0).getActualResult()).isEqualTo("result1");
        softAssertions.assertAll();
    }

    @ParameterizedTest
    @MethodSource("getXrayIterationObject")
    public void shouldAllowToChangeValue(XrayJsonIterationObject infoBase) {

        XrayJsonIterationObject info = new XrayJsonIterationObject.XrayJsonIterationObjectBuilder(infoBase)
                .name("name2")
                .build();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(info.getName()).isEqualTo("name2");
        softAssertions.assertThat(info.getParameters().get(0).getName()).isEqualTo("name1");
        softAssertions.assertThat(info.getLog()).isEqualTo("log");
        softAssertions.assertThat(info.getDuration()).isEqualTo("duration");
        softAssertions.assertThat(info.getStatusAsString()).isEqualTo("PASSED");
        softAssertions.assertThat(info.getSteps().get(0).getActualResult()).isEqualTo("result1");
        softAssertions.assertAll();
    }

    @ParameterizedTest
    @MethodSource("getXrayIterationObject")
    public void shouldCreateAJsonObject(XrayJsonIterationObject info) {
        JSONObject obj = info.asJsonObject();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(obj.get("name")).isEqualTo(info.getName());
        softAssertions.assertThat(((JSONObject) ((JSONArray) obj.get("parameters")).get(0)).get("name").toString()).isEqualTo("name1");
        softAssertions.assertThat(obj.get("log")).isEqualTo(info.getLog());
        softAssertions.assertThat(obj.get("duration")).isEqualTo(info.getDuration());
        softAssertions.assertThat(obj.get("status")).isEqualTo(info.getStatus().toString());
        softAssertions.assertThat(((JSONObject) ((JSONArray) obj.get("steps")).get(0)).get("defects").toString()).isEqualTo("defects1");

        softAssertions.assertAll();

    }

    @ParameterizedTest
    @MethodSource("getXrayIterationObject")
    public void shouldCreateAStringObject(XrayJsonIterationObject info) throws ParseException {
        String objString = info.asString();
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(objString);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(obj.get("name")).isEqualTo(info.getName());
        softAssertions.assertThat(((JSONObject) ((JSONArray) obj.get("parameters")).get(0)).get("name").toString()).isEqualTo("name1");
        softAssertions.assertThat(obj.get("log")).isEqualTo(info.getLog());
        softAssertions.assertThat(obj.get("duration")).isEqualTo(info.getDuration());
        softAssertions.assertThat(obj.get("status")).isEqualTo(info.getStatus().toString());
        softAssertions.assertThat(((JSONObject) ((JSONArray) obj.get("steps")).get(0)).get("defects").toString()).isEqualTo("defects1");

        softAssertions.assertAll();

    }


    @Synchronized
    private static Stream<XrayJsonIterationObject> getXrayIterationObject() {

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

        return Stream.of(iteration);
    }
}
