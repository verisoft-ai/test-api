package co.verisoft.fw.xray;

import co.verisoft.fw.utils.Builder;
import co.verisoft.fw.utils.JsonObject;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @since 0.0.2 (Jan 2022)
 *
 * @see <a href="https://docs.getxray.app/display/XRAYCLOUD/Using+Xray+JSON+format+to+import+execution+results#UsingXrayJSONformattoimportexecutionresults-stepdef">
 *     "step def" object - step definition</a>
 */
public class XrayJsonStepDefObject implements JsonObject {

    private final String action;                // The step action - native field
    private final String data;                  // The step data - native field
    private final String result;                // The step expected result - native field
    private Map<String, String> customFields;   // Any other step custom fields


    public String getAction() {
        return action;
    }

    public String getData() {
        return data;
    }

    public String getResult() {
        return result;
    }

    public Map<String, String> getCustomFields() {
        return customFields;
    }

    public <T> T getCustomField(Object field) {
        return (T)customFields.get(field);
    }


    private XrayJsonStepDefObject(XrayJsonStepDefObjectBuilder builder) {
        this.action = builder.action;
        this.data = builder.data;
        this.result = builder.result;
        this.customFields = builder.customFields;
    }


    @SuppressWarnings("unchecked")
    @Override
    public JSONObject asJsonObject() {
        JSONObject obj = new JSONObject();
        obj.put("action", this.action);
        obj.put("data", this.data);
        obj.put("result", this.result);

        // Write down the rest of the custom fields
        Iterator it = this.customFields.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            obj.put(pair.getKey(), pair.getValue());
        }

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
    public static class XrayJsonStepDefObjectBuilder implements Builder {
        private String action;                      // The step action - native field
        private String data;                        // The step data - native field
        private String result;                      // The step expected result - native field
        private Map<String, String> customFields;   // Any other step custom fields


        public XrayJsonStepDefObjectBuilder(XrayJsonStepDefObject obj) {
            this.action = obj.action;
            this.data = obj.data;
            this.result = obj.result;
            this.customFields = obj.customFields; // Note! This is shallow copy... A change will effect both maps
        }

        public XrayJsonStepDefObjectBuilder() {
            customFields = new HashMap<>();
        }

        public XrayJsonStepDefObjectBuilder action(String action) {
            this.action = action;
            return this;
        }

        public XrayJsonStepDefObjectBuilder data(String data) {
            this.data = data;
            return this;
        }

        public XrayJsonStepDefObjectBuilder result(String result) {
            this.result = result;
            return this;
        }

        public XrayJsonStepDefObjectBuilder customFields(Map customFields) {
            this.customFields = customFields;
            return this;
        }

        public XrayJsonStepDefObjectBuilder addCustomField(String key, String value){
            this.customFields.put(key, value);
            return this;
        }


        public XrayJsonStepDefObject build() {
            XrayJsonStepDefObject info = new XrayJsonStepDefObject(this);
            validateXrayStepDefObject(info);
            return info;
        }

        private void validateXrayStepDefObject(XrayJsonStepDefObject obj) {
            // TOOD: some validation code here
        }
    }
}
