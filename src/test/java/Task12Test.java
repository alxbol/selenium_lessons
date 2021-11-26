import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class Task12Test {
    private WebDriver driver;

    @BeforeClass
    public void start() {
        driver = new ChromeDriver();

    }

    @Test
    public void countriesTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        //General tab
        driver.findElement(By.cssSelector("#content > div:nth-child(7) > a:nth-child(2)")).click();
        driver.findElement(By.cssSelector("input[name='status']")).click();
        String productName = "newProduct";
        driver.findElement(By.cssSelector("input[name='name[en]']")).sendKeys(productName);
        driver.findElement(By.cssSelector("input[name='code']")).sendKeys("PD");
        driver.findElement(By.cssSelector("input[value='1-3']")).click();
        driver.findElement(By.cssSelector("input[name='quantity']")).clear();
        driver.findElement(By.cssSelector("input[name='quantity']")).sendKeys("1");
        driver.findElement(By.cssSelector("input[name='new_images[]']")).sendKeys(new File("src/test/java/resources/product.jpg").getAbsolutePath());
        driver.findElement(By.cssSelector("input[name='date_valid_from']")).sendKeys("01112021");
        driver.findElement(By.cssSelector("input[name='date_valid_from']")).sendKeys("01112023");
        driver.findElement(By.cssSelector("a[href='#tab-information']")).click();

        //Information tab
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("select[name='manufacturer_id']")));
        Select select = new Select(driver.findElement(By.cssSelector("select[name='manufacturer_id']")));
        select.selectByValue("1");
        driver.findElement(By.cssSelector("input[name='keywords']")).sendKeys("keywords");
        driver.findElement(By.cssSelector("input[name='short_description[en]']")).sendKeys("short_description[en]");
        driver.findElement(By.cssSelector("span.input-wrapper > div > div:nth-child(2)")).sendKeys("Description");
        driver.findElement(By.cssSelector("input[name='head_title[en]']")).sendKeys("head_title[en]");
        driver.findElement(By.cssSelector("input[name='meta_description[en]']")).sendKeys("meta_description[en]");
        driver.findElement(By.cssSelector("a[href='#tab-prices']")).click();

        //Prices tab
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='purchase_price']")));
        driver.findElement(By.cssSelector("input[name='purchase_price']")).clear();
        driver.findElement(By.cssSelector("input[name='purchase_price']")).sendKeys("400");
        select = new Select(driver.findElement(By.cssSelector("select[name='purchase_price_currency_code']")));
        select.selectByValue("USD");

        driver.findElement(By.cssSelector("button[name='save']")).click();
        Assert.assertEquals(driver.findElements(By.xpath(String.format("//a[contains(text(),'%s'", productName))).size(), 1);
    }

    @AfterClass
    public void stop() {
        driver.quit();
        driver = null;
    }
}
