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
public class XrayJsonIterationObject implements JsonObject {

    private final String name;          // The iteration name
    private final String parameters;    // An array of parameters along with their values
    private final String log;           // The log for the iteration
    private final String duration;      // A duration for the iteration
    private final String status;        // The status for the iteration (PASSED, FAILED, EXECUTING, TODO, custom statuses ...)
    private final String steps;         // An array of step results (for Manual tests)

    public String getName() {
        return name;
    }

    public String getParameters() {
        return parameters;
    }

    public String getLog() {
        return log;
    }

    public String getDuration() {
        return duration;
    }

    public String getStatus() {
        return status;
    }

    public String getSteps() {
        return steps;
    }




    private XrayJsonIterationObject(XrayJsonIterationObjectBuilder builder) {
        this.name = builder.name;
        this.parameters = builder.parameters;
        this.log = builder.log;
        this.duration = builder.duration;
        this.status = builder.status;
        this.steps = builder.steps;
    }


    @SuppressWarnings("unchecked")
    @Override
    public JSONObject asJsonObject() {
        JSONObject obj = new JSONObject();
        obj.put("name", this.name);
        obj.put("parameters", this.parameters);
        obj.put("log", this.log);
        obj.put("duration", this.duration);
        obj.put("status", this.status);
        obj.put("steps", this.steps);

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
    public static class XrayJsonIterationObjectBuilder implements Builder {

        private String name;          // The iteration name
        private String parameters;    // An array of parameters along with their values
        private String log;           // The log for the iteration
        private String duration;      // A duration for the iteration
        private String status;        // The status for the iteration (PASSED, FAILED, EXECUTING, TODO, custom statuses ...)
        private String steps;         // An array of step results (for Manual tests)


        public XrayJsonIterationObjectBuilder(XrayJsonIterationObject obj) {
            this.name = obj.name;
            this.parameters = obj.parameters;
            this.log = obj.log;
            this.duration = obj.duration;
            this.status = obj.status;
            this.steps = obj.steps;
        }

        public XrayJsonIterationObjectBuilder() {
            // No op
        }

        public XrayJsonIterationObjectBuilder name(String name) {
            this.name = name;
            return this;
        }

        public XrayJsonIterationObjectBuilder parameters(String parameters) {
            this.parameters = parameters;
            return this;
        }

        public XrayJsonIterationObjectBuilder log(String log) {
            this.log = log;
            return this;
        }

        public XrayJsonIterationObjectBuilder duration(String duration) {
            this.duration = duration;
            return this;
        }

        public XrayJsonIterationObjectBuilder status(String status) {
            this.status = status;
            return this;
        }

        public XrayJsonIterationObjectBuilder steps(String steps) {
            this.steps = steps;
            return this;
        }


        public XrayJsonIterationObject build() {
            XrayJsonIterationObject info = new XrayJsonIterationObject(this);
            validateXrayIterationObject(info);
            return info;
        }

        private void validateXrayIterationObject(XrayJsonIterationObject obj) {
            // TOOD: some validation code here
        }
    }
}
