import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class Task8Test {
    private WebDriver driver;

    @BeforeClass
    public void start() {
        driver = new ChromeDriver();
    }

    @Test
    public void task8Test() {
        driver.get("http://localhost/litecart/en/");

        List<WebElement> productElementList = driver.findElements(By.cssSelector("li.product"));
        for (WebElement productElement : productElementList) {
            Assert.assertEquals(productElement.findElements(By.cssSelector("div.sticker")).size(), 1);
        }
    }

    @AfterClass
    public void stop() {
        driver.quit();
        driver = null;
    }
}
