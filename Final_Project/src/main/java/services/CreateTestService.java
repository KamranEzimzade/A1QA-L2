package services;

import aquality.selenium.browser.AqualityServices;
import configuration.Configuration;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utils.ApiUtils;
import utils.enums.Endpoint;
import utils.enums.QueryParams;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateTestService {


    public static String createTest(String SID, String projectName, String testName, String methodName, String browser, ResponseSpecification responseSpecification) {

        String env = null;

        try {
            env = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            AqualityServices.getLogger().info(String.valueOf(e));
        }

        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

        RequestSpecification testCreateSpec = new RequestSpecBuilder().
                setBaseUri(Configuration.getBaseUri() + Configuration.getApiUri()).
                addQueryParam(QueryParams.SID.getValue(), SID).
                addQueryParam(QueryParams.PROJECT_NAME.getValue(), projectName).
                addQueryParam(QueryParams.TEST_NAME.getValue(), testName).
                addQueryParam(QueryParams.METHOD_NAME.getValue(), methodName).
                addQueryParam(QueryParams.ENV.getValue(), env).
                addQueryParam(QueryParams.START_TIME.getValue(), formatForDateNow.format(dateNow)).
                addQueryParam(QueryParams.BROWSER.getValue(), browser).
                build();

        return ApiUtils.sendPostRequest(Endpoint.CREATE_TEST_POST, testCreateSpec, responseSpecification).asString();
    }
}
