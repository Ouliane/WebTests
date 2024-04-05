package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class HoversPage extends AbstractPage {
    private static final By FIGURE_BOX = By.className("figure");
    private static final By BOX_CAPTION = By.xpath("//div[@class='figcaption']//h5");

    @Step("Наводим курсор на {index} картинку")
    public HoversPage hoverOverFigure(int index) {
        ElementsCollection elements = $$(FIGURE_BOX);
        elements.get(index).hover();
        return this;
    }

    @Step("Получаем текст")
    public String getVisibleText() {
        String text = "";
        for (SelenideElement element : $$(BOX_CAPTION)) {
            if (element.isDisplayed())
                text += element.getText();
        }
        return text;
    }

    @Override
    protected void initComponents() {
        $(FIGURE_BOX).should(Condition.visible);
    }
}
