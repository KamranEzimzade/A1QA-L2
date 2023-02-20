package forms;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import models.Project;
import org.openqa.selenium.By;
import utils.enums.ColumnNames;

import java.util.ArrayList;
import java.util.List;

public class ProjectForm extends Form {


    private final ITextBox modalWindow = getElementFactory().getTextBox(By.xpath("//div[@class='messi-modal']"),
            "messi modal");

    public ITextBox getTable() {
        return getElementFactory().getTextBox(By.xpath("//table[@class='table']"), "table");
    }

    public ITextBox getTestByName(String testName) {
        return getElementFactory().getTextBox(By.xpath(String.format("//td//a[text()='%s']", testName)), "test");
    }

    public ProjectForm() {
        super(By.xpath("//div[@class='panel-heading']"), "locator");
    }

    public List<Project> getListWebTests() {
        List<ITextBox> columnNames = getTableColumnNames(getTable());
        List<ITextBox> rows = getTableRows(getTable());

        List<Project> projects = new ArrayList<>();
        Project project = new Project();


        for (int i = 1; i < rows.size(); i++) {
            List<ITextBox> cols = rows.get(i).findChildElements(By.tagName("td"), ElementType.TEXTBOX);

            for (int j = 0; j < cols.size() - 1; j++) {
                ColumnNames c;
                c = ColumnNames.valueOf(columnNames.get(j).getText());
                switch (c) {
                    case TEST_NAME:
                        project.setName(cols.get(j).getText());
                    case TEST_METHOD:
                        project.setMethod(cols.get(j).getText());
                    case LATEST_TEST_RESULT:
                        project.setStatus(cols.get(j).getText());
                    case LATEST_TEST_START_TIME:
                        project.setStartTime(cols.get(j).getText());
                    case LATEST_TEST_END_TIME:
                        project.setEndTime(cols.get(j).getText());
                    case LATEST_TEST_DURATION:
                        project.setDuration(cols.get(j).getText());
                    default:
                        throw new RuntimeException("Unknown column name");
                }
            }
            projects.add(project);
            project = new Project();
        }
        return projects;
    }

    public List<String> getListWebTimes(List<Project> projects) {
        List<String> listWebTimes = new ArrayList<>();
        for (Project project : projects) {
            listWebTimes.add(project.getStartTime());
        }
        return listWebTimes;
    }

    private List<ITextBox> getTableRows(ITextBox table) {
        return table.findChildElements(By.tagName("tr"), ElementType.TEXTBOX);
    }

    private List<ITextBox> getTableColumnNames(ITextBox table) {
        return table.findChildElements(By.tagName("th"), ElementType.TEXTBOX);
    }

    public void waitForModalWindowToAppear() {
        modalWindow.state().waitForDisplayed();
    }
}