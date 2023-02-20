package utils.enums;

import lombok.Getter;

public enum ProjectNameJsMethod {
    NEXAGE("Nexage"),
    CLOSE("window.close();");

    @Getter
    private final String value;

    ProjectNameJsMethod(String value) {
        this.value = value;
    }
}