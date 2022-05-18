
Feature: Проверка яндекс маркет
  @run
  Scenario Outline:
    When открыть браузер и найти в поисковике <search>
    When перейти по ссылке <nameLink>
    When перейти в раздел <nameSelection>,<nameSubSelection>
    When поставить фильтр производителя <nameProducer>
    When поставить количество страниц <count>
    When проверить, что все товары в списке от <produceName> и закрываем браузер

    Examples:
      | search          | nameLink                                      | nameSelection | nameSubSelection | nameProducer | count | produceName |
      | 'Яндекс Маркет' | 'Яндекс.Маркет — покупки с быстрой доставкой' | 'Электроника' | 'Смартфоны'      | 'Apple'      | 12    | 'iPhone'    |
      | 'Яндекс Маркет' | 'Яндекс.Маркет — покупки с быстрой доставкой' | 'Электроника' | 'Смартфоны'      | 'Lenovo'     | 12    | 'Lenovo'    |

