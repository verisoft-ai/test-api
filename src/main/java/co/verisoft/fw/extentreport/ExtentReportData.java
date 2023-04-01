package co.verisoft.fw.extentreport;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ExtentReportData {

    private Type type;
    private Object data;

    public enum Type{
        SCREENSHOT, THROWABLE
    }
}
