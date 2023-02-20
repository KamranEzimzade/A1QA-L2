package model;

import aquality.selenium.core.utilities.JsonSettingsFile;
import lombok.Getter;

@Getter
public class TestData {

    private String photo;
    private String difference;
    private String randomLength;


    public TestData(JsonSettingsFile jsonSettingsFile) {
        photo = jsonSettingsFile.getValue("/photo").toString();
        difference = jsonSettingsFile.getValue("/difference").toString();
        randomLength = jsonSettingsFile.getValue("/randomLength").toString();
    }


}
