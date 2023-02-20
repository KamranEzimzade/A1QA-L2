package pojos.API;

import lombok.Data;

@Data
public class ConfigData {

    private String baseUrl;
    private String postsUrl;
    private String usersUrl;
    private String testDataUrl;
    private String db_credentials_url;
    private int textLength;

}
