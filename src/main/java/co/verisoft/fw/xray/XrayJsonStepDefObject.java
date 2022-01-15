package co.verisoft.fw.xray;

import co.verisoft.fw.utils.Builder;
import co.verisoft.fw.utils.JsonObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

/**
 * Representation of Xray object - <b>Step Definition</b>. This class follows the builder design pattern<br>
 *
 * <br><b>From Xray documentation:</b><br>
 * "step def" object - step definition <br>
 * This object allows you to define the step fields for manual tests. Custom test step fields are also supported.
 *
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @since 0.0.2 (Jan 2022)
 *
 * @see <a href="https://docs.getxray.app/display/XRAYCLOUD/Using+Xray+JSON+format+to+import+execution+results#UsingXrayJSONformattoimportexecutionresults-%22stepdef%22object-stepdefinition">
 *     Using Xray JSON format to import execution results - Step Definition</a>
 */
public class XrayJsonStepDefObject implements JsonObject {

    private final String action;                            // The step action - native field
    private final String data;                              // The step data - native field
    private final String result;                            // The step expected result - native field
    private List<XrayJsonCustomFieldObject> customFields;   // Any other step custom fields


    public String getAction() {
        return action;
    }

    public String getData() {
        return data;
    }

    public String getResult() {
        return result;
    }

    public List<XrayJsonCustomFieldObject> getCustomFields() {
        return customFields;
    }


    public XrayJsonCustomFieldObject getCustomField(String id) {
        return customFields.stream()
                .filter(custField -> custField.getId().equalsIgnoreCase(id))
                .findAny()
                .orElse(null);
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
        JSONArray arr = new JSONArray();
        for (XrayJsonCustomFieldObject custField:customFields)
            arr.add(custField.asJsonObject());

        if (!arr.isEmpty()){
            obj.put("customFields", arr);
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
        private String action;
        private String data;
        private String result;
        private List<XrayJsonCustomFieldObject> customFields;


        public XrayJsonStepDefObjectBuilder(XrayJsonStepDefObject obj) {
            this.action = obj.action;
            this.data = obj.data;
            this.result = obj.result;
            this.customFields = obj.customFields; // Note! This is shallow copy... A change will effect both Lists
        }

        public XrayJsonStepDefObjectBuilder() {
            customFields = new ArrayList<>();
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

        public XrayJsonStepDefObjectBuilder customFields(List customFields) {
            this.customFields = customFields;
            return this;
        }

        public XrayJsonStepDefObjectBuilder customField(XrayJsonCustomFieldObject customField){
            this.customFields.add(customField);
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
