package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import static com.codeborne.selenide.Condition.textCaseSensitive;
import static com.codeborne.selenide.Selenide.$$;

public class InfiniteScrollPage extends AbstractPage {
    private static final By JSCROLL_ELEMENTS = By.cssSelector("div[class='jscroll-added']");

    @Step("Скроллим")
    public InfiniteScrollPage scroll() {
        JavascriptExecutor jse = (JavascriptExecutor) WebDriverRunner.getWebDriver();
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        return this;
    }

    @Step("Возвращаем элемент с нужным текстом")
    public SelenideElement checkTextIsPresent(String text) {
        ElementsCollection selenideElements = $$(JSCROLL_ELEMENTS).filterBy(textCaseSensitive(text));
        return selenideElements.isEmpty() ? null : selenideElements.get(0);
    }

    @Step("Скороллим до текста {text}")
    public InfiniteScrollPage scrollToElement(String text) {
        while (checkTextIsPresent(text) == null)
            scroll();
        checkTextIsPresent(text).scrollTo();
        return this;
    }

    @Override
    protected void initComponents() {
        $$(JSCROLL_ELEMENTS).get(0).shouldBe(Condition.visible);
    }
}
