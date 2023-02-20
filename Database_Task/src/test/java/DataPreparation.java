import db.AuthorDB;
import db.TestDB;
import logger.Log;
import models.SQL.Test;
import org.testng.annotations.DataProvider;
import utils.SQL.ConfigManager;

import java.util.ArrayList;

public class DataPreparation {

    private static TestDB testDB = new TestDB();
    private static AuthorDB authorDB = new AuthorDB();


    @DataProvider(name = "getTestRecords")
    public Object[][] getTestRecords() {
        Log.warn("GET RECORDS FOR TEST");
        ArrayList<Test> prepared = getPreparedTestRecords();
        Test[][] tests = new Test[prepared.size()][1];
        for (int i = 0; i < tests.length; i++) {
            tests[i][0] = testDB.copy(prepared.get(i));
        }
        return tests;
    }

    public ArrayList<Test> getPreparedTestRecords() {
        Log.warn("PREPARATION TEST RECORDS FOR TEST");
        ArrayList<Integer> IDs = getDoubleDigitIDList(ConfigManager.getTestsNumber());
        ArrayList<Test> tests = new ArrayList<>();
        for (int i = 0; i < IDs.size(); i++) {
            tests.add(testDB.get(IDs.get(i)));
            tests.get(i).getEnv();
            tests.get(i).setAuthor_id(authorDB.addAuthor().getId());
        }
        return tests;
    }

    public ArrayList<Integer> getDoubleDigitIDList(int size) {
        Log.info("Get " + size + " double digit numbers");
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 10; i < 1000; i++) {
            char[] number = String.valueOf(i).toCharArray();
            char previous = number[0];
            for (int j = 1; j < number.length; j++) {
                if (number[j] == previous && result.size() < size) result.add(i);
                previous = number[j];
            }
        }
        return result;
    }

}