package pages;

import org.openqa.selenium.By;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;

public class CardPage extends Form {

    private IButton sendToBottomBtn = getElementFactory().getButton(
            By.xpath("//button[contains(@class,'help-form__send-to-bottom-button')]"), "Button 'Send to bottom' ");

    private ITextBox helpFormTextBox = getElementFactory()
            .getTextBox(By.xpath("//div[contains(@class,'help-form')]"), "Help Form window");

    private IButton acceptCookiesBtn = getElementFactory()
            .getButton(By.xpath("//button[contains(@class,'button--solid')]"), "button cookies accept");

    private ILabel timerLabel = getElementFactory().getLabel(By.xpath("//div[contains(@class,'timer')]"),
            "timer label");

    public CardPage() {
        super(By.xpath("//div[contains(@class,'login-form')]"), "Card Page unique element");
    }

    public void hideHelpForm() {
        sendToBottomBtn.click();
    }

    public boolean isHelpFormTextBoxHidden() {
        return helpFormTextBox.state().waitForDisplayed();
    }

    public void acceptCookies() {
        acceptCookiesBtn.clickAndWait();
    }

    public boolean areCookiesAccepted() {
        return acceptCookiesBtn.state().waitForDisplayed();
    }

    public String retrieveTimerValue() {
        return timerLabel.getText();
    }

    public FirstCard getFirstCard() {
        return new FirstCard();
    }

    public SecondCard getSecondCard() {
        return new SecondCard();
    }

}
