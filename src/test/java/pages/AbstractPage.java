package pages;

import com.codeborne.selenide.WebDriverRunner;
import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.Selenide.open;

@Slf4j
public abstract class AbstractPage {
    public AbstractPage() {
        if (wdIsSet()) {
            initComponents();
        }
    }

    protected boolean wdIsSet() {
        try {
            WebDriverRunner.getWebDriver();
            return true;
        } catch (IllegalStateException e) {
            return false;
        }
    }

    protected abstract void initComponents();

    public void openPage(String url) {
        open(url);
        initComponents();
    }
}
