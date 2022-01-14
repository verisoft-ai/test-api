package co.verisoft.fw.xray;

import lombok.Synchronized;
import org.assertj.core.api.SoftAssertions;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class XrayJsonParameterObjectTest {

    @ParameterizedTest
    @MethodSource("getXrayParameterObject")
    public void shouldBuildAllFieldsCorrectly(XrayJsonParameterObject info){

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(info.getName()).isEqualTo("name");
        softAssertions.assertThat(info.getValue()).isEqualTo("value");
        softAssertions.assertAll();
    }

    @ParameterizedTest
    @MethodSource("getXrayParameterObject")
    public void shouldAllowToChangeValue(XrayJsonParameterObject infoBase){

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
    public void shouldCreateAJsonObject(XrayJsonParameterObject info){
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
    private static Stream<XrayJsonParameterObject> getXrayParameterObject(){
        XrayJsonParameterObject custField = new XrayJsonParameterObject.XrayJsonParameterObjectBuilder()
                .name("name")
                .value("value")
                .build();

        return Stream.of(custField);
    }
}
