package services;

import configuration.Configuration;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.Project;
import utils.ApiUtils;
import utils.enums.Endpoint;
import utils.enums.QueryParams;

import java.util.List;

public class GetTestListService {

    public static List<Project> getTestList(Character projectId, ResponseSpecification responseSpecification) {

        RequestSpecification testListSpec = new RequestSpecBuilder()
                .setBaseUri(Configuration.getBaseUri() + Configuration.getApiUri())
                .addQueryParam(QueryParams.PROJECT_ID.getValue(), projectId)
                .build();

        return ApiUtils.sendPostRequest(Endpoint.GET_LIST_TESTS_JSON, testListSpec, responseSpecification).getBody().jsonPath().getList("", Project.class);
    }
}
