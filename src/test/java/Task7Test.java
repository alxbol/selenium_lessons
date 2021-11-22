import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Task7Test {
    private WebDriver driver;

    @BeforeClass
    public void start(){
        driver = new ChromeDriver();
    }

    @Test
    public void task7Test() {
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        List<WebElement> spanElementsList = driver.findElements(By.xpath("//span[@class='name']"));
        List<String> spanNames = new ArrayList<>();
        for (WebElement spanElement : spanElementsList) {
            spanNames.add(spanElement.getText());
        }
        for (String name : spanNames) {
            driver.findElement(By.xpath(String.format("//span[@class='name'][contains(text(),'%s')]", name))).click();
            int linkElementsSize = driver.findElements(By.xpath("//ul[@class='docs']//a")).size();
            for (int i = 0; i < linkElementsSize; i++) {
                List<WebElement> linkElements = driver.findElements(By.xpath("//ul[@class='docs']//a"));
                linkElements.get(i).click();
                Assert.assertTrue(areElementsPresent(driver, By.xpath("//h1")));
            }
        }
    }

    boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }

    @AfterClass
    public void stop(){
        driver.quit();
        driver = null;
    }
}
