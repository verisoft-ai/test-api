package co.verisoft.fw.xray;

import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;

/**
 * A representation of a xray test object, with the relevant fields neede for the x-ray report
 *
 * @author Nir Gallner
 * @since Jan 2022
 */
@AllArgsConstructor
public class XrayTestObject {
    private String testKey;
    private String start;
    private String finish;
    private String comment;
    private String status;


    /**
     * Private c-tor from builder static class
     * @param builder
     */
    private XrayTestObject(XrayTestObjectBuilder builder){
        this.start = builder.start;
        this.finish = builder.finish;
        this.testKey = builder.testKey;
        this.comment = builder.comment;
        this.status = builder.status;
    }


    public void setFinish(String finish) {
        this.finish = finish;
    }


    public void setComment(String comment) {
        this.comment = comment;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public String getTestKey() {
        return testKey;
    }


    public String getStart() {
        return start;
    }


    public String getFinish() {
        return finish;
    }


    public String getComment() {
        return comment;
    }


    public String getStatus() {
        return status;
    }


    /**
     * Builds a json object using xray format.
     * https://docs.getxray.app/display/XRAY/Import+Execution+Results+-+REST
     * @return json object - xray test format
     */
    public JSONObject asJsonObject(){
        JSONObject obj = new JSONObject();
        obj.put("testKey", this.testKey);
        obj.put("start", this.start);
        obj.put("finish", this.finish);
        obj.put("status", this.status);
        obj.put("comment", this.comment);
        return obj;
    }


    /**
     * Builds a json object using xray format.
     * https://docs.getxray.app/display/XRAY/Import+Execution+Results+-+REST
     * @return A string representation of json object - xray test format
     */
    public String asString(){
        return this.asJsonObject().toJSONString();
    }


    /**
     * Builder static class
     */
    public static class XrayTestObjectBuilder {
        private String testKey;
        private String start;
        private String finish;
        private String comment;
        private String status;

        public XrayTestObjectBuilder(String testKey){
            this.testKey = testKey;
        }


        public XrayTestObjectBuilder start(String start) {
            this.start = start;
            return this;
        }

        public XrayTestObjectBuilder finish(String finish) {
            this.finish = finish;
            return this;
        }

        public XrayTestObjectBuilder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public XrayTestObjectBuilder status(String status) {
            this.status = status;
            return this;
        }

        public XrayTestObject build() {
            XrayTestObject test = new XrayTestObject(this);
            validateXrayTestObject(test);
            return test;
        }


        /**
         * Validation method. Throws exception if validation fails.
         * Currently there is not validation
         *
         * @param obj object to be validated
         */
        private void validateXrayTestObject(XrayTestObject obj) {
            // TOOD: some validation code here
        }
    }
}
