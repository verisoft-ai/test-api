package co.verisoft.fw.xray;

import lombok.Synchronized;
import org.assertj.core.api.SoftAssertions;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class XrayJsonIterationObjectTest {

    @ParameterizedTest
    @MethodSource("getXrayIterationObject")
    public void shouldBuildAllFieldsCorrectly(XrayJsonIterationObject info){

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(info.getName()).isEqualTo("name");
        softAssertions.assertThat(info.getParameters()).isEqualTo("parameters");
        softAssertions.assertThat(info.getLog()).isEqualTo("log");
        softAssertions.assertThat(info.getDuration()).isEqualTo("duration");
        softAssertions.assertThat(info.getStatus()).isEqualTo("status");
        softAssertions.assertThat(info.getSteps()).isEqualTo("steps");
        softAssertions.assertAll();
    }

    @ParameterizedTest
    @MethodSource("getXrayIterationObject")
    public void shouldAllowToChangeValue(XrayJsonIterationObject infoBase){

        XrayJsonIterationObject info = new XrayJsonIterationObject.XrayJsonIterationObjectBuilder(infoBase)
                .name("name2")
                .build();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(info.getName()).isEqualTo("name2");
        softAssertions.assertThat(info.getParameters()).isEqualTo("parameters");
        softAssertions.assertThat(info.getLog()).isEqualTo("log");
        softAssertions.assertThat(info.getDuration()).isEqualTo("duration");
        softAssertions.assertThat(info.getStatus()).isEqualTo("status");
        softAssertions.assertThat(info.getSteps()).isEqualTo("steps");
        softAssertions.assertAll();
    }

    @ParameterizedTest
    @MethodSource("getXrayIterationObject")
    public void shouldCreateAJsonObject(XrayJsonIterationObject info){
        JSONObject obj = info.asJsonObject();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(obj.get("name")).isEqualTo(info.getName());
        softAssertions.assertThat(obj.get("parameters")).isEqualTo(info.getParameters());
        softAssertions.assertThat(obj.get("log")).isEqualTo(info.getLog());
        softAssertions.assertThat(obj.get("duration")).isEqualTo(info.getDuration());
        softAssertions.assertThat(obj.get("status")).isEqualTo(info.getStatus());
        softAssertions.assertThat(obj.get("steps")).isEqualTo(info.getSteps());

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
        softAssertions.assertThat(obj.get("parameters")).isEqualTo(info.getParameters());
        softAssertions.assertThat(obj.get("log")).isEqualTo(info.getLog());
        softAssertions.assertThat(obj.get("duration")).isEqualTo(info.getDuration());
        softAssertions.assertThat(obj.get("status")).isEqualTo(info.getStatus());
        softAssertions.assertThat(obj.get("steps")).isEqualTo(info.getSteps());

        softAssertions.assertAll();

    }


    @Synchronized
    private static Stream<XrayJsonIterationObject> getXrayIterationObject(){
        XrayJsonIterationObject stepDef = new XrayJsonIterationObject.XrayJsonIterationObjectBuilder()
                .name("name")
                .parameters("parameters")
                .log("log")
                .duration("duration")
                .status("status")
                .steps("steps")
                .build();

        return Stream.of(stepDef);
    }
}
