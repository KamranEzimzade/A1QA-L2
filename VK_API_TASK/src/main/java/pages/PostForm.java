package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.DownloadPhotoUtil;

import java.io.IOException;

public class PostForm extends Form {

    private static final String POST_ID_LOC = "post%s_%d";
    private static final String PHOTO_FROM_POST_LOC = "//div[@id='post%s_%d']//a[@href='/photo%s_%d']";
    private static final String NEW_COMMENT_ON_WALL_LOC = "//div[@id='post%s_%d']//div[@class='reply_content']//a[@href='/id%s']";
    private static final String LIKE_BUTTON_LOC = "//div[@id='post%s_%d']//div[contains(@class,'PostBottomAction--empty')]";
    private static final String SHOW_NEW_COMMENT_LOC = "//div[@id='post%s_%d']//span[@class='js-replies_next_label']";
    private final ILink image = getElementFactory().getLink(By.xpath("//div[@id='pv_photo']/img"), "Image");
    private static final String SRC_ATRIBUTE = "src";

    private String ownerId;
    private int idOfPost;

    public PostForm(String ownerId, int idOfPost) {
        super(By.id(String.format(POST_ID_LOC, ownerId, idOfPost)), "Post Form");
        this.ownerId = ownerId;
        this.idOfPost = idOfPost;
    }

    private IButton getPhotoFromPost(int idOfPicture) {
        return getElementFactory().getButton(By.xpath(String.format(PHOTO_FROM_POST_LOC, ownerId, idOfPost,
                ownerId, idOfPicture)), "Photo from Post");
    }

    private IButton getNewCommentOnWall() {
        return getElementFactory().getButton(By.xpath(String.format(NEW_COMMENT_ON_WALL_LOC, ownerId, idOfPost,
                ownerId)), "New Comment on wall");
    }

    private IButton getLikeButton() {
        return getElementFactory().getButton(By.xpath(String.format(LIKE_BUTTON_LOC, ownerId, idOfPost)), "Like Button");
    }

    public void clickOnLikePost() {
        getLikeButton().clickAndWait();
    }

    private IButton getShowNextComment() {
        return getElementFactory().getButton(By.xpath(String.format(SHOW_NEW_COMMENT_LOC, ownerId, idOfPost)),
                "Show New Comment Button");
    }

    public void clickOnShowNextComment() {
        getShowNextComment().clickAndWait();
        getShowNextComment().state().waitForDisplayed();
    }

    public String getTextFromPost() {
        return getElementFactory().getTextBox(By.id(String.format(POST_ID_LOC, ownerId, idOfPost)), "Post").getText();
    }

    public Boolean isNewCreatedPostExist() {
        return this.state().waitForDisplayed();
    }

    public Boolean isPhotoFromPostDisplayed(int idOfPicture) {
        return getPhotoFromPost(idOfPicture).state().waitForDisplayed();
    }

    public Boolean isNewCommentOnWallDisplayed() {
        return getNewCommentOnWall().state().waitForDisplayed();
    }

    public String downloadFullImageFromPost(int idOfPicture) {
        getPhotoFromPost(idOfPicture).clickAndWait();
        try {
            return DownloadPhotoUtil.downloadPhoto(image.getAttribute(SRC_ATRIBUTE));
        } catch (IOException e) {
            throw new IllegalArgumentException("Image not found");
        }
    }
}
