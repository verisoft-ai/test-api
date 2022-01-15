package co.verisoft.fw.xray;

import lombok.Synchronized;
import org.apiguardian.api.API;

import java.util.Locale;

@API(
        status = API.Status.EXPERIMENTAL,
        since = "0.0.2"
)
public enum Status {
    TODO,
    FAILED,
    PASSED,
    EXECUTING;

    @Synchronized
    public static Status toStatus(String status)
    {
        Status result;
        switch (status.toUpperCase(Locale.ROOT)) {
            case "TODO":
                result = Status.TODO;
                break;
            case "FAILED":
                result = Status.FAILED;
                break;
            case "PASSED":
                result = Status.PASSED;
                break;
            case "EXECUTING":
                result = Status.EXECUTING;
                break;
            default:
                result = Status.TODO;
                break;
        }
        return result;
    }
}
