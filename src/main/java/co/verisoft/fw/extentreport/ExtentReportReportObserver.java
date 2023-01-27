package co.verisoft.fw.extentreport;

import co.verisoft.fw.report.observer.BaseObserver;
import co.verisoft.fw.report.observer.ReportEntry;
import co.verisoft.fw.report.observer.ReportLevel;
import co.verisoft.fw.report.observer.ReportSource;
import com.aventstack.extentreports.Status;

import java.util.Objects;


/**
 * An implementation of the rerport observer mechanism for extent report.
 * Currently, only info level entries and higher are written t
 */
public class ExtentReportReportObserver extends BaseObserver {
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
        if (reportEntry.getReportLevel() == ReportLevel.WARNING)
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

        if (reportEntry.getReportSource() != ReportSource.REPORT)
            reportMsg += " (Report source: " + reportEntry.getReportSource().toString() + ")";

        // Write to report
        Objects.requireNonNull(ReportManager.getInstance().getCurrentTest()).log(status, reportMsg);
    }
}
