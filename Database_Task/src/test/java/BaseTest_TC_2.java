import db.SessionDB;
import db.TestDB;
import logger.Log;
import models.SQL.Test;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import utils.SQL.DatabaseConnection;

import java.sql.Timestamp;
import java.util.ArrayList;


public class BaseTest_TC_2 {

    protected final ArrayList<Test> tests = new ArrayList<models.SQL.Test>();
    private static TestDB testDB = new TestDB();
    private static SessionDB sessionDB = new SessionDB();


    @AfterMethod
    public void updateTestRecord(ITestResult result) throws NoSuchMethodException {
//        int testId = this.getClass().getDeclaredMethod(result.getMethod().getMethodName()).getAnnotation(TestId.class).id();
        Log.warn("UPDATE TEST RECORD");
        Test completedTest = tests.get(tests.size() - 1);
        completedTest.setStart_time((new Timestamp(result.getStartMillis())));
        completedTest.setEnd_time((new Timestamp(result.getEndMillis())));
        completedTest.setStatus_id(result.getStatus());
        Assert.assertTrue(testDB.update(completedTest.getId(), completedTest), "Record hasn't been updated");
    }


    @AfterTest
    public void restoreDatabase() {
        Log.warn("CLOSE DATABASE CONNECTION AND RESTORE DATABASE");
        for (Test test : tests) {
            Assert.assertTrue(testDB.delete(test), "Record hasn't been deleted");
        }
        DatabaseConnection.getInstance().closeConnection();
    }

}
