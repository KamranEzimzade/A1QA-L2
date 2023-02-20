package services;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utils.ApiUtils;
import utils.JsonFileReader;

import static enums.EndPoint.GET_USER;
import static enums.EndPoint.USER;

public class UserService {

    public static final String BASE_URI = JsonFileReader.getConfigData().getBaseUrl();

    public static Response getUser(int id, ResponseSpecification responseSpecification) {

        RequestSpecification userSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .addPathParam("userId", id)
                .build();
        return ApiUtils.sendGetRequest(GET_USER, userSpec, responseSpecification);
    }

    public static Response getUsers(RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        return ApiUtils.sendGetRequest(USER, requestSpecification, responseSpecification);
    }

}
