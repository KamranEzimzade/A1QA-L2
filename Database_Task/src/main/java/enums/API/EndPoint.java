package enums.API;

import lombok.Getter;
import utils.API.JsonFileReader;

public enum EndPoint {

    BASE_URI(JsonFileReader.getConfigData().getBaseUrl()),
    POST("/posts/"),

    USER("/users/");

    @Getter
    private final String endpoint;

    EndPoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
