package models.SQL;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Data
public class Session {
    private int id;
    private String session_key;
    private Timestamp created_time;
    private int build_number;

    public Session() {

    }

    public Session(ResultSet resultSet) {
        try {
            id = resultSet.getInt("id");
            session_key = resultSet.getString("session_key");
            created_time = resultSet.getTimestamp("created_time");
            build_number = resultSet.getInt("build_number");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
