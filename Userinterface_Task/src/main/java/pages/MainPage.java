package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MainPage extends Form {

    private IButton btnHere = getElementFactory().getButton(By.className("start__link"), "HERE button on main page");

    public MainPage() {
        super(By.xpath("//a[contains(@href,'/game.html')]"), "main page unique element");
    }

    public void clickOnHereBtn() {
        btnHere.clickAndWait();
    }


}
