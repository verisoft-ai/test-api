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

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.*;

import java.util.Optional;

/**
 * <b>A generic extension implementation.</b><br>
 * This class is meant to serve as an example to copy parts from it and use them for other extension
 * implementations. <br>
 * <b>Note!! You don't have to implement all the listed methods for all the extensions. Use this class as example only,
 * choose the method that fit your needs and implements it's interface only in the extension</b>
 * <p>
 * Some considerations when implementing extensions:<br>
 * 1. You must register the extension at the class level in order for it to be invoked.<br>
 * 2. afterAll and beforeAll work <b>per container</b>, which means that if you run several tests from different test
 * classes these methods will be invoked <b>more than once</b>.<br>
 * 3. You may use more than 1 extension. Junit 5 guarantees that all extension methods will be executed.<br>
 * 4. <b>However!</b> Junit 5 does not guarantee the order of execution. So keep the single responsibility and
 * independence of extensions.
 * </p><br>
 * <p>
 * Some useful links to refer to:<br>
 * 1. Understanding Junit 5 extension model, from <a href="https://junit.org/junit5/docs/current/user-guide/#extensions">Junit5 user guide</a><br>
 * 2. Test lifecycle callback order is listed <a href="https://junit.org/junit5/docs/current/user-guide/#extensions-lifecycle-callbacks">here</a><br>
 * 3. An example of VeriSoft: @see co.verisoft.selenium.framework.extensions.junit.PageSourceSaverExtension
 * 4. Junit <a href="http://javadox.com/org.junit.jupiter/junit-jupiter-api/5.5.2/org/junit/jupiter/api/extension/package-summary.html">extension package javadoc</a>
 * </p> <br>
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a> @ <a href="http://www.verisoft.co">www.VeriSoft.co</a>
 * @since 2.0.3.9
 */
@Slf4j
public class GenericExampleExtension implements
        AfterTestExecutionCallback,
        AfterAllCallback,
        AfterEachCallback,
        BeforeAllCallback,
        BeforeEachCallback,
        BeforeTestExecutionCallback,
        ExtensionContext.Store.CloseableResource,
        TestWatcher {


    // Since the beforeAll method will be invoked for each class registered with this extension, and there are pieces
    // of code we wish to run ONLY ONCE per execution, we mark this flag and use it in the beforeAll method
    private static boolean didRun = false;


    /**
     * Callback that is invoked once <em>after</em> all tests in the current
     * container.
     *
     * @param context the current extension context; never {@code null}
     */
    @Override
    public void afterAll(ExtensionContext context) {

        log.info("GeneralExampleExtension- In afterAll");

    }


    /**
     * Callback that is invoked <em>after</em> an individual test and any
     * user-defined teardown methods for that test have been executed.
     *
     * @param context the current extension context; never {@code null}
     */
    @Override
    public void afterEach(ExtensionContext context) {
        log.info("GeneralExampleExtension- In afterEach");
    }


    /**
     * Callback that is invoked <em>immediately after</em> an individual test has
     * been executed but before any user-defined teardown methods have been
     * executed for that test.
     *
     * @param context the current extension context; never {@code null}
     */
    @Override
    public void afterTestExecution(ExtensionContext context) {
        log.info("GeneralExampleExtension- In afterTestExecution");
    }


    /**
     * Callback that is invoked once <em>before</em> all tests in the current
     * container.
     *
     * @param context the current extension context; never {@code null}
     */
    @Override
    public void beforeAll(ExtensionContext context) {

        // This part will run ONLY ONCE per execution, no matter how many classes register the extension
        if (!didRun) {
            // Set the callback for close method at the end of the session
            context.getRoot().getStore(ExtensionContext.Namespace.GLOBAL).put("ExtensionCallback", this);

            didRun = true;
        }

        log.debug("Registered " + this.getClass().getName() + " for class " + context.getRequiredTestClass().getName());
        log.info("GeneralExampleExtension- In beforeAll");
    }


    /**
     * Callback that is invoked <em>before</em> an individual test and any
     * user-defined setup methods for that test have been executed.
     *
     * @param context the current extension context; never {@code null}
     */
    @Override
    public void beforeEach(ExtensionContext context) {
        log.info("GeneralExampleExtension- In beforeEach");
    }


    /**
     * Callback that is invoked <em>immediately before</em> an individual test is
     * executed but after any user-defined setup methods have been executed
     * for that test.
     *
     * @param context the current extension context; never {@code null}
     */
    @Override
    public void beforeTestExecution(ExtensionContext context) {
        log.info("GeneralExampleExtension- In beforeTestExecution");
    }


    /**
     * Invoked after a disabled test has been skipped.
     *
     * <p>The default implementation does nothing. Concrete implementations can
     * override this method as appropriate.
     *
     * @param context the current extension context; never {@code null}
     * @param reason  the reason the test is disabled; never {@code null} but
     *                potentially <em>empty</em>
     */
    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        String s = "GeneralExampleExtension- Test " + context.getDisplayName() + " was disabled";
        if (reason.isPresent())
            s += ", reason: " + reason;

        log.info(s);
    }


    /**
     * Invoked after a test has completed successfully.
     *
     * <p>The default implementation does nothing. Concrete implementations can
     * override this method as appropriate.
     *
     * @param context the current extension context; never {@code null}
     */
    @Override
    public void testSuccessful(ExtensionContext context) {
        String s = "GeneralExampleExtension- Test " + context.getDisplayName() + " passed";
        log.info(s);
    }


    /**
     * Invoked after a test has been aborted.
     *
     * <p>The default implementation does nothing. Concrete implementations can
     * override this method as appropriate.
     *
     * @param context the current extension context; never {@code null}
     * @param cause   the throwable responsible for the test being aborted; may be {@code null}
     */
    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        String s = "GeneralExampleExtension- Test " + context.getDisplayName() + " aborted";
        s += ", cause: " + cause.getMessage();

        log.info(s);
    }


    /**
     * Invoked after a test has failed.
     *
     * <p>The default implementation does nothing. Concrete implementations can
     * override this method as appropriate.
     *
     * @param context the current extension context; never {@code null}
     * @param cause   the throwable that caused test failure; may be {@code null}
     */
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        String s = "GeneralExampleExtension- Test " + context.getDisplayName() + " failed";
        s += ", cause: " + cause.getMessage();

        log.info(s);
    }


    /**
     * Invoked after all tests have been executed, from all classes. This method will run while junit engine is
     * shutting donwn.
     */
    @Override
    public void close() {
        log.info("This is the last method, after all other methods and extension methods are performed");
    }
}
