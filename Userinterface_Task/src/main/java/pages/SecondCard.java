package pages;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

import configuration.Configuration;
import org.openqa.selenium.By;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.forms.Form;
import pojos.TestData;
import utils.JsonFileReader;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.core.elements.ElementsCount;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import aquality.selenium.elements.ElementType;

public class SecondCard extends Form {

    private final IButton btnNext = getElementFactory()
            .getButton(By.xpath("//button[contains(@class,'button--white')]"), "Next button");
    private final ICheckBox unselectAllInterests = getElementFactory()
            .getCheckBox(By.xpath("//label[@for = 'interest_unselectall']"), "Checkbox to 'Unselect All' ");

    public List<ICheckBox> listOfInterests() {
        return getElementFactory().findElements(By.xpath("//span[@class='checkbox__box']"), ElementType.CHECKBOX,
                ElementsCount.MORE_THEN_ZERO, ElementState.DISPLAYED);
    }

    public SecondCard() {
        super(By.xpath("//div[@class='page-indicator']"), "page indicator");
    }

    public void unselectAllInterests() {
        unselectAllInterests.check();
    }

    public void selectInterests(int numberOfInterests) {

        Set<Integer> set = new Random().ints(0, listOfInterests().size())
                .distinct()
                .limit(numberOfInterests)
                .boxed()
                .collect(Collectors.toSet());

        List<Integer> list = new ArrayList<Integer>(set);

        for (int i = 0; i < numberOfInterests; i++) {
            int index = list.get(i);
            ICheckBox interest = listOfInterests().get(index);
            interest.getMouseActions().moveMouseToElement();
            interest.getJsActions().click();
        }
    }

    public void switchToNextCard() {
        btnNext.click();
    }

}
