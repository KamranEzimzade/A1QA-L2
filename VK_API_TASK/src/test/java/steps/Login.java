package steps;

import model.TestUser;
import org.testng.Assert;
import pages.FeedPage;
import pages.WelcomePage;


public class Login {

    public static void loginStep(TestUser user) {

        WelcomePage welcomePage = new WelcomePage();
        Assert.assertTrue(welcomePage.state().waitForDisplayed(), "EnterPage is not open");

        welcomePage.sendLogin(user.getLogin());
        welcomePage.clickOnSingInButton();
        welcomePage.sendPassword(user.getPassword());
        welcomePage.clickBtnContinue();
        FeedPage feedPage = new FeedPage();
        Assert.assertTrue(feedPage.state().waitForDisplayed(), "MainPage is not open");
    }
}
