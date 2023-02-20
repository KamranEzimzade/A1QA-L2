import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Configurations.ConfigFileReader;
import pages.MainPage;
import pages.PolicyPage;
import pages.Singleton;

public class Task2_1_Test {

        private MainPage mainPage;
        private PolicyPage policyPage;
        private ConfigFileReader fileReader;

        @BeforeTest
        void setup() {
                fileReader = new ConfigFileReader();
                Singleton.Driver().get(fileReader.getUrl());
                Singleton.Driver().manage().window().maximize();
        }

        @Test()
        void privacyPolicy() throws InterruptedException {

                mainPage = new MainPage();
                policyPage = new PolicyPage();

                // Check if the main page is displayed
                Assert.assertTrue(mainPage.isMainPageDisplayed(),
                                "The labs section on the header navigation bar in the main page should be visible");

                // Scroll to the bottom of the main page
                mainPage.scrollToFooter();

                // Click on the Privacy Policy link on the footer
                mainPage.clickPrivacyPolicy();

                // Switch the test to the new opened privacy policy tab
                mainPage.switchToPolicyTab();

                // Check if the privacy policy page is displayed
                Assert.assertTrue(policyPage.isPolicyPageDisplayed(),
                                "The long informative text in the privacy policy page should be visible");

                // Check if the switch language element panel is displayed
                Assert.assertTrue(policyPage.isLanguagePanelDisplayed(),
                                "The panel for changing the page to different languages should be visible");

                // Check if the flags for switching different languages is displayed
                for (int i = 0; i < policyPage.languageIcons.size(); i++) {
                        boolean isFlagDisplayed = policyPage.languageIcons.get(i).isDisplayed();
                        Assert.assertTrue(isFlagDisplayed,
                                        "All the different language options should be visible on the panel");
                }

                // Check if the Policy Revision signed in the current year
                Assert.assertEquals(policyPage.getCurrentYear(), policyPage.policyRevisionSignedYear(),
                                "The policy revision must be signed in the current year");
        }

        @Test()
        void gameSearch() throws InterruptedException {

                mainPage = new MainPage();

                Singleton.Driver().get(fileReader.getUrl());

                // Check if the main page is displayed
                Assert.assertTrue(mainPage.isMainPageDisplayed(),
                                "The labs section on the header navigation bar in the main page should be visible");

                // Search the game in the search box
		
	        String nameOfTheGame = mainPage.searchGame(fileReader.getGame());
		

                // Check if the search box on the result page contains searched name
                Assert.assertEquals(nameOfTheGame, mainPage.gameOnResultBox(),
                                "The name on the searchbox of result page should be equal to the searched name");

                // Check if the first name on the results is equal to the searched name
                String firstResult = mainPage.firstResultElement().getText();
                String secondResult = mainPage.secondResultElement().getText();

                Assert.assertEquals(nameOfTheGame, firstResult,
                                "The first result after the search should be equal to the searched name");

                // Go to the first result
                mainPage.firstResultElement().click();

                // Retrieve Game Information from the page for First Result
                mainPage.parseGameInfoToJson("game1");
               
                Assert.assertEquals(mainPage.getPrice(), mainPage.retrieveGameInfo().get("Price"),
                                "The price on the web page should be equal to the one stored in the json file for the first game");
                Assert.assertEquals(mainPage.getReviewSummary(), mainPage.retrieveGameInfo().get("Review Summary"),
                                "The review summary statistic on the web page should be equal to the one stored in the json file for the first game");
                Assert.assertEquals(mainPage.getName(), mainPage.retrieveGameInfo().get("Name"),
                                "The name on the web page should be equal to the one stored in the json file for the first game");
                Assert.assertEquals(mainPage.getReleaseDate(), mainPage.retrieveGameInfo().get("Release Date"),
                                "The release date on the web page should be equal to the one stored in the json file for the first game");

                // Go to the second result
                mainPage.searchGame(secondResult);
                mainPage.firstResultElement().click();

                // Retrieve Game Information from the page for Second Result
                mainPage.parseGameInfoToJson("game2");

                Assert.assertEquals(mainPage.getPrice(), mainPage.retrieveGameInfo().get("Price"),
                                "The price on the web page should be equal to the one stored in the json file for the second game");
                Assert.assertEquals(mainPage.getReviewSummary(), mainPage.retrieveGameInfo().get("Review Summary"),
                                "The review summary statistic on the web page should be equal to the one stored in the json file for the second game");
                Assert.assertEquals(mainPage.getName(), mainPage.retrieveGameInfo().get("Name"),
                                "The name on the web page should be equal to the one stored in the json file for the second game");
                Assert.assertEquals(mainPage.getReleaseDate(), mainPage.retrieveGameInfo().get("Release Date"),
                                "The release date on the web page should be equal to the one stored in the json file for the second game");
        }

        @AfterTest
        void clearCookies() throws InterruptedException {
                Singleton.Driver().manage().deleteAllCookies();
                Singleton.Driver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }

        @AfterTest
        void tearDown() throws InterruptedException {
                Singleton.Driver().close();
        }

}
