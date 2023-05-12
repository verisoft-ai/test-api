package co.verisoft.fw;

import co.verisoft.fw.store.StoreManager;
import co.verisoft.fw.store.StoreType;
import com.epam.reportportal.junit5.ItemType;
import com.epam.reportportal.junit5.ReportPortalExtension;
import com.epam.reportportal.listeners.ItemStatus;
import com.epam.ta.reportportal.ws.model.FinishTestItemRQ;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.AssumptionViolatedException;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.opentest4j.TestAbortedException;

import java.util.Calendar;
import java.util.Date;

public class CustomReportPortalExtension extends ReportPortalExtension implements AfterEachCallback {


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