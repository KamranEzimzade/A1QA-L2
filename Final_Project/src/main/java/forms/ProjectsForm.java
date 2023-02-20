package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class ProjectsForm extends Form {

    private final IButton buttonAddProject = getElementFactory().getButton(By.xpath("//a[@href='addProject']"),
            "button add project");

    private final ILabel versionText = getElementFactory().
            getLabel(By.xpath("//p[contains(@class, 'footer-text')]//span"), "footer version text");

    private IButton getNeededProject(String projectName) {
        return getElementFactory().getButton(By.xpath(String.format("//a[text()='%s']", projectName)),
                "project " + projectName);
    }

    public ProjectsForm() {
        super(By.xpath("//div[@class='panel-heading']"), "locator");
    }

    public Character getActualVariant() {
        return getLastNum(versionText.getText());
    }

    public void addProject() {
        buttonAddProject.clickAndWait();
    }

    public Character getProjectId(String projectName) {
        return getLastNum(getNeededProject(projectName).getAttribute("href"));
    }

    public boolean isProjectExist(String projectName) {
        return getElementFactory().getTextBox(By.xpath(String.format("//a[text()='%s']", projectName)),
                "project " + projectName).state().isDisplayed();
    }

    public void goToProject(String projectName) {
        getNeededProject(projectName).clickAndWait();
    }

    private Character getLastNum(String text) {
        Character lastNum = text.charAt(text.length() - 1);
        return lastNum;
    }
}

