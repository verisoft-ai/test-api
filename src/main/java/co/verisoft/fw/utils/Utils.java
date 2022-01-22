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

import lombok.Synchronized;

/**
 * Utilities class
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @since 0.0.2 (Jan 2022)
 */
public final class Utils {

    private Utils() { /* Do not instantiate */ }


    /**
     * get the stack trace from throwable exception
     *
     * @param e throwable object to extract stack trace from
     * @return string equal to throwable exception from the stack trace
     */
    @Synchronized
    public static String getStackTrace(Throwable e) {
        StringBuilder sb = new StringBuilder(500);
        StackTraceElement[] st = e.getStackTrace();
        sb.append(e.getClass().getName()).append(": ").append(e.getMessage()).append("\n");
        for (StackTraceElement stackTraceElement : st) sb.append("\t at ").append(stackTraceElement.toString()).append("\n");
        return sb.toString();
    }
}
