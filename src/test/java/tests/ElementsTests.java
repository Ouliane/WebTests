package tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.*;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.Selenide.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ElementsTests {
    private final String URL = "https://the-internet.herokuapp.com/";
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private StartPage startPage;

    @BeforeEach
    public void setUp() {
        startPage = new StartPage();
        startPage.openPage(URL);
    }

    @Test
    @Story("Перейти на страницу Checkboxes. Выделить первый чекбокс, снять выделение со второго чекбокса. Вывести в консоль состояние атрибута checked для каждого чекбокса.")
    void checkboxTest() {
        CheckboxPage checkboxPage = startPage.goToCheckboxPage()
                .setFirstCheckbox(true)
                .setSecondCheckbox(false);

        log.info("Состояние атрибута checked первого чекбокса - " + checkboxPage.getFirstCheckboxAttribute());
        log.info("Состояние атрибута checked второго чекбокса - " + checkboxPage.getSecondCheckboxAttribute());
    }

    @Test
    @Story("Перейти на страницу Dropdown. Выбрать первую опцию, вывести в консоль текущий текст элемента dropdown, выбрать вторую опцию, вывести в консоль текущий текст элемента dropdown.")
    void dropdownTest() {
        DropdownPage dropdownPage = startPage.goToDropdownPage()
                .selectOptionInDropdown("Option 1");
        log.info("Текущий текст Dropdown'а - " + dropdownPage.getCurrentTextInDropdown());
        dropdownPage.selectOptionInDropdown("Option 2");
        log.info("Текущий текст Dropdown'а - " + dropdownPage.getCurrentTextInDropdown());
    }

    @Test
    @Story("Перейти на страницу Disappearing Elements. Добиться отображения 5 элементов, максимум за 10 попыток, если нет, провалить тест с ошибкой.")
    void disappearingElementsTest() {
        DisappearingElementsPage disappearingElementsPage = startPage.goToDisappearingElementsPage();
        int i = 0;
        while (i < 10) {
            if (disappearingElementsPage.getAmountOfElements() == 5)
                break;
            refresh();
            i++;
        }
        log.info("Для отображения 5 элементов понадобилоась %s попыток".formatted(i));
        assertThat(i).as("За 10 попыток не добились отображения 5 элементов").isLessThan(10);
    }

    @Test
    @Story("Перейти на страницу Inputs. Ввести любое случайное число от 1 до 10 000. Вывести в консоль значение элемента Input.")
    void inputsTest() {
        int randNumber = new Random().nextInt(1, 10000000);
        InputsPage inputsPage = startPage.goToInputsPage()
                .inputNumber(randNumber);
        log.info("Значение элемента Input - " + inputsPage.getValue());
    }

    @Test
    @Story("Перейти на страницу Hovers. Навести курсор на каждую картинку. Вывести в консоль текст, который появляется при наведении.")
    void hoversTest() {
        HoversPage hoversPage = startPage.goToHoversPage();
        for (int i = 0; i < 3; i++) {
            hoversPage.hoverOverFigure(i);
            log.info("Текст, который появился при наведении - " + hoversPage.getVisibleText());
        }
    }

    @Test
    @Story("Перейти на страницу Notification Message. Кликать до тех пор, пока не покажется уведомление Action successful. После каждого неудачного клика закрывать всплывающее уведомление.")
    void notificationMessageTest() {
        NotificationMessagePage notificationMessagePage = startPage.goToNotificationMessagePage();
        while (true) {
            if (notificationMessagePage.getAlertText().contains("Action successful"))
                break;
            else notificationMessagePage.closeAlert()
                    .loadNewMessage();
        }
    }

    @Test
    @Story("Перейти на страницу Add/Remove Elements. Нажать на кнопку Add Element 5 раз. С каждым нажатием выводить в консоль текст появившегося элемента. Нажать на разные кнопки Delete три раза. Выводить в консоль оставшееся количество кнопок Delete и их тексты.")
    void addRemoveElementsTest() {
        AddRemoveElementsPage addRemoveElementsPage = startPage.goToAddRemoveElementsPage();
        for (int i = 0; i < 5; i++) {
            addRemoveElementsPage.addElement();
            log.info("Текст появившегося элемента - " + addRemoveElementsPage.getTextOfLastElement());
        }
        for (int i = 0; i < 3; i++) {
            addRemoveElementsPage.deleteElement(i);
            log.info("Кол-во оставшихся кнопок Delete - " + addRemoveElementsPage.getAmountDeleteBtns());
            log.info("Тексты кнопок Delete - " + addRemoveElementsPage.getDeleteBtnsText());
        }
    }

    @Test
    @Story("Перейти на страницу Status Codes. Кликнуть на каждый статус в новом тестовом методе, вывести на экран текст после перехода на страницу статуса.")
    void statusCodesTest() {
        StatusCodesPage statusCodesPage = startPage.goToStatusCodesPage();
        List<String> listOfCodes = statusCodesPage.getCodes();
        for (String code : listOfCodes) {
            StatusCodesPage.StatusCodePage statusCodePage = statusCodesPage.clickOnCode(code);
            log.info(String.format("Описание кода %s - %s", code, statusCodePage.getCodeDescription()));
            statusCodePage.returnToStatusCodesPage();
        }
    }


    @AfterEach
    void tearDown() {
        closeWindow();
    }
}
