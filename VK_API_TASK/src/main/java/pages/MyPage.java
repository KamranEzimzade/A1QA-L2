package pages;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MyPage extends Form {

    public MyPage() {
        super(By.xpath("//div[contains(@class,'ProfileHeader__in')]"), "Profile Page");
    }

    public PostForm getPostForm(String ownerId, int idOfPost) {
        return new PostForm(ownerId, idOfPost);
    }

}
