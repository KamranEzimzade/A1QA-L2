package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Calendar;
import java.util.List;

public class PolicyPage {

    public List<WebElement> languageIcons = Singleton.Driver().findElements(By.cssSelector("#languages > a"));
    private String revisionSignedText = "//*[@id=\"newsColumn\"]/i[3]";
    private String newsColumnElement = "//div[@id='newsColumn']";
    private String languagePanel = "languages";
    


    public PolicyPage() {
    }

    public boolean isPolicyPageDisplayed() {
        return Singleton.Driver().findElement(By.xpath(newsColumnElement)).isDisplayed();
    }


    public boolean isLanguagePanelDisplayed() {
        return Singleton.Driver().findElement(By.id(languagePanel)).isDisplayed();
    }

    public int policyRevisionSignedYear() {
        String dateTextInPage = Singleton.Driver().findElement(By.xpath(revisionSignedText)).getText();
        String yearString = dateTextInPage.substring(dateTextInPage.indexOf(", ") + 2).trim();
        int yearInPage = Integer.parseInt(yearString);
        return yearInPage;
    }

    public int getCurrentYear() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return currentYear;
    }
}
