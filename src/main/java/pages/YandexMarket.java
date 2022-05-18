package pages;

import com.codeborne.selenide.*;
import custom.drivers.Waits;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import static com.codeborne.selenide.Selenide.*;

/**
 * Класс поиска нужного раздела на сайте, фильтрации элементов по производителю,
 * проверки элементов на странице.
 */

public class YandexMarket extends BaseClass {

    /**
     *
     * @param nameSelection - название секции
     * @param nameSubSelection - название подсекции
     * @return объект класса
     */
    @Step("Ищем раздел {nameSelection} и подраздел {nameSubSelection}")
    public void searchSelection(String nameSelection, String nameSubSelection) {
        $(By.xpath("//button[@id='catalogPopupButton']")).click();
        $$(By.xpath("//li[@data-zone-name='category-link']"))
                .findBy(Condition.text(nameSelection));
        $$(By.xpath("//div[@data-apiary-widget-name='@MarketNode/NavigationTree']//li"))
                .findBy(Condition.text(nameSubSelection))
                .click();
    }

    /**
     * Проверяет, если этот элемент уже выбран, то снимаем галку и ставит фильтр.
     * @param nameProducer - наименование производителя
     */
    @Step("Фильтруем по производителю и проверяет чекбокс {nameProducer}")
    public void filterProducer(String nameProducer) {
        $(By.xpath("//legend[contains(text(),'Производитель')]/..//button")).click();

        $(By.xpath("//input[@name='Поле поиска']"))
                .setValue(nameProducer);

        SelenideElement checkSelect = $(By.xpath("//legend[contains(text(),'Производитель')]/..//li/div//input"));
        if(!checkSelect.isSelected()){
            $(By.xpath("//legend[contains(text(),'Производитель')]/../ul//label/div")).click();
        }
        Waits.waitUntilElementClickable("//div[@data-apiary-widget-name='@MarketNode/SearchPager']//button[@type='button']");

    }

    /**
     * @param count - установить количество элементов на странице
     * @return объект YandexMarket
     */
    @Step("Устанавливаем количество элементов")
    public void SelectCountOfItems(int count){
        try {
            Waits.waitUntilElementPresents("//div[@data-apiary-widget-name='@MarketNode/SearchPager']//button[@type='button']");
            $(By.xpath("//div[@data-apiary-widget-name='@MarketNode/SearchPager']//button[@type='button']")).click();
        }catch (TimeoutException e){
            Assert.fail("Установить количество элементов невозможно, нет кнопки количества элементов" +
                    " c этим именем производителя");
        }
        $(By.xpath("//div[@data-apiary-widget-name='@MarketNode/SearchPager']//button[contains(text(),'"+
                count+"')]")).click();

        Waits.waitUntilElementNotExist("//div[@aria-label='Результаты поиска']/div[last()]");

    }


    /**
     * @param produceName - наименование марки
     */
    @Step("Убедится что в выборку попали только cмартфоны от {produceName}")
    public void checkItems(String produceName) {
        SelenideElement element = $(By.xpath("//a[contains(text(),'Вперёд')]"));
        int count = 0;
        Waits.waitUntilElementNotExist("//div[@aria-label='Результаты поиска']/div[last()]");
        while (count<14&&element.exists()){
                ElementsCollection arr = $$(By.xpath("//article//h3"));
            Assert.assertFalse("В списке есть товар с другим названием",
                    arr.stream().noneMatch(x->x.getText().contains(produceName)));
                count++;
                element.click();
            Waits.waitUntilElementNotExist("//div[@aria-label='Результаты поиска']/div[last()]");
        }

    }
}
