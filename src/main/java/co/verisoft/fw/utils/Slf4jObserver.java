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

import co.verisoft.fw.report.observer.BaseObserver;
import co.verisoft.fw.report.observer.ReportEntry;
import co.verisoft.fw.report.observer.ReportLevel;
import lombok.extern.log4j.Log4j2;


/**
 * A combination of Log4j appender with report observer.
 * Make sure to register the custom Appender in the log4j2.xml file
 * for more information and example, <a href="https://www.baeldung.com/log4j2-custom-appender">see blog</a>
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @since 0.0.5 (Jan 2023)
 */
@Log4j2
public class Slf4jObserver extends BaseObserver {


    @Override
    public void update(ReportEntry reportEntry) {

        // Build the report message
        String reportMsg = reportEntry.getMsg();
        reportMsg += " (Report source: " + reportEntry.getReportSource() + ")";

        if (reportEntry.getAdditionalObject() != null)
            reportMsg += " Additional object data is: " + reportEntry.getAdditionalObject();


        ReportLevel reportLevel = reportEntry.getReportLevel();

        if (reportLevel.equals(ReportLevel.TRACE))
            log.trace(reportMsg);
        else if (reportLevel.equals(ReportLevel.DEBUG))
            log.debug(reportMsg);
        else if (reportLevel.equals(ReportLevel.INFO))
            log.info(reportMsg);
        else if (reportLevel.equals(ReportLevel.WARNING))
            log.warn(reportMsg);
        else if (reportLevel.equals(ReportLevel.ERROR))
            log.error(reportMsg);
        else if (reportLevel.equals(ReportLevel.FATAL))
            log.error(reportMsg);
    }
}
