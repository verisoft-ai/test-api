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
package co.verisoft.fw.visual_regression_tracker;

import io.visual_regression_tracker.sdk_java.VisualRegressionTrackerConfig;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Singleton class to init VisualRegressionTracker object once.
 *
 * @author Efrat Cohen
 * @since March 2023
 */
@Slf4j
public class VisualRegressionTrackerSingleton {

    private static VisualRegressionTrackerConfig instance = null;

    /**
     * load VRT properties from visual-regression-tracker.properties file
     * if visual-regression-tracker file does not exist in the project path - VisualRegressionTracker object will not be initialized
     *
     * @return Properties - all VRT properties
     */
    public static Properties getVisualRegressionTrackerProperties() {
        // Load VRT properties
        String vrtConfigPath = System.getProperty("user.dir") + "/src/test/resources/visual-regression-tracker.properties";
        Properties appProps = new Properties();
        try {
            appProps.load(new FileInputStream(vrtConfigPath));
        } catch (Exception e) {
            // No VRT property file found
            log.warn("No property file were found in " + vrtConfigPath + " path, please create new properties file in this project path, then rerun the scrip");
        }
        return appProps;
    }

    /**
     * Constructor that init VisualRegressionTrackerConfig object if not initialize already
     *
     * @return instance - new VRT instance
     */
    public static VisualRegressionTrackerConfig getInstance() {

        if (instance == null) {
            // Load VRT properties from visual-regression-tracker.properties file
            Properties appProps = getVisualRegressionTrackerProperties();
            // Init the instance with the desired configuration values from the properties file
            instance = VisualRegressionTrackerConfig.builder()
                    .apiUrl(appProps.getProperty("api.url"))
                    .apiKey(appProps.getProperty("api.key"))
                    .project(appProps.getProperty("project.name"))
                    .branchName(appProps.getProperty("branch.name"))
                    .enableSoftAssert(true)
                    .ciBuildId(appProps.getProperty("ci.build.id"))
                    .build();
        }
        return instance;
    }
}
