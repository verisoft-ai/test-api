package co.verisoft.fw.report.observer;

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

import co.verisoft.fw.extensions.jupiter.ExtentReportExtension;
import co.verisoft.fw.extentreport.DelegateExtentTest;
import co.verisoft.fw.extentreport.ReportManager;
import com.aventstack.extentreports.model.Log;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.List;

@Execution(ExecutionMode.SAME_THREAD)
@ExtendWith({ExtentReportExtension.class})
@Log4j2
public class ExtentReportObserverTest extends BaseTest {

    @Test
    public void shouldFilterOutMessagesForExtentReport() {

        // Setup
        DelegateExtentTest test = ReportManager.getInstance().getCurrentTest();
        assert test != null;
        List<Log> list = test.getModel().getLogs();

        // Test
        Report.report(ReportLevel.TRACE, "Trace message - should only appear on log");
        Assertions.assertFalse(list.stream().anyMatch(x -> x.getDetails().contains("Trace message")));

        Report.report(ReportLevel.DEBUG, "Debug message - should only appear on log");
        Assertions.assertFalse(list.stream().anyMatch(x -> x.getDetails().contains("Debug message")));

        Report.report(ReportLevel.INFO, "Info message - SHOULD appear on log");
        Assertions.assertTrue(list.stream().anyMatch(x -> x.getDetails().contains("Info message")));

        Report.report(ReportLevel.WARNING, "Warn message - SHOULD appear on log");
        Assertions.assertTrue(list.stream().anyMatch(x -> x.getDetails().contains("Warn message")));

        Report.report(ReportLevel.ERROR, "Error message - SHOULD appear on log");
        Assertions.assertTrue(list.stream().anyMatch(x -> x.getDetails().contains("Error message")));

        Report.report(ReportLevel.FATAL, "Fatal message - SHOULD appear on log");
        Assertions.assertTrue(list.stream().anyMatch(x -> x.getDetails().contains("Fatal message")));
    }
}
