package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import configuration.Configuration;

import static aquality.selenium.browser.AqualityServices.getBrowser;

public class BaseTest {

    protected static Logger log = AqualityServices.getLogger();
    protected static String url = Configuration.getStartUrl();

    @BeforeMethod
    protected void navigateMainPage() {
        log.info(String.format("Navigating to: " + url));
        getBrowser().goTo(url);
        getBrowser().maximize();
        getBrowser().waitForPageToLoad();
    }

    @AfterMethod
    protected void tearDown() {
        getBrowser().quit();
    }

}
