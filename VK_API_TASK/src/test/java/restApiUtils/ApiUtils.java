package restApiUtils;

import enums.ParamsEnum;
import enums.VkAPIMethodsEnum;
import io.restassured.specification.ResponseSpecification;
import pojos.CreateNewPostOnWallPojos;
import pojos.LikePojos;
import pojos.SavePhotoPojos;
import pojos.WallPhotoUploadServerPojos;

import static io.restassured.RestAssured.given;

public class ApiUtils {

    public static CreateNewPostOnWallPojos postOnWall(String message, ResponseSpecification responseSpecification) {
        return given()
            .when()
            .queryParam(ParamsEnum.MESSAGE.getParam(), message)
            .post(VkAPIMethodsEnum.WALL_POST.getMethod())
            .then().spec(responseSpecification).log().all()
            .extract().as(CreateNewPostOnWallPojos.class);
    }

    public static void deletePostOnWall(Integer postId, ResponseSpecification responseSpecification) {
        given()
                .when()
                .queryParam(ParamsEnum.POST_ID.getParam(), postId)
                .post(VkAPIMethodsEnum.WALL_DELETE_POST.getMethod())
                .then().spec(responseSpecification).log().all();
    }

    public static WallPhotoUploadServerPojos getWallPhotoUploadServer(ResponseSpecification responseSpecification) {
        return given()
                .when()
                .get(VkAPIMethodsEnum.PHOTOS_GET_WALL_UPLOAD_SERVER.getMethod())
                .then().spec(responseSpecification).log().all()
                .extract().as(WallPhotoUploadServerPojos.class);
    }

    public static SavePhotoPojos saveUploadWallPhoto(Integer server, String photo, String hash, ResponseSpecification responseSpecification) {
        return given()
                .queryParam(ParamsEnum.SERVER.getParam(), server)
                .queryParam(ParamsEnum.PHOTO.getParam(), photo)
                .queryParam(ParamsEnum.HASH.getParam(), hash)
                .post(VkAPIMethodsEnum.PHOTOS_SAVE_WALL_PHOTO.getMethod())
                .then().spec(responseSpecification).log().all()
                .extract().as(SavePhotoPojos.class);
    }

    public static CreateNewPostOnWallPojos editPostOnWall(String message, Integer idPostFromWall, String photo, ResponseSpecification responseSpecification) {
        return given()
                .queryParam(ParamsEnum.MESSAGE.getParam(), message)
                .queryParam(ParamsEnum.ATTACHMENTS.getParam(), photo)
                .queryParam(ParamsEnum.POST_ID.getParam(), idPostFromWall)
                .post(VkAPIMethodsEnum.WALL_EDIT_POST.getMethod())
                .then().spec(responseSpecification).log().all()
                .extract().as(CreateNewPostOnWallPojos.class);
    }

    public static void createNewCommentOnWall(Integer post_id, String message, ResponseSpecification responseSpecification) {
        given()
                .queryParam(ParamsEnum.POST_ID.getParam(), post_id)
                .queryParam(ParamsEnum.MESSAGE.getParam(), message)
                .post(VkAPIMethodsEnum.WALL_CREATE_COMMENT.getMethod())
                .then().spec(responseSpecification).log().all();
    }

    public static LikePojos getLikesFromPost(Integer post_id, ResponseSpecification responseSpecification) {
        return given()
                .queryParam(ParamsEnum.POST_ID.getParam(), post_id)
                .post(VkAPIMethodsEnum.WALL_GET_LIKES.getMethod())
                .then().spec(responseSpecification).log().all()
                .extract().as(LikePojos.class);
    }
}
