/*
 * (C) Copyright 2023 VeriSoft (http://www.verisoft.co)
 *
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
package co.verisoft.fw.asserts;

import co.verisoft.fw.report.observer.Report;
import co.verisoft.fw.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

/**
 * Class wraps the whole assert process into one step:
 * 1. Assert
 * 2. Log
 * 3. Report
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 */
@Slf4j
@SuppressWarnings("unused")
public class Asserts {


    /**
     * Protected constructor allowing subclassing but not direct instantiation.
     */
    protected Asserts() {

    }

    public static <V> V fail(String message) {

        log.info("Assert - FAIL. " + message);
        Assertions.fail(message);
        return null; // satisfying the compiler: this line will never be executed.
    }


    public static void assertTrue(boolean condition, String message) {

        try {
            Assertions.assertTrue(condition);
        } catch (AssertionError e) {
            Report.error("Assertion failure. message: " + message + " error: " + e);
            log.error("Fail message: '" + message + "' Stack trace is: " + Utils.getStackTrace(e));
            throw e;
        }
    }


    public static void assertFalse(boolean condition, String message) {

        try {
            Assertions.assertFalse(condition);
        } catch (AssertionError e) {
            Report.error("Assertion failure. message: " + message + " error: " + e);
            log.error("Fail message: '" + message + "' Stack trace is: " + Utils.getStackTrace(e));
            throw e;
        }

    }


    public static void assertNull(Object actual, String message) {

        try {
            Assertions.assertNull(actual);
        } catch (AssertionError e) {
            Report.error("Assertion failure. message: " + message + " error: " + e);
            log.error("Fail message: '" + message + "' Stack trace is: " + Utils.getStackTrace(e));
            throw e;
        }
    }


    public static void assertNotNull(Object actual, String message) {

        try {
            Assertions.assertNotNull(actual);
        } catch (AssertionError e) {
            Report.error("Assertion failure. message: " + message + " error: " + e);
            log.error("Fail message: '" + message + "' Stack trace is: " + Utils.getStackTrace(e));
            throw e;
        }
    }


    public static void assertEquals(short expected, short actual, String message) {

        try {
            Assertions.assertEquals(expected, actual);
        } catch (AssertionError e) {
            Report.error("Assertion failure. message: " + message + " error: " + e);
            log.error("Fail message: '" + message + "' Stack trace is: " + Utils.getStackTrace(e));
            throw e;
        }
    }


    public static void assertEquals(byte expected, byte actual, String message) {

        try {
            Assertions.assertEquals(expected, actual);
        } catch (AssertionError e) {
            Report.error("Assertion failure. message: " + message + " error: " + e);
            log.error("Fail message: '" + message + "' Stack trace is: " + Utils.getStackTrace(e));
            throw e;
        }
    }


    public static void assertEquals(int expected, int actual, String message) {

        try {
            Assertions.assertEquals(expected, actual);
        } catch (AssertionError e) {
            Report.error("Assertion failure. message: " + message + " error: " + e);
            log.error("Fail message: '" + message + "' Stack trace is: " + Utils.getStackTrace(e));
            throw e;
        }
    }


    public static void assertEquals(long expected, long actual, String message) {

        try {
            Assertions.assertEquals(expected, actual);
        } catch (AssertionError e) {
            Report.error("Assertion failure. message: " + message + " error: " + e);
            log.error("Fail message: '" + message + "' Stack trace is: " + Utils.getStackTrace(e));
            throw e;
        }
    }


    public static void assertEquals(char expected, char actual, String message) {

        try {
            Assertions.assertEquals(expected, actual);
        } catch (AssertionError e) {
            Report.error("Assertion failure. message: " + message + " error: " + e);
            log.error("Fail message: '" + message + "' Stack trace is: " + Utils.getStackTrace(e));
            throw e;
        }
    }


    public static void assertEquals(float expected, float actual, String message) {

        try {
            Assertions.assertEquals(expected, actual);
        } catch (AssertionError e) {
            Report.error("Assertion failure. message: " + message + " error: " + e);
            log.error("Fail message: '" + message + "' Stack trace is: " + Utils.getStackTrace(e));
            throw e;
        }
    }


    public static void assertEquals(double expected, double actual, String message) {

        try {
            Assertions.assertEquals(expected, actual);
        } catch (AssertionError e) {
            Report.error("Assertion failure. message: " + message + " error: " + e);
            log.error("Fail message: '" + message + "' Stack trace is: " + Utils.getStackTrace(e));
            throw e;
        }
    }


    public static void assertEquals(Object expected, Object actual, String message) {


        try {
            Assertions.assertEquals(expected, actual);
        } catch (AssertionError e) {
            Report.error("Assertion failure. message: " + message + " error: " + e);
            log.error("Fail message: '" + message + "' Stack trace is: " + Utils.getStackTrace(e));
            throw e;
        }
    }


    public static void assertArrayEquals(boolean[] expected, boolean[] actual, String message) {

        try {
            Assertions.assertArrayEquals(expected, actual);
        } catch (AssertionError e) {
            Report.error("Assertion failure. message: " + message + " error: " + e);
            log.error("Fail message: '" + message + "' Stack trace is: " + Utils.getStackTrace(e));
            throw e;
        }
    }


    public static void assertArrayEquals(char[] expected, char[] actual, String message) {

        try {
            Assertions.assertArrayEquals(expected, actual);
        } catch (AssertionError e) {
            Report.error("Assertion failure. message: " + message + " error: " + e);
            log.error("Fail message: '" + message + "' Stack trace is: " + Utils.getStackTrace(e));
            throw e;
        }
    }


    public static void assertArrayEquals(byte[] expected, byte[] actual, String message) {

        try {
            Assertions.assertArrayEquals(expected, actual);
        } catch (AssertionError e) {
            Report.error("Assertion failure. message: " + message + " error: " + e);
            log.error("Fail message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            throw e;
        }
    }

    public static void assertArrayEquals(short[] expected, short[] actual, String message) {

        try {
            Assertions.assertArrayEquals(expected, actual);
        } catch (AssertionError e) {
            Report.error("Assertion failure. message: " + message + " error: " + e);
            log.error("Fail message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            throw e;
        }
    }


    public static void assertArrayEquals(int[] expected, int[] actual, String message) {

        try {
            Assertions.assertArrayEquals(expected, actual);
        } catch (AssertionError e) {
            Report.error("Assertion failure. message: " + message + " error: " + e);
            log.error("Fail message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            throw e;
        }
    }


    public static void assertArrayEquals(long[] expected, long[] actual, String message) {


        try {
            Assertions.assertArrayEquals(expected, actual);
        } catch (AssertionError e) {
            Report.error("Assertion failure. message: " + message + " error: " + e);
            log.error("Fail message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            throw e;
        }
    }


    public static void assertArrayEquals(float[] expected, float[] actual, String message) {

        try {
            Assertions.assertArrayEquals(expected, actual);
        } catch (AssertionError e) {
            Report.error("Assertion failure. message: " + message + " error: " + e);
            log.error("Fail message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            throw e;
        }
    }


    public static void assertArrayEquals(double[] expected, double[] actual, String message) {

        try {
            Assertions.assertArrayEquals(expected, actual);
        } catch (AssertionError e) {
            Report.error("Assertion failure. message: " + message + " error: " + e);
            log.error("Fail message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            throw e;
        }
    }


    public static void assertArrayEquals(Object[] expected, Object[] actual, String message) {

        try {
            Assertions.assertArrayEquals(expected, actual);
        } catch (AssertionError e) {
            Report.error("Assertion failure. message: " + message + " error: " + e);
            log.error("Fail message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            throw e;
        }
    }


    public static void assertNotEquals(Object unexpected, Object actual, String message) {

        try {
            Assertions.assertNotEquals(unexpected, actual);
        } catch (AssertionError e) {
            Report.error("Assertion failure. message: " + message + " error: " + e);
            log.error("Fail message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            throw e;
        }
    }


    public static void assertSame(Object expected, Object actual, String message) {

        try {
            Assertions.assertSame(expected, actual);
        } catch (AssertionError e) {
            Report.error("Assertion failure. message: " + message + " error: " + e);
            log.error("Fail message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            throw e;
        }
    }


    public static void assertNotSame(Object unexpected, Object actual, String message) {

        try {
            Assertions.assertNotSame(unexpected, actual);
        } catch (AssertionError e) {
            Report.error("Assertion failure. message: " + message + " error: " + e);
            log.error("Fail message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            throw e;
        }
    }


    public static void assertDoesNotThrow(Executable executable, String message) {

        try {
            Assertions.assertDoesNotThrow(executable);
        } catch (AssertionError e) {
            Report.error("Assertion failure. message: " + message + " error: " + e);
            log.error("Fail message: " + message + " Stack trace is: " + Utils.getStackTrace(e));
            throw e;
        }
    }
}
