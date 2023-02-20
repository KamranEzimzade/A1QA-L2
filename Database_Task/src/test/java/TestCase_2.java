import logger.Log;
import org.testng.annotations.Test;


@Test(testName = "Working with test data")
public class TestCase_2 extends BaseTest_TC_2 {


    @Test(dataProvider = "getTestRecords", dataProviderClass = DataPreparation.class)
    @TestId(id = 780)
    public void testSimulation(models.SQL.Test test) {
        Log.warn("TEST: " + test.getName() + " IS LAUNCHED");
        tests.add(test);
    }
}
