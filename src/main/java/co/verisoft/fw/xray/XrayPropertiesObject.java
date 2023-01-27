package co.verisoft.fw.xray;

/**
 *
 * xray properties
 * An object that contains all the necessary settings for entering the values of the test results into the xray.
 * if it is cloud - we needs clientId and clientSecret,
 * otherwise - server / data center - we needs jiraBaseUrl, jiraUserName and jiraPassword or jiraToken
 *
 * @author chagit.donat
 * @since 01.2023
 */
public class XrayPropertiesObject {
    
    private String XrayType;
    private String xrayCloudApiBaseUrl;
    private String clientId;
    private String clientSecret;
    private String jiraBaseUrl;
    private String jiraUserName;
    private String jiraPassword;
    private String jiraToken;

    public String getXrayType() {
        return XrayType;
    }
    public String getXrayCloudApiBaseUrl() {
        return xrayCloudApiBaseUrl;
    }
    public String getClientId() {
        return clientId;
    }
    public String getClientSecret() {
        return clientSecret;
    }
    public String getJiraBaseUrl() {
        return jiraBaseUrl;
    }
    public String getJiraUserName() {
        return jiraUserName;
    }
    public String getJiraPassword() {
        return jiraPassword;
    }

    public String getJiraToken() { return  jiraToken; }

    private XrayPropertiesObject(XrayPropertiesObjectBuilder builder){
        this.XrayType = builder.XrayType;
        this.xrayCloudApiBaseUrl = builder.xrayCloudApiBaseUrl;
        this.clientId = builder.clientId;
        this.clientSecret = builder.clientSecret;
        this.jiraBaseUrl = builder.jiraBaseUrl;
        this.jiraUserName = builder.jiraUserName;
        this.jiraPassword = builder.jiraPassword;
        this.jiraToken = builder.jiraToken;

    }

    public static class XrayPropertiesObjectBuilder{

        private String XrayType;
        private String xrayCloudApiBaseUrl;
        private String clientId;
        private String clientSecret;
        private String jiraBaseUrl;
        private String jiraUserName;
        private String jiraPassword;

        private String jiraToken;

        public XrayPropertiesObjectBuilder(String xrayType){
            this.XrayType = xrayType;
        }
        public XrayPropertiesObjectBuilder setXrayCloudApiBaseUrl(String xrayCloudApiBaseUrl){
                this.xrayCloudApiBaseUrl = xrayCloudApiBaseUrl;
                return this;
        }
        public XrayPropertiesObjectBuilder setClientId(String clientId){
            this.clientId = clientId;
            return this;
        }
        public XrayPropertiesObjectBuilder setClientSecret(String clientSecret){
            this.clientSecret = clientSecret;
            return this;
        }
        public XrayPropertiesObjectBuilder setJiraBaseUrl(String jiraBaseUrl){
            this.jiraBaseUrl = jiraBaseUrl;
            return this;
        }
        public XrayPropertiesObjectBuilder setJiraUserName(String jiraUserName){
            this.jiraUserName = jiraUserName;
            return  this;
        }

        public XrayPropertiesObjectBuilder setJiraPassword(String jiraPassword){
            this.jiraPassword = jiraPassword;
            return this;
        }
        public XrayPropertiesObjectBuilder setJiraToken(String jiraToken){
            this.jiraToken = jiraToken;
            return this;
        }

        public XrayPropertiesObject build(){
            return new XrayPropertiesObject(this);
        }

    }

}
