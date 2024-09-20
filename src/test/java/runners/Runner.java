package runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty"//Konsola daha güzel renkli yazdırma yapar.
        },
        features = "src/test/resources/features",
        glue = "stepdefinitions",//calisacak java kodları yeri
        tags = "@EntegrasyonUserAndUserGroup or @Userinfo",//hangi testin calisacagi belirtme yeri
        dryRun =false//'dryRun = true' testi çalıştırmaz, eksik step deinitionları bulur.
)

public class Runner {
}