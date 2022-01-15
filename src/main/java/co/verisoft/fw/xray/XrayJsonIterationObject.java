package co.verisoft.fw.xray;

import co.verisoft.fw.utils.Builder;
import co.verisoft.fw.utils.JsonObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Representation of Xray object - <b>Iteration</b>. This class follows the builder design pattern<br>
 *
 * <br><b>From Xray documentation:</b><br>
 * "iteration" object - Data-driven test results <br>
 * If you need to import data-driven test results you need to use the iteration object.
 * Xray will store all iterations within the same Test Run object.
 * It is also possible to import iteration results with parameters.
 * <b>Currently, this is only supported for manual tests.</b>
 * In this case, Xray will create a dataset automatically within the Test Run object.
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @see <a href="https://docs.getxray.app/display/XRAYCLOUD/Using+Xray+JSON+format+to+import+execution+results#UsingXrayJSONformattoimportexecutionresults-%22iteration%22object-Data-driventestresults">
 * Using Xray JSON format to import execution results - Iteration</a>
 * @since 0.0.2 (Jan 2022)
 */
public class XrayJsonIterationObject implements JsonObject {

    private final String name;                              // The iteration name
    private final List<XrayJsonParameterObject> parameters; // An array of parameters along with their values
    private final String log;                               // The log for the iteration
    private final String duration;                          // A duration for the iteration
    private final Status status;                            // The status for the iteration (PASSED, FAILED, EXECUTING, custom statuses ...)
    private final List<XrayJsonStepResultObject> steps;     // An array of step results (for Manual tests)

    public String getName() {
        return name;
    }

    public List<XrayJsonParameterObject> getParameters() {
        return parameters;
    }

    public String getLog() {
        return log;
    }

    public String getDuration() {
        return duration;
    }

    public Status getStatus() {
        return status;
    }

    public String getStatusAsString() {
        return status.toString();
    }


    public List<XrayJsonStepResultObject> getSteps() {
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
        obj.put("log", this.log);
        obj.put("duration", this.duration);
        obj.put("status", this.status.toString());

        // Parameters
        JSONArray arr = new JSONArray();
        for (XrayJsonParameterObject parameter : parameters)
            arr.add(parameter.asJsonObject());

        if (!arr.isEmpty()) {
            obj.put("parameters", arr);
        }

        // Step results
        arr = new JSONArray(); // arr.clear reveales Json simple BUG!
        for (XrayJsonStepResultObject result : steps)
            arr.add(result.asJsonObject());

        if (!arr.isEmpty()) {
            obj.put("steps", arr);
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
    public static class XrayJsonIterationObjectBuilder implements Builder {

        private String name;
        private List<XrayJsonParameterObject> parameters;
        private String log;
        private String duration;
        private Status status;
        private List<XrayJsonStepResultObject> steps;


        public XrayJsonIterationObjectBuilder(XrayJsonIterationObject obj) {
            this.name = obj.name;
            this.parameters = obj.parameters;
            this.log = obj.log;
            this.duration = obj.duration;
            this.status = obj.status;
            this.steps = obj.steps;
        }

        public XrayJsonIterationObjectBuilder() {
            parameters = new ArrayList<>();
            steps = new ArrayList<>();
        }

        public XrayJsonIterationObjectBuilder name(String name) {
            this.name = name;
            return this;
        }

        public XrayJsonIterationObjectBuilder parameters(List<XrayJsonParameterObject> parameters) {
            this.parameters = parameters;
            return this;
        }

        public XrayJsonIterationObjectBuilder parameter(XrayJsonParameterObject parameter) {
            this.parameters.add(parameter);
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

        public XrayJsonIterationObjectBuilder status(Status status) {
            this.status = status;
            return this;
        }

        public XrayJsonIterationObjectBuilder status(String status) {
            this.status = Status.toStatus(status);
            return this;
        }

        public XrayJsonIterationObjectBuilder steps(List<XrayJsonStepResultObject> steps) {
            this.steps = steps;
            return this;
        }

        public XrayJsonIterationObjectBuilder step(XrayJsonStepResultObject step) {
            this.steps.add(step);
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
