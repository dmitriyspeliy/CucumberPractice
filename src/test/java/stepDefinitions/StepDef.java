package stepDefinitions;

import io.cucumber.java.en.When;
import pages.BaseClass;
import pages.YandexMarket;
import pages.YandexSearch;


public class StepDef extends BaseClass {
    YandexSearch yandexSearch = new YandexSearch();
    YandexMarket yandexMarket = new YandexMarket();

    @When("открыть браузер и найти в поисковике {string}")
    public void Search(String search){
        yandexSearch.yandexSearch(search);
    }
    @When("перейти по ссылке {string}")
    public void crossLink(String nameLink){
        yandexSearch.crossLink(nameLink);
    }
    @When("перейти в раздел {string},{string}")
    public void searchSelection(String nameSelection, String nameSubSelection){
        yandexMarket.searchSelection(nameSelection,nameSubSelection);
    }
    @When("поставить фильтр производителя {string}")
    public void filterProducer(String nameProducer){
        yandexMarket.filterProducer(nameProducer);
    }
    @When("поставить количество страниц {int}")
    public void filterProducer(int count){
        yandexMarket.SelectCountOfItems(count);
    }
    @When("проверить, что все товары в списке от {string} и закрываем браузер")
    public void checkItems(String produceName){
        yandexMarket.checkItems(produceName);
    }


}
