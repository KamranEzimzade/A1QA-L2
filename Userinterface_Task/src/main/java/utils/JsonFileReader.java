package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;
import configuration.Configuration;
import pojos.TestData;

import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

public class JsonFileReader {

    public TestData getTestData() {

        TestData testData = null;

        try {

            Reader reader = Files.newReader(Paths.get(Configuration.getTestDataPath()).toFile(),
                    StandardCharsets.UTF_8);

            ObjectMapper objectMapper = new ObjectMapper();

            testData = objectMapper.readValue(reader, TestData.class);

            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return testData;
    }

}
