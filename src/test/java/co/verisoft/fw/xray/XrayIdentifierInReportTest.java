package co.verisoft.fw.xray;

import co.verisoft.fw.extentreport.ExtentReport;
import org.junit.jupiter.api.Test;

@ExtentReport
public class XrayIdentifierInReportTest {
    @Test
    @XrayIdentifier("NIR-1")
    public void shouldHaveXrayIdentifierInReport()
    {
    }

    @Test
    @XrayIdentifier({"NIR-1","NIR-2"})
    public void shouldHaveTwoXrayIdentifierInReport()
    {
    }

    @Test
    public void shouldNotHaveXrayIdentifierInReport()
    {
    }
}
