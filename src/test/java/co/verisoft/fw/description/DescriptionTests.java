package co.verisoft.fw.description;

import co.verisoft.fw.extentreport.Description;
import co.verisoft.fw.extentreport.ExtentReport;
import org.junit.jupiter.api.Test;

@ExtentReport
public class DescriptionTests {

    @Test
    @Description("This test has a description")
    public void shouldHaveDescription()
    {}

    @Test
    public void shouldNotHaveDescription()
    {}
}