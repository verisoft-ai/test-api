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
package co.verisoft.fw.extentreport;

import co.verisoft.fw.report.observer.BaseObserver;
import co.verisoft.fw.report.observer.ReportEntry;
import co.verisoft.fw.report.observer.ReportLevel;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;
import lombok.Getter;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.File;
import java.util.Objects;


/**
 * An implementation of the rerport observer mechanism for extent report.
 * Currently, only info level entries and higher are written t
 */
@Slf4j
public class ExtentReportReportObserver extends BaseObserver {

    @Getter
    private final ReportLevel minReportLevel;

    public ExtentReportReportObserver(ReportLevel minReportLevel) {
        this.minReportLevel = minReportLevel;
    }

    /**
     * Computes the relative path from a base path to a target path.
     * This method converts both the base path and the target path to absolute paths,
     * then calculates the relative path from the base path to the target path. The result
     * is returned as a string representation of the relative path.
     *
     * @param targetPath The path to which the relative path is computed.
     * @param basePath   The base path from which the relative path is calculated.
     * @return A string representing the relative path from the base path to the target path.
     */
    public static String RelativePath(String targetPath, String basePath) {
        Path base = Paths.get(basePath).toAbsolutePath();
        Path target = Paths.get(targetPath).toAbsolutePath();
        Path relativePath = base.relativize(target);
        return relativePath.toString();
    }

    @Override
    @Synchronized
    public void update(ReportEntry reportEntry) {

        if (reportEntry.getReportLevel().compareTo(minReportLevel) < 0)
            return;
        if (Objects.isNull(ReportManager.getInstance().getCurrentTest()))
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

        // Write to report
        if(reportEntry.getAdditionalObject() == null){
            Objects.requireNonNull(ReportManager.getInstance().getCurrentTest()).log(status, reportMsg);
        }
        else if(reportEntry.getAdditionalObject() instanceof ExtentReportData){
            ExtentReportData extentReportData = (ExtentReportData) reportEntry.getAdditionalObject();
            if (extentReportData.getType() == ExtentReportData.Type.SCREENSHOT){
                Media media = MediaEntityBuilder
                        .createScreenCaptureFromPath(RelativePath((String)((ExtentReportData) reportEntry.getAdditionalObject()).getData(),"target/Extent-Report/")).build();
                Objects.requireNonNull(ReportManager.getInstance().getCurrentTest()).log(status, reportMsg, media);
            }
            else if (extentReportData.getType() == ExtentReportData.Type.THROWABLE){
                Throwable throwable = (Throwable) extentReportData.getData();
                Objects.requireNonNull(ReportManager.getInstance().getCurrentTest())
                        .log(status, reportMsg + " Exception Message: " + throwable.getLocalizedMessage());
            }
        }
        if(reportEntry.getAdditionalObject() instanceof File)
        {
            Media media = MediaEntityBuilder
                    .createScreenCaptureFromPath(RelativePath((((File) reportEntry.getAdditionalObject()).getPath()),"target/Extent-Report/")).build();
            Objects.requireNonNull(ReportManager.getInstance().getCurrentTest()).log(status, reportMsg, media);
        }
    }
}
