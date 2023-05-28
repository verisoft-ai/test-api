package co.verisoft.fw.utils;

import co.verisoft.fw.errors.SoftAssertionError;
import co.verisoft.fw.extentreport.DelegateExtentTest;
import co.verisoft.fw.extentreport.ReportManager;
import co.verisoft.fw.report.observer.Report;
import co.verisoft.fw.report.observer.ReportSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;
/**
 * Utility class for performing soft assertions in tests.
 * Soft assertions allow multiple assertions to be executed even if some of them fail
 * @author Gili Eliach
 * @since 0.1.4
 */
@Slf4j
public class SoftAsserts {

    private static final List<String> failures= new ArrayList<>();

    /**
     * Constructs a new co.verisoft.SoftAsserts object.
     */
    public SoftAsserts() {}


    public void assertTrue(boolean condition, String message) {

        try {
            Assertions.assertTrue(condition);
        } catch (AssertionError e) {
            Report.error("Soft assert failure. message: " + message +" error: "+ e);
            log.error("Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }
    }

    public static void assertFalse(boolean condition, String message) {

        try {
            Assertions.assertFalse(condition);
        } catch (AssertionError e) {
            Report.error("Soft assert failure. message: " + message +" error: "+ e);
            log.error("Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }

    }

    public static void assertNull(Object actual, String message) {

        try {
            Assertions.assertNull(actual);
        } catch (AssertionError e) {
            Report.error("Soft assert failure. message: " + message +" error: "+ e);
            log.error("Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }
    }

    public static void assertNotNull(Object actual, String message) {

        try {
            Assertions.assertNotNull(actual);
        } catch (AssertionError e) {
            Report.error("Soft assert failure. message: " + message +" error: "+ e);
            log.error("Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }
    }

    public static void assertEquals(short expected, short actual, String message) {

        try {
            Assertions.assertEquals(expected, actual);
        } catch (AssertionError e) {
            Report.error("Soft assert failure. message: " + message +" error: "+ e);
            log.error("Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }
    }

    public static void assertEquals(byte expected, byte actual, String message) {

        try {
            Assertions.assertEquals(expected, actual);
        } catch (AssertionError e) {
            Report.error("Soft assert failure. message: " + message +" error: "+ e);
            log.error("Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }
    }

    public static void assertEquals(int expected, int actual, String message) {

        try {
            Assertions.assertEquals(expected, actual);
        } catch (AssertionError e) {
            Report.error("Soft assert failure. message: " + message +" error: "+ e);
            log.error("Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }
    }

    public static void assertEquals(long expected, long actual, String message) {

        try {
            Assertions.assertEquals(expected, actual);
        } catch (AssertionError e) {
            Report.error("Soft assert failure. message: " + message +" error: "+ e);
            log.error("Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }
    }

    public static void assertEquals(char expected, char actual, String message) {

        try {
            Assertions.assertEquals(expected, actual);
        } catch (AssertionError e) {
            Report.error("Soft assert failure. message: " + message +" error: "+ e);
            log.error("Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }
    }


    public static void assertEquals(float expected, float actual, String message) {

        try {
            Assertions.assertEquals(expected, actual);
        } catch (AssertionError e) {
            Report.error("Soft assert failure. message: " + message +" error: "+ e);
            log.error("Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }
    }


    public static void assertEquals(double expected, double actual, String message) {

        DelegateExtentTest testCase = ReportManager.getInstance().getCurrentTest();

        try {
            Assertions.assertEquals(expected, actual);
            if (null != testCase)
                testCase.pass(message);

        } catch (AssertionError e) {
            if (null != testCase)
                testCase.fail(message);
            log.error("Soft assert failure. message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }
    }


    public static void assertEquals(Object expected, Object actual, String message) {

        try {
            Assertions.assertEquals(expected, actual);
        } catch (AssertionError e) {
            Report.error("Soft assert failure. message: " + message +" error: "+ e);
            log.error("Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }
    }


    public static void assertArrayEquals(boolean[] expected, boolean[] actual, String message) {

        try {
            Assertions.assertArrayEquals(expected, actual);
        } catch (AssertionError e) {
            Report.error("Soft assert failure. message: " + message +" error: "+ e);
            log.error("Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }
    }


    public static void assertArrayEquals(char[] expected, char[] actual, String message) {

        try {
            Assertions.assertArrayEquals(expected, actual);
        } catch (AssertionError e) {
            Report.error("Soft assert failure. message: " + message +" error: "+ e);
            log.error("Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }
    }


    public static void assertArrayEquals(byte[] expected, byte[] actual, String message) {

        try {
            Assertions.assertArrayEquals(expected, actual);
        } catch (AssertionError e) {
            Report.error("Soft assert failure. message: " + message +" error: "+ e);
            log.error("Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }
    }

    public static void assertArrayEquals(short[] expected, short[] actual, String message) {

        try {
            Assertions.assertArrayEquals(expected, actual);
        } catch (AssertionError e) {
            Report.error("Soft assert failure. message: " + message +" error: "+ e);
            log.error("Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }
    }


    public static void assertArrayEquals(int[] expected, int[] actual, String message) {

        try {
            Assertions.assertArrayEquals(expected, actual);
        } catch (AssertionError e) {
            Report.error("Soft assert failure. message: " + message +" error: "+ e);
            log.error("Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }
    }


    public static void assertArrayEquals(long[] expected, long[] actual, String message) {

        try {
            Assertions.assertArrayEquals(expected, actual);
        } catch (AssertionError e) {
            Report.error("Soft assert failure. message: " + message +" error: "+ e);
            log.error("Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }
    }


    public static void assertArrayEquals(float[] expected, float[] actual, String message) {

        try {
            Assertions.assertArrayEquals(expected, actual);
        } catch (AssertionError e) {
            Report.error("Soft assert failure. message: " + message +" error: "+ e);
            log.error("Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }
    }


    public static void assertArrayEquals(double[] expected, double[] actual, String message) {

        try {
            Assertions.assertArrayEquals(expected, actual);
        } catch (AssertionError e) {
            Report.error("Soft assert failure. message: " + message +" error: "+ e);
            log.error("Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }
    }


    public static void assertArrayEquals(Object[] expected, Object[] actual, String message) {

        try {
            Assertions.assertArrayEquals(expected, actual);
        } catch (AssertionError e) {
            Report.error("Soft assert failure. message: " + message +" error: "+ e);
            log.error("Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }
    }


    public static void assertNotEquals(Object unexpected, Object actual, String message) {

        try {
            Assertions.assertNotEquals(unexpected, actual);
        } catch (AssertionError e) {
            Report.error("Soft assert failure. message: " + message +" error: "+ e);
            log.error("Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }
    }


    public static void assertSame(Object expected, Object actual, String message) {

        try {
            Assertions.assertSame(expected, actual);
        } catch (AssertionError e) {
            Report.error("Soft assert failure. message: " + message +" error: "+ e);
            log.error("Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }
    }


    public static void assertNotSame(Object unexpected, Object actual, String message) {

        try {
            Assertions.assertNotSame(unexpected, actual);
        } catch (AssertionError e) {
            Report.error("Soft assert failure. message: " + message +" error: "+ e);
            log.error("Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }
    }


    public static void assertDoesNotThrow(Executable executable, String message) {

        try {
            Assertions.assertDoesNotThrow(executable);
        } catch (AssertionError e) {
            Report.error("Soft assert failure. message: " + message +" error: "+ e);
            log.error("Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }
    }

    /**
     * Checks all the recorded failures and throws an AssertionError
     * if there are any failures.
     *
     * @throws AssertionError if there are recorded failures
     */
    public void assertAll() {
        if (!failures.isEmpty()) {
            String message="Soft assertion failure, see details above.";
            // Clear failures after assertAll is called
            failures.clear();
            throw new SoftAssertionError(message);
        }

    }


}
