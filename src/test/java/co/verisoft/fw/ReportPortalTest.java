package co.verisoft.fw;

import co.verisoft.fw.extensions.jupiter.GenericExampleExtension;
import co.verisoft.fw.extentreport.ExtentReport;
import co.verisoft.fw.report.observer.Report;
import com.epam.reportportal.junit5.ReportPortalExtension;
import com.epam.reportportal.listeners.LogLevel;
import com.epam.reportportal.service.ReportPortal;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;

@ExtentReport
@ExtendWith({CustomReportPortalExtension.class})
public class ReportPortalTest {

    public enum TestParams {
        ONE,
        TWO
    }

    @ParameterizedTest
    @EnumSource (TestParams.class)
    public void testParameters(TestParams param) throws AWTException, IOException {
        Report.info("Test: " + param.name());

        Robot robot = new Robot();
        BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        ByteArrayOutputStream out = new ByteArrayOutputStream(8192);
        ImageIO.write(screenShot, "png", out);
        String base64ScreenShot =  Base64.getEncoder().encodeToString(out.toByteArray());
        ReportPortal.emitLog("This is my log", LogLevel.INFO.name(), new Date());
        Report.debug(base64ScreenShot);

    }
}
