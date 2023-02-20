package model;

import aquality.selenium.core.utilities.JsonSettingsFile;
import lombok.Getter;

@Getter
public class TestUser {

    private String login;
    private String password;
    private String ownerId;

    public TestUser(JsonSettingsFile jsonSettingsFile) {
        login = jsonSettingsFile.getValue("/login").toString();
        password = jsonSettingsFile.getValue("/password").toString();
        ownerId = jsonSettingsFile.getValue("/owner_id").toString();
    }
}
