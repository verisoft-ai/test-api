package co.verisoft.fw.xray;

import lombok.Synchronized;
import org.assertj.core.api.SoftAssertions;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class XrayJsonInfoObjectTest {

    @ParameterizedTest
    @MethodSource("getXrayInfoObject")
    public void shouldBuildAllFieldsCorrectly(XrayJsonInfoObject info){

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
    public void shouldAllowToChangeValue(XrayJsonInfoObject infoBase){

        XrayJsonInfoObject info = new XrayJsonInfoObject.XrayInfoObjectBuilder(infoBase)
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
    public void shouldCreateAJsonObject(XrayJsonInfoObject info){
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
    public void shouldCreateAStringObject(XrayJsonInfoObject info) throws ParseException {
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


    @Synchronized
    private static Stream<XrayJsonInfoObject> getXrayInfoObject(){
        XrayJsonInfoObject infoBase = new XrayJsonInfoObject.XrayInfoObjectBuilder()
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
