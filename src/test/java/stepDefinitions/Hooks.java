package stepDefinitions;

import com.codeborne.selenide.WebDriverRunner;
import custom.properties.TestData;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import static com.codeborne.selenide.Selenide.open;


public class Hooks {
    @Before
    public void openBr(){
        open(TestData.driverProps.defaultUrl());
    }

    @After
    public void close(){
        WebDriverRunner.getWebDriver().close();
    }

}
