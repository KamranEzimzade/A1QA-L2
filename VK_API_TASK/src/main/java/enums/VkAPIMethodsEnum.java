package enums;

public enum VkAPIMethodsEnum {

    WALL_POST("wall.post"),
    WALL_DELETE_POST("wall.delete"),
    PHOTOS_GET_WALL_UPLOAD_SERVER("photos.getWallUploadServer"),
    PHOTOS_SAVE_WALL_PHOTO("photos.saveWallPhoto"),
    WALL_EDIT_POST("wall.edit"),
    WALL_CREATE_COMMENT("wall.createComment"),
    WALL_GET_LIKES("wall.getLikes");

    private String method;

    VkAPIMethodsEnum(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }
}
