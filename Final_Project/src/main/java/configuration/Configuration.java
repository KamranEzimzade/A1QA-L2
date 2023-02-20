package configuration;

public class Configuration {

    private Configuration() {
    }

    public static String getApiUri() {
        return Environment.getCurrentEnvironment().getValue("/URI_API").toString();
    }

    public static String getWebUri() {
        return Environment.getCurrentEnvironment().getValue("/URI_WEB").toString();
    }

    public static String getBaseUri() {
        return Environment.getCurrentEnvironment().getValue("/BASE_URI").toString();
    }

    public static String getLoginUri() {
        return Environment.getCurrentEnvironment().getValue("/URI_LOGIN").toString();
    }


}
