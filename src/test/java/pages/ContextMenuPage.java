package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ContextMenuPage extends AbstractPage {
    private static final By HOTSPOT = By.id("hot-spot");

    @Step("Кликаем ПКМ на отмеченной области")
    public ContextMenuPage hotspotContextClick() {
        $(HOTSPOT).contextClick();
        return this;
    }

    @Step("Получаем текст из алерта")
    public String getTextInAlert() {
        return WebDriverRunner.getWebDriver().switchTo().alert().getText();
    }

    @Override
    protected void initComponents() {
        $(HOTSPOT).should(Condition.visible);
    }
}
