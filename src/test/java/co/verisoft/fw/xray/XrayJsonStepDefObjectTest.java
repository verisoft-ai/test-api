package co.verisoft.fw.xray;

import lombok.Synchronized;
import org.assertj.core.api.SoftAssertions;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class XrayJsonStepDefObjectTest {

    @ParameterizedTest
    @MethodSource("getXrayStepDefObject")
    public void shouldBuildAllFieldsCorrectly(XrayJsonStepDefObject info){

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(info.getAction()).isEqualTo("action");
        softAssertions.assertThat(info.getData()).isEqualTo("data");
        softAssertions.assertThat(info.getResult()).isEqualTo("result");
        softAssertions.assertThat(info.getCustomFields().get("cust1")).isEqualTo("cust1");
        softAssertions.assertThat(info.getCustomFields().get("cust2")).isEqualTo("cust2");
        softAssertions.assertAll();
    }

    @ParameterizedTest
    @MethodSource("getXrayStepDefObject")
    public void shouldAllowToChangeValue(XrayJsonStepDefObject infoBase){

        XrayJsonStepDefObject info = new XrayJsonStepDefObject.XrayJsonStepDefObjectBuilder(infoBase)
                .action("action2")
                .build();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(info.getAction()).isEqualTo("action2");
        softAssertions.assertThat(info.getData()).isEqualTo("data");
        softAssertions.assertThat(info.getResult()).isEqualTo("result");
        softAssertions.assertThat(info.getCustomFields().get("cust1")).isEqualTo("cust1");
        softAssertions.assertThat(info.getCustomFields().get("cust2")).isEqualTo("cust2");
        softAssertions.assertAll();
    }

    @ParameterizedTest
    @MethodSource("getXrayStepDefObject")
    public void shouldCreateAJsonObject(XrayJsonStepDefObject info){
        JSONObject obj = info.asJsonObject();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(obj.get("action")).isEqualTo(info.getAction());
        softAssertions.assertThat(obj.get("data")).isEqualTo(info.getData());
        softAssertions.assertThat(obj.get("result")).isEqualTo(info.getResult());
        softAssertions.assertThat(obj.get("cust1")).isEqualTo(info.getCustomField("cust1"));
        softAssertions.assertThat(obj.get("cust2")).isEqualTo(info.getCustomField("cust2"));

        softAssertions.assertAll();

    }

    @ParameterizedTest
    @MethodSource("getXrayStepDefObject")
    public void shouldCreateAStringObject(XrayJsonStepDefObject info) throws ParseException {
        String objString = info.asString();
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(objString);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(obj.get("action")).isEqualTo(info.getAction());
        softAssertions.assertThat(obj.get("data")).isEqualTo(info.getData());
        softAssertions.assertThat(obj.get("result")).isEqualTo(info.getResult());
        softAssertions.assertThat(obj.get("cust1")).isEqualTo(info.getCustomField("cust1"));
        softAssertions.assertThat(obj.get("cust2")).isEqualTo(info.getCustomField("cust2"));

        softAssertions.assertAll();

    }


    @Synchronized
    private static Stream<XrayJsonStepDefObject> getXrayStepDefObject(){
        XrayJsonStepDefObject stepDef = new XrayJsonStepDefObject.XrayJsonStepDefObjectBuilder()
                .action("action")
                .data("data")
                .result("result")
                .addCustomField("cust1", "cust1")
                .addCustomField("cust2", "cust2")
                .build();

        return Stream.of(stepDef);
    }
}
