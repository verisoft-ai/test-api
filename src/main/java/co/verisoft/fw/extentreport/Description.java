package co.verisoft.fw.extentreport;

import org.apiguardian.api.API;

import java.lang.annotation.*;


@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@API(
            status = API.Status.STABLE,
            since = "0.1.1"
    )
public @interface Description {
        String value();
    }

