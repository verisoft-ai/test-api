package co.verisoft.fw.xray;

import org.apiguardian.api.API;

@API(
        status = API.Status.EXPERIMENTAL,
        since = "0.0.2"
)
public enum Result {
    FAILED("FAILED"),
    PASSED("PASSED");

    Result(String result) {
    }
}
