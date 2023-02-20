import io.restassured.response.Response;
import models.Post;
import models.User;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.PostService;
import services.UserService;
import utils.ApiUtils;

import java.util.Arrays;
import java.util.List;

import static enums.EndPoint.POST;
import static specifications.ResponseSpecifications.*;
import static utils.JsonParser.setUser;
import static utils.PostUtils.arePostsInAscendingOrder;
import static utils.UserUtils.getUserFromListById;

public class Tests extends BaseTest {

    private static final int VALID_POST_ID = TEST_DATA.getValidPostId();
    private static final int VALID_POST_USER_ID = TEST_DATA.getValidPostUserId();
    private static final int INVALID_POST_ID = TEST_DATA.getInvalidPostId();
    private static final int POST_USER_ID = TEST_DATA.getPostUserId();
    private static final int VALID_USER_ID = TEST_DATA.getValidUserId();


    @Test
    public void restApiTest() {
        log.info("Step 1");
        log.info("Sending a get request to obtain all the posts from the response and checking if there are all in ascending order");
        Response response = PostService.getPosts(baseReqSpec, foundResponseSpec);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Status code doesn't match");
        List<Post> posts = Arrays.asList(response.getBody().as(Post[].class));
        Assert.assertTrue(arePostsInAscendingOrder(posts), "Posts are unsorted");


        log.info("Step 2");
        log.info("Sending a get request to post 99 and doing validations with it's fields");
        Post post99 = PostService.getPost(VALID_POST_ID, foundResponseSpec).getBody().as(Post.class);
        int expectedUserId = VALID_POST_USER_ID;
        Assert.assertEquals(expectedUserId, post99.getUserId());
        int expectedId = VALID_POST_ID;
        Assert.assertEquals(expectedId, post99.getId(), "Values don't match");
        Assert.assertFalse(post99.getTitle().isEmpty(), "Title is empty");
        Assert.assertFalse(post99.getBody().isEmpty(), "Body is empty");

        log.info("Step 3");
        log.info("Sending a get request to post 150 and doing validations of it's being an invalid post");
        String body = PostService.getPost(INVALID_POST_ID, notFoundResponseSpec).getBody().asString();
        Assert.assertEquals(body, "{}", "Post is not empty");

        log.info("Step 4");
        log.info("Sending a post request with randomly generated text");
        Post expectedPost = new Post();
        expectedPost.setBody(RandomStringUtils.randomAlphabetic(CONFIG_DATA.getTextLength()));
        expectedPost.setTitle(RandomStringUtils.randomAlphabetic(CONFIG_DATA.getTextLength()));
        expectedPost.setUserId(POST_USER_ID);
        response = ApiUtils.sendPostRequest(POST, expectedPost, baseReqSpec, createdResponseSpec);
        Post actualPost = response.getBody().as(Post.class);
        Assert.assertEquals(expectedPost.getBody(), actualPost.getBody(), "Bodies are not equal");
        Assert.assertEquals(expectedPost.getTitle(), actualPost.getTitle(), "Titles are not equal");
        Assert.assertEquals(expectedPost.getUserId(), actualPost.getUserId(), "UserId not equal");
        Assert.assertNotNull(actualPost.getId(), "Id is Null");


        log.info("Step 5");
        log.info("Sending a get request to obtain all the users from the response and doing validation for the fifth user");
        response = UserService.getUsers(baseReqSpec, foundResponseSpec);
        User userWithExpectedData = setUser(VALID_USER_ID);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Status code doesn't match");
        List<User> users = Arrays.asList(response.getBody().as(User[].class));
        User user_5 = getUserFromListById(users, TEST_DATA.getValidUserId());
        Assert.assertEquals(userWithExpectedData, user_5, "Users are not equal");


        log.info("Step 6");
        log.info("Sending a get request to specifically fifth user and validating it's information equals to the results from previous test");
        User expectedUser = UserService.getUser(VALID_USER_ID, foundResponseSpec).getBody().as(User.class);
        Assert.assertEquals(user_5, expectedUser, "Users are not equal");
    }
}