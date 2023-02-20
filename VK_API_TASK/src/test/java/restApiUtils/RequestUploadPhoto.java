package restApiUtils;

import aquality.selenium.core.utilities.JsonSettingsFile;
import enums.ParamsEnum;
import io.restassured.http.ContentType;

import java.io.File;

import static io.restassured.RestAssured.given;

public class RequestUploadPhoto {

    private static JsonSettingsFile testData = new JsonSettingsFile("testData.json");
    private static String photoUrl = testData.getValue("/photo").toString();

    public static String uploadPhotoToServer(String uploadServerUrl) {
        return given()
                .contentType(ContentType.MULTIPART)
                .multiPart(ParamsEnum.PHOTO.getParam(), new File(photoUrl))
                .post(uploadServerUrl)
                .then().log().all()
                .extract().asString();
    }
}
