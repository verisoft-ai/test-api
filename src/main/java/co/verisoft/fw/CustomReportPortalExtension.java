//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
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
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.AssumptionViolatedException;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.opentest4j.TestAbortedException;
public class CustomReportPortalExtension extends ReportPortalExtension implements BeforeAllCallback, AfterEachCallback {
    private static boolean didRun = false;
    private static final Object lock = new Object();
    public CustomReportPortalExtension() {
    }
    public void beforeAll(ExtensionContext context) {
        synchronized(lock) {
            if (!didRun) {
                new ReportPortalObserver(ReportLevel.INFO);
                didRun = true;
            }
            super.beforeAll(context);
        }
    }
    protected @NotNull FinishTestItemRQ buildFinishTestItemRq(@NotNull ExtensionContext context, @Nullable ItemStatus status) {
        FinishTestItemRQ finishTestItemRQ = super.buildFinishTestItemRq(context, status);
        if (context.getExecutionException().isPresent()) {
            String var10001 = finishTestItemRQ.getDescription() == null ? " " : finishTestItemRQ.getDescription();
            finishTestItemRQ.setDescription(var10001 + "<br>" + ((Throwable)context.getExecutionException().get()).getMessage());
        }
        return finishTestItemRQ;
    }
    public void afterEach(ExtensionContext context) {
        if (context.getExecutionException().isPresent() && (context.getExecutionException().get() instanceof TestAbortedException || context.getExecutionException().get() instanceof AssumptionViolatedException)) {
            this.handleTestAbortedException(context, (Throwable)context.getExecutionException().get());
        }
    }
    public void afterTestExecution(ExtensionContext context) {
        Map<String, List<String>> screenShots = (Map)StoreManager.getStore(StoreType.LOCAL_THREAD).getValueFromStore("screenshots");
        List<String> images = Objects.isNull(screenShots) ? null : (List)screenShots.get(context.getDisplayName());
        if (!Objects.isNull(images)) {
            Iterator var4 = images.iterator();
            while(var4.hasNext()) {
                String image = (String)var4.next();
                File file = new File(image);
                ReportPortal.emitLog("Error Screenshot", LogLevel.INFO.name(), new Date(), file);
            }
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
        Boolean after = (Boolean)StoreManager.getStore(StoreType.LOCAL_THREAD).getValueFromStore("ReportPortalExtension.afterTestExecution");
        if (after != null && after) {
            StoreManager.getStore(StoreType.LOCAL_THREAD).putValueInStore("ReportPortalExtension.afterTestExecution", (Object)null);
        }else {
            this.startTestItem(context, (List)null, ItemType.STEP, this.createStepDescription(context), skipStartTime);
            this.createSkippedSteps(context, throwable);
            this.getExecutionStatus(context);
            FinishTestItemRQ finishRq = this.buildFinishTestItemRq(context, ItemStatus.SKIPPED);
            this.finishTestItem(context, finishRq);
        }
    }
}