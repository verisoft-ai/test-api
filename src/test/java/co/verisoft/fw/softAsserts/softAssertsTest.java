package co.verisoft.fw.softAsserts;

import co.verisoft.fw.extentreport.ExtentReport;
import co.verisoft.fw.utils.Asserts;
import co.verisoft.fw.utils.SoftAsserts;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
@ExtentReport
@Slf4j
public class softAssertsTest {

    @Test
    public void shouldPass()
    {
        SoftAsserts softAsserts=new SoftAsserts();
        softAsserts.assertTrue(false,"The 1st softAssert should FAIL");
        softAsserts.assertTrue(true,"The 2nd softAssert should PASS");
        softAsserts.assertNotNull(null,"The 3rd softAssert should FAIL");
        softAsserts.assertAll();
    }
}
