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

import co.verisoft.fw.report.observer.ReportLevel;
import co.verisoft.fw.store.StoreManager;
import co.verisoft.fw.store.StoreType;
import com.epam.reportportal.junit5.ItemType;
import com.epam.reportportal.junit5.ReportPortalExtension;
import com.epam.reportportal.listeners.ItemStatus;
import com.epam.reportportal.listeners.LogLevel;
import com.epam.reportportal.service.ReportPortal;
import com.epam.ta.reportportal.ws.model.FinishTestItemRQ;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.AssumptionViolatedException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.opentest4j.TestAbortedException;

import java.io.File;
import java.util.*;
import java.util.List;

public class CustomReportPortalExtension extends ReportPortalExtension implements BeforeAllCallback,AfterEachCallback {
    private static boolean didRun = false;
    private static final Object lock = new Object();
    @Override
    public void beforeAll(ExtensionContext context)  {
        synchronized (lock) {
            if (!didRun) {
                @SuppressWarnings("unused")
                ReportPortalObserver reportPortalObserver = new ReportPortalObserver(ReportLevel.INFO);
                didRun = true;
            }
            super.beforeAll(context);
        }
    }

    @NotNull
    @Override
    protected FinishTestItemRQ buildFinishTestItemRq(@NotNull ExtensionContext context, @Nullable ItemStatus status) {
        FinishTestItemRQ finishTestItemRQ = super.buildFinishTestItemRq(context, status);
        if(context.getExecutionException().isPresent()){
            finishTestItemRQ.setDescription((finishTestItemRQ.getDescription() == null ? " " : finishTestItemRQ.getDescription()) + "<br>"+ context.getExecutionException().get().getMessage());
        }
        return finishTestItemRQ;
    }

    @Override
    public void afterEach(ExtensionContext context)  {
        if (context.getExecutionException().isPresent() &&
                (context.getExecutionException().get() instanceof TestAbortedException ||
                        context.getExecutionException().get() instanceof AssumptionViolatedException)){
            handleTestAbortedException(context, context.getExecutionException().get());
        }
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        // Find out if there are screenshots collected during the test
        Map<String, List<String>> screenShots = StoreManager.getStore(StoreType.LOCAL_THREAD)
                .getValueFromStore("screenshots");

        List<String> images = Objects.isNull(screenShots) ? null : screenShots.get(context.getDisplayName());

        if (!Objects.isNull(images))
            for (String image : images) {
                File file = new File("target/screenshots",image);
                ReportPortal.emitLog("Error Screenshot",LogLevel.INFO.name(),new Date(), file);
            }
        super.afterTestExecution(context);
        StoreManager.getStore(StoreType.LOCAL_THREAD).putValueInStore("ReportPortalExtension.afterTestExecution", true);
    }

    protected void handleTestAbortedException(ExtensionContext context, Throwable throwable) {

        Date startTime = Calendar.getInstance().getTime();

        Date skipStartTime = Calendar.getInstance().getTime();
        if (skipStartTime.after(startTime)) {
            skipStartTime = new Date(skipStartTime.getTime() - 1L);
        }

        Boolean after = StoreManager.getStore(StoreType.LOCAL_THREAD).getValueFromStore("ReportPortalExtension.afterTestExecution");
        if (after == null || after == false) {
            this.startTestItem(context, null, ItemType.STEP, this.createStepDescription(context), skipStartTime);
            this.createSkippedSteps(context, throwable);
            getExecutionStatus(context);
            FinishTestItemRQ finishRq = this.buildFinishTestItemRq(context, ItemStatus.SKIPPED);
            this.finishTestItem(context, finishRq);
        }
        else {
            StoreManager.getStore(StoreType.LOCAL_THREAD).putValueInStore("ReportPortalExtension.afterTestExecution", null);
        }

    }

}