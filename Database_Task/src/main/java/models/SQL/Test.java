package models.SQL;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Data
public class Test {
    private Integer id;
    private String name;
    private Integer status_id;
    private String method_name;
    private Integer project_id;
    private Integer session_id;
    private Timestamp start_time;
    private Timestamp end_time;
    private String env;
    private String browser;
    private Integer author_id;

    public Test() {

    }

    public Test(ResultSet resultSet) {
        try {
            id = resultSet.getInt("id");
            name = resultSet.getString("name");
            status_id = resultSet.getInt("status_id");
            method_name = resultSet.getString("method_name");
            project_id = resultSet.getInt("project_id");
            session_id = resultSet.getInt("session_id");
            start_time = resultSet.getTimestamp("start_time");
            end_time = resultSet.getTimestamp("end_time");
            env = resultSet.getString("env");
            browser = resultSet.getString("browser");
            author_id = resultSet.getInt("author_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Test(Test copied) {
        this.id = copied.getId();
        this.name = copied.getName();
        this.status_id = copied.getStatus_id();
        this.method_name = copied.getMethod_name();
        this.project_id = copied.getProject_id();
        this.session_id = copied.getSession_id();
        this.start_time = copied.getStart_time();
        this.end_time = copied.getEnd_time();
        this.env = copied.getEnv();
        this.browser = copied.getBrowser();
        this.author_id = copied.getAuthor_id();
    }
}