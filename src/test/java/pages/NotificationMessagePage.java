package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class NotificationMessagePage extends AbstractPage {
    private static final By ALERT = By.id("flash");
    private static final By CLOSE_ALERT_BTN = By.className("close");
    private static final By LOAD_NEW_MESSAGE_BTN = By.cssSelector("a[href='/notification_message']");


    @Step("Загружаем новое сообщение")
    public NotificationMessagePage loadNewMessage() {
        $(LOAD_NEW_MESSAGE_BTN).click();
        return this;
    }

    @Step("Закрываем алерт")
    public NotificationMessagePage closeAlert() {
        $(CLOSE_ALERT_BTN).click();
        return this;
    }

    @Step("Получаем текст алерта")
    public String getAlertText() {
        return $(ALERT).getText();
    }

    @Override
    protected void initComponents() {
        $(ALERT).should(Condition.visible);
    }
}
