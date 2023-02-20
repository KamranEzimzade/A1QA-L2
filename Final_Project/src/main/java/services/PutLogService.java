package services;

import configuration.Configuration;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utils.ApiUtils;
import utils.enums.Endpoint;
import utils.enums.QueryParams;

public class PutLogService {

    public static void putLogs(String testId, String logs, ResponseSpecification responseSpecification) {

        RequestSpecification testLogSpec = new RequestSpecBuilder()
                .setBaseUri(Configuration.getBaseUri() + Configuration.getApiUri())
                .addQueryParam(QueryParams.TEST_ID.getValue(), testId)
                .addQueryParam(QueryParams.CONTENT.getValue(), logs)
                .build();

        ApiUtils.sendPostRequest(Endpoint.SEND_TEST_LOG, testLogSpec, responseSpecification);
    }
}
