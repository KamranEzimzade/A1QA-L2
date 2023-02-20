package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;
import models.User;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

public class JsonParser {

    private static final String USERS_PATH = "user%s.json";
    private static final String RESOURCE_PATH = "src/main/resources/";

    @SneakyThrows
    public static User setUser(int postId) {
        String jsonString = getJsonStr(String.format((RESOURCE_PATH + USERS_PATH), postId));
        StringReader reader = new StringReader(jsonString);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(reader, User.class);
    }

    @SneakyThrows
    private static String getJsonStr(String filePath) {
        return FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
    }
}