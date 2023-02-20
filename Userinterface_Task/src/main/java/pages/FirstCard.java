package pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;

import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import aquality.selenium.elements.ElementType;

public class FirstCard extends Form {

    private static int randomDomain;

    private final ITextBox passwordTextBox = getElementFactory()
            .getTextBox(By.xpath("//input[@placeholder='Choose Password']"), "Password text box");
    private final ITextBox emaITextBox = getElementFactory().getTextBox(
            By.xpath("//input[@placeholder = 'Your email']"),
            "Email text box");
    private final ITextBox domainTextBox = getElementFactory().getTextBox(By.xpath("//input[@placeholder = 'Domain']"),
            "Domain text box");
    private final IButton btnOther = getElementFactory().getButton(By.xpath("//div[@class = 'dropdown__opener']"),
            "Drop down 'Other' ");

    private final ICheckBox acceptCheckBox = getElementFactory().getCheckBox(
            By.xpath("//span[contains(@class,'checkbox')]"),
            "checkbox accept Terms & Conditions");

    private final IButton btnNext = getElementFactory()
            .getButton(By.xpath("//a[contains(@class, 'button--secondary')]"), " Button Next");

    private List<ITextBox> domainList() {
        return getElementFactory().findElements(By.xpath("//div[contains(@class,'dropdown__list-item')]"),
                ElementType.TEXTBOX);
    }

    public FirstCard() {
        super(By.xpath("//div[contains(@class,'login-form')]"), "Card Page unique element");
    }

    public void enterPassword(String password) {
        passwordTextBox.clearAndType(password);
    }

    public void enterEmail(String email) {
        emaITextBox.clearAndType(email);
    }

    public void enterDomain(String domain) {
        domainTextBox.clearAndType(domain);
    }

    public void clickOtherDomain() {
        btnOther.click();
    }

    public void acceptTermsConditions() {
        acceptCheckBox.check();
    }

    public void pickDomainForEmail() {

        randomDomain = randomDomain();

        while (randomDomain == 1) {

            randomDomain();
        }

        domainList().get(randomDomain).getJsActions().click();

    }

    public int randomDomain() {
        return new Random().nextInt(domainList().size());
    }

    public void clickNext() {
        btnNext.click();
    }

}
