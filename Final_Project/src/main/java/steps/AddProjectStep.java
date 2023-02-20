package steps;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import forms.AddProjectForm;
import forms.ProjectsForm;
import org.testng.Assert;

import static aquality.selenium.browser.AqualityServices.getBrowser;
import static utils.enums.ProjectNameJsMethod.CLOSE;

public class AddProjectStep {
    private static final Logger logger = AqualityServices.getLogger();


    public static void addProject(String projName) {
        logger.info("Clicking on '+Add' button");
        ProjectsForm projectsForm = new ProjectsForm();
        projectsForm.addProject();
        getBrowser().tabs().switchToLastTab();
        logger.info("Entering the project name");
        AddProjectForm addProjectForm = new AddProjectForm();
        addProjectForm.inputProjectName(projName);
        logger.info("Saving the project");
        addProjectForm.saveProject();
        Assert.assertTrue(addProjectForm.isSuccessMessageDisplayed(),
                "Success message did not appear");
        logger.info("Closing the Add Project with the JavaScript Method");
        getBrowser().executeScript(CLOSE.getValue());
        Assert.assertFalse(addProjectForm.state().isDisplayed(), "Add project window is still visible");
        getBrowser().tabs().switchToLastTab();
    }
}