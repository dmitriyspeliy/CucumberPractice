
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = "src/test/java/feature",
        glue = "stepDefinitions",
        plugin = {"io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm", "pretty", "json:target/cucumber-report/report.json"},
        tags = {"@run"}
)


public class TestBSa {
}
