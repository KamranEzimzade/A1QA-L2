package utils.API;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;
import pojos.API.ConfigData;

import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

public class JsonFileReader {


    public static ConfigData getConfigData() {

        ConfigData configData = null;

        try {

            Reader reader = Files.newReader(Paths.get("src/main/resources/API/config.json").toFile(), StandardCharsets.UTF_8);

            ObjectMapper objectMapper = new ObjectMapper();

            configData = objectMapper.readValue(reader, ConfigData.class);

            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return configData;
    }

    public static <T> T getJsonData(String jsonFilePath, Class<T> testClass) {

        T jsonData = null;

        try {
            Reader reader = Files.newReader(Paths.get(jsonFilePath).toFile(),
                    StandardCharsets.UTF_8);

            ObjectMapper objectMapper = new ObjectMapper();

            jsonData = objectMapper.readValue(reader, testClass);

            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return jsonData;
    }

}
