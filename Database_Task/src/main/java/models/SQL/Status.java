package models.SQL;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class Status {
    private int id;
    private String name;

    public Status() {

    }

    public Status(ResultSet resultSet) {
        try {
            id = resultSet.getInt("id");
            name = resultSet.getString("name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
