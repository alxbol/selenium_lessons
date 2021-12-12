package tests;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Timestamp;

public class Task11Test {
    private WebDriver driver;

    @BeforeClass
    public void start() {
        driver = new ChromeDriver();
    }

    @Test
    public void task11Test() {
        driver.get("http://localhost/litecart/en/create_account");
        String email = "username" + new Timestamp(System.currentTimeMillis()).getTime() + "@gmail.com";
        User user = new User("Alexey", "Bolshakov", "1600 Johnson Way", "19720", "New Castle", "United States", email, "79855005795", "123456", "123456");
        driver.findElement(By.cssSelector("input[name='firstname']")).sendKeys(user.getFirstName());
        driver.findElement(By.cssSelector("input[name='lastname']")).sendKeys(user.getLastName());
        driver.findElement(By.cssSelector("input[name='address1']")).sendKeys(user.getAddress1());
        driver.findElement(By.cssSelector("input[name='postcode']")).sendKeys(user.getPostcode());
        driver.findElement(By.cssSelector("input[name='city']")).sendKeys(user.getCity());
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys(user.getEmail());
        driver.findElement(By.cssSelector("input[name='phone']")).sendKeys(user.getPhone());
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys(user.getPassword());
        driver.findElement(By.cssSelector("input[name='confirmed_password']")).sendKeys(user.getConfirmedPassword());
        driver.findElement(By.cssSelector("span.select2-selection__arrow")).click();
        driver.findElement(By.cssSelector("li[id$='US']")).click();
        driver.findElement(By.cssSelector("button[name='create_account']")).click();
        Select select = new Select(driver.findElement(By.cssSelector("select[name='zone_code']")));
        select.selectByValue("MN");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys(user.getPassword());
        driver.findElement(By.cssSelector("input[name='confirmed_password']")).sendKeys(user.getConfirmedPassword());
        driver.findElement(By.cssSelector("button[name='create_account']")).click();
        //logout
        driver.findElement(By.cssSelector("#box-account > div > ul > li:nth-child(4) > a")).click();
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys(user.getEmail());
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys(user.getPassword());
        driver.findElement(By.cssSelector("button[name='login']")).click();
        driver.findElement(By.cssSelector("#box-account > div > ul > li:nth-child(4) > a")).click();
    }

    @AfterClass
    public void stop() {
        driver.quit();
        driver = null;
    }
}
