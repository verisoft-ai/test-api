package co.verisoft.fw.report.observer;

import co.verisoft.fw.extensions.jupiter.ExtentReportExtension;
import co.verisoft.fw.extentreport.ExtentReportReportObserver;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({ExtentReportExtension.class})
@Log4j2
@SuppressWarnings("unused")
public class ObserverTest {

    @Test
    public void shouldPrint() {
        SampleObserver sampleObserver = new SampleObserver();
        ExtentReportReportObserver extentReportReportObserver = new ExtentReportReportObserver(ReportLevel.INFO);
        Report.report(ReportLevel.ERROR, "bla bla");
        Report.report(ReportSource.REPORT, ReportLevel.ERROR, "should  be something");

        log.warn("debug");
        try {
            throw new Exception("This is an exception");
        } catch (Throwable t) {
            Report.report("Error occured during test", t);
        }
    }
}
