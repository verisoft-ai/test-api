package co.verisoft.fw.utils;

import org.apiguardian.api.API;
import org.json.simple.JSONObject;

/**
 * Representation of a Json object class. A json object class in considered to be a json object class if it is able
 * to produce a json object from the class data in 2 formats: json object (in this case it's google's JSONObject),
 * and in a json string format.
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @since 0.0.2 (Jan 2022)
 */
@API(
        status = API.Status.EXPERIMENTAL,
        since = "0.0.2"
)
public interface JsonObject {

    /**
     * Retrieve class data in a json format representaion
     * @return json representaion of the class data
     */
    JSONObject asJsonObject();


    /**
     * Retrieve class data in a json string format representaion
     * @return json string representation of the class data
     */
    String asString();
}
