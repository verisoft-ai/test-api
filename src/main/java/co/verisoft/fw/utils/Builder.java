package co.verisoft.fw.utils;

import org.apiguardian.api.API;

@API(
        status = API.Status.EXPERIMENTAL,
        since = "0.0.2"
)
public interface Builder<T> {

    T build();
}
