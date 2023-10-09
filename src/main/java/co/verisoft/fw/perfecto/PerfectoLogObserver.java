package co.verisoft.fw.perfecto;

import co.verisoft.fw.report.observer.BaseObserver;
import co.verisoft.fw.report.observer.ReportEntry;
import co.verisoft.fw.report.observer.ReportLevel;
import co.verisoft.fw.store.StoreManager;
import co.verisoft.fw.store.StoreType;
import com.epam.reportportal.service.ReportPortal;
import com.perfecto.reportium.client.ReportiumClient;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.Date;


@Slf4j
public class PerfectoLogObserver extends BaseObserver {

    @Getter
    private final ReportLevel minReportLevel;

    public PerfectoLogObserver(ReportLevel minReportLevel) {
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
        ReportiumClient reportiumClient = StoreManager.getStore(StoreType.LOCAL_THREAD).getValueFromStore("REPORTIUM");
        if (level==ReportLevel.DEBUG)
            reportiumClient.reportiumAssert("DEBUG: "+reportMsg, true);
        else {
            reportiumClient.reportiumAssert(reportMsg, level != ReportLevel.ERROR);
        }

    }
}

