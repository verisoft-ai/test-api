package co.verisoft.fw.xray;

import lombok.Synchronized;
import org.assertj.core.api.SoftAssertions;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class XrayJsonFormatObjectTest {

    @ParameterizedTest
    @MethodSource("getXrayFormatObject")
    public void shouldBuildAllFieldsCorrectly(XrayJsonFormatObject info){

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(info.getTestExecutionKey()).isEqualTo("testExecutionKey");
        softAssertions.assertThat(info.getInfo()).isEqualTo("info");
        softAssertions.assertThat(info.getTests()).isEqualTo("tests");
        softAssertions.assertAll();
    }

    @ParameterizedTest
    @MethodSource("getXrayFormatObject")
    public void shouldAllowToChangeValue(XrayJsonFormatObject infoBase){

        XrayJsonFormatObject info = new XrayJsonFormatObject.XrayJsonFormatObjectBuilder(infoBase)
                .testExecutionKey("testExecutionKey2")
                .build();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(info.getTestExecutionKey()).isEqualTo("testExecutionKey2");
        softAssertions.assertThat(info.getInfo()).isEqualTo("info");
        softAssertions.assertThat(info.getTests()).isEqualTo("tests");
        softAssertions.assertAll();
    }

    @ParameterizedTest
    @MethodSource("getXrayFormatObject")
    public void shouldCreateAJsonObject(XrayJsonFormatObject info){
        JSONObject obj = info.asJsonObject();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(obj.get("testExecutionKey")).isEqualTo(info.getTestExecutionKey());
        softAssertions.assertThat(obj.get("info")).isEqualTo(info.getInfo());
        softAssertions.assertThat(obj.get("tests")).isEqualTo(info.getTests());

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
        softAssertions.assertThat(obj.get("info")).isEqualTo(info.getInfo());
        softAssertions.assertThat(obj.get("tests")).isEqualTo(info.getTests());

        softAssertions.assertAll();

    }



    @Synchronized
    private static Stream<XrayJsonFormatObject> getXrayFormatObject(){
        XrayJsonFormatObject custField = new XrayJsonFormatObject.XrayJsonFormatObjectBuilder()
                .testExecutionKey("testExecutionKey")
                .info("info")
                .tests("tests")
                .build();

        return Stream.of(custField);
    }
}
