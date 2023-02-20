package pojos;

import lombok.Data;

@Data
public class TestData {

    private Character variant;
    private String projectName;
    private String testName;
    private String methodName;
    private String browserName;
    private int textLength;
    private String login;
    private String password;

}
