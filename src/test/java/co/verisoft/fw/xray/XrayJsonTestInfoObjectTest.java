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

public class XrayJsonTestInfoObjectTest {

    @ParameterizedTest
    @MethodSource("getXrayTestInfoObject")
    public void shouldBuildAllFieldsCorrectly(XrayJsonTestInfoObject info) {

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(info.getProjectKey()).isEqualTo("projectKey1");
        softAssertions.assertThat(info.getSummary()).isEqualTo("summary1");
        softAssertions.assertThat(info.getType().toString()).isEqualTo(TestType.GENERIC.toString());
        softAssertions.assertThat(info.getType()).isEqualTo(TestType.GENERIC);
        softAssertions.assertThat(info.getRequirementKeys().get(0)).isEqualTo("key1");
        softAssertions.assertThat(info.getLabels()).isEqualTo("label1");
        softAssertions.assertThat(info.getSteps().get(0).getResult()).isEqualTo("result1");
        softAssertions.assertThat(info.getSteps().get(0).getCustomFields().get(0).getId()).isEqualTo("custId1");
        softAssertions.assertThat(info.getScenario()).isEqualTo("scenario1");
        softAssertions.assertThat(info.getDefinition()).isEqualTo("def1");

        softAssertions.assertAll();
    }

    @ParameterizedTest
    @MethodSource("getXrayTestInfoObject")
    public void shouldAllowToChangeValue(XrayJsonTestInfoObject infoBase) {

        XrayJsonTestInfoObject info = new XrayJsonTestInfoObject.XrayJsonTestInfoObjectBuilder(infoBase)
                .projectKey("projectKey2")
                .build();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(info.getProjectKey()).isEqualTo("projectKey2");
        softAssertions.assertThat(info.getSummary()).isEqualTo("summary1");
        softAssertions.assertThat(info.getType().toString()).isEqualTo(TestType.GENERIC.toString());
        softAssertions.assertThat(info.getType()).isEqualTo(TestType.GENERIC);
        softAssertions.assertThat(info.getRequirementKeys().get(0)).isEqualTo("key1");
        softAssertions.assertThat(info.getLabels()).isEqualTo("label1");
        softAssertions.assertThat(info.getSteps().get(0).getResult()).isEqualTo("result1");
        softAssertions.assertThat(info.getSteps().get(0).getCustomFields().get(0).getId()).isEqualTo("custId1");
        softAssertions.assertThat(info.getScenario()).isEqualTo("scenario1");
        softAssertions.assertThat(info.getDefinition()).isEqualTo("def1");

        softAssertions.assertAll();
    }

    @ParameterizedTest
    @MethodSource("getXrayTestInfoObject")
    public void shouldCreateAJsonObject(XrayJsonTestInfoObject info) {
        JSONObject obj = info.asJsonObject();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(obj.get("projectKey")).isEqualTo(info.getProjectKey());
        softAssertions.assertThat(obj.get("summary")).isEqualTo(info.getSummary());
        softAssertions.assertThat(obj.get("type")).isEqualTo(info.getTypeAsString());
        softAssertions.assertThat(((JSONArray) obj.get("requirementKeys")).get(0)).isEqualTo("key1");
        softAssertions.assertThat(obj.get("labels")).isEqualTo(info.getLabels());
        softAssertions.assertThat(((JSONObject) ((JSONArray) obj.get("steps")).get(0)).get("result")).isEqualTo("result1");
        softAssertions.assertThat(obj.get("scenario")).isEqualTo(info.getScenario());
        softAssertions.assertThat(obj.get("definition")).isEqualTo(info.getDefinition());

        softAssertions.assertAll();
    }

    @ParameterizedTest
    @MethodSource("getXrayTestInfoObject")
    public void shouldCreateAStringObject(XrayJsonTestInfoObject info) throws ParseException {
        String objString = info.asString();
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(objString);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(obj.get("projectKey")).isEqualTo(info.getProjectKey());
        softAssertions.assertThat(obj.get("summary")).isEqualTo(info.getSummary());
        softAssertions.assertThat(obj.get("type")).isEqualTo(info.getTypeAsString());
        softAssertions.assertThat(((JSONArray) obj.get("requirementKeys")).get(0)).isEqualTo("key1");
        softAssertions.assertThat(obj.get("labels")).isEqualTo(info.getLabels());
        softAssertions.assertThat(((JSONObject) ((JSONArray) obj.get("steps")).get(0)).get("result")).isEqualTo("result1");
        softAssertions.assertThat(obj.get("scenario")).isEqualTo(info.getScenario());
        softAssertions.assertThat(obj.get("definition")).isEqualTo(info.getDefinition());

        softAssertions.assertAll();

    }


    @Synchronized
    private static Stream<XrayJsonTestInfoObject> getXrayTestInfoObject() {

        XrayJsonCustomFieldObject cust1 = new XrayJsonCustomFieldObject.XrayJsonCustomFieldObjectBuilder()
                .id("custId1")
                .name("custName1")
                .value("custValue1")
                .build();

        XrayJsonStepDefObject step1 = new XrayJsonStepDefObject.XrayJsonStepDefObjectBuilder()
                .action("action1")
                .result("result1")
                .customField(cust1)
                .data("data1")
                .build();

        XrayJsonTestInfoObject testInfo = new XrayJsonTestInfoObject.XrayJsonTestInfoObjectBuilder()
                .projectKey("projectKey1")
                .summary("summary1")
                .type("generic")
                .requirementKey("key1")
                .labels("label1")
                .step(step1)
                .scenario("scenario1")
                .definition("def1")
                .build();


        System.out.println(testInfo.asString());
        return Stream.of(testInfo);
    }
}
