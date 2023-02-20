package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;


public class AddProjectForm extends Form {

    private final ITextBox inputProjectName = getElementFactory().getTextBox(By.xpath("//input[@name='projectName']"),
            "input for project name");

    private final IButton saveButton = getElementFactory().getButton(By.xpath("//button[@type='submit']"),
            "save project button");

    private final ITextBox successMessage = getElementFactory().getTextBox(By.xpath("//div[contains(@class,'alert-success')]"),
            "alert success");


    public AddProjectForm() {
        super(By.xpath("//form[@id='addProjectForm']"), "locator");
    }

    public void inputProjectName(String projectName) {
        inputProjectName.clearAndType(projectName);
    }

    public void saveProject() {
        saveButton.clickAndWait();
    }

    public boolean isSuccessMessageDisplayed() {
        return successMessage.state().waitForDisplayed();
    }
}