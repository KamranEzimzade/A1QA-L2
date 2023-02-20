package db;

import dao.IDao;
import exceptions.NoEntryException;
import lombok.SneakyThrows;
import models.SQL.Project;
import models.SQL.Session;
import utils.SQL.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SessionDB implements IDao<Session> {
    List<Session> sessions = new ArrayList<>();

    private static final String SQL_GET = "SELECT * FROM session WHERE id = ?";
    String SQL_GET_ATTRIBUTES = "SELECT * FROM session";
    String SQL_INSERT = "INSERT INTO session (session_key, created_time, build_number) values (?, ?, ?)";

    @Override
    public List<Session> getAll() {
        ResultSet resultSet;
        sessions = new ArrayList<>();
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SQL_GET_ATTRIBUTES)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                sessions.add(new Session(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sessions;
    }

    @Override
    public Session get(int id) {
        ResultSet rs;
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SQL_GET)) {
            rs = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException();
        }
        return new Session(rs);
    }


    @SneakyThrows
    @Override
    public void insert(Session session) {

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SQL_INSERT)) {
            statement.setString(1, session.getSession_key());
            statement.setTimestamp(2, session.getCreated_time());
            statement.setInt(3, session.getBuild_number());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NoEntryException("No session entry has been inserted to DB");
        }
        sessions.add(session);
    }

    @Override
    public boolean update(int id, Session session) {

        return false;
    }

    @Override
    public boolean delete(Session session) {
        return false;
    }
}
