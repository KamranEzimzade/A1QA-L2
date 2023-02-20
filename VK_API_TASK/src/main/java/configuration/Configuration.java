package configuration;

public class Configuration {

    private Configuration() {
    }

    public static String getStartUrl() {
        return Environment.getCurrentEnvironment().getValue("/startUrl").toString();
    }

    public static String getRestUrl() {
        return Environment.getCurrentEnvironment().getValue("/resturl").toString();
    }

    public static String getToken() {
        return Environment.getCurrentEnvironment().getValue("/token").toString();
    }

    public static String getVersion() {
        return Environment.getCurrentEnvironment().getValue("/version").toString();
    }


}
