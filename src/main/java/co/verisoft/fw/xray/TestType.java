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

import lombok.Synchronized;
import org.apiguardian.api.API;

import java.util.Locale;

@API(
        status = API.Status.EXPERIMENTAL,
        since = "0.0.2"
)
public enum TestType {
    CUCUMBER,
    MANUAL,
    GENERIC;

    /**
     * Converts a string to TestType enum. If string is not recognized, default is TestType.GENERIC
     * @param type string based TestType report
     * @return a TestType object, after parsed
     */
    @Synchronized
    public static TestType toType(String type) {
        TestType result;
        switch (type.toUpperCase(Locale.ROOT)) {
            case "CUCUMBER":
                result = TestType.CUCUMBER;
                break;
            case "MANUAL":
                result = TestType.MANUAL;
                break;
            default:
                result = TestType.GENERIC;
                break;
        }
        return result;
    }
}
