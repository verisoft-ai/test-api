package co.verisoft.fw.xray;

import lombok.Synchronized;
import org.assertj.core.api.SoftAssertions;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class XrayJsonTestObjectTest {

    @ParameterizedTest
    @MethodSource("getXrayTestObject")
    public void shouldBuildAllFieldsCorrectly(XrayJsonTestObject info){

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(info.getTestKey()).isEqualTo("testKey");
        softAssertions.assertThat(info.getTestInfo()).isEqualTo("testInfo");
        softAssertions.assertThat(info.getStart()).isEqualTo("start");
        softAssertions.assertThat(info.getFinish()).isEqualTo("finish");
        softAssertions.assertThat(info.getComment()).isEqualTo("comment");
        softAssertions.assertThat(info.getExecutedBy()).isEqualTo("executedBy");
        softAssertions.assertThat(info.getAssignee()).isEqualTo("assignee");
        softAssertions.assertThat(info.getStatus()).isEqualTo("status");
        softAssertions.assertThat(info.getSteps()).isEqualTo("steps");
        softAssertions.assertThat(info.getExamples()).isEqualTo("examples");
        softAssertions.assertThat(info.getIterations()).isEqualTo("iterations");
        softAssertions.assertThat(info.getDefects()).isEqualTo("defects");
        softAssertions.assertThat(info.getEvidence()).isEqualTo("evidence");
        softAssertions.assertThat(info.getCustomFields()).isEqualTo("customFields");
        softAssertions.assertAll();
    }

    @ParameterizedTest
    @MethodSource("getXrayTestObject")
    public void shouldAllowToChangeValue(XrayJsonTestObject infoBase){

        XrayJsonTestObject info = new XrayJsonTestObject.XrayJsonTestObjectBuilder(infoBase)
                .testKey("testKey2")
                .build();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(info.getTestKey()).isEqualTo("testKey2");
        softAssertions.assertThat(info.getTestInfo()).isEqualTo("testInfo");
        softAssertions.assertThat(info.getStart()).isEqualTo("start");
        softAssertions.assertThat(info.getFinish()).isEqualTo("finish");
        softAssertions.assertThat(info.getComment()).isEqualTo("comment");
        softAssertions.assertThat(info.getExecutedBy()).isEqualTo("executedBy");
        softAssertions.assertThat(info.getAssignee()).isEqualTo("assignee");
        softAssertions.assertThat(info.getStatus()).isEqualTo("status");
        softAssertions.assertThat(info.getSteps()).isEqualTo("steps");
        softAssertions.assertThat(info.getExamples()).isEqualTo("examples");
        softAssertions.assertThat(info.getIterations()).isEqualTo("iterations");
        softAssertions.assertThat(info.getDefects()).isEqualTo("defects");
        softAssertions.assertThat(info.getEvidence()).isEqualTo("evidence");
        softAssertions.assertThat(info.getCustomFields()).isEqualTo("customFields");
        softAssertions.assertAll();
    }

    @ParameterizedTest
    @MethodSource("getXrayTestObject")
    public void shouldCreateAJsonObject(XrayJsonTestObject info){
        JSONObject obj = info.asJsonObject();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(obj.get("testKey")).isEqualTo(info.getTestKey());
        softAssertions.assertThat(obj.get("testInfo")).isEqualTo(info.getTestInfo());
        softAssertions.assertThat(obj.get("start")).isEqualTo(info.getStart());
        softAssertions.assertThat(obj.get("finish")).isEqualTo(info.getFinish());
        softAssertions.assertThat(obj.get("comment")).isEqualTo(info.getComment());
        softAssertions.assertThat(obj.get("executedBy")).isEqualTo(info.getExecutedBy());
        softAssertions.assertThat(obj.get("assignee")).isEqualTo(info.getAssignee());
        softAssertions.assertThat(obj.get("status")).isEqualTo(info.getStatus());
        softAssertions.assertThat(obj.get("steps")).isEqualTo(info.getSteps());
        softAssertions.assertThat(obj.get("examples")).isEqualTo(info.getExamples());
        softAssertions.assertThat(obj.get("iterations")).isEqualTo(info.getIterations());
        softAssertions.assertThat(obj.get("defects")).isEqualTo(info.getDefects());
        softAssertions.assertThat(obj.get("evidence")).isEqualTo(info.getEvidence());
        softAssertions.assertThat(obj.get("customFields")).isEqualTo(info.getCustomFields());

        softAssertions.assertAll();

    }

    @ParameterizedTest
    @MethodSource("getXrayTestObject")
    public void shouldCreateAStringObject(XrayJsonTestObject info) throws ParseException {
        String objString = info.asString();
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(objString);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(obj.get("testKey")).isEqualTo(info.getTestKey());
        softAssertions.assertThat(obj.get("testInfo")).isEqualTo(info.getTestInfo());
        softAssertions.assertThat(obj.get("start")).isEqualTo(info.getStart());
        softAssertions.assertThat(obj.get("finish")).isEqualTo(info.getFinish());
        softAssertions.assertThat(obj.get("comment")).isEqualTo(info.getComment());
        softAssertions.assertThat(obj.get("executedBy")).isEqualTo(info.getExecutedBy());
        softAssertions.assertThat(obj.get("assignee")).isEqualTo(info.getAssignee());
        softAssertions.assertThat(obj.get("status")).isEqualTo(info.getStatus());
        softAssertions.assertThat(obj.get("steps")).isEqualTo(info.getSteps());
        softAssertions.assertThat(obj.get("examples")).isEqualTo(info.getExamples());
        softAssertions.assertThat(obj.get("iterations")).isEqualTo(info.getIterations());
        softAssertions.assertThat(obj.get("defects")).isEqualTo(info.getDefects());
        softAssertions.assertThat(obj.get("evidence")).isEqualTo(info.getEvidence());
        softAssertions.assertThat(obj.get("customFields")).isEqualTo(info.getCustomFields());

        softAssertions.assertAll();

    }


    @Synchronized
    private static Stream<XrayJsonTestObject> getXrayTestObject(){
        XrayJsonTestObject infoBase = new XrayJsonTestObject.XrayJsonTestObjectBuilder()
                .testKey("testKey")
                .testInfo("testInfo")
                .start("start")
                .finish("finish")
                .comment("comment")
                .executedBy("executedBy")
                .assignee("assignee")
                .status("status")
                .steps("steps")
                .examples("examples")
                .iterations("iterations")
                .defects("defects")
                .evidence("evidence")
                .customFields("customFields")
                .build();

        return Stream.of(infoBase);
    }
}
