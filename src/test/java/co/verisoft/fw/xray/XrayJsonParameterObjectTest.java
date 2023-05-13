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

import java.util.stream.Stream;

@SuppressWarnings("ALL")
@Execution(ExecutionMode.CONCURRENT)
@ExtendWith({CustomReportPortalExtension.class})
public class XrayJsonParameterObjectTest {

    @ParameterizedTest
    @MethodSource("getXrayParameterObject")
    public void shouldBuildAllFieldsCorrectly(XrayJsonParameterObject info) {

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(info.getName()).isEqualTo("name");
        softAssertions.assertThat(info.getValue()).isEqualTo("value");
        softAssertions.assertAll();
    }

    @ParameterizedTest
    @MethodSource("getXrayParameterObject")
    public void shouldAllowToChangeValue(XrayJsonParameterObject infoBase) {

        XrayJsonParameterObject info = new XrayJsonParameterObject.XrayJsonParameterObjectBuilder(infoBase)
                .name("name2")
                .build();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(info.getName()).isEqualTo("name2");
        softAssertions.assertThat(info.getValue()).isEqualTo("value");
        softAssertions.assertAll();
    }

    @ParameterizedTest
    @MethodSource("getXrayParameterObject")
    public void shouldCreateAJsonObject(XrayJsonParameterObject info) {
        JSONObject obj = info.asJsonObject();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(obj.get("name")).isEqualTo(info.getName());
        softAssertions.assertThat(obj.get("value")).isEqualTo(info.getValue());

        softAssertions.assertAll();

    }

    @ParameterizedTest
    @MethodSource("getXrayParameterObject")
    public void shouldCreateAStringObject(XrayJsonParameterObject info) throws ParseException {
        String objString = info.asString();
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(objString);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(obj.get("name")).isEqualTo(info.getName());
        softAssertions.assertThat(obj.get("value")).isEqualTo(info.getValue());

        softAssertions.assertAll();

    }


    @Synchronized
    private static Stream<XrayJsonParameterObject> getXrayParameterObject() {
        XrayJsonParameterObject custField = new XrayJsonParameterObject.XrayJsonParameterObjectBuilder()
                .name("name")
                .value("value")
                .build();

        return Stream.of(custField);
    }
}
