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
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Extension to load properties at the beginning of the test run. Extension goes through property files at the
 * BeforeAll stage (only once in the test life cycle), and if the properties were no loaded, it is being loaded from
 * the properties file. If the property file was not found (not provided, missing etc.) the class will rely only on
 * the injected (-D) parameters, which result to null if missing, without the property file fallback.<br><br>
 * <p>
 * TODO: The property file back up solution is not best pratice and relies on property files located in the test app
 * itself. Should find a bettern solution
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a> @ <a href="http://www.verisoft.co">www.VeriSoft.co</a>
 * @since 0.0.2 (Jan 2022)
 */
@Slf4j
public class PropertyLoaderExtension implements BeforeAllCallback {

    private static boolean didRun = false;

    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        if (!didRun) {
            setXrayProperties();
            didRun = true;
        }

        log.debug("Registered " + this.getClass().getName() + " for class " + extensionContext.getRequiredTestClass().getName());
    }


    /**
     * Loads Xray plugin properties from /src/test/resources/xray-plugin.properties if no data was injected during
     * start-up. If the property file is not found - return and let the system rely on injected properties only (-D)
     */
    private void setXrayProperties() {
        String xrayConfigPath = System.getProperty("user.dir") + "/src/test/resources/xray-plugin.properties";
        Properties appProps = new Properties();
        try {
            appProps.load(new FileInputStream(xrayConfigPath));
        } catch (Exception e) {
            // No property file found - rely on -D only
            log.warn("No property file were found: " + xrayConfigPath + ", will rely on injection (-D) only");
            return;
        }

        if (System.getProperty("xray.testExecution.key") == null)
            System.setProperty("xray.testExecution.key", appProps.getProperty("xray.testExecution.key"));

        if (System.getProperty("xray.testPlan.key") == null)
            System.setProperty("xray.testPlan.key", appProps.getProperty("xray.testPlan.key"));
    }
}
