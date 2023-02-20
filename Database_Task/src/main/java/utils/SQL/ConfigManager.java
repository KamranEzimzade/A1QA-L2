package utils.SQL;

import pojos.SQL.ConfigDataDb;
import utils.API.JsonFileReader;

public final class ConfigManager {

    private static final ConfigDataDb CONFIG_DATA_DB = JsonFileReader.getJsonData("src/main/resources/SQL/config.json", ConfigDataDb.class);


    public static int getTestsNumber() {
        return CONFIG_DATA_DB.getTc2_tests_number();
    }

    public static String getAuthorName() {
        return CONFIG_DATA_DB.getAuthor_name();
    }

    public static String getAuthorLogin() {
        return CONFIG_DATA_DB.getAuthor_login();
    }

    public static String getAuthorEmail() {
        return CONFIG_DATA_DB.getAuthor_email();
    }

    public static String getProjectName() {
        return CONFIG_DATA_DB.getProject_name();
    }

    public static int getAuthorId() {
        return CONFIG_DATA_DB.getAuthor_id();
    }

    public static int getProjectId() {
        return CONFIG_DATA_DB.getProject_id();
    }

    public static int getSessionId() {
        return CONFIG_DATA_DB.getSession_id();
    }

    public static String getSessionKey() {
        return CONFIG_DATA_DB.getSession_key();
    }

    public static int getBuildNumber() {
        return CONFIG_DATA_DB.getBuild_number();
    }

    public static String getEnv() {
        return CONFIG_DATA_DB.getEnv();
    }


}