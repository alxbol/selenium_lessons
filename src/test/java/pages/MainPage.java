package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainPage extends Page {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage open() {
        driver.get("http://localhost/litecart/index.php/en/");
        return this;
    }

    public boolean isOnThisPage() {
        return driver.findElements(By.cssSelector("input[name='query']")).size() > 0;
    }

    public List<WebElement> getAllProductElenents() {
        return driver.findElements(By.cssSelector("li.product"));
    }

    public ProductPage clickProductByNumber(int numOfProduct) {
        getAllProductElenents().get(numOfProduct).click();
        return new ProductPage(driver);
    }

    public CartPage clickCartLink() {
        driver.findElement(By.cssSelector("a.link")).click();
        return new CartPage(driver);
    }

}
