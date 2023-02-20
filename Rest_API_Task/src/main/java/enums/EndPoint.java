package enums;

import lombok.Getter;
import utils.JsonFileReader;

public enum EndPoint {

    POST("/posts/"),

    GET_POST("/posts/{postId}"),

    USER("/users/"),

    GET_USER("/users/{userId}");

    @Getter
    private final String endpoint;

    EndPoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
