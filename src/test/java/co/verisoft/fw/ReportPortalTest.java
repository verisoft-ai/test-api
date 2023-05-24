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

import co.verisoft.fw.extentreport.ExtentReport;
import co.verisoft.fw.report.observer.Report;
import com.epam.reportportal.listeners.LogLevel;
import com.epam.reportportal.service.ReportPortal;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.api.Disabled;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;

@ExtentReport
@Disabled
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
