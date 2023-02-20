package pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePojos {

    @JsonProperty("album_id")
    private Integer albumId;
    private Integer date;
    private Integer id;
    @JsonProperty("owner_id")
    private Integer ownerId;
    @JsonProperty("access_key")
    private String accessKey;
    private List<ResponseSizes> sizes;
    private String text;
    @JsonProperty("has_tags")
    private boolean hasTags;

}
