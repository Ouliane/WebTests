package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DisappearingElementsPage extends AbstractPage {
    private static final By DISAPPEARING_ELEMENTS_LABEL = By.xpath("//h3[contains(text(), 'Disappearing Elements')]");
    private static final By VISIBLE_ELEMENTS = By.cssSelector("ul li");

    @Step("Получаем кол-во элементов")
    public int getAmountOfElements() {
        return $$(VISIBLE_ELEMENTS).size();
    }

    @Override
    protected void initComponents() {
        $(DISAPPEARING_ELEMENTS_LABEL).should(Condition.visible);
    }
}
