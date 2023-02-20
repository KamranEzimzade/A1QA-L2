package utils.API;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import models.API.User;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

import static enums.API.FilePath.RESOURCE_PATH;
import static enums.API.FilePath.USERS_PATH;

public class JsonParser {


    @SneakyThrows
    public static User setUser(int postId) {
        String jsonString = getJsonStr(String.format((RESOURCE_PATH.getPath() + USERS_PATH.getPath()), postId));
        StringReader reader = new StringReader(jsonString);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(reader, User.class);
    }

    @SneakyThrows
    private static String getJsonStr(String filePath) {
        return FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
    }
}