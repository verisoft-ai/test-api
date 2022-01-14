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
public class XrayJsonEvidenceObject implements JsonObject {

    private final String data;        // The attachment data encoded in base64
    private final String filename;      // The file name for the attachment

    // The Content-Type representation header is used to indicate the original media type of the resource
    private final String contentType;




    public String getData() {
        return data;
    }

    public String getFileName() {
        return filename;
    }

    public String getContentType() {
        return contentType;
    }

    private XrayJsonEvidenceObject(XrayJsonEvidenceObjectBuilder builder) {
        this.data = builder.data;
        this.filename = builder.filename;
        this.contentType = builder.contentType;
    }


    @SuppressWarnings("unchecked")
    @Override
    public JSONObject asJsonObject() {
        JSONObject obj = new JSONObject();
        obj.put("data", this.data);
        obj.put("filename", this.filename);
        obj.put("contentType", this.contentType);

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
    public static class XrayJsonEvidenceObjectBuilder implements Builder {

        private String data;
        private String filename;
        private String contentType;

        public XrayJsonEvidenceObjectBuilder(XrayJsonEvidenceObject obj) {
            this.data = obj.data;
            this.filename = obj.filename;
            this.contentType = obj.contentType;
        }

        public XrayJsonEvidenceObjectBuilder() {
            // No op
        }

        public XrayJsonEvidenceObjectBuilder data(String data) {
            this.data = data;
            return this;
        }

        public XrayJsonEvidenceObjectBuilder filename(String filename) {
            this.filename = filename;
            return this;
        }

        public XrayJsonEvidenceObjectBuilder contentType(String contentType) {
            this.contentType = contentType;
            return this;
        }


        public XrayJsonEvidenceObject build() {
            XrayJsonEvidenceObject info = new XrayJsonEvidenceObject(this);
            validateXrayEvidenceObject(info);
            return info;
        }

        private void validateXrayEvidenceObject(XrayJsonEvidenceObject obj) {
            // TOOD: some validation code here
        }
    }
}
