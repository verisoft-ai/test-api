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

import co.verisoft.fw.report.observer.*;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.*;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;

/**
 * A combination of Log4j appender with report observer.
 * Make sure to register the custom Appender in the log4j2.xml file
 * for more information and example, <a href="https://www.baeldung.com/log4j2-custom-appender">see blog</a>
 * TODO: Fix issue with SLF4J and Log4j2 - should be SLF4J
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @since 0.0.5 (Jan 2023)
 */
@Log4j2
@Plugin(name = "Log4jObserverAppender", category = Core.CATEGORY_NAME, elementType = Appender.ELEMENT_TYPE)
public class Log4jObserverAppender extends AbstractAppender implements Observer {

    private final Layout<? extends Serializable> layout;

    public Log4jObserverAppender(final String name, final Layout<? extends Serializable> layout, final Filter filter,
                                 final boolean ignoreExceptions) {

        super(name, filter, layout);
        this.layout = layout;
        ReportPublisher.getInstance().register(this);
    }

    @Override
    public void append(LogEvent event) {

        // Parse the message
        String message = "";
        try {
            message = new String(layout.toByteArray(event), StandardCharsets.UTF_8);
        } catch (Exception e) {
            // If the log failed, we want the test to fail. In production log appender we would handle this exception
            error("Unable to log message in appender [" + getName() + "]", event, e);
        }

        // Parse the level
        ReportLevel level = null;
        if (event.getLevel().equals(Level.TRACE)) {
            level = ReportLevel.TRACE;
        } else if (event.getLevel().equals(Level.DEBUG)) {
            level = ReportLevel.DEBUG;
        } else if (event.getLevel().equals(Level.INFO)) {
            level = ReportLevel.INFO;
        } else if (event.getLevel().equals(Level.WARN)) {
            level = ReportLevel.WARNING;
        } else if (event.getLevel().equals(Level.ERROR)) {
            level = ReportLevel.ERROR;
        } else if (event.getLevel().equals(Level.FATAL)) {
            level = ReportLevel.FATAL;
        }

        ReportEntry reportEntry = ReportEntry.builder()
                .reportLevel(level)
                .msg(message)
                .reportSource(ReportSource.LOG)
                .build();

        Report.report(reportEntry);
    }

    @Override
    public void update(ReportEntry reportEntry) {

        // No need for double log entry
        if (!reportEntry.getReportSource().equals(ReportSource.LOG))
            return;

        // Build the report message
        String reportMsg = reportEntry.getMsg();

        if (reportEntry.getAdditionalObject() != null)
            reportMsg += " Additional object data is: " + reportEntry.getAdditionalObject();

        reportMsg += " (Report source: " + reportEntry.getReportSource() + ")";


        // Write the message
        if (reportEntry.getReportLevel() == ReportLevel.TRACE)
            log.trace(reportMsg);
        else if (reportEntry.getReportLevel() == ReportLevel.DEBUG)
            log.debug(reportMsg);
        else if (reportEntry.getReportLevel() == ReportLevel.INFO)
            log.info(reportMsg);
        if (reportEntry.getReportLevel() == ReportLevel.WARNING)
            log.warn(reportMsg);
        else if (reportEntry.getReportLevel() == ReportLevel.ERROR)
            log.error(reportMsg);
        else if (reportEntry.getReportLevel() == ReportLevel.FATAL)
            log.error(reportMsg);
        else
            log.info(reportMsg);
    }


    @PluginFactory
    public static Log4jObserverAppender createAppender(
            @PluginAttribute("name") String name,
            @PluginElement("Layout") Layout<? extends Serializable> layout,
            @PluginElement("Filter") final Filter filter) {
        if (name == null) {
            LOGGER.error("No name provided for MyCustomAppenderImpl");
            return null;
        }
        if (layout == null) {
            layout = PatternLayout.createDefaultLayout();
        }
        return new Log4jObserverAppender(name, layout, filter, true);
    }
}
