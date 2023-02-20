package configuration;

public class Configuration {

    private Configuration() {
    }

    public static String getStartUrl() {
        return Environment.getCurrentEnvironment().getValue("/url").toString();
    }

    public static String getLength() {
        return Environment.getCurrentEnvironment().getValue("/length").toString();
    }

    public static String getTestDataPath() {
        return Environment.getCurrentEnvironment().getValue("/TestDataPath").toString();
    }

}