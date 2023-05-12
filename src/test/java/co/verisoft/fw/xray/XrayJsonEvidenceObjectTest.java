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
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Base64;
import java.util.stream.Stream;

@SuppressWarnings("ALL")
@Execution(ExecutionMode.CONCURRENT)
@ExtendWith({CustomReportPortalExtension.class})
public class XrayJsonEvidenceObjectTest {

    @ParameterizedTest
    @MethodSource("getXrayEvidenceObject")
    public void shouldBuildAllFieldsCorrectly(XrayJsonEvidenceObject info) {

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(info.getData()).isEqualTo("data");
        softAssertions.assertThat(info.getFileName()).isEqualTo("fileName");
        softAssertions.assertThat(info.getContentType()).isEqualTo("contentType");
        softAssertions.assertAll();
    }

    @ParameterizedTest
    @MethodSource("getXrayEvidenceObject")
    public void shouldAllowToChangeValue(XrayJsonEvidenceObject infoBase) {

        XrayJsonEvidenceObject info = new XrayJsonEvidenceObject.XrayJsonEvidenceObjectBuilder(infoBase)
                .data("data2")
                .build();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(info.getData()).isEqualTo("data2");
        softAssertions.assertThat(info.getFileName()).isEqualTo("fileName");
        softAssertions.assertThat(info.getContentType()).isEqualTo("contentType");
        softAssertions.assertAll();
    }

    @ParameterizedTest
    @MethodSource("getXrayEvidenceObject")
    public void shouldCreateAJsonObject(XrayJsonEvidenceObject info) {
        JSONObject obj = info.asJsonObject();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(obj.get("data"))
                .isEqualTo(Base64.getEncoder().encodeToString(info.getData().getBytes()));
        softAssertions.assertThat(obj.get("filename")).isEqualTo(info.getFileName());
        softAssertions.assertThat(obj.get("contentType")).isEqualTo(info.getContentType());

        softAssertions.assertAll();

    }

    @ParameterizedTest
    @MethodSource("getXrayEvidenceObject")
    public void shouldCreateAStringObject(XrayJsonEvidenceObject info) throws ParseException {
        String objString = info.asString();
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(objString);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(obj.get("data"))
                .isEqualTo(Base64.getEncoder().encodeToString(info.getData().getBytes()));
        softAssertions.assertThat(obj.get("filename")).isEqualTo(info.getFileName());
        softAssertions.assertThat(obj.get("contentType")).isEqualTo(info.getContentType());

        softAssertions.assertAll();

    }


    @Synchronized
    private static Stream<XrayJsonEvidenceObject> getXrayEvidenceObject() {
        XrayJsonEvidenceObject evidence = new XrayJsonEvidenceObject.XrayJsonEvidenceObjectBuilder()
                .data("data")
                .filename("fileName")
                .contentType("contentType")
                .build();

        return Stream.of(evidence);
    }
}
