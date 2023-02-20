package enums.API;

import lombok.Getter;
import utils.API.JsonFileReader;

public enum BaseUri {

    BASE_URI(JsonFileReader.getConfigData().getBaseUrl());

    @Getter
    private final String uri;

    BaseUri(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }
}
