/*
 * (C) Copyright 2023 VeriSoft (http://www.verisoft.co)
 *
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
package co.verisoft.fw.xray;

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

import java.time.ZonedDateTime;
import java.util.stream.Stream;

@SuppressWarnings("ALL")
@Execution(ExecutionMode.CONCURRENT)
@ExtendWith({CustomReportPortalExtension.class})
public class XrayJsonInfoObjectTest {

    @ParameterizedTest
    @MethodSource("getXrayInfoObject")
    public void shouldBuildAllFieldsCorrectly(XrayJsonInfoObject info) {

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(info.getProject()).isEqualTo("project");
        softAssertions.assertThat(info.getSummary()).isEqualTo("summary");
        softAssertions.assertThat(info.getDescription()).isEqualTo("description");
        softAssertions.assertThat(info.getVersion()).isEqualTo("version");
        softAssertions.assertThat(info.getRevision()).isEqualTo("revision");
        softAssertions.assertThat(info.getUser()).isEqualTo("user");
        softAssertions.assertThat(info.getStartDate()).isEqualTo("2022-01-06T11:44:11+02");
        softAssertions.assertThat(info.getFinishDate()).isEqualTo("2022-01-06T11:44:24+02");
        softAssertions.assertThat(info.getTestPlanKey()).isEqualTo("testPlanKey");
        softAssertions.assertThat(info.getTestEnvironments()).isEqualTo("testEnvironments");
        softAssertions.assertAll();
    }

    @ParameterizedTest
    @MethodSource("getXrayInfoObject")
    public void shouldAllowToChangeValue(XrayJsonInfoObject infoBase) {

        XrayJsonInfoObject info = new XrayJsonInfoObject.XrayInfoObjectBuilder(infoBase)
                .project("project2")
                .build();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(info.getProject()).isEqualTo("project2");
        softAssertions.assertThat(info.getSummary()).isEqualTo("summary");
        softAssertions.assertThat(info.getDescription()).isEqualTo("description");
        softAssertions.assertThat(info.getVersion()).isEqualTo("version");
        softAssertions.assertThat(info.getRevision()).isEqualTo("revision");
        softAssertions.assertThat(info.getUser()).isEqualTo("user");
        softAssertions.assertThat(info.getStartDate()).isEqualTo("2022-01-06T11:44:11+02");
        softAssertions.assertThat(info.getFinishDate()).isEqualTo("2022-01-06T11:44:24+02");
        softAssertions.assertThat(info.getTestPlanKey()).isEqualTo("testPlanKey");
        softAssertions.assertThat(info.getTestEnvironments()).isEqualTo("testEnvironments");
        softAssertions.assertAll();
    }

    @ParameterizedTest
    @MethodSource("getXrayInfoObject")
    public void shouldCreateAJsonObject(XrayJsonInfoObject info) {
        JSONObject obj = info.asJsonObject();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(obj.get("project")).isEqualTo(info.getProject());
        softAssertions.assertThat(obj.get("summary")).isEqualTo(info.getSummary());
        softAssertions.assertThat(obj.get("description")).isEqualTo(info.getDescription());
        softAssertions.assertThat(obj.get("version")).isEqualTo(info.getVersion());
        softAssertions.assertThat(obj.get("revision")).isEqualTo(info.getRevision());
        softAssertions.assertThat(obj.get("user")).isEqualTo(info.getUser());
        softAssertions.assertThat(obj.get("startDate")).isEqualTo(info.getStartDate());
        softAssertions.assertThat(obj.get("finishDate")).isEqualTo(info.getFinishDate());
        softAssertions.assertThat(obj.get("testPlanKey")).isEqualTo(info.getTestPlanKey());
        softAssertions.assertThat(obj.get("testEnvironments")).isEqualTo(info.getTestEnvironments());

        softAssertions.assertAll();

    }

    @ParameterizedTest
    @MethodSource("getXrayInfoObject")
    public void shouldCreateAStringObject(XrayJsonInfoObject info) throws ParseException {
        String objString = info.asString();
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(objString);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(obj.get("project")).isEqualTo(info.getProject());
        softAssertions.assertThat(obj.get("summary")).isEqualTo(info.getSummary());
        softAssertions.assertThat(obj.get("description")).isEqualTo(info.getDescription());
        softAssertions.assertThat(obj.get("version")).isEqualTo(info.getVersion());
        softAssertions.assertThat(obj.get("revision")).isEqualTo(info.getRevision());
        softAssertions.assertThat(obj.get("user")).isEqualTo(info.getUser());
        softAssertions.assertThat(obj.get("startDate")).isEqualTo(info.getStartDate());
        softAssertions.assertThat(obj.get("finishDate")).isEqualTo(info.getFinishDate());
        softAssertions.assertThat(obj.get("testPlanKey")).isEqualTo(info.getTestPlanKey());
        softAssertions.assertThat(obj.get("testEnvironments")).isEqualTo(info.getTestEnvironments());

        softAssertions.assertAll();

    }


    @Synchronized
    private static Stream<XrayJsonInfoObject> getXrayInfoObject() {
        XrayJsonInfoObject infoBase = new XrayJsonInfoObject.XrayInfoObjectBuilder()
                .project("project")
                .summary("summary")
                .description("description")
                .version("version")
                .revision("revision")
                .user("user")
                .startDate(ZonedDateTime.parse("2022-01-06T11:44:11+02"))
                .finishDate(ZonedDateTime.parse("2022-01-06T11:44:24+02"))
                .testPlanKey("testPlanKey")
                .testEnvironments("testEnvironments")
                .build();

        return Stream.of(infoBase);
    }
}
