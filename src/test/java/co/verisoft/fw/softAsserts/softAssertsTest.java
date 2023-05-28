package co.verisoft.fw.softAsserts;

import co.verisoft.fw.errors.SoftAssertionError;
import co.verisoft.fw.extentreport.ExtentReport;
import co.verisoft.fw.utils.SoftAsserts;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtentReport
@Slf4j
public class softAssertsTest {

        private SoftAsserts softAsserts;

        @BeforeEach
        void setUp() {
            softAsserts = new SoftAsserts();
        }

        @Test
        void assertTrueConditionTrueNoFailure() {
            boolean condition = true;
            softAsserts.assertTrue(condition, "Condition should be true");
            softAsserts.assertAll();
        }

        @Test
        void assertTrueConditionFalseSingleFailure() {

            boolean condition = false;
            softAsserts.assertTrue(condition, "Condition should be true");
            assertThrows(SoftAssertionError.class, softAsserts::assertAll);
        }

        @Test
        void assertAllMultipleFailuresAssertionErrorWithAllFailures() {

            boolean condition1 = false;
            boolean condition2 = false;

            softAsserts.assertTrue(condition1, "Condition 1 should be true");
            softAsserts.assertTrue(condition2, "Condition 2 should be true");
            assertThrows(SoftAssertionError.class, softAsserts::assertAll);
        }
    }


