import enums.API.EndPoint;
import logger.Log;
import models.API.Post;
import models.API.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.API.ConfigData;
import pojos.API.TestData;
import utils.API.ApiUtils;
import utils.API.JsonFileReader;

import java.util.List;

import static enums.API.EndPoint.POST;
import static enums.API.EndPoint.USER;
import static utils.API.GetSpecificUser.getUserFromListById;
import static utils.API.JsonParser.setUser;
import static utils.API.OrderChecker.arePostsInAscendingOrder;

@Test(testName = "Add Test Results to DB for TC-1---")
public class TestCase_1 extends BaseTest_TC_1 {
    private static final ConfigData CONFIG_DATA = JsonFileReader.getConfigData();
    private static final TestData TEST_DATA = JsonFileReader.getJsonData(CONFIG_DATA.getTestDataUrl(), TestData.class);
    private static final int VALID_POST_ID = TEST_DATA.getValidPostId();
    private static final int VALID_POST_USER_ID = TEST_DATA.getValidPostUserId();
    private static final int INVALID_POST_ID = TEST_DATA.getInvalidPostId();
    private static final int POST_USER_ID = TEST_DATA.getPostUserId();
    private static final int VALID_USER_ID = TEST_DATA.getValidUserId();
    private static final ApiUtils request = new ApiUtils();


    @Test
    @TestId(id = 770)
    public void restApiTest() {
        Log.info("Step 1");
        Log.info("Sending a get request to obtain all the posts from the response and checking if there are all in ascending order");
        List<Post> posts = request.getList(EndPoint.POST.getEndpoint(), Post.class);
        Assert.assertTrue(arePostsInAscendingOrder(posts));


        Log.info("Step 2");
        Log.info("Sending a get request to post 99 and doing validations with it's fields");
        Post post99 = request.getValidObject(POST.getEndpoint(), VALID_POST_ID, Post.class);
        int expectedUserId = VALID_POST_USER_ID;
        Assert.assertEquals(expectedUserId, post99.getUserId());
        int expectedId = VALID_POST_ID;
        Assert.assertEquals(expectedId, post99.getId());
        Assert.assertFalse(post99.getTitle().isEmpty());
        Assert.assertFalse(post99.getBody().isEmpty());


        Log.info("Step 3");
        Log.info("Sending a get request to post 150 and doing validations of it's being an invalid post");
        Assert.assertNotNull(request.getInvalidObject(POST.getEndpoint(), INVALID_POST_ID, Post.class), "Post is not valid");


        Log.info("Step 4");
        Log.info("Sending a post request with randomly generated text");
        Post expectedPost = new Post();
        expectedPost.setBody(RandomStringUtils.randomAlphabetic(CONFIG_DATA.getTextLength()));
        expectedPost.setTitle(RandomStringUtils.randomAlphabetic(CONFIG_DATA.getTextLength()));
        expectedPost.setUserId(POST_USER_ID);
        Post actualPost = request.sendPostRequest(EndPoint.POST.getEndpoint(), expectedPost, Post.class);
        Assert.assertEquals(expectedPost.getBody(), actualPost.getBody(), "Bodies are not equal");
        Assert.assertEquals(expectedPost.getTitle(), actualPost.getTitle(), "Titles are not equal");
        Assert.assertEquals(expectedPost.getUserId(), actualPost.getUserId(), "UserId not equal");
        Assert.assertNotNull(actualPost.getId(), "Id is Null");


        Log.info("Step 5");
        Log.info("Sending a get request to obtain all the users from the response and doing validation for the fifth user");
        List<User> userList = request.getList(USER.getEndpoint(), User.class);
        User userWithExpectedData = setUser(VALID_USER_ID);
        User user_5 = getUserFromListById(userList, TEST_DATA.getValidUserId());
        Assert.assertEquals(userWithExpectedData, user_5, "Users are not equal");


        Log.info("Step 6");
        Log.info("Sending a get request to specifically fifth user and validating it's information equals to the results from previous test");
        Assert.assertEquals(user_5, request.getValidObject(USER.getEndpoint(), VALID_USER_ID, User.class), "Users are not equal");
    }

}