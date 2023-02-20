package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class WelcomePage extends Form {

    private IButton singInButton = getElementFactory().getButton(By.xpath("//button[contains(@class,'signInButton')]"), "Sing in Button");
    private ITextBox loginTextBox = getElementFactory().getTextBox(By.xpath("//input[@name='login']"), "Login Text Box");
    private ITextBox passwordTextBox = getElementFactory().getTextBox(By.xpath("//input[@name='password']"), "Password Text Box");
    private final IButton buttonSubmit = getElementFactory().getButton(By.xpath("//span[contains(@class, 'vkuiButton__in')]"), "Continue");
    private static final String PAGE_LOC = "index_login";

    public WelcomePage() {
        super(By.id(PAGE_LOC), "Enter Page");
    }

    public void clickOnSingInButton() {
        singInButton.clickAndWait();
    }

    public void sendLogin(String emailOrPhone) {
        loginTextBox.clearAndType(emailOrPhone);
    }

    public void sendPassword(String password) {
        passwordTextBox.clearAndTypeSecret(password);
    }

    public void clickBtnContinue() {
        buttonSubmit.clickAndWait();
    }
}
