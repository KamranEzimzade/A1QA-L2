package utils;

import configuration.Configuration;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import kong.unirest.HttpRequestWithBody;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import models.Project;
import services.GetTestListService;
import utils.enums.Endpoint;
import utils.enums.QueryParams;

import java.util.List;

import static io.restassured.RestAssured.given;
import static specifications.ResponseSpecs.OK;
import static utils.enums.Endpoint.SEND_TEST_ATTACHMENT;
import static utils.enums.QueryParams.*;

public class ApiUtils {

    public static Response sendPostRequest(Endpoint endPoint, RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        return given().spec(requestSpecification)
                .basePath(endPoint.getEndPoint())
                .when()
                .post().then().log().all().spec(responseSpecification).extract().response();
    }

    public static HttpRequestWithBody post(String route) {
        return Unirest.post(route);
    }

    public static HttpResponse<String> sendAttachment(String testId, String attachmentContent, String contentType) {
        return post(Configuration.getBaseUri() + Configuration.getApiUri() + SEND_TEST_ATTACHMENT.getEndPoint())
                .field(TEST_ID.getValue(), testId)
                .field(CONTENT.getValue(), attachmentContent)
                .field(CONTENT_TYPE.getValue(), contentType).asString();
    }

    public static List<Project> getListAPITimes(Character projId, Integer listSize) {
        List<Project> sortedProjects = SortUtils.sortListForTimeReverseOrder(GetTestListService.getTestList(projId, OK));
        return sortedProjects.subList(0, listSize);
    }

}