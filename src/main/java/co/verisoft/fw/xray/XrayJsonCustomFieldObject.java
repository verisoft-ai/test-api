package co.verisoft.fw.xray;

import co.verisoft.fw.utils.Builder;
import co.verisoft.fw.utils.JsonObject;
import org.json.simple.JSONObject;

/**
 *
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @since 0.0.2 (Jan 2022)
 *
 * @see <a href="https://docs.getxray.app/display/XRAYCLOUD/Using+Xray+JSON+format+to+import+execution+results#UsingXrayJSONformattoimportexecutionresults-stepdef">
 *     "step def" object - step definition</a>
 */
public class XrayJsonCustomFieldObject implements JsonObject {

    private final String id;        // The test run custom field ID
    private final String name;      // The test run custom field name
    private final String value;     // The test run custom field value


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    private XrayJsonCustomFieldObject(XrayJsonCustomFieldObjectBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.value = builder.value;
    }


    @SuppressWarnings("unchecked")
    @Override
    public JSONObject asJsonObject() {
        JSONObject obj = new JSONObject();
        obj.put("id", this.id);
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
    public static class XrayJsonCustomFieldObjectBuilder implements Builder {

        private String id;        // The test run custom field ID
        private String name;      // The test run custom field name
        private String value;     // The test run custom field value

        public XrayJsonCustomFieldObjectBuilder(XrayJsonCustomFieldObject obj) {
            this.id = obj.id;
            this.name = obj.name;
            this.value = obj.value;
        }

        public XrayJsonCustomFieldObjectBuilder() {
            // No op
        }

        public XrayJsonCustomFieldObjectBuilder id(String id) {
            this.id = id;
            return this;
        }

        public XrayJsonCustomFieldObjectBuilder name(String name) {
            this.name = name;
            return this;
        }

        public XrayJsonCustomFieldObjectBuilder value(String value) {
            this.value = value;
            return this;
        }


        public XrayJsonCustomFieldObject build() {
            XrayJsonCustomFieldObject info = new XrayJsonCustomFieldObject(this);
            validateXrayCustomFieldObject(info);
            return info;
        }

        private void validateXrayCustomFieldObject(XrayJsonCustomFieldObject obj) {
            // TOOD: some validation code here
        }
    }
}
