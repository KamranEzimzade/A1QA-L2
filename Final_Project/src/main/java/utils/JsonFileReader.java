package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;
import pojos.TestData;

import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

public class JsonFileReader {

    private static final String testDataFilePath = "src/main/resources/testData.json";

    public static TestData getTestData() {

        TestData testData = null;

        try {

            Reader reader = Files.newReader(Paths.get(testDataFilePath).toFile(), StandardCharsets.UTF_8);

            ObjectMapper objectMapper = new ObjectMapper();

            testData = objectMapper.readValue(reader, TestData.class);

            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return testData;
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
