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
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.File;
import java.util.*;

public class CustomReportPortalExtension extends ReportPortalExtension implements BeforeAllCallback, AfterEachCallback, BeforeTestExecutionCallback {
    private static boolean didRun = false;
    private static final Object lock = new Object();

    public CustomReportPortalExtension() {
    }

    public void beforeAll(ExtensionContext context) {
        synchronized (lock) {
            if (!didRun) {
                new ReportPortalObserver(ReportLevel.INFO);
                didRun = true;
            }
            super.beforeAll(context);
        }
    }

    public void beforeTestExecution(ExtensionContext extensionContext) {
        this.startTestItem(extensionContext, (List) null, ItemType.STEP, this.createStepDescription(extensionContext), Calendar.getInstance().getTime());
    }

    protected @NotNull FinishTestItemRQ buildFinishTestItemRq(@NotNull ExtensionContext context, @Nullable ItemStatus status) {
        FinishTestItemRQ finishTestItemRQ = super.buildFinishTestItemRq(context, status);
        ReportPortal.emitLog("Error ", "Error", new Date());
        if (context.getExecutionException().isPresent()) {
            String var10001 = finishTestItemRQ.getDescription() == null ? " " : finishTestItemRQ.getDescription();
            finishTestItemRQ.setDescription(var10001 + "<br>" + ((Throwable) context.getExecutionException().get()).getMessage());
        }
        return finishTestItemRQ;
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) {
        Map<String, List<String>> screenShots = (Map) StoreManager.getStore(StoreType.LOCAL_THREAD).getValueFromStore("screenshots");
        List<String> images = Objects.isNull(screenShots) ? null : (List) screenShots.get(extensionContext.getDisplayName());
        if (!Objects.isNull(images)) {
            Iterator var4 = images.iterator();
            while (var4.hasNext()) {
                String image = (String) var4.next();
                File file = new File(image);
                ReportPortal.emitLog("Error Screenshot", LogLevel.INFO.name(), new Date(), file);
            }
        }
        ReportPortal.emitLog("Error without Screenshot", LogLevel.INFO.name(), new Date());
        super.afterTestExecution(extensionContext);
        StoreManager.getStore(StoreType.LOCAL_THREAD).putValueInStore("ReportPortalExtension.afterTestExecution", true);
    }

    protected void handleTestAbortedException(ExtensionContext context, Throwable throwable) {
        Date startTime = Calendar.getInstance().getTime();
        Date skipStartTime = Calendar.getInstance().getTime();
        if (skipStartTime.after(startTime)) {
            skipStartTime = new Date(skipStartTime.getTime() - 1L);
        }
        Boolean after = (Boolean) StoreManager.getStore(StoreType.LOCAL_THREAD).getValueFromStore("ReportPortalExtension.afterTestExecution");
        if (after != null && after) {
            StoreManager.getStore(StoreType.LOCAL_THREAD).putValueInStore("ReportPortalExtension.afterTestExecution", (Object) null);
        } else {
            this.startTestItem(context, (List) null, ItemType.STEP, this.createStepDescription(context), skipStartTime);
            this.createSkippedSteps(context, throwable);
            this.getExecutionStatus(context);
            FinishTestItemRQ finishRq = this.buildFinishTestItemRq(context, ItemStatus.SKIPPED);
            this.finishTestItem(context, finishRq);
        }
    }
}