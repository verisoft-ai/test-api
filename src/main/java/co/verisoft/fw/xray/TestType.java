package co.verisoft.fw.xray;

import lombok.Synchronized;
import org.apiguardian.api.API;

import java.util.Locale;

@API(
        status = API.Status.EXPERIMENTAL,
        since = "0.0.2"
)
public enum TestType {
    CUCUMBER,
    MANUAL,
    GENERIC;

    @Synchronized
    public static TestType toType(String type)
    {
        TestType result;
        switch (type.toUpperCase(Locale.ROOT)) {
            case "CUCUMBER":
                result = TestType.CUCUMBER;
                break;
            case "MANUAL":
                result = TestType.MANUAL;
                break;
            case "GENERIC":
                result = TestType.GENERIC;
                break;
            default:
                result = TestType.GENERIC;
                break;
        }
        return result;
    }
}
