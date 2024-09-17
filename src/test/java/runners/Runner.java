package runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "rerun:target/failedScenarios.txt",//Fail olan testleri bu text dosyasına yazar ve tekara çalıştırılmalarına yardımcı olur.
                "pretty"//Konsola daha güzel renkli yazdırma yapar.
        },
        features = "src/test/resources/features",
        glue = "stepdefinitions",//calisacak java kodları yeri
        tags = "@UsersGroupsApi",//hangi testin calisacagi belirtme yeri
        dryRun =false//'dryRun = true' testi çalıştırmaz, eksik step deinitionları bulur.
)

public class Runner {
}