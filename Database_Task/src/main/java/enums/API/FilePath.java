package enums.API;

import lombok.Getter;

public enum FilePath {

    USERS_PATH("user%s.json"),
    RESOURCE_PATH("src/main/resources/API/");

    @Getter
    private final String path;

    FilePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
