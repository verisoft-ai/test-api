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

import co.verisoft.fw.extentreport.DelegateExtentTest;
import co.verisoft.fw.extentreport.ExtentReport;
import co.verisoft.fw.extentreport.ReportManager;
import com.aventstack.extentreports.model.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.List;

@Execution(ExecutionMode.SAME_THREAD)
@ExtentReport
public class ExtentReportObserverTest extends BaseTest {

    @Test
    public void shouldFilterOutMessagesForExtentReport() {

        // Setup
        DelegateExtentTest test = ReportManager.getInstance().getCurrentTest();
        assert test != null;
        List<Log> list = test.getModel().getLogs();

        // Test
        Report.trace("Trace message - should only appear on log");
        Assertions.assertFalse(list.stream().anyMatch(x -> x.getDetails().contains("Trace message")));

        Report.debug("Debug message - should only appear on log");
        Assertions.assertFalse(list.stream().anyMatch(x -> x.getDetails().contains("Debug message")));

        Report.info("Info message - SHOULD appear on log");
        Assertions.assertTrue(list.stream().anyMatch(x -> x.getDetails().contains("Info message")));

        Report.warn("Warn message - SHOULD appear on log");
        Assertions.assertTrue(list.stream().anyMatch(x -> x.getDetails().contains("Warn message")));

        Report.error("Error message - SHOULD appear on log");
        Assertions.assertTrue(list.stream().anyMatch(x -> x.getDetails().contains("Error message")));

        Report.fatal("Fatal message - SHOULD appear on log");
        Assertions.assertTrue(list.stream().anyMatch(x -> x.getDetails().contains("Fatal message")));
    }
}
