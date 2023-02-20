package utils.API;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static enums.API.BaseUri.BASE_URI;
import static io.restassured.RestAssured.given;

public class ApiUtils {


    public ApiUtils() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URI.getUri())
                .setContentType(ContentType.JSON)
                .build();
    }

    public <T> List<T> getList(String uri, Class<T> clazz) {
        return given()
                .basePath(uri)
                .get()
                .then().statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .extract().body().jsonPath().getList(StringUtils.EMPTY, clazz);
    }

    public <T> T getValidObject(String uri, int id, Class<T> clazz) {
        return given()
                .basePath(uri + id)
                .get()
                .then().statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .extract().body().jsonPath().getObject(StringUtils.EMPTY, clazz);
    }

    public <T> T getInvalidObject(String uri, int id, Class<T> clazz) {
        return given()
                .basePath(uri + id)
                .get()
                .then().statusCode(HttpStatus.SC_NOT_FOUND)
                .contentType(ContentType.JSON)
                .extract().body().jsonPath().getObject(StringUtils.EMPTY, clazz);
    }

    public <T> T sendPostRequest(String uri, T resource, Class<T> clazz) {
        return given()
                .basePath(uri)
                .body(resource)
                .when().post()
                .then().statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .extract().body().jsonPath().getObject(StringUtils.EMPTY, clazz);
    }
}