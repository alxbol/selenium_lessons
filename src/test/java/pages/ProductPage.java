package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends Page {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOnThisPage() {
        return driver.findElements(By.cssSelector("td.quantity")).size() > 0;
    }

    public ProductPage addProductToCart() {
        if (driver.findElements(By.cssSelector("select[name='options[Size]']")).size() > 0) {
            Select select = new Select(driver.findElement(By.cssSelector("select[name='options[Size]']")));
            select.selectByValue("Small");
        }
        driver.findElement(By.cssSelector("button[name='add_cart_product']")).click();
        return this;
    }

}

