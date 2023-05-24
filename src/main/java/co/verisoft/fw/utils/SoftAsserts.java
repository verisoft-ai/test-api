package co.verisoft.fw.utils;

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
 */
@Slf4j
public class SoftAsserts {

    private static final List<String> failures= new ArrayList<>();

    /**
     * Constructs a new co.verisoft.SoftAsserts object.
     */
    public SoftAsserts() {}


    public void assertTrue(boolean condition, String message) {

        DelegateExtentTest testCase = ReportManager.getInstance().getCurrentTest();
        try {
            Assertions.assertTrue(condition);
            if (null != testCase)
                testCase.pass(message);
        } catch (AssertionError e) {
            if (null != testCase)
                testCase.fail(message);
            log.error("Soft assert failure. message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }
    }

    public static void assertFalse(boolean condition, String message) {

        DelegateExtentTest testCase = ReportManager.getInstance().getCurrentTest();

        try {
            Assertions.assertFalse(condition);
            if (null != testCase)
                testCase.pass(message);

        } catch (AssertionError e) {
            if (null != testCase)
                testCase.fail(message);
            log.error("Soft assert failure. message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }

    }

    public static void assertNull(Object actual, String message) {

        DelegateExtentTest testCase = ReportManager.getInstance().getCurrentTest();

        try {
            Assertions.assertNull(actual);
            if (null != testCase)
                testCase.pass(message);

        } catch (AssertionError e) {
            if (null != testCase)
                testCase.fail(message);
            log.error("Soft assert failure. message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }
    }

    public static void assertNotNull(Object actual, String message) {

        DelegateExtentTest testCase = ReportManager.getInstance().getCurrentTest();

        try {
            Assertions.assertNotNull(actual);
            if (null != testCase)
                testCase.pass(message);

        } catch (AssertionError e) {
            if (null != testCase)
                testCase.fail(message);
            log.error("Soft assert failure. message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }
    }

    public static void assertEquals(short expected, short actual, String message) {

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

    public static void assertEquals(byte expected, byte actual, String message) {

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

    public static void assertEquals(int expected, int actual, String message) {

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

    public static void assertEquals(long expected, long actual, String message) {

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

    public static void assertEquals(char expected, char actual, String message) {

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


    public static void assertEquals(float expected, float actual, String message) {

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


    public static void assertArrayEquals(boolean[] expected, boolean[] actual, String message) {

        DelegateExtentTest testCase = ReportManager.getInstance().getCurrentTest();

        try {
            Assertions.assertArrayEquals(expected, actual);
            if (null != testCase)
                testCase.pass(message);

        } catch (AssertionError e) {
            if (null != testCase)
                testCase.fail(message);
            log.error("Soft assert failure. message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }
    }


    public static void assertArrayEquals(char[] expected, char[] actual, String message) {

        DelegateExtentTest testCase = ReportManager.getInstance().getCurrentTest();

        try {
            Assertions.assertArrayEquals(expected, actual);
            if (null != testCase)
                testCase.pass(message);

        } catch (AssertionError e) {
            if (null != testCase)
                testCase.fail(message);
            log.error("Soft assert failure. message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }
    }


    public static void assertArrayEquals(byte[] expected, byte[] actual, String message) {

        DelegateExtentTest testCase = ReportManager.getInstance().getCurrentTest();

        try {
            Assertions.assertArrayEquals(expected, actual);
            if (null != testCase)
                testCase.pass(message);

        } catch (AssertionError e) {
            if (null != testCase)
                testCase.fail(message);
            log.error("Soft assert failure. message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }
    }

    public static void assertArrayEquals(short[] expected, short[] actual, String message) {

        DelegateExtentTest testCase = ReportManager.getInstance().getCurrentTest();

        try {
            Assertions.assertArrayEquals(expected, actual);
            if (null != testCase)
                testCase.pass(message);

        } catch (AssertionError e) {
            if (null != testCase)
                testCase.fail(message);
            log.error("Soft assert failure. message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }
    }


    public static void assertArrayEquals(int[] expected, int[] actual, String message) {

        DelegateExtentTest testCase = ReportManager.getInstance().getCurrentTest();

        try {
            Assertions.assertArrayEquals(expected, actual);
            if (null != testCase)
                testCase.pass(message);

        } catch (AssertionError e) {
            if (null != testCase)
                testCase.fail(message);
            log.error("Soft assert failure. message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }
    }


    public static void assertArrayEquals(long[] expected, long[] actual, String message) {

        DelegateExtentTest testCase = ReportManager.getInstance().getCurrentTest();

        try {
            Assertions.assertArrayEquals(expected, actual);
            if (null != testCase)
                testCase.pass(message);

        } catch (AssertionError e) {
            if (null != testCase)
                testCase.fail(message);
            log.error("Soft assert failure. message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }
    }


    public static void assertArrayEquals(float[] expected, float[] actual, String message) {

        DelegateExtentTest testCase = ReportManager.getInstance().getCurrentTest();

        try {
            Assertions.assertArrayEquals(expected, actual);
            if (null != testCase)
                testCase.pass(message);

        } catch (AssertionError e) {
            if (null != testCase)
                testCase.fail(message);
            log.error("Soft assert failure. message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }
    }


    public static void assertArrayEquals(double[] expected, double[] actual, String message) {

        DelegateExtentTest testCase = ReportManager.getInstance().getCurrentTest();

        try {
            Assertions.assertArrayEquals(expected, actual);
            if (null != testCase)
                testCase.pass(message);

        } catch (AssertionError e) {
            if (null != testCase)
                testCase.fail(message);
            log.error("Soft assert failure. message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }
    }


    public static void assertArrayEquals(Object[] expected, Object[] actual, String message) {

        DelegateExtentTest testCase = ReportManager.getInstance().getCurrentTest();

        try {
            Assertions.assertArrayEquals(expected, actual);
            if (null != testCase)
                testCase.pass(message);

        } catch (AssertionError e) {
            if (null != testCase)
                testCase.fail(message);
            log.error("Soft assert failure. message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }
    }


    public static void assertNotEquals(Object unexpected, Object actual, String message) {

        DelegateExtentTest testCase = ReportManager.getInstance().getCurrentTest();

        try {
            Assertions.assertNotEquals(unexpected, actual);
            if (null != testCase)
                testCase.pass(message);

        } catch (AssertionError e) {
            if (null != testCase)
                testCase.fail(message);
            log.error("Soft assert failure. message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }
    }


    public static void assertSame(Object expected, Object actual, String message) {

        DelegateExtentTest testCase = ReportManager.getInstance().getCurrentTest();

        try {
            Assertions.assertSame(expected, actual);
            if (null != testCase)
                testCase.pass(message);

        } catch (AssertionError e) {
            if (null != testCase)
                testCase.fail(message);
            log.error("Soft assert failure. message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }
    }


    public static void assertNotSame(Object unexpected, Object actual, String message) {

        DelegateExtentTest testCase = ReportManager.getInstance().getCurrentTest();

        try {
            Assertions.assertNotSame(unexpected, actual);
            if (null != testCase)
                testCase.pass(message);

        } catch (AssertionError e) {
            if (null != testCase)
                testCase.fail(message);
            log.error("Soft assert failure. message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            failures.add(message);
        }
    }


    public static void assertDoesNotThrow(Executable executable, String message) {

        DelegateExtentTest testCase = ReportManager.getInstance().getCurrentTest();

        try {
            Assertions.assertDoesNotThrow(executable);
            if (null != testCase)
                testCase.pass(message);

        } catch (AssertionError e) {
            if (null != testCase)
                testCase.fail(message);
            log.error("Soft assert failure. message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
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
            StringBuilder message = new StringBuilder("\nThe following assertions failed:\n");
            for (String failure : failures) {
                message.append("- ").append(failure).append("\n");
            }

            throw new AssertionError(message.toString());
        }
    }


}
