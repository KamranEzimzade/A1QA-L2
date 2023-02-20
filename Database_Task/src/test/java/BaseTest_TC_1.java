import db.AuthorDB;
import db.ProjectDB;
import db.TestDB;
import logger.Log;
import models.SQL.Author;
import models.SQL.Project;
import models.SQL.Test;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import utils.SQL.ConfigManager;
import utils.SQL.DatabaseConnection;

import java.sql.Timestamp;

public class BaseTest_TC_1 {


    @AfterMethod
    public void addNewTestRecord(ITestResult result) throws NoSuchMethodException {
        int testId = this.getClass().getDeclaredMethod(result.getMethod().getMethodName()).getAnnotation(TestId.class).id();
        Log.info("Adding a new record to the database about the result of each test performed in the corresponding table");

        AuthorDB authorDB = new AuthorDB();
        Author author = authorDB.addAuthor();

        ProjectDB projectDB = new ProjectDB();
        Project project = projectDB.addProject();

        TestDB testDB = new TestDB();
        Test test = new Test();

        test.setName(result.getName());
        test.setStatus_id(result.getStatus());
        test.setMethod_name(result.getInstanceName());
        test.setProject_id(project.getId());
        test.setSession_id(ConfigManager.getSessionId());
        test.setStart_time(new Timestamp(result.getStartMillis()));
        test.setEnd_time(new Timestamp(result.getEndMillis()));
        test.setEnv(ConfigManager.getEnv());
        test.setAuthor_id(author.getId());
        testDB.insert(test);
        Log.info("Entry added successfully");

    }


    @AfterTest
    public void tearDown() {
        DatabaseConnection.getInstance().closeConnection();
    }

}
