package utils.SQL;

import logger.Log;
import pojos.API.ConfigData;
import pojos.SQL.CredentialsData;
import utils.API.JsonFileReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final ConfigData CONFIG_DATA = JsonFileReader.getConfigData();

    private static final CredentialsData DB_SETTINGS = JsonFileReader.getJsonData(CONFIG_DATA.getDb_credentials_url(), CredentialsData.class);

    private static Connection connection;

    private static DatabaseConnection databaseConnection;

    public static DatabaseConnection getInstance() {
        if (databaseConnection == null) {
            databaseConnection = new DatabaseConnection();
        }
        return databaseConnection;
    }


    private DatabaseConnection() {
        try {
            connection = DriverManager.getConnection(DB_SETTINGS.getDb_url(), System.getenv("MYSQL_ROOT_USER"), System.getenv("MYSQL_ROOT_PASSWORD"));
            if (connection != null) {
                Log.warn("Connected to the database!");
            } else {
                Log.warn("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        if (connection == null) {
            new DatabaseConnection();
        }
        return connection;
    }


    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
