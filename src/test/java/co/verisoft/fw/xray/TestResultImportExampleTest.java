package co.verisoft.fw.xray;
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

import co.verisoft.fw.extensions.jupiter.PropertyLoaderExtension;
import co.verisoft.fw.extensions.jupiter.XrayPluginExtension;
import org.apache.xmlbeans.SystemProperties;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.FileWriter;
import java.time.ZonedDateTime;

/**
 * Example class for creating a result json object fo xray tests
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @since 0.0.2 (Jan 2022)
 */
@SuppressWarnings("unchecked")
@ExtendWith({XrayPluginExtension.class, PropertyLoaderExtension.class})
public class TestResultImportExampleTest {

    @Test
    @XrayIdentifier("NIR-1")
    public void resultWithTestExecutionKey() {

        XrayJsonTestObject tests = new XrayJsonTestObject.XrayJsonTestObjectBuilder()
                .start(ZonedDateTime.parse("2022-01-06T11:40:11+02"))
                .finish(ZonedDateTime.parse("2022-01-06T11:44:24+02"))
                .testKey("VER-3")
                .status(Status.PASSED)
                .build();
        //noinspection MismatchedQueryAndUpdateOfCollection
        JSONObject obj = new JSONObject();
        obj.put("testExecutionKey", "VER-5");
        JSONArray arr = new JSONArray();
        arr.add(tests.asJsonObject());
        obj.put("tests", arr);
    }

    @Test
    @XrayIdentifier("NIR-2")
    public void resultWithTestPlanKey() {

        XrayJsonTestObject tests = new XrayJsonTestObject.XrayJsonTestObjectBuilder()
                .start(ZonedDateTime.parse("2022-01-06T11:40:11+02"))
                .finish(ZonedDateTime.parse("2022-01-06T11:44:24+02"))
                .comment("This is a comment")
                .testKey("VER-3")
                .defect("VER-6")
                .status(Status.PASSED)
                .build();

        XrayJsonInfoObject info1 = new XrayJsonInfoObject.XrayInfoObjectBuilder()
                .summary("Execution Report demo for DOKA")
                .startDate(ZonedDateTime.parse("2022-01-06T11:40:11+02"))
                .finishDate(ZonedDateTime.parse("2022-01-06T11:44:24+02"))
                .testPlanKey("VER-4")
                .build();


        XrayJsonFormatObject f = new XrayJsonFormatObject.XrayJsonFormatObjectBuilder()
                .info(info1)
                .test(tests)
                .build();

        try {
            FileWriter file = new FileWriter(SystemProperties.getProperty("user.dir") + "/target/result.json");
            file.write(f.asString());
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
