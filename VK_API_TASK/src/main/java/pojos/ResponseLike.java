package pojos;

import lombok.Data;

import java.util.List;

@Data
public class ResponseLike {

    private Integer count;
    private List<ResponseUser> users;

}
