package co.verisoft.fw.xray;

import co.verisoft.fw.utils.Builder;
import co.verisoft.fw.utils.JsonObject;
import org.json.simple.JSONObject;

/**
 * Representation of Xray object - <b>Parameter</b>. This class follows the builder design pattern<br>
 *
 * <br><b>From Xray documentation:</b><br>
 * "parameter" object - parameters within iteration results <br>
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @since 0.0.2 (Jan 2022)
 *
 * @see <a href="https://docs.getxray.app/display/XRAYCLOUD/Using+Xray+JSON+format+to+import+execution+results#UsingXrayJSONformattoimportexecutionresults-%22parameter%22object-parameterswithiniterationresults">
 *     Using Xray JSON format to import execution results - Parameter</a>
 */
public class XrayJsonParameterObject implements JsonObject {

    private final String name;        // The parameter name
    private final String value;      // The parameter value


    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    private XrayJsonParameterObject(XrayJsonParameterObjectBuilder builder) {
        this.name = builder.name;
        this.value = builder.value;
    }


    @SuppressWarnings("unchecked")
    @Override
    public JSONObject asJsonObject() {
        JSONObject obj = new JSONObject();
        obj.put("name", this.name);
        obj.put("value", this.value);

        return obj;
    }

    @Override
    public String asString() {
        return this.asJsonObject().toJSONString();
    }

    /**
     * Builder class for XrayInfoObject
     *
     * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
     * @since 0.0.2 (Jan 2022)
     */
    public static class XrayJsonParameterObjectBuilder implements Builder {

        private String name;      // The test run custom field name
        private String value;     // The test run custom field value

        public XrayJsonParameterObjectBuilder(XrayJsonParameterObject obj) {
            this.name = obj.name;
            this.value = obj.value;
        }

        public XrayJsonParameterObjectBuilder() {
            // No op
        }

        public XrayJsonParameterObjectBuilder name(String name) {
            this.name = name;
            return this;
        }

        public XrayJsonParameterObjectBuilder value(String value) {
            this.value = value;
            return this;
        }



        public XrayJsonParameterObject build() {
            XrayJsonParameterObject info = new XrayJsonParameterObject(this);
            validateXrayParameterObject(info);
            return info;
        }

        private void validateXrayParameterObject(XrayJsonParameterObject obj) {
            // TOOD: some validation code here
        }
    }
}
