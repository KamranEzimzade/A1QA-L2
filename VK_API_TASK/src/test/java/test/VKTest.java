package test;

import aquality.selenium.browser.AqualityServices;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MyPage;
import pages.FeedPage;
import pages.PostForm;
import pojos.CreateNewPostOnWallPojos;
import pojos.LikePojos;
import steps.Login;
import steps.SendPhoto;
import model.PhotoStepFields;
import utils.ImageUtils;
import restApiUtils.ApiUtils;
import restApiUtils.ResponseSpecs;

import java.util.stream.Collectors;


public class VKTest extends BaseTest {

    @Test
    public void VKTest() {

        logger.info("2. Authorize");
        Login.loginStep(user);

        logger.info("3. Go to My Profile");
        FeedPage feedPage = new FeedPage();
        feedPage.clickOnMyPageButton();
        MyPage myPage = new MyPage();
        Assert.assertTrue(myPage.state().waitForDisplayed(), "MyPage is not open");

        logger.info("4. Create post on wall with randomly generated text");
        String firstRandomText = RandomStringUtils.randomAlphabetic(Integer.parseInt(testData.getRandomLength()));
        CreateNewPostOnWallPojos createdNewPostOnWall = ApiUtils.postOnWall(firstRandomText, ResponseSpecs.OK);

        logger.info("5. Checking the created post");
        PostForm post = myPage.getPostForm(user.getOwnerId(), createdNewPostOnWall.getResponse().getPostId());
        Assert.assertTrue(post.isNewCreatedPostExist(), "Post is not created");
        Assert.assertTrue(post.getTextFromPost().contains(firstRandomText), "Post is not contains the text");

        logger.info("6. Editing the post");
        PhotoStepFields photoStepFields = SendPhoto.sendPhotoStep(user);
        String editRandomText = RandomStringUtils.randomAlphabetic(Integer.parseInt(testData.getRandomLength()));
        CreateNewPostOnWallPojos createNewPostOnWallPojos = ApiUtils.editPostOnWall(editRandomText,
                createdNewPostOnWall.getResponse().getPostId(), photoStepFields.getPhoto(), ResponseSpecs.OK);

        logger.info("7. Checking edited post");
        Assert.assertFalse(post.getTextFromPost().contains(firstRandomText), "Post did not change the text");
        Assert.assertTrue(post.isPhotoFromPostDisplayed(photoStepFields.getSavePhotoPojos().getResponse().get(0).getId()),
                "Photo is not found");
        String actualImage = post.downloadFullImageFromPost(photoStepFields.getSavePhotoPojos().getResponse().get(0).getId());
        String expectedImage = testData.getPhoto();
        Assert.assertEquals(String.valueOf(ImageUtils.compareImages(expectedImage, actualImage)), testData.getDifference(), "Images aren't the same");
        AqualityServices.getBrowser().goBack();
        ImageUtils.deleteFile(actualImage);

        logger.info("8. Add a comment to post with randomly generated text");
        String randomStringForComment = RandomStringUtils.randomAlphabetic(Integer.parseInt(testData.getRandomLength()));
        ApiUtils.createNewCommentOnWall(createdNewPostOnWall.getResponse().getPostId(), randomStringForComment,
                ResponseSpecs.OK);

        logger.info("9. Checking new comment");
        post.clickOnShowNextComment();
        Assert.assertTrue(post.isNewCommentOnWallDisplayed(), "Comment from correct user is not created");

        logger.info("10. like the post using UI");
        post.clickOnLikePost();

        logger.info("11. Checking the post received the like");
        LikePojos likesPojos = ApiUtils.getLikesFromPost(createdNewPostOnWall.getResponse().getPostId(), ResponseSpecs.OK);
        Assert.assertTrue(likesPojos.getResponse().getUsers().stream().map(user -> user.getUid()).
                collect(Collectors.toList())
                .contains(Integer.valueOf(user.getOwnerId())), "No like from this User");

        logger.info("12. Delete the post using API");
        ApiUtils.deletePostOnWall(createdNewPostOnWall.getResponse().getPostId(), ResponseSpecs.OK);
        post.state().waitForNotDisplayed();

        logger.info("13. Checking if the post still exist");
        Assert.assertFalse(post.isNewCreatedPostExist(), "Post is not deleted");
    }
}
