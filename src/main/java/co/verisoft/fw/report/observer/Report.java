package co.verisoft.fw.report.observer;

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

/**
 * Utility class to simplify the creation of a report entry.
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @since 0.0.5 (Jan 2023)
 */
public final class Report {
    private Report() {
    }


    public static void report(ReportSource type, ReportLevel level, String msg, Object object) {
        ReportEntry reportEntry = ReportEntry.builder()
                .reportSource(type)
                .reportLevel(level)
                .msg(msg)
                .additionalObject(object)
                .build();

        ReportPublisher.getInstance().notifyObserver(reportEntry);
    }


    public static void report(ReportSource type, ReportLevel level, String msg) {
        ReportEntry reportEntry = ReportEntry.builder()
                .reportSource(type)
                .reportLevel(level)
                .msg(msg)
                .build();

        ReportPublisher.getInstance().notifyObserver(reportEntry);
    }


    public static void report(ReportLevel level, String msg) {
        ReportEntry reportEntry = ReportEntry.builder()
                .reportSource(ReportSource.OTHER)
                .reportLevel(level)
                .msg(msg)
                .build();

        ReportPublisher.getInstance().notifyObserver(reportEntry);
    }


    public static void report(ReportSource type, String msg) {
        ReportEntry reportEntry = ReportEntry.builder()
                .reportSource(type)
                .reportLevel(ReportLevel.INFO)
                .msg(msg)
                .build();

        ReportPublisher.getInstance().notifyObserver(reportEntry);
    }


    public static void report(String msg) {
        ReportEntry reportEntry = ReportEntry.builder()
                .reportSource(ReportSource.OTHER)
                .reportLevel(ReportLevel.INFO)
                .msg(msg)
                .build();

        ReportPublisher.getInstance().notifyObserver(reportEntry);
    }

    public static void report(String msg, Object object) {
        ReportEntry reportEntry = ReportEntry.builder()
                .reportSource(ReportSource.OTHER)
                .reportLevel(ReportLevel.INFO)
                .msg(msg)
                .additionalObject(object)
                .build();

        ReportPublisher.getInstance().notifyObserver(reportEntry);
    }


    public static void report(ReportEntry reportEntry) {
        ReportPublisher.getInstance().notifyObserver(reportEntry);
    }
}
