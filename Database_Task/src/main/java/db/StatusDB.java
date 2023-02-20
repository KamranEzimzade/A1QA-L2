package db;

import dao.IDao;
import exceptions.NoEntryException;
import lombok.SneakyThrows;
import models.SQL.Session;
import models.SQL.Status;
import utils.SQL.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StatusDB implements IDao<Status> {

    List<Status> statuses = new ArrayList<>();
    private static final String SQL_GET = "SELECT * FROM status WHERE id = ?";
    private static final String SQL_GET_ATTRIBUTES = "SELECT * FROM status";
    private static final String SQL_INSERT = "INSERT INTO status (name) values (?)";

    @Override
    public List<Status> getAll() {
        ResultSet resultSet;
        statuses = new ArrayList<>();
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SQL_GET_ATTRIBUTES)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                statuses.add(new Status(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statuses;
    }

    @Override
    public Status get(int id) {
        ResultSet rs;

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SQL_GET)) {
            rs = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException();
        }
        return new Status(rs);
    }


    @SneakyThrows
    @Override
    public void insert(Status status) {

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SQL_INSERT)) {
            statement.setString(1, status.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NoEntryException("No status entry has been inserted to DB");
        }
        statuses.add(status);
    }

    @Override
    public boolean update(int id, Status status) {

        return false;
    }

    @Override
    public boolean delete(Status status) {
        return false;
    }
}
