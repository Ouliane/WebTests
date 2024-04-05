package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Selenide.$;

public class DragAndDropPage extends AbstractPage {
    private static final By ELEMENT_A = By.id("column-a");
    private static final By ELEMENT_B = By.id("column-b");

    @Step("Меняем элементы местами")
    public DragAndDropPage swapElements() {
        Actions builder = new Actions(WebDriverRunner.getWebDriver());
        Action dragAndDrop = builder.clickAndHold($(ELEMENT_A))
                .moveToElement($(ELEMENT_B))
                .release($(ELEMENT_B))
                .build();
        dragAndDrop.perform();
        return this;
    }

    @Step("Получаем текст первого элемента")
    public String getTextOfFirstElement() {
        return $(ELEMENT_A).getText();
    }

    @Step("Получаем текст второго элемента")
    public String getTextOfSecondElement() {
        return $(ELEMENT_B).getText();
    }

    @Override
    protected void initComponents() {
        $(ELEMENT_A).should(Condition.visible);
    }
}
