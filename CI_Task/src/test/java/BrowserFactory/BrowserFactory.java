package BrowserFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import Configurations.ConfigFileReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {

    public static WebDriver driver;
    private static ConfigFileReader fileReader = new ConfigFileReader();

    public static WebDriver createDriver() {
        switch (fileReader.getBrowser()) {
            case "firefox":
                WebDriverManager.firefoxdriver().clearDriverCache().setup();

                // Open Firefox in incognito mode
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--incognito");
                firefoxOptions.addArguments("--headless");
                firefoxOptions.addArguments("--window-size=1920,1080");
                firefoxOptions.addArguments("--start-maximized");

                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "chrome":
                WebDriverManager.chromedriver().clearDriverCache().setup();

                // Open Chrome in incognito mode
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--incognito");
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--window-size=1920,1080");
                chromeOptions.addArguments("--start-maximized");

                driver = new ChromeDriver(chromeOptions);
                break;
        }

        return driver;

    }

}
