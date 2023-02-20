package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;
import pojos.ConfigData;

import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

public class JsonFileReader {

    private static final String configFilePath = "src/main/resources/config.json";

    public static ConfigData getConfigData() {

        ConfigData configData = null;

        try {

            Reader reader = Files.newReader(Paths.get(configFilePath).toFile(), StandardCharsets.UTF_8);

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
            // create a reader
            Reader reader = Files.newReader(Paths.get(jsonFilePath).toFile(),
                    StandardCharsets.UTF_8);

            // create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // convert a JSON string to a Book object
            jsonData = objectMapper.readValue(reader, testClass);

            // close reader
            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return jsonData;
    }

}
