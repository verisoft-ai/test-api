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

import java.util.stream.Stream;

@SuppressWarnings("ALL")
@Execution(ExecutionMode.CONCURRENT)
public class XrayJsonStepResultObjectTest {

    @ParameterizedTest
    @MethodSource("getXrayStepResultObject")
    public void shouldBuildAllFieldsCorrectly(XrayJsonStepResultObject info) {

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(info.getStatusAsString()).isEqualTo("PASSED");
        softAssertions.assertThat(info.getStatus()).isEqualTo(Status.PASSED);
        softAssertions.assertThat(info.getComment()).isEqualTo("comment");
        softAssertions.assertThat(info.getActualResult()).isEqualTo("actualResult");
        softAssertions.assertThat((info.getEvidences().get(0)).getFileName()).isEqualTo("fileName1");
        softAssertions.assertThat((info.getEvidences().get(1)).getFileName()).isEqualTo("fileName2");
        softAssertions.assertThat(info.getDefects()).isEqualTo("defects");
        softAssertions.assertAll();
    }

    @ParameterizedTest
    @MethodSource("getXrayStepResultObject")
    public void shouldAllowToChangeValue(XrayJsonStepResultObject stepResult) {

        XrayJsonStepResultObject info = new XrayJsonStepResultObject.XrayJsonStepResultObjectBuilder(stepResult)
                .status("failed")
                .build();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(info.getStatusAsString()).isEqualTo("FAILED");
        softAssertions.assertThat(info.getStatus()).isEqualTo(Status.FAILED);
        softAssertions.assertThat(info.getComment()).isEqualTo("comment");
        softAssertions.assertThat(info.getActualResult()).isEqualTo("actualResult");
        softAssertions.assertThat((info.getEvidences().get(0)).getFileName()).isEqualTo("fileName1");
        softAssertions.assertThat(((XrayJsonEvidenceObject) info.getEvidences().get(1)).getFileName()).isEqualTo("fileName2");
        softAssertions.assertThat(info.getDefects()).isEqualTo("defects");
        softAssertions.assertAll();
    }

    @ParameterizedTest
    @MethodSource("getXrayStepResultObject")
    public void shouldCreateAJsonObject(XrayJsonStepResultObject info) {
        JSONObject obj = info.asJsonObject();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(obj.get("status")).isEqualTo(info.getStatusAsString());
        softAssertions.assertThat(obj.get("comment")).isEqualTo(info.getComment());
        softAssertions.assertThat(obj.get("actualResult")).isEqualTo(info.getActualResult());
        softAssertions.assertThat(((JSONObject) ((JSONArray) obj.get("evidences")).get(0)).get("filename")).isEqualTo("fileName1");
        softAssertions.assertThat(((JSONObject) ((JSONArray) obj.get("evidences")).get(1)).get("filename")).isEqualTo("fileName2");
        softAssertions.assertThat(obj.get("defects")).isEqualTo(info.getDefects());

        softAssertions.assertAll();

    }

    @ParameterizedTest
    @MethodSource("getXrayStepResultObject")
    public void shouldCreateAStringObject(XrayJsonStepResultObject info) throws ParseException {
        String objString = info.asString();
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(objString);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(obj.get("status")).isEqualTo(info.getStatusAsString());
        softAssertions.assertThat(obj.get("comment")).isEqualTo(info.getComment());
        softAssertions.assertThat(obj.get("actualResult")).isEqualTo(info.getActualResult());
        softAssertions.assertThat(((JSONObject) ((JSONArray) obj.get("evidences")).get(0)).get("filename")).isEqualTo("fileName1");
        softAssertions.assertThat(((JSONObject) ((JSONArray) obj.get("evidences")).get(1)).get("filename")).isEqualTo("fileName2");
        softAssertions.assertThat(obj.get("defects")).isEqualTo(info.getDefects());

        softAssertions.assertAll();

    }


    @Synchronized
    private static Stream<XrayJsonStepResultObject> getXrayStepResultObject() {
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

        XrayJsonStepResultObject result = new XrayJsonStepResultObject.XrayJsonStepResultObjectBuilder()
                .status("passed")
                .comment("comment")
                .actualResult("actualResult")
                .evidence(evidenc1)
                .evidence(evidenc2)
                .defects("defects")
                .build();

        return Stream.of(result);
    }
}
