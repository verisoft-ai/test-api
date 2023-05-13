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
package co.verisoft.fw;

import co.verisoft.fw.report.observer.BaseObserver;
import co.verisoft.fw.report.observer.ReportEntry;
import co.verisoft.fw.report.observer.ReportLevel;
import com.epam.reportportal.service.ReportPortal;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.Date;


/**
 * An implementation of the rerport observer mechanism for report portal.
 * Currently, only info level entries and higher are written t
 */
@Slf4j
public class ReportPortalObserver extends BaseObserver {

    @Getter
    private final ReportLevel minReportLevel;

    public ReportPortalObserver(ReportLevel minReportLevel) {
        this.minReportLevel = minReportLevel;
    }

    @Override
    public void update(ReportEntry reportEntry) {

        if (reportEntry.getReportLevel().compareTo(minReportLevel) < 0)
            return;

        // Determine if entry should be printed to extent report, and the proper level of entry
        ReportLevel level;
        if (reportEntry.getReportLevel() == ReportLevel.DEBUG)
            level = ReportLevel.DEBUG;
        else if (reportEntry.getReportLevel() == ReportLevel.INFO)
            level = ReportLevel.INFO;
        else if (reportEntry.getReportLevel() == ReportLevel.WARNING)
            level = ReportLevel.WARNING;
        else if (reportEntry.getReportLevel() == ReportLevel.ERROR)
            level = ReportLevel.ERROR;
        else if (reportEntry.getReportLevel() == ReportLevel.FATAL)
            level = ReportLevel.FATAL;
        else
            level = ReportLevel.INFO;

        // Build the report message
        String reportMsg = reportEntry.getMsg();

        // Write to report
        if(reportEntry.getAdditionalObject() == null){
            ReportPortal.emitLog(reportMsg, level.name(), new Date());
        }
        else if (reportEntry.getAdditionalObject() instanceof File){
            ReportPortal.emitLog(reportMsg, level.name(), new Date(), ((File) reportEntry.getAdditionalObject()));
        }
    }
}
