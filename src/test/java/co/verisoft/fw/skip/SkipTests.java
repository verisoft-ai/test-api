package co.verisoft.fw.skip;

import co.verisoft.fw.extentreport.ExtentReport;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

@ExtentReport
public class SkipTests {
    @Test
    public void ShouldBeSkipped() {
        Assumptions.assumeTrue(false);
    }
    @Test
    public void ShouldNotBeSkipped() {
        Assumptions.assumeTrue(true);
    }
}