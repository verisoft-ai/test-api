package co.verisoft.fw.xray;

import lombok.Synchronized;
import org.assertj.core.api.SoftAssertions;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class XrayJsonCustomFieldObjectTest {

    @ParameterizedTest
    @MethodSource("getXrayCustomFieldObject")
    public void shouldBuildAllFieldsCorrectly(XrayJsonCustomFieldObject info){

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(info.getId()).isEqualTo("id");
        softAssertions.assertThat(info.getName()).isEqualTo("name");
        softAssertions.assertThat(info.getValue()).isEqualTo("value");
        softAssertions.assertAll();
    }

    @ParameterizedTest
    @MethodSource("getXrayCustomFieldObject")
    public void shouldAllowToChangeValue(XrayJsonCustomFieldObject infoBase){

        XrayJsonCustomFieldObject info = new XrayJsonCustomFieldObject.XrayJsonCustomFieldObjectBuilder(infoBase)
                .id("id2")
                .build();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(info.getId()).isEqualTo("id2");
        softAssertions.assertThat(info.getName()).isEqualTo("name");
        softAssertions.assertThat(info.getValue()).isEqualTo("value");
        softAssertions.assertAll();
    }

    @ParameterizedTest
    @MethodSource("getXrayCustomFieldObject")
    public void shouldCreateAJsonObject(XrayJsonCustomFieldObject info){
        JSONObject obj = info.asJsonObject();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(obj.get("id")).isEqualTo(info.getId());
        softAssertions.assertThat(obj.get("name")).isEqualTo(info.getName());
        softAssertions.assertThat(obj.get("value")).isEqualTo(info.getValue());

        softAssertions.assertAll();

    }

    @ParameterizedTest
    @MethodSource("getXrayCustomFieldObject")
    public void shouldCreateAStringObject(XrayJsonCustomFieldObject info) throws ParseException {
        String objString = info.asString();
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(objString);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(obj.get("id")).isEqualTo(info.getId());
        softAssertions.assertThat(obj.get("name")).isEqualTo(info.getName());
        softAssertions.assertThat(obj.get("value")).isEqualTo(info.getValue());

        softAssertions.assertAll();

    }



    @Synchronized
    private static Stream<XrayJsonCustomFieldObject> getXrayCustomFieldObject(){
        XrayJsonCustomFieldObject custField = new XrayJsonCustomFieldObject.XrayJsonCustomFieldObjectBuilder()
                .id("id")
                .name("name")
                .value("value")
                .build();

        return Stream.of(custField);
    }
}
