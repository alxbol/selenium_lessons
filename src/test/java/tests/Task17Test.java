package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Task17Test {
    private WebDriver driver;

    @BeforeClass
    public void start() {
        driver = new ChromeDriver();
    }

    @Test
    public void task17Test() {
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        driver.findElement(By.xpath("//a[contains(text(),'Subcategory')]")).click();
        for (int i = 3; i < driver.findElements(By.cssSelector("tr.row")).size(); i++) {
            driver.findElements(By.cssSelector("tr.row")).get(i).findElement(By.xpath(".//a[not(@title)]")).click();
            Assert.assertEquals(driver.manage().logs().get("browser").getAll().size(), 0);
            driver.navigate().back();
        }
    }

    @AfterClass
    public void stop() {
        driver.quit();
        driver = null;
    }
}
