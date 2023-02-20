import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import configuration.Configuration;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pojos.TestData;
import utils.JsonFileReader;

import static aquality.selenium.browser.AqualityServices.getBrowser;

public class BaseTest {
    protected Logger logger = AqualityServices.getLogger();
    protected TestData testData = JsonFileReader.getTestData();
    protected String SID;

    @BeforeMethod
    protected void beforeMethod() {
        getBrowser().maximize();
    }

    @BeforeSuite
    protected void beforeSuite() {
        SID = java.time.LocalDateTime.now().toString();

        RestAssured.requestSpecification = new RequestSpecBuilder().
                setBaseUri(Configuration.getBaseUri() + Configuration.getApiUri()).
                setContentType(ContentType.JSON).
                setAccept(ContentType.JSON).
                log(LogDetail.ALL).
                build();
    }

    @AfterMethod
    public void afterMethod() {
        if (AqualityServices.isBrowserStarted()) {
            getBrowser().quit();
        }
    }
}