package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class DropdownPage extends AbstractPage {
    private static final By DROPDOWN = By.id("dropdown");

    @Step("Выбираем опцию {option} Dropdown")
    public DropdownPage selectOptionInDropdown(String option) {
        $(DROPDOWN).selectOption(option);
        return this;
    }

    @Step("Получаем текущий текст Dropdown")
    public String getCurrentTextInDropdown() {
        return $(DROPDOWN).getSelectedOption().getText();
    }

    @Override
    protected void initComponents() {
        $(DROPDOWN).should(Condition.visible);
    }
}
