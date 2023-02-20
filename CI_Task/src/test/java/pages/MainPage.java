package pages;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    WebDriverWait waits = new WebDriverWait(Singleton.Driver(), Duration.ofSeconds(5));

    private JSONObject game;

    private String privacyPolicyBtn = "//*[@id=\"footer_text\"]/div[2]/a[1]";
    private String searchBox = "store_nav_search_term";
    private String resultBox = "//*[@id=\"term\"]";

    private String firstResultElement = "#search_resultsRows > a:nth-child(1) > div.responsive_search_name_combined > div.col.search_name.ellipsis > span";
    private String secondResultElement = "#search_resultsRows > a:nth-child(2) > div.responsive_search_name_combined > div.col.search_name.ellipsis > span";
    private String labsOnNavigationBar = "#store_nav_area > div.store_nav_bg > div > a:nth-child(10)";

    // Game Information

    private String name = "appHubAppName";
    private String releaseDate = "//div[@class='date']";
    private String price = "//div[@class='game_purchase_price price']";
    private String reviewSummary = "//*[@id=\"userReviews\"]/div[1]/div[2]/span[2]";

    public MainPage() {
    }

    public boolean isMainPageDisplayed() {
        return Singleton.Driver().findElement(By.cssSelector(labsOnNavigationBar)).isDisplayed();
    }

    public String getName() {
        waits.until(ExpectedConditions.visibilityOfElementLocated(By.id(name)));
        return Singleton.Driver().findElement(By.id(name)).getText();
    }

    public String getReleaseDate() {
        waits.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(releaseDate)));
        return Singleton.Driver().findElement(By.xpath(releaseDate)).getText();
    }

    public String getPrice() {
        waits.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(price)));
        return Singleton.Driver().findElement(By.xpath(price)).getText();
    }

    public String getReviewSummary() {
        waits.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(reviewSummary)));
        return Singleton.Driver().findElement(By.xpath(reviewSummary)).getText();
    }

    public void parseGameInfoToJson(String gameNo) {

        game = new JSONObject();

        game.put("Name", Singleton.Driver().findElement(By.id(name)).getText());
        game.put("Release Date",
                Singleton.Driver().findElement(By.xpath(releaseDate)).getText());
        game.put("Price", Singleton.Driver().findElement(By.xpath(price)).getText());
        game.put("Review Summary",
                Singleton.Driver().findElement(By.xpath(reviewSummary)).getText());

        // Write JSON file
        try (FileWriter file = new FileWriter("src/main/resources/" + gameNo + ".json")) {
            file.write(game.toString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Map<String, String> retrieveGameInfo() {

        Iterator<String> game1Itr = game.keys();
        Map<String, String> GameInfo = new HashMap<String, String>();
        while (game1Itr.hasNext()) {
            String info = game1Itr.next();
            GameInfo.put(info, game.getString(info));
        }
        return GameInfo;
    }

    public WebElement firstResultElement() {
        return Singleton.Driver().findElement(By.cssSelector(firstResultElement));
    }

    public WebElement secondResultElement() {
        return Singleton.Driver().findElement(By.cssSelector(secondResultElement));
    }

    public String gameOnResultBox() {

        String gameOnResultBox = Singleton.Driver().findElement(By.xpath(resultBox)).getAttribute("value");
        return gameOnResultBox;
    }

    public void clickPrivacyPolicy() {
        Singleton.Driver().findElement(By.xpath(privacyPolicyBtn)).click();
    }

    public void scrollToFooter() {
        JavascriptExecutor js = (JavascriptExecutor) Singleton.Driver();
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void switchToPolicyTab() {
        String currentWindowHandle = Singleton.Driver().getWindowHandle();
        Set<String> handles = Singleton.Driver().getWindowHandles();
        for (String actual : handles) {
            if (!actual.equalsIgnoreCase(currentWindowHandle))
                Singleton.Driver().switchTo().window(actual);
        }
    }

    public String searchGame(String game) {
        Singleton.Driver().findElement(By.id(searchBox)).sendKeys(game);
        Singleton.Driver().findElement(By.id(searchBox)).sendKeys(Keys.ENTER);
        return game;
    }

}
