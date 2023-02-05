package co.verisoft.fw.extentreport;

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
import com.aventstack.extentreports.Status;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.Objects;


/**
 * An implementation of the rerport observer mechanism for extent report.
 * Currently, only info level entries and higher are written t
 */
@Log4j2
public class ExtentReportReportObserver extends BaseObserver {

    @Getter
    private final ReportLevel minReportLevel;

    public ExtentReportReportObserver(ReportLevel minReportLevel) {
        this.minReportLevel = minReportLevel;
    }

    @Override
    public void update(ReportEntry reportEntry) {

        if (reportEntry.getReportLevel().compareTo(minReportLevel) < 0)
            return;

        // Determine if entry should be printed to extent report, and the proper level of entry
        Status status;
        if (reportEntry.getReportLevel() == ReportLevel.DEBUG)
            status = Status.INFO;
        else if (reportEntry.getReportLevel() == ReportLevel.INFO)
            status = Status.INFO;
        else if (reportEntry.getReportLevel() == ReportLevel.WARNING)
            status = Status.WARNING;
        else if (reportEntry.getReportLevel() == ReportLevel.ERROR)
            status = Status.WARNING;
        else if (reportEntry.getReportLevel() == ReportLevel.FATAL)
            status = Status.WARNING;
        else
            status = Status.INFO;

        // Build the report message
        String reportMsg = reportEntry.getMsg();

        if (reportEntry.getAdditionalObject() != null)
            reportMsg += " Additional object data is: " + reportEntry.getAdditionalObject();

        // Write to report
        Objects.requireNonNull(ReportManager.getInstance().getCurrentTest()).log(status, reportMsg);
    }
}