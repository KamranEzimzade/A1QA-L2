package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class FeedPage extends Form {

    private static final String PAGE_LOC = "main_feed";
    private IButton myPageButton = getElementFactory().getButton(By.id("l_pr"), "My Page Button");

    public FeedPage() {
        super(By.id(PAGE_LOC), "News Page");
    }

    public void clickOnMyPageButton() {
        myPageButton.clickAndWait();
    }
}
