package db;

import dao.IDao;
import exceptions.GeneralException;
import exceptions.NoEntryException;
import logger.Log;
import lombok.SneakyThrows;
import models.SQL.Test;
import utils.SQL.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestDB implements IDao<Test> {

    List<Test> tests = new ArrayList<>();
    private static final String SQL_GET_ATTRIBUTES = "SELECT * FROM test";
    private static final String SQL_GET = "SELECT * FROM test WHERE id = ?";
    private static final String SQL_INSERT = "INSERT INTO test (name, status_id, method_name, project_id, " +
            "session_id, start_time, end_time, env, browser, author_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE test SET " +
            "name = ?, " +
            "status_id = ?, " +
            "method_name = ?, " +
            "project_id = ?, " +
            "session_id = ?, " +
            "start_time = ?, " +
            "end_time = ?, " +
            "env = ?, " +
            "browser = ?, " +
            "author_id = ? " +
            "WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM test WHERE id = ?";
    private static final String SQL_GET_MAX_ID = "SELECT MAX(id) FROM test";

    @Override
    public List<Test> getAll() {
        ResultSet resultSet;
        tests = new ArrayList<>();
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SQL_GET_ATTRIBUTES)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tests;
    }

    @SneakyThrows
    @Override
    public Test get(int id) {
        ResultSet rs;
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SQL_GET)) {
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if (rs.next()) {
                return new Test(rs);
            } else throw new GeneralException("No test in the db");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException();
        }
    }


    @SneakyThrows
    @Override
    public void insert(Test test) {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        List<Test> testTables = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
            statement.setString(1, test.getName());
            statement.setInt(2, test.getStatus_id());
            statement.setString(3, test.getMethod_name());
            statement.setInt(4, test.getProject_id());
            statement.setInt(5, test.getSession_id());
            statement.setTimestamp(6, test.getStart_time());
            statement.setTimestamp(7, test.getEnd_time());
            statement.setString(8, test.getEnv());
            statement.setString(9, test.getBrowser());
            statement.setInt(10, test.getAuthor_id());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NoEntryException("No test entry has been inserted to DB");
        }
        tests.add(test);
    }

    @Override
    public boolean update(int id, Test test) {
        tests = new ArrayList<>();

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SQL_UPDATE)) {
            statement.setString(1, test.getName());
            statement.setInt(2, test.getStatus_id());
            statement.setString(3, test.getMethod_name());
            statement.setInt(4, test.getProject_id());
            statement.setInt(5, test.getSession_id());
            statement.setTimestamp(6, test.getStart_time());
            statement.setTimestamp(7, test.getEnd_time());
            statement.setString(8, test.getEnv());
            statement.setString(9, test.getBrowser());
            statement.setInt(10, test.getAuthor_id());
            statement.setInt(11, test.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tests.add(test);
        return true;
    }


    @Override
    public boolean delete(Test test) {
        List<Test> testTables = new ArrayList<>();
        ResultSet rs;
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SQL_DELETE)) {
            statement.setInt(1, test.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tests.remove(test);
        return true;
    }


    private int getMaxID() {
        Log.info("Get max test ID");
        ResultSet rs;
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SQL_GET_MAX_ID)) {
            rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            } else throw new RuntimeException("No max id in the db");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException();
        }
    }


    public Test copy(Test test) {
        Log.info("Copy test: " + test);
        Test copied = new Test(test);
        insert(copied);
        return get(getMaxID());
    }


}