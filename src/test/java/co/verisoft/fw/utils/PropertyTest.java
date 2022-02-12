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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.slf4j.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@Execution(ExecutionMode.CONCURRENT)
public class PropertyTest {

    private static final Logger logger = new ExtendedLog(PropertyTest.class);

    @Test
    public void loadPropertiesFileByPath() {
        Property property = new Property(
                System.getProperty("user.dir") + "/src/test/resources/specific.config.properties");
        assertEquals("specific_file", property.getProperty("str"));
    }


    @Test
    public void getIntProperty() {
        Property property = new Property(
                System.getProperty("user.dir") + "/src/test/resources/specific.config.properties");
        assertEquals(500, property.getIntProperty("int"));
    }

    @Test
    public void getIntPropertyValueIsString() {
        logger.info("This test will invoke a warning log messge 'specific_file cannot parse it to int' . " +
                "IT IS A NORMAL BEHAVIOR");
        Property property = new Property(
                System.getProperty("user.dir") + "/src/test/resources/specific.config.properties");
        assertNull(property.getIntProperty("str"));
    }

    @Test
    public void getBooleanPropertyValueIsTrue() {
        Property property = new Property(
                System.getProperty("user.dir") + "/src/test/resources/specific.config.properties");
        assertEquals(true, property.getBooleanProperty("boolean_True"));
    }

    @Test
    public void getBooleanPropertyValueIsFalse() {

        Property property = new Property(
                System.getProperty("user.dir") + "/src/test/resources/specific.config.properties");
        assertEquals(false, property.getBooleanProperty("boolean_false"));
    }

    @Test
    public void getDoubleProperty() {

        Property property = new Property(
                System.getProperty("user.dir") + "/src/test/resources/specific.config.properties");
        assertEquals(52.23, property.getDoubleProperty("double"));
    }

    @Test
    public void getDoublePropertyValueIsString() {
        logger.info("This test will invoke a warning log messge 'specific_file cannot parse it to double' . " +
                "IT IS A NORMAL BEHAVIOR");
        Property property = new Property(
                System.getProperty("user.dir") + "/src/test/resources/specific.config.properties");
        assertNull(property.getDoubleProperty("str"));
    }

    @Test
    public void setNewValue() {
        Property property = new Property(
                System.getProperty("user.dir") + "/src/test/resources/specific.config.properties");
        property.setProperty("new", "new value");
        assertEquals("new value", property.getProperty("new"));
    }

    @Test
    public void shouldLoadDefaultPropertyFile() {
        Property property = new Property();
        assertEquals(3, property.getIntProperty("max_retry_number"));
    }

    @Test
    public void shouldFallbackToDefaultPropertyFile() {
        Property property = new Property("noName.properties");
        assertEquals(3, property.getIntProperty("max_retry_number"));
    }

    @Test
    public void removeValue() {
        Property property = new Property(
                System.getProperty("user.dir") + "/src/test/resources/specific.config.properties");
        assertEquals("forRemove", property.getProperty("for_remove"));
        property.removeProperty("for_remove");
        assertEquals(null, property.getProperty("for_remove"));

        // put it back
        property.setProperty("for_remove", "forRemove");
    }
}
