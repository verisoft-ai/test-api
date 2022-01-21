package co.verisoft.fw.extensions.jupiter;

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

import co.verisoft.fw.Utils;
import co.verisoft.selenium.framework.inf.ExtendedLog;
import org.junit.jupiter.api.extension.*;
import org.slf4j.Logger;

/**
 * Write information to the log, according to the test life cycle (before all, before each, before
 * test execution, after test execution, after each, after all)
 *
 * @author David Yehezkel
 * @since 1.9.6
 */
public class JunitLogExtension implements BeforeAllCallback, BeforeEachCallback, BeforeTestExecutionCallback, AfterTestExecutionCallback, AfterEachCallback, AfterAllCallback {

    private static final Logger logger = new ExtendedLog(JunitLogExtension.class);

    @Override
    public void afterAll(ExtensionContext context) {
        if (!context.getTestClass().isPresent())
            return;

        logger.info("After All : " + context.getTestClass().get().getName());
    }

    @Override
    public void afterEach(ExtensionContext context) {
        if (!context.getTestClass().isPresent())
            return;

        //noinspection OptionalGetWithoutIsPresent
        logger.info(String.format("After Each : %s", context.getTestMethod().get().getName()));
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Override
    public void afterTestExecution(ExtensionContext context) {

        if (!context.getTestClass().isPresent())
            return;

        if (context.getExecutionException().isPresent()) {
            logger.info("After Test Execution -Test result : FAIL. Test name: " + context.getTestMethod().get().getName());
            logger.debug("Stack trace is: " + Utils.getStackTrace(context.getExecutionException().get()));
        } else
            logger.info("After Test Execution -Test result : PASS. Test name: " + context.getTestMethod().get().getName());
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Override
    public void beforeTestExecution(ExtensionContext context) {
        if (!context.getTestClass().isPresent())
            return;

        logger.info("Before Test Execution : " + context.getTestMethod().get().getName());
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        if (!context.getTestClass().isPresent())
            return;

        //noinspection OptionalGetWithoutIsPresent
        logger.info("Before Each : " + context.getTestMethod().get().getName());
    }

    @Override
    public void beforeAll(ExtensionContext context) {
        if (!context.getTestClass().isPresent())
            return;

        logger.info("Before All : " + context.getTestClass().get().getName());
    }
}
