package models.SQL;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class Author {
    private Integer id;
    private String name;
    private String login;
    private String email;

    public Author() {

    }

    public Author(ResultSet resultSet) {
        try {
            id = resultSet.getInt("id");
            name = resultSet.getString("name");
            login = resultSet.getString("login");
            email = resultSet.getString("email");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
