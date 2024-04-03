package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class AddRemoveElementsPage extends AbstractPage {
    private static final By ADD_ELEMENT_BTN = By.cssSelector("[onclick='addElement()']");
    private static final By DELETE_BTN = By.cssSelector("[onclick='deleteElement()']");


    @Step("Кликаем по {num}-ой кнопке Delete")
    public AddRemoveElementsPage deleteElement(int num) {
        $$(DELETE_BTN).get(num).click();
        return this;
    }

    @Step("Получае кол-во кнопок Delete")
    public int getAmountDeleteBtns() {
        ElementsCollection elementsCollection = $$(DELETE_BTN);
        return elementsCollection.size();
    }

    @Step("Получаем текст последнего элемента")
    public String getTextOfLastElement() {
        return $$(DELETE_BTN).last().getText();
    }

    @Step("Получаем тексты кнопок Delete")
    public List<String> getDeleteBtnsText() {
        return $$(DELETE_BTN).texts();
    }

    @Step("Кликаем по кнопке Add Element")
    public AddRemoveElementsPage addElement() {
        $(ADD_ELEMENT_BTN).click();
        return this;
    }

    @Override
    protected void initComponents() {
        $(ADD_ELEMENT_BTN).should(Condition.visible);
    }
}
