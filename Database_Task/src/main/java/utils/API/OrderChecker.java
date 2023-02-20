package utils.API;

import models.API.Post;

import java.util.List;

public class OrderChecker {


    public static boolean arePostsInAscendingOrder(List<Post> postsList) {
        int previousId = 0;
        for (Post post : postsList) {
            if (post.getId() <= previousId) {
                return false;
            }
        }
        return true;
    }
}
