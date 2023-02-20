package pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResponseWallPhotoUploadServerURL {

    @JsonProperty("upload_url")
    private String uploadUrl;
    @JsonProperty("album_id")
    private Integer albumId;
    @JsonProperty("user_id")
    private Integer userId;

}
