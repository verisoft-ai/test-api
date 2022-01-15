package co.verisoft.fw.xray;

import lombok.Synchronized;
import org.assertj.core.api.SoftAssertions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class XrayJsonStepDefObjectTest {

    @ParameterizedTest
    @MethodSource("getXrayStepDefObject")
    public void shouldBuildAllFieldsCorrectly(XrayJsonStepDefObject info){

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(info.getAction()).isEqualTo("action");
        softAssertions.assertThat(info.getData()).isEqualTo("data");
        softAssertions.assertThat(info.getResult()).isEqualTo("result");
        softAssertions.assertThat(info.getCustomField("custId1").getValue()).isEqualTo("custValue1");
        softAssertions.assertThat(info.getCustomField("custId2").getValue()).isEqualTo("custValue2");
        softAssertions.assertAll();
    }

    @ParameterizedTest
    @MethodSource("getXrayStepDefObject")
    public void shouldReturnNullIfNoCustoField(XrayJsonStepDefObject info){

        assertThat(info.getCustomField("custId3")).isNull();
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
        softAssertions.assertThat(info.getCustomField("custId1").getValue()).isEqualTo("custValue1");
        softAssertions.assertThat(info.getCustomField("custId2").getValue()).isEqualTo("custValue2");
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
        softAssertions.assertThat(obj.containsKey("customFields")).isTrue();
        softAssertions.assertThat(((JSONArray) obj.get("customFields")).size()).isEqualTo(2);
        softAssertions.assertThat(((JSONObject) ((JSONArray) obj.get("customFields")).get(0)).get("id")).isEqualTo("custId1");
        softAssertions.assertThat(((JSONObject) ((JSONArray) obj.get("customFields")).get(1)).get("id")).isEqualTo("custId2");

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
        softAssertions.assertThat(((JSONArray) obj.get("customFields")).size()).isEqualTo(2);
        softAssertions.assertThat(((JSONObject) ((JSONArray) obj.get("customFields")).get(0)).get("id")).isEqualTo("custId1");
        softAssertions.assertThat(((JSONObject) ((JSONArray) obj.get("customFields")).get(1)).get("id")).isEqualTo("custId2");

        softAssertions.assertAll();

    }


    @Synchronized
    private static Stream<XrayJsonStepDefObject> getXrayStepDefObject(){
        XrayJsonCustomFieldObject custObj1 = new XrayJsonCustomFieldObject.XrayJsonCustomFieldObjectBuilder()
                .id("custId1")
                .name("custName1")
                .value("custValue1")
                .build();

        XrayJsonCustomFieldObject custObj2 = new XrayJsonCustomFieldObject.XrayJsonCustomFieldObjectBuilder()
                .id("custId2")
                .name("custName2")
                .value("custValue2")
                .build();

        XrayJsonStepDefObject stepDef = new XrayJsonStepDefObject.XrayJsonStepDefObjectBuilder()
                .action("action")
                .data("data")
                .result("result")
                .customField(custObj1)
                .customField(custObj2)
                .build();

        return Stream.of(stepDef);
    }
}
