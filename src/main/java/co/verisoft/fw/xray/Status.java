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

/**
 * Jira Xray statuses
 */
@API(
        status = API.Status.EXPERIMENTAL,
        since = "0.0.2"
)
public enum Status {
    TODO,
    FAILED,
    PASSED,
    EXECUTING;


    /**
     * Converts a string to status enum. If string is not recognized, default is Status.TODO
     * @param status string based status report
     * @return a status object, after parsed
     */
    @Synchronized
    public static Status toStatus(String status) {
        Status result;
        switch (status.toUpperCase(Locale.ROOT)) {
            case "FAILED":
                result = Status.FAILED;
                break;
            case "PASSED":
                result = Status.PASSED;
                break;
            case "EXECUTING":
                result = Status.EXECUTING;
                break;
            default:
                result = Status.TODO;
                break;
        }
        return result;
    }
}
