package co.verisoft.fw.xray;

import org.assertj.core.api.SoftAssertions;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class XrayInfoObjectTest {

    @ParameterizedTest
    @MethodSource("getXrayInfoObject")
    public void shouldBuildAllFieldsCorrectly(JsonInfoObject info){

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(info.getProject()).isEqualTo("project");
        softAssertions.assertThat(info.getSummary()).isEqualTo("summary");
        softAssertions.assertThat(info.getDescription()).isEqualTo("description");
        softAssertions.assertThat(info.getVersion()).isEqualTo("version");
        softAssertions.assertThat(info.getRevision()).isEqualTo("revision");
        softAssertions.assertThat(info.getUser()).isEqualTo("user");
        softAssertions.assertThat(info.getStartDate()).isEqualTo("startDate");
        softAssertions.assertThat(info.getFinishDate()).isEqualTo("finishDate");
        softAssertions.assertThat(info.getTestPlanKey()).isEqualTo("testPlanKey");
        softAssertions.assertThat(info.getTestEnvironments()).isEqualTo("testEnvironments");
        softAssertions.assertAll();
    }

    @ParameterizedTest
    @MethodSource("getXrayInfoObject")
    public void shouldAllowToChangeValue(JsonInfoObject infoBase){

        JsonInfoObject info = new JsonInfoObject.XrayInfoObjectBuilder(infoBase)
                .project("project2")
                .build();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(info.getProject()).isEqualTo("project2");
        softAssertions.assertThat(info.getSummary()).isEqualTo("summary");
        softAssertions.assertThat(info.getDescription()).isEqualTo("description");
        softAssertions.assertThat(info.getVersion()).isEqualTo("version");
        softAssertions.assertThat(info.getRevision()).isEqualTo("revision");
        softAssertions.assertThat(info.getUser()).isEqualTo("user");
        softAssertions.assertThat(info.getStartDate()).isEqualTo("startDate");
        softAssertions.assertThat(info.getFinishDate()).isEqualTo("finishDate");
        softAssertions.assertThat(info.getTestPlanKey()).isEqualTo("testPlanKey");
        softAssertions.assertThat(info.getTestEnvironments()).isEqualTo("testEnvironments");
        softAssertions.assertAll();
    }

    @ParameterizedTest
    @MethodSource("getXrayInfoObject")
    public void shouldCreateAJsonObject(JsonInfoObject info){
        JSONObject obj = info.asJsonObject();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(obj.get("project")).isEqualTo(info.getProject());
        softAssertions.assertThat(obj.get("summary")).isEqualTo(info.getSummary());
        softAssertions.assertThat(obj.get("description")).isEqualTo(info.getDescription());
        softAssertions.assertThat(obj.get("version")).isEqualTo(info.getVersion());
        softAssertions.assertThat(obj.get("revision")).isEqualTo(info.getRevision());
        softAssertions.assertThat(obj.get("user")).isEqualTo(info.getUser());
        softAssertions.assertThat(obj.get("startDate")).isEqualTo(info.getStartDate());
        softAssertions.assertThat(obj.get("finishDate")).isEqualTo(info.getFinishDate());
        softAssertions.assertThat(obj.get("testPlanKey")).isEqualTo(info.getTestPlanKey());
        softAssertions.assertThat(obj.get("testEnvironments")).isEqualTo(info.getTestEnvironments());

        softAssertions.assertAll();

    }

    @ParameterizedTest
    @MethodSource("getXrayInfoObject")
    public void shouldCreateAStringObject(JsonInfoObject info) throws ParseException {
        String objString = info.asString();
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(objString);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(obj.get("project")).isEqualTo(info.getProject());
        softAssertions.assertThat(obj.get("summary")).isEqualTo(info.getSummary());
        softAssertions.assertThat(obj.get("description")).isEqualTo(info.getDescription());
        softAssertions.assertThat(obj.get("version")).isEqualTo(info.getVersion());
        softAssertions.assertThat(obj.get("revision")).isEqualTo(info.getRevision());
        softAssertions.assertThat(obj.get("user")).isEqualTo(info.getUser());
        softAssertions.assertThat(obj.get("startDate")).isEqualTo(info.getStartDate());
        softAssertions.assertThat(obj.get("finishDate")).isEqualTo(info.getFinishDate());
        softAssertions.assertThat(obj.get("testPlanKey")).isEqualTo(info.getTestPlanKey());
        softAssertions.assertThat(obj.get("testEnvironments")).isEqualTo(info.getTestEnvironments());

        softAssertions.assertAll();

    }


    private static Stream<JsonInfoObject> getXrayInfoObject(){
        JsonInfoObject infoBase = new JsonInfoObject.XrayInfoObjectBuilder()
                .project("project")
                .summary("summary")
                .description("description")
                .version("version")
                .revision("revision")
                .user("user")
                .startDate("startDate")
                .finishDate("finishDate")
                .testPlanKey("testPlanKey")
                .testEnvironments("testEnvironments")
                .build();

        return Stream.of(infoBase);
    }
}
