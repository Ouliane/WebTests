package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class InputsPage extends AbstractPage {
    private static final By NUMBER_INPUT = By.cssSelector("input[type='number']");

    @Step("Получаем значение из поля ввода")
    public String getValue() {
        return $(NUMBER_INPUT).getValue();
    }

    @Step("Вводим число {number}")
    public InputsPage inputNumber(int number) {
        $(NUMBER_INPUT).setValue(String.valueOf(number));
        return this;
    }

    @Override
    protected void initComponents() {
        $(NUMBER_INPUT).should(Condition.visible);
    }
}
