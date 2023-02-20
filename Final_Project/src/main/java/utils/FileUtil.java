package utils;

import aquality.selenium.browser.AqualityServices;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class FileUtil {

    public static String takeScreenAs64String() {
        File screenshot = AqualityServices.getBrowser().getDriver().getScreenshotAs(OutputType.FILE);
        byte[] fileContent = new byte[0];
        try {
            fileContent = FileUtils.readFileToByteArray(screenshot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        return encodedString;
    }
}
