package utils;

import enums.EndPoint;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.commons.lang3.StringUtils;

import static io.restassured.RestAssured.*;

public class ApiUtils {

    public static Response sendGetRequest(EndPoint endPoint, RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        return given()
                .spec(requestSpecification)
                .basePath(endPoint.getEndpoint())
                .get()
                .then().log().all().spec(responseSpecification).extract().response();
    }


    public static Response sendPostRequest(EndPoint endPoint, Object resource, RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        return given().spec(requestSpecification)
                .basePath(endPoint.getEndpoint())
                .body(resource)
                .when()
                .post().then().log().all().spec(responseSpecification).extract().response();
    }


}