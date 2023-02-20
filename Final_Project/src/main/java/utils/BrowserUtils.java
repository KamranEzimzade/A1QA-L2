package utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import configuration.Configuration;
import org.openqa.selenium.Cookie;
import pojos.TestData;

import static aquality.selenium.browser.AqualityServices.getBrowser;

public class BrowserUtils {

    private static final TestData testData = JsonFileReader.getTestData();


    public static void authorization() {
        getBrowser().goTo(String.format(
                Configuration.getLoginUri() + Configuration.getWebUri(),
                testData.getLogin(),
                testData.getPassword()));
    }

    public static void sendToken(String token) {
        getBrowser().getDriver().manage().addCookie(new Cookie("token", token));
    }
}
