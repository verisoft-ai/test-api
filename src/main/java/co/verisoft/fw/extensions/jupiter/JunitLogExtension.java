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
package co.verisoft.fw.extensions.jupiter;

import co.verisoft.fw.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.*;

/**
 * Write information to the log, according to the test life cycle (before all, before each, before
 * test execution, after test execution, after each, after all)
 *
 * @author David Yehezkel
 * @since 1.9.6
 */
@Slf4j
@SuppressWarnings("unused")
public class JunitLogExtension implements BeforeAllCallback, BeforeEachCallback, BeforeTestExecutionCallback, AfterTestExecutionCallback, AfterEachCallback, AfterAllCallback {


    @Override
    public void afterAll(ExtensionContext context) {
        if (context.getTestClass().isEmpty())
            return;

        log.info("After All : " + context.getTestClass().get().getName());
    }


    @Override
    public void afterEach(ExtensionContext context) {
        if (context.getTestClass().isEmpty())
            return;

        //noinspection OptionalGetWithoutIsPresent
        log.info(String.format("After Each : %s", context.getTestMethod().get().getName()));
    }


    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Override
    public void afterTestExecution(ExtensionContext context) {

        if (context.getTestClass().isEmpty())
            return;

        if (context.getExecutionException().isPresent()) {
            log.info("After Test Execution -Test result : FAIL. Test name: " + context.getTestMethod().get().getName());
            log.debug("Stack trace is: " + Utils.getStackTrace(context.getExecutionException().get()));
        } else
            log.info("After Test Execution -Test result : PASS. Test name: " + context.getTestMethod().get().getName());
    }


    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Override
    public void beforeTestExecution(ExtensionContext context) {
        if (context.getTestClass().isEmpty())
            return;

        log.info("Before Test Execution : " + context.getTestMethod().get().getName());
    }


    @Override
    public void beforeEach(ExtensionContext context) {
        if (context.getTestClass().isEmpty())
            return;

        //noinspection OptionalGetWithoutIsPresent
        log.info("Before Each : " + context.getTestMethod().get().getName());
    }


    @Override
    public void beforeAll(ExtensionContext context) {
        if (context.getTestClass().isEmpty())
            return;

        log.debug("Registered " + this.getClass().getName() + " for class " + context.getRequiredTestClass().getName());
        log.info("Before All : " + context.getTestClass().get().getName());
    }
}
