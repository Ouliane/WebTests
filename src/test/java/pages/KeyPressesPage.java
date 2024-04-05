package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class KeyPressesPage extends AbstractPage {
    private static final By INPUT = By.id("target");
    private static final By RESULT = By.id("result");

    @Step("Получаем имя клавиши во всплывающем тексте")
    public String getKeyName() {
        return $(RESULT).getText().replace("You entered: ", "");
    }

    public KeyPressesPage clickOnInput(CharSequence... key) {
        $(INPUT).press(key);
        return this;
    }

    @Override
    protected void initComponents() {
        $(INPUT).should(Condition.visible);
    }
}
