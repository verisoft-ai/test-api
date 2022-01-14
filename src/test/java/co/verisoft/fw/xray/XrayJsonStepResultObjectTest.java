package co.verisoft.fw.xray;

import lombok.Synchronized;
import org.assertj.core.api.SoftAssertions;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class XrayJsonStepResultObjectTest {

    @ParameterizedTest
    @MethodSource("getXrayStepResultObject")
    public void shouldBuildAllFieldsCorrectly(XrayJsonStepResultObject info){

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(info.getStatus()).isEqualTo("status");
        softAssertions.assertThat(info.getComment()).isEqualTo("comment");
        softAssertions.assertThat(info.getActualResult()).isEqualTo("actualResult");
        softAssertions.assertThat(info.getEvidence()).isEqualTo("evidence");
        softAssertions.assertThat(info.getDefects()).isEqualTo("defects");
        softAssertions.assertAll();
    }

    @ParameterizedTest
    @MethodSource("getXrayStepResultObject")
    public void shouldAllowToChangeValue(XrayJsonStepResultObject stepResult){

        XrayJsonStepResultObject info = new XrayJsonStepResultObject.XrayJsonStepResultObjectBuilder(stepResult)
                .status("status2")
                .build();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(info.getStatus()).isEqualTo("status2");
        softAssertions.assertThat(info.getComment()).isEqualTo("comment");
        softAssertions.assertThat(info.getActualResult()).isEqualTo("actualResult");
        softAssertions.assertThat(info.getEvidence()).isEqualTo("evidence");
        softAssertions.assertThat(info.getDefects()).isEqualTo("defects");
        softAssertions.assertAll();
    }

    @ParameterizedTest
    @MethodSource("getXrayStepResultObject")
    public void shouldCreateAJsonObject(XrayJsonStepResultObject info){
        JSONObject obj = info.asJsonObject();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(obj.get("status")).isEqualTo(info.getStatus());
        softAssertions.assertThat(obj.get("comment")).isEqualTo(info.getComment());
        softAssertions.assertThat(obj.get("actualResult")).isEqualTo(info.getActualResult());
        softAssertions.assertThat(obj.get("evidence")).isEqualTo(info.getEvidence());
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
        softAssertions.assertThat(obj.get("status")).isEqualTo(info.getStatus());
        softAssertions.assertThat(obj.get("comment")).isEqualTo(info.getComment());
        softAssertions.assertThat(obj.get("actualResult")).isEqualTo(info.getActualResult());
        softAssertions.assertThat(obj.get("evidence")).isEqualTo(info.getEvidence());
        softAssertions.assertThat(obj.get("defects")).isEqualTo(info.getDefects());

        softAssertions.assertAll();

    }



    @Synchronized
    private static Stream<XrayJsonStepResultObject> getXrayStepResultObject(){
        XrayJsonStepResultObject result = new XrayJsonStepResultObject.XrayJsonStepResultObjectBuilder()
                .status("status")
                .comment("comment")
                .actualResult("actualResult")
                .evidence("evidence")
                .defects("defects")
                .build();

        return Stream.of(result);
    }
}
