package services;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utils.ApiUtils;
import utils.JsonFileReader;

import static enums.EndPoint.GET_POST;
import static enums.EndPoint.POST;


public class PostService {

    public static final String BASE_URI = JsonFileReader.getConfigData().getBaseUrl();

    public static Response getPost(int id, ResponseSpecification responseSpecification) {

        RequestSpecification postSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .addPathParam("postId", id)
                .build();

        return ApiUtils.sendGetRequest(GET_POST, postSpec, responseSpecification);
    }

    public static Response getPosts(RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        return ApiUtils.sendGetRequest(POST, requestSpecification, responseSpecification);
    }
}
