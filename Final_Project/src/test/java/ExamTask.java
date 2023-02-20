import forms.ProjectForm;
import forms.ProjectsForm;
import models.Project;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.CreateTestService;
import services.PutLogService;
import services.GetTokenService;
import utils.*;
import utils.enums.QueryParams;

import java.util.List;

import static aquality.selenium.browser.AqualityServices.getBrowser;
import static specifications.ResponseSpecs.OK;
import static utils.enums.ProjectNameJsMethod.NEXAGE;

public class ExamTask extends BaseTest {

    @Test
    public void ExamTest() {
        logger.info("Obtaining a token according to the variant number using API request ");
        String token = GetTokenService.getToken(QueryParams.VARIANT.getValue(), OK);
        logger.info("Token is: " + token);
        Assert.assertFalse(token.isEmpty(), "Token is not obtained");

        logger.info("Passing the authorization");
        BrowserUtils.authorization();
        ProjectsForm projectsForm = new ProjectsForm();
        Assert.assertTrue(projectsForm.state().waitForDisplayed(), "Project page is not open");
        logger.info("Sending a token using cookie");
        BrowserUtils.sendToken(token);
        logger.info("Refreshing the page");
        getBrowser().refresh();
        Assert.assertEquals(projectsForm.getActualVariant(),
                testData.getVariant(), "Variant number does not match");

        Character projID = projectsForm.getProjectId(NEXAGE.getValue());
        logger.info("Going to the project page \"Nexage\"");
        projectsForm.goToProject(NEXAGE.getValue());
        getBrowser().waitForPageToLoad();
        ProjectForm projectForm = new ProjectForm();
        projectForm.waitForModalWindowToAppear();
        logger.info("Getting a list of tests in JSON format using API request");

        List<Project> projects = projectForm.getListWebTests();
        Assert.assertEquals(projectForm.getListWebTimes(projects), SortUtils.sortListReverseOrder(
                        projectForm.getListWebTimes(projects)),
                "Tests on the first page are not sorted in descending order by date");
        Assert.assertTrue(AssertUtils.assertEquals(projects, ApiUtils.getListAPITimes(projID, projects.size())),
                "The tests on the first page do not match with those returned by the API request");

        logger.info("Return to previous page");
        getBrowser().goBack();
        steps.AddProjectStep.addProject(testData.getProjectName());
        logger.info("Refreshing the page");
        getBrowser().refresh();
        Assert.assertTrue(projectsForm.isProjectExist(testData.getProjectName()),
                "The project does not appear in the list, after refreshing the page");

        logger.info("Going to the created project page");
        projectsForm.goToProject(testData.getProjectName());

        String id = CreateTestService.createTest(
                SID,
                testData.getProjectName(),
                testData.getTestName(),
                testData.getMethodName(),
                testData.getBrowserName(), OK);

        PutLogService.putLogs(id, RandomStringUtils.randomAlphabetic(testData.getTextLength()), OK);

        ApiUtils.sendAttachment(id, FileUtil.takeScreenAs64String(), QueryParams.CONTENT_IMAGE_TYPE.getValue());

        Assert.assertTrue(projectForm.getTestByName(testData.getTestName()).state().waitForDisplayed());
    }
}