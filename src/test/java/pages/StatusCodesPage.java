package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class StatusCodesPage extends AbstractPage {
    private static final By STATUS_CODES = By.cssSelector("[href*='status_codes']");


    @Step("Получаем список кодов")
    public List<String> getCodes() {
        return $$(STATUS_CODES).texts();
    }

    @Step("Кликаем на код {code}")
    public StatusCodePage clickOnCode(String code) {
        ElementsCollection selenideElements = $$(STATUS_CODES).filterBy(text(code));
        selenideElements.get(0).click();
        return new StatusCodePage();
    }

    @Override
    protected void initComponents() {
        $(STATUS_CODES).should(Condition.visible);
    }

    public class StatusCodePage extends AbstractPage {
        private static final By RETURN_TO_STATUS_PAGE_BTN = By.cssSelector("[href='/status_codes']");
        private static final By CODE_DESCRIPTION = By.cssSelector("#content > div > p");

        @Step("Получаем описание кода")
        public String getCodeDescription() {
            return $(CODE_DESCRIPTION).text();

        }

        @Step("Возвращается на страницу с кодами")
        public StatusCodesPage returnToStatusCodesPage() {
            $(RETURN_TO_STATUS_PAGE_BTN).click();
            return new StatusCodesPage();
        }

        @Override
        protected void initComponents() {
            $(RETURN_TO_STATUS_PAGE_BTN).should(Condition.visible);
        }
    }
}
