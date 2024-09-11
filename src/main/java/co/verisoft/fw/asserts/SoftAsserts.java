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
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;


/**
 * Utility class for performing soft assertions in tests.
 * Soft assertions allow multiple assertions to be executed even if some of them fail
 *
 * @author Gili Eliach
 * @since 0.1.4
 */
@Slf4j
@ToString
public class SoftAsserts {

    private final List<String> failures;

    public SoftAsserts() {
        failures = new ArrayList<>();
    }

    protected void handleFailure(String message , AssertionError e){
        Report.error("Soft assert failure. message: " + message + " error: " + e);
        log.error("Stack trace is: " + Utils.getStackTrace(e));
        failures.add(message);
    }

    public void assertTrue(boolean condition, String message) {
        try {
            Assertions.assertTrue(condition);
            Report.info("Soft assertion is ok, message: "+message);
        } catch (AssertionError e) {
            handleFailure(message,e);
        }
    }


    public void assertFalse(boolean condition, String message) {
        try {
            Assertions.assertFalse(condition);
            Report.info("Soft assertion is ok, message: "+message);
        } catch (AssertionError e) {
            handleFailure(message,e);
        }
    }


    public void assertNull(Object actual, String message) {

        try {
            Assertions.assertNull(actual);
            Report.info("Soft assertion is ok, message: "+message);
        } catch (AssertionError e) {
            handleFailure(message,e);
        }
    }


    public void assertNotNull(Object actual, String message) {

        try {
            Assertions.assertNotNull(actual);
            Report.info("Soft assertion is ok, message: "+message);
        } catch (AssertionError e) {
            handleFailure(message,e);
        }
    }


    public void assertEquals(short expected, short actual, String message) {

        try {
            Assertions.assertEquals(expected, actual);
            Report.info("Soft assertion is ok, message: "+message);
        } catch (AssertionError e) {
            handleFailure(message,e);
        }
    }

    public void assertEquals(byte expected, byte actual, String message) {

        try {
            Assertions.assertEquals(expected, actual);
            Report.info("Soft assertion is ok, message: "+message);
        } catch (AssertionError e) {
            handleFailure(message,e);
        }
    }

    public void assertEquals(int expected, int actual, String message) {

        try {
            Assertions.assertEquals(expected, actual);
            Report.info("Soft assertion is ok, message: "+message);
        } catch (AssertionError e) {
            handleFailure(message,e);
        }
    }


    public void assertEquals(long expected, long actual, String message) {

        try {
            Assertions.assertEquals(expected, actual);
            Report.info("Soft assertion is ok, message: "+message);
        } catch (AssertionError e) {
            handleFailure(message,e);
        }
    }


    public void assertEquals(char expected, char actual, String message) {

        try {
            Assertions.assertEquals(expected, actual);
            Report.info("Soft assertion is ok, message: "+message);
        } catch (AssertionError e) {
            handleFailure(message,e);
        }
    }


    public void assertEquals(float expected, float actual, String message) {

        try {
            Assertions.assertEquals(expected, actual);
            Report.info("Soft assertion is ok, message: "+message);
        } catch (AssertionError e) {
            handleFailure(message,e);
        }
    }


    public void assertEquals(double expected, double actual, String message) {

        try {
            Assertions.assertEquals(expected, actual);
            Report.info("Soft assertion is ok, message: "+message);
        } catch (AssertionError e) {
            handleFailure(message,e);
        }
    }


    public void assertEquals(Object expected, Object actual, String message) {

        try {
            Assertions.assertEquals(expected, actual);
            Report.info("Soft assertion is ok, message: "+message);
        } catch (AssertionError e) {
            handleFailure(message,e);
        }
    }


    public void assertArrayEquals(boolean[] expected, boolean[] actual, String message) {

        try {
            Assertions.assertArrayEquals(expected, actual);
            Report.info("Soft assertion is ok, message: "+message);
        } catch (AssertionError e) {
            handleFailure(message,e);
        }
    }


    public void assertArrayEquals(char[] expected, char[] actual, String message) {

        try {
            Assertions.assertArrayEquals(expected, actual);
            Report.info("Soft assertion is ok, message: "+message);
        } catch (AssertionError e) {
            handleFailure(message,e);
        }
    }


    public void assertArrayEquals(byte[] expected, byte[] actual, String message) {

        try {
            Assertions.assertArrayEquals(expected, actual);
            Report.info("Soft assertion is ok, message: "+message);
        } catch (AssertionError e) {
            handleFailure(message,e);
        }
    }


    public void assertArrayEquals(short[] expected, short[] actual, String message) {

        try {
            Assertions.assertArrayEquals(expected, actual);
            Report.info("Soft assertion is ok, message: "+message);
        } catch (AssertionError e) {
            handleFailure(message,e);
        }
    }


    public void assertArrayEquals(int[] expected, int[] actual, String message) {

        try {
            Assertions.assertArrayEquals(expected, actual);
            Report.info("Soft assertion is ok, message: "+message);
        } catch (AssertionError e) {
            handleFailure(message,e);
        }
    }


    public void assertArrayEquals(long[] expected, long[] actual, String message) {

        try {
            Assertions.assertArrayEquals(expected, actual);
            Report.info("Soft assertion is ok, message: "+message);
        } catch (AssertionError e) {
            handleFailure(message,e);
        }
    }


    public void assertArrayEquals(float[] expected, float[] actual, String message) {

        try {
            Assertions.assertArrayEquals(expected, actual);
            Report.info("Soft assertion is ok, message: "+message);
        } catch (AssertionError e) {
            handleFailure(message,e);
        }
    }


    public void assertArrayEquals(double[] expected, double[] actual, String message) {

        try {
            Assertions.assertArrayEquals(expected, actual);
            Report.info("Soft assertion is ok, message: "+message);
        } catch (AssertionError e) {
            handleFailure(message,e);
        }
    }


    public void assertArrayEquals(Object[] expected, Object[] actual, String message) {

        try {
            Assertions.assertArrayEquals(expected, actual);
            Report.info("Soft assertion is ok, message: "+message);
        } catch (AssertionError e) {
            handleFailure(message,e);
        }
    }


    public void assertNotEquals(Object unexpected, Object actual, String message) {

        try {
            Assertions.assertNotEquals(unexpected, actual);
            Report.info("Soft assertion is ok, message: "+message);
        } catch (AssertionError e) {
            handleFailure(message,e);
        }
    }


    public void assertSame(Object expected, Object actual, String message) {

        try {
            Assertions.assertSame(expected, actual);
            Report.info("Soft assertion is ok, message: "+message);
        } catch (AssertionError e) {
            handleFailure(message,e);
        }
    }


    public void assertNotSame(Object unexpected, Object actual, String message) {

        try {
            Assertions.assertNotSame(unexpected, actual);
            Report.info("Soft assertion is ok, message: "+message);
        } catch (AssertionError e) {
            handleFailure(message,e);
        }
    }


    public void assertDoesNotThrow(Executable executable, String message) {

        try {
            Assertions.assertDoesNotThrow(executable);
            Report.info("Soft assertion is ok, message: "+message);
        } catch (AssertionError e) {
            handleFailure(message,e);
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
            String message = "Soft assertion failure, see above logs for further details. " +
                    "Total " + failures.size() + " failures counted";

            // Clear failures after assertAll is called
            failures.clear();
            throw new SoftAssertionError(message);
        }
    }
}
