package services;

import configuration.Configuration;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojos.TestData;
import utils.ApiUtils;
import utils.JsonFileReader;
import utils.enums.Endpoint;
import utils.enums.QueryParams;


public class GetTokenService {

    private static TestData testData = JsonFileReader.getTestData();


    public static String getToken(String variant, ResponseSpecification responseSpecification) {

        RequestSpecification tokenSpec = new RequestSpecBuilder()
                .setBaseUri(Configuration.getBaseUri() + Configuration.getApiUri())
                .addQueryParam(QueryParams.VARIANT.getValue(), testData.getVariant())
                .build();

        return ApiUtils.sendPostRequest(Endpoint.GENERATE_TOKEN, tokenSpec, responseSpecification).asString();
    }

}
