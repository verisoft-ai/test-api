package co.verisoft.fw.extentreport;

import co.verisoft.fw.extensions.jupiter.ExtentReportExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(ExtentReportExtension.class)
public @interface ExtentReport {
}
