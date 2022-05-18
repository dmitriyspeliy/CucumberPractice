package pages;

import com.codeborne.selenide.Condition;
import custom.drivers.Waits;
import custom.properties.TestData;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;

/**
 * @author Pospelov Dmitriy
 * Класс осуществляет нужным нам поиск и переходит по ссылке после поиска
 */
public class YandexSearch extends BaseClass {

    /**
     * @param name - поиск по слову
     */
    @Step("Ищем в яндексе {name}")
    public void yandexSearch(String name){

        Waits.initWait();
        $(By.xpath("//input[@class='input__control input__input mini-suggest__input']"))
                .setValue(name)
                .pressEnter();
    }

    /**
     * @param linkName - переход по имени ссылки
     */
    @Step("переходим по ссылке {linkName}")
    public void crossLink(String linkName){

        $$(By.xpath("//ul[@aria-label='Результаты поиска']//li//a"))
                .findBy(Condition.text(linkName))
                .click();

        switchTo().window(1);
    }


}
