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
package co.verisoft.fw.report.observer;

import com.google.common.html.HtmlEscapers;
import lombok.Synchronized;

/**
 * Utility class to simplify the creation of a report entry.
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @since 0.0.5 (Jan 2023)
 */
public final class Report {
    private Report() {
    }


    public static void report(ReportEntry reportEntry) {
        ReportPublisher.getInstance().notifyObserver(reportEntry);
    }


    @Synchronized
    public static void report(ReportSource source, ReportLevel level, String msg, Object object) {
        ReportEntry reportEntry = ReportEntry.builder()
                .reportSource(source)
                .reportLevel(level)
                .msg(HtmlEscapers.htmlEscaper().escape(msg))
                .additionalObject(object)
                .build();

        ReportPublisher.getInstance().notifyObserver(reportEntry);
    }


    public static void report(ReportSource source, ReportLevel level, String msg) {
        report(source, level, msg, null);
    }



    public static void trace(ReportSource source, String msg, Object object) {
        report(source, ReportLevel.TRACE, msg, object);
    }


    public static void trace(ReportSource source, String msg) {
        report(source, ReportLevel.TRACE, msg);
    }


    public static void trace(String msg, Object object) {
        report(ReportSource.OTHER, ReportLevel.TRACE, msg, object);
    }


    public static void trace(String msg) {
        report(ReportSource.OTHER, ReportLevel.TRACE, msg);
    }


    public static void debug(ReportSource source, String msg, Object object) {
        report(source, ReportLevel.DEBUG, msg, object);
    }


    public static void debug(ReportSource source, String msg) {
        report(source, ReportLevel.DEBUG, msg);
    }


    public static void debug(String msg, Object object) {
        report(ReportSource.OTHER, ReportLevel.DEBUG, msg, object);
    }


    public static void debug(String msg) {
        report(ReportSource.OTHER, ReportLevel.DEBUG, msg);
    }


    public static void info(ReportSource source, String msg, Object object) {
        report(source, ReportLevel.INFO, msg, object);
    }


    public static void info(ReportSource source, String msg) {
        report(source, ReportLevel.INFO, msg);
    }


    public static void info(String msg, Object object) {
        report(ReportSource.OTHER, ReportLevel.INFO, msg, object);
    }


    public static void info(String msg) {
        report(ReportSource.OTHER, ReportLevel.INFO, msg);
    }


    public static void warn(ReportSource source, String msg, Object object) {
        report(source, ReportLevel.WARNING, msg, object);
    }


    public static void warn(ReportSource source, String msg) {
        report(source, ReportLevel.WARNING, msg);
    }


    public static void warn(String msg, Object object) {
        report(ReportSource.OTHER, ReportLevel.WARNING, msg, object);
    }


    public static void warn(String msg) {
        report(ReportSource.OTHER, ReportLevel.WARNING, msg);
    }


    public static void error(ReportSource source, String msg, Object object) {
        report(source, ReportLevel.ERROR, msg, object);
    }


    public static void error(ReportSource source, String msg) {
        report(source, ReportLevel.ERROR, msg);
    }


    public static void error(String msg, Object object) {
        report(ReportSource.OTHER, ReportLevel.ERROR, msg, object);
    }


    public static void error(String msg) {
        report(ReportSource.OTHER, ReportLevel.ERROR, msg);
    }


    public static void fatal(ReportSource source, String msg, Object object) {
        report(source, ReportLevel.FATAL, msg, object);
    }


    public static void fatal(ReportSource source, String msg) {
        report(source, ReportLevel.FATAL, msg);
    }


    public static void fatal(String msg, Object object) {
        report(ReportSource.OTHER, ReportLevel.FATAL, msg, object);
    }


    public static void fatal(String msg) {
        report(ReportSource.OTHER, ReportLevel.FATAL, msg);
    }
}
