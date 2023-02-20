package pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResponseCreatePostOnWallPostId {

    @JsonProperty("post_id")
    private Integer postId;

}
