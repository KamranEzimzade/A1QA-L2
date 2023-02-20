package enums;

public enum ParamsEnum {

    MESSAGE("message"),
    POST_ID("post_id"),
    SERVER("server"),
    PHOTO("photo"),
    HASH("hash"),
    ATTACHMENTS("attachments"),
    OWNER_ID("owner_id"),
    ACCESS_TOKEN("access_token"),
    VERSION("v");

    private String param;

    ParamsEnum(String param) {
        this.param = param;
    }

    public String getParam() {
        return param;
    }
}
