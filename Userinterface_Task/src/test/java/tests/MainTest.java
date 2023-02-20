package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CardPage;
import pages.MainPage;
import utils.JsonFileReader;
import utils.RandomUtil;

public class MainTest extends BaseTest {

    private MainPage mainPage = new MainPage();
    private CardPage cardPage = new CardPage();
    private JsonFileReader fileReader = new JsonFileReader();

    @Test
    public void firstTestCase() {
        Assert.assertTrue(mainPage.state().waitForDisplayed(), "Welcome page is not displayed");
        log.info("Clicking 'HERE' button to navigate the next page");
        mainPage.clickOnHereBtn();
        Assert.assertTrue(cardPage.getFirstCard().state().waitForDisplayed(), "Authorization page is not displayed");
        log.info("Entering the authorization details to the form");
        cardPage.getFirstCard().enterPassword(RandomUtil.getPassword());
        cardPage.getFirstCard().enterEmail(RandomUtil.getEmail());
        cardPage.getFirstCard().enterDomain(RandomUtil.getDomain());
        cardPage.getFirstCard().clickOtherDomain();
        cardPage.getFirstCard().pickDomainForEmail();
        cardPage.getFirstCard().acceptTermsConditions();
        cardPage.getFirstCard().clickNext();
        Assert.assertTrue(cardPage.getSecondCard().state().waitForDisplayed(), "Second card isn't open.");
        cardPage.getSecondCard().unselectAllInterests();
        cardPage.getSecondCard().selectInterests(fileReader.getTestData().getNumberOfInterests());
        cardPage.getSecondCard().switchToNextCard();
    }

    @Test
    public void secondTestCase() {
        Assert.assertTrue(mainPage.state().waitForDisplayed(), "Welcome page is not displayed");
        log.info("Clicking 'HERE' button to navigate the next page");
        mainPage.clickOnHereBtn();
        Assert.assertTrue(cardPage.getFirstCard().state().waitForDisplayed(), "Authorization page is not displayed");
        log.info("Closing the help form window");
        cardPage.hideHelpForm();
        Assert.assertTrue(cardPage.isHelpFormTextBoxHidden(), "Help Form window is still visible");
    }

    @Test
    public void thirdTestCase() {
        Assert.assertTrue(mainPage.state().waitForDisplayed(), "Welcome page is not displayed");
        log.info("Clicking 'HERE' button to navigate the next page");
        mainPage.clickOnHereBtn();
        Assert.assertTrue(cardPage.getFirstCard().state().waitForDisplayed(), "Authorization page is not displayed");
        log.info("Accepting cookies...");
        cardPage.acceptCookies();
        Assert.assertTrue(cardPage.areCookiesAccepted(), "Cookies banner is still visible");
    }

    @Test
    public void fourthTestCase() {
        Assert.assertTrue(mainPage.state().waitForDisplayed(), "Welcome page is not displayed");
        log.info("Clicking 'HERE' button to navigate the next page");
        mainPage.clickOnHereBtn();
        Assert.assertTrue(cardPage.getFirstCard().state().waitForDisplayed(), "Authorization page is not displayed");
        log.info("Validating that timer starts counting from: "
                + fileReader.getTestData().getTimerStartValue());
        Assert.assertEquals(cardPage.retrieveTimerValue(), fileReader.getTestData().getTimerStartValue(),
                "Invalid timer value");
    }

}
