import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

public class Task13Test {
    private WebDriver driver;

    @BeforeClass
    public void start() {
        driver = new ChromeDriver();
    }

    @Test
    public void task13Test() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        for (int i = 0; i < 3; i++) {
            driver.get("http://localhost/litecart/index.php/en/");
            int quantityCount = Integer.parseInt(driver.findElement(By.cssSelector("span.quantity")).getText());
            driver.findElement(By.cssSelector("li.product")).click();
            if (driver.findElements(By.cssSelector("select[name='options[Size]']")).size() > 0) {
                Select select = new Select(driver.findElement(By.cssSelector("select[name='options[Size]']")));
                select.selectByValue("Small");
            }
            driver.findElement(By.cssSelector("button[name='add_cart_product']")).click();
            quantityCount++;
            wait.until(presenceOfElementLocated(By.xpath(String.format("//span[@class='quantity'][contains(text(),'%s')]", quantityCount))));
        }
        driver.findElement(By.cssSelector("a.link")).click();
        while (driver.findElements(By.cssSelector("#box-checkout-cart > div > ul > li:nth-child(1) > form > div > p:nth-child(1) > a > strong")).size() > 0) {
            wait.until(presenceOfElementLocated(By.cssSelector("button[name='remove_cart_item']")));
            String productName = driver.findElement(By.cssSelector("#box-checkout-cart > div > ul > li:nth-child(1) > form > div > p:nth-child(1) > a > strong")).getText();
            WebElement nameInTableElement = driver.findElement(By.xpath(String.format("//td[@class='item'][contains(text(),'%s')]", productName)));
            driver.findElement(By.cssSelector("button[name='remove_cart_item']")).click();
            wait.until(stalenessOf(nameInTableElement));
        }
    }

    @AfterClass
    public void stop() {
        driver.quit();
        driver = null;
    }
}
