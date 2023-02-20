package test;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.core.utilities.JsonSettingsFile;
import configuration.Configuration;
import enums.ParamsEnum;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import model.TestData;
import model.TestUser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    protected Logger logger = AqualityServices.getLogger();
    protected TestUser user = new TestUser(new JsonSettingsFile("user.json"));
    protected TestData testData = new TestData(new JsonSettingsFile("testData.json"));


    protected static RequestSpecification baseReqSpec;


    @BeforeSuite
    public void setBaseRequestSpec() {
        baseReqSpec = new RequestSpecBuilder()
                .setBaseUri(Configuration.getRestUrl())
                .log(LogDetail.ALL)
                .addQueryParam(ParamsEnum.ACCESS_TOKEN.getParam(), Configuration.getToken())
                .addQueryParam(ParamsEnum.VERSION.getParam(), Configuration.getVersion())
                .setContentType(ContentType.JSON)
                .build();
    }

    @BeforeMethod
    public void beforeMethod() {
        logger.info("1. Go to the VK webpage");
        AqualityServices.getBrowser().maximize();
        AqualityServices.getBrowser().goTo(Configuration.getStartUrl());
        RestAssured.requestSpecification = baseReqSpec;
    }

    @AfterMethod
    public void afterMethod() {
        AqualityServices.getBrowser().quit();
    }
}
