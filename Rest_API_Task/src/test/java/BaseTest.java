import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeSuite;
import pojos.ConfigData;
import pojos.TestData;
import utils.JsonFileReader;

public class BaseTest {

    public static final String BASE_URI = JsonFileReader.getConfigData().getBaseUrl();
    protected static final String testDataPath = "src/main/resources/testData.json";
    protected static final ConfigData CONFIG_DATA = JsonFileReader.getConfigData();
    protected static final TestData TEST_DATA = JsonFileReader.getJsonData(testDataPath, TestData.class);
    protected static final Logger log = Logger.getLogger(BaseTest.class);
    protected static RequestSpecification baseReqSpec;

    @BeforeSuite
    public static void setBaseRequestSpec() {
        baseReqSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .log(LogDetail.ALL)
                .setContentType(ContentType.JSON)
                .build();
    }

}
