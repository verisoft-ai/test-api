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

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

/**
 * Decorator for java.util.Properties that handles config.properties file. <br>
 * Extension functionality load properties file allowing load file by parameter
 * or default.
 * <p>
 * Extension functionality get from Properties.<br>
 * So it is possible get the parameter according to the parameter type and not
 * only String also possible remove and set property (from Properties object,
 * not from file),<br>
 * using java.util.Properties functionality <b>Example #1</b><br>
 * The following example get int property from default project properties file
 * final int i = new Property().getIntProperty("keyForintVal");
 * <b>Example #2</b><br>
 * The following example get boolean property from specific properties file
 * boolean b=new
 * Property("C:/data/config.properties").getBooleanProperty("keyForBoolVal")
 *
 * @author <a href="mailto:yael.rozenfeld@verisoft.co">Yael Rozenfeld</a> @
 * <a href="http://www.verisoft.co">www.VeriSoft.co</a>
 * @since 2.0.3.9
 */
@ToString
@Slf4j
public class Property {

    @Getter
    private final Properties properties;

    /**
     * private c-tor. Handle c-tor with path parm and without param initial instance
     * of Property object with properties file <br>
     * (default or according to param).
     *
     * @param pathToPropertyFile - optional- path to properties file<br>
     *                           - in case don't interested use default
     */
    private Property(Optional<String> pathToPropertyFile) {
        // Get the path to the file and initial properties object
        String propertyFile = pathToPropertyFile.orElse("");
        properties = initPropertyObject(propertyFile);
    }

    /**
     * c-tor - will load properties from default file
     */
    public Property() {
        properties = new Properties();
        try {
            properties.load(Property.class.getClassLoader().getResourceAsStream("root.config.properties"));
        } catch (Exception e) {
            log.error("Could not initialize root.config.properties file. " + Utils.getStackTrace(e));
        }

    }

    /**
     * c-tor - will load properties from specific file in case file not found - will
     * load default.
     *
     * @param pathToProperty - path to specific properties file
     */
    public Property(String pathToProperty) {
        this(Optional.of(pathToProperty));
    }

    /**
     * initial properties object - load properties file to object
     *
     * @param path - path to property file or "" for load default.
     */
    private Properties initPropertyObject(String path) {
        Properties prop = new Properties();

        String absolutePath = "";
        try {
            absolutePath = Objects.requireNonNull(getClass().getClassLoader().getResource(path)).getPath();
        } catch (NullPointerException e) {
            log.error("Failed to load resource file " + path +  " System will load default property file");
        }


        try {
            if (!path.isEmpty() && !absolutePath.isEmpty()) {
                // load properties file according to parameter
                FileInputStream inputStream = new FileInputStream(absolutePath);
                prop.load(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            } else {
                // load default properties file
                prop.load(Property.class.getClassLoader().getResourceAsStream("root.config.properties"));
            }

        } catch (Throwable t) {
            log.error("Could not initialize property file. " + Utils.getStackTrace(t));
        }
        return prop;
    }

    /**
     * return the value by fine the key send from user parameter
     *
     * @param key - parameter key
     * @return return string type value of key if key dosn't exist return null
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * set new property by key and value set property in stuck. dons't in the
     * config.properties file
     *
     * @param key   Key to set
     * @param value Value to set
     */
    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }

    /**
     * remove property from stuck dons't erase from the config.properties file
     *
     * @param key Key to remove
     */
    public void removeProperty(String key) {
        properties.remove(key);
    }

    /**
     * get property by key and convert it to type Integer from String Note! If you
     * are using <code>int x= getIntProperty("some_value") </code><br>
     * and the result is NULL, an exception will be thrown since you are trying to
     * assign null into a basic java type, which cannot contain null.
     *
     * @param key the key to look for and return it's value
     * @return Integer value. if dosn't match to Integer return null
     */
    public Integer getIntProperty(String key) {
        try {
            return Integer.parseInt(getProperty(key));
        } catch (NumberFormatException numberFormatExeption) {
            log.warn("KEY: " + key + " VALUE: " + getProperty(key) + " - cannot parse to int");
            return null;
        }
    }

    /**
     * get property by key and convert it to type Double from String Note! If you
     * are using <code>double x= getIntProperty("some_value") </code><br>
     * and the result is NULL, an exception will be thrown since you are trying to
     * assign null into a basic java type, which cannot contain null.
     *
     * @param key the key to look for and return it's value
     * @return Double value. if dosn't match to Double will throw exception
     */
    public Double getDoubleProperty(String key) {
        try {
            return Double.parseDouble(getProperty(key));
        } catch (NumberFormatException numberFormatExeption) {
            log.warn("KEY: " + key + " VALUE: " + getProperty(key) + " - cannot parse to double");
            return null;
        }
    }

    /**
     * return boolean value if the key (String) is equals to True || true return
     * true other wise return false
     *
     * @param key to retrieve
     * @return true if key equals to True || true false other wise
     */
    public boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(getProperty(key));
    }

}