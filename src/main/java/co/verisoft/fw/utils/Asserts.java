package co.verisoft.fw.utils;

/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

//import org.apache.commons.text.StringEscapeUtils;

import co.verisoft.fw.extentreport.DelegateExtentTest;
import co.verisoft.fw.extentreport.ReportManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;
import org.slf4j.Logger;

/**
 * Class wraps the whole assert process into one step:
 * 1. Assert
 * 2. Log
 * 3. Report
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 */
public class Asserts {

    private static final Logger logger = new ExtendedLog(Asserts.class);

    /**
     * Protected constructor allowing subclassing but not direct instantiation.
     */
    protected Asserts() {

    }

    public static <V> V fail(String message) {

        logger.info("Assert - FAIL. " + message);

        DelegateExtentTest testCase = ReportManager.getInstance().getCurrentTest();
        if (testCase != null)
            testCase.fail(message);

        Assertions.fail(message);

        return null; // satisfying the compiler: this line will never be executed.
    }


    public static void assertTrue(boolean condition, String message) {

        DelegateExtentTest testCase = ReportManager.getInstance().getCurrentTest();
        try {
            Assertions.assertTrue(condition);
            if (null != testCase)
                testCase.pass(message);
        } catch (AssertionError e) {
            if (null != testCase)
                testCase.fail(message);
            logger.error("Fail message: '" + message + "' Stack trace is: " + Utils.getStackTrace(e));
            throw e;
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
            logger.error("Fail message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            throw e;
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
            logger.error("Fail message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            throw e;
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
            logger.error("Fail message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            throw e;
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
            logger.error("Fail message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            throw e;
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
            logger.error("Fail message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            throw e;
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
            logger.error("Fail message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            throw e;
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
            logger.error("Fail message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            throw e;
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
            logger.error("Fail message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            throw e;
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
            logger.error("Fail message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            throw e;
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
            logger.error("Fail message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            throw e;
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
            logger.error("Fail message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            throw e;
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
            logger.error("Fail message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            throw e;
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
            logger.error("Fail message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            throw e;
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
            logger.error("Fail message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            throw e;
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
            logger.error("Fail message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            throw e;
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
            logger.error("Fail message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            throw e;
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
            logger.error("Fail message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            throw e;
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
            logger.error("Fail message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            throw e;
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
            logger.error("Fail message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            throw e;
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
            logger.error("Fail message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            throw e;
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
            logger.error("Fail message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            throw e;
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
            logger.error("Fail message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            throw e;
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
            logger.error("Fail message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            throw e;
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
            logger.error("Fail message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            throw e;
        }
    }
}
