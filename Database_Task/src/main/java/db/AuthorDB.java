package db;

import dao.IDao;
import exceptions.GeneralException;
import exceptions.NoEntryException;
import logger.Log;
import lombok.SneakyThrows;
import models.SQL.Author;
import utils.SQL.ConfigManager;
import utils.SQL.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDB implements IDao<Author> {

    private List<Author> authors = new ArrayList<>();
    private static final String SQL_GET_BY_ID = "SELECT * FROM author WHERE id = ?";
    private static final String SQL_GET_BY_LOGIN = "SELECT * FROM author WHERE login = ?";
    private static final String SQL_GET_ATTRIBUTES = "SELECT * FROM author";
    private static final String SQL_INSERT = "INSERT INTO author (name, login, email) values (?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE author SET " +
            "id = ?, " +
            "name = ?, " +
            "login = ?, " +
            "email = ? ";


    @Override
    public List<Author> getAll() {
        ResultSet resultSet;
        authors = new ArrayList<>();
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SQL_GET_ATTRIBUTES)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                authors.add(new Author(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }


    @SneakyThrows
    @Override
    public Author get(int id) {
        ResultSet rs;
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SQL_GET_BY_ID)) {
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if (rs.next()) {
                return new Author(rs);
            } else throw new GeneralException("No authors present in DB");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error with SQL query");
        }
    }

    public Author addAuthor() {
        ResultSet rs;
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SQL_GET_BY_LOGIN)) {
            statement.setString(1, ConfigManager.getAuthorLogin());
            rs = statement.executeQuery();
            if (rs.next() == true) {
                Log.info("Author is already present in the database");
                return new Author(rs);
            } else {
                AuthorDB authorDB = new AuthorDB();
                Author author = new Author();
                author.setName(ConfigManager.getAuthorName());
                author.setLogin(ConfigManager.getAuthorLogin());
                author.setEmail(ConfigManager.getAuthorEmail());
                authorDB.insert(author);
                Log.info("Author inserted to the database");
                return get(author.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException();
        }
    }


    @SneakyThrows
    @Override
    public void insert(Author author) {

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SQL_INSERT)) {
            statement.setString(1, author.getName());
            statement.setString(2, author.getLogin());
            statement.setString(3, author.getEmail());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NoEntryException("No author entry has been inserted to DB");
        }
        authors.add(author);
    }

    @Override
    public boolean update(int id, Author author) {
        return false;
    }

    @Override
    public boolean delete(Author author) {
        return false;
    }
}
