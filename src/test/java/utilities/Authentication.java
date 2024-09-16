package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Authentication {
    public static String gSessionId() {

        // WebDriver driver = new ChromeDriver();//son parantez arka planda çalıştırır test ederken sekme açılmaz.
        WebDriver driver = new ChromeDriver(new ChromeOptions().addArguments("--headless"));
        driver.get("https://qa-gm3.quaspareparts.com/");
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("username")).sendKeys("cstm@qualitron.com");
        driver.findElement(By.id("password")).sendKeys("k4clPULt1E1RnyP");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        String id = driver.manage().getCookieNamed("GSESSIONID").getValue();
        driver.quit();

        return id;

    }

}
