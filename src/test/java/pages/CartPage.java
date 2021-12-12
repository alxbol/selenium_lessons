package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

public class CartPage extends Page {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOnThisPage() {
        return driver.findElements(By.cssSelector("button[name='update_cart_item']")).size() > 0;
    }

    public CartPage removeAllProducts() {
        while (driver.findElements(By.cssSelector("#box-checkout-cart > div > ul > li:nth-child(1) > form > div > p:nth-child(1) > a > strong")).size() > 0) {
            wait.until(presenceOfElementLocated(By.cssSelector("button[name='remove_cart_item']")));
            String productName = driver.findElement(By.cssSelector("#box-checkout-cart > div > ul > li:nth-child(1) > form > div > p:nth-child(1) > a > strong")).getText();
            WebElement nameInTableElement = driver.findElement(By.xpath(String.format("//td[@class='item'][contains(text(),'%s')]", productName)));
            driver.findElement(By.cssSelector("button[name='remove_cart_item']")).click();
            wait.until(stalenessOf(nameInTableElement));
        }
        return this;
    }

}

