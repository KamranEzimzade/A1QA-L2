package db;

import dao.IDao;
import exceptions.GeneralException;
import exceptions.NoEntryException;
import logger.Log;
import lombok.SneakyThrows;
import models.SQL.Project;
import utils.SQL.ConfigManager;
import utils.SQL.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectDB implements IDao<Project> {

    List<Project> projects = new ArrayList<>();

    private static final String SQL_GET_BY_ID = "SELECT * FROM project WHERE id = ?";
    private static final String SQL_GET_BY_NAME = "SELECT * FROM project WHERE name = ?";
    private static final String SQL_GET_ATTRIBUTES = "SELECT * FROM project";
    private static final String SQL_INSERT = "INSERT INTO project (name) values (?)";

    @Override
    public List<Project> getAll() {
        ResultSet resultSet;
        projects = new ArrayList<>();
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SQL_GET_ATTRIBUTES)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                projects.add(new Project(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    @SneakyThrows
    @Override
    public Project get(int id) {
        ResultSet rs;
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SQL_GET_BY_ID)) {
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if (rs.next()) {
                return new Project(rs);
            } else throw new GeneralException("No projects present in DB");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException();
        }
    }


    public Project addProject() {
        ResultSet rs;
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SQL_GET_BY_NAME)) {
            statement.setString(1, ConfigManager.getProjectName());
            rs = statement.executeQuery();
            if (rs.next() == true) {
                Log.info("Project is already present in the database");
                return new Project(rs);
            } else {
                ProjectDB projectDB = new ProjectDB();
                Project project = new Project();
                project.setId(ConfigManager.getProjectId());
                project.setName(ConfigManager.getProjectName());
                projectDB.insert(project);
                Log.info("Project inserted to the database");
                return get(project.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException();
        }
    }


    @SneakyThrows
    @Override
    public void insert(Project project) {

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SQL_INSERT)) {
            statement.setString(1, project.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NoEntryException("No project entry has been inserted to DB");
        }
        projects.add(project);
    }

    @Override
    public boolean update(int id, Project project) {
        return false;
    }

    @Override
    public boolean delete(Project project) {
        return false;
    }
}
