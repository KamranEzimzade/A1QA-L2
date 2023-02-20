package pojos.SQL;

import lombok.Data;

@Data
public class ConfigDataDb {


    private int author_id;
    private String author_name;
    private String author_login;
    private String author_email;
    private int project_id;
    private String project_name;
    private int session_id;
    private String session_key;
    private int build_number;
    private int tc2_tests_number;
    private String env;


}
