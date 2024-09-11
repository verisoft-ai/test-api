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
package co.verisoft.fw.softAsserts;

import co.verisoft.fw.asserts.SoftAssertionError;
import co.verisoft.fw.extentreport.ExtentReport;
import co.verisoft.fw.asserts.SoftAsserts;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
@ExtentReport
//TODO: Add all types of asserts to unit tests.
public class SoftAssertsTest {

    private SoftAsserts softAsserts = new SoftAsserts();

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
