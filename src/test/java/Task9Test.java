import models.Country;
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
import java.util.stream.Collectors;

public class Task9Test {
    private WebDriver driver;

    @BeforeClass
    public void start() {
        driver = new ChromeDriver();
    }

    @Test
    public void task9Test() {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        //Читаем со страницы страны и добавляем их в список сущностей Country
        List<Country> countries = new ArrayList<>();
        List<WebElement> allCountriesElementList = driver.findElements(By.cssSelector("tr.row"));
        for (WebElement countryElement : allCountriesElementList) {
            Country country = new Country();
            country.setName(countryElement.findElement(By.cssSelector("td:nth-of-type(5)")).getText())
                    .setZonesCount(Integer.parseInt(countryElement.findElement(By.cssSelector("td:nth-of-type(6)")).getText()))
                    .setLink(countryElement.findElement(By.cssSelector("td:nth-of-type(5) a")).getAttribute("href"));
            countries.add(country);
        }

        // Берем из списка Country только названия стран
        List<String> actualCountryNamesList = new ArrayList<>();
        for (Country country : countries) {
            actualCountryNamesList.add(country.getName());
        }
        // Проверяем список названий стран с таким же списком отсортированным по алфавиту
        Assert.assertEquals(actualCountryNamesList, actualCountryNamesList.stream().sorted().toList());

        //Создаем список Country в котором зон бульше чем 0
        List<Country> countriesWithMultipleZones = countries.stream().filter(c -> c.getZonesCount() > 0).collect(Collectors.toList());
        for (Country countryWithMultipleZones : countriesWithMultipleZones) {
            driver.get(countryWithMultipleZones.getLink());
            // По аналогии со странами, проверяем список зон
            List<String> actualZonesList = new ArrayList<>();
            List<WebElement> zonesElementList = driver.findElements(By.cssSelector("#table-zones > tbody > tr > td:nth-child(3)"));
            for (WebElement zoneElement : zonesElementList) {
                actualZonesList.add(zoneElement.getText());
            }

            actualZonesList.remove(actualZonesList.size() - 1);
            Assert.assertEquals(actualZonesList, actualZonesList.stream().sorted().toList());
        }
    }

    @AfterClass
    public void stop() {
        driver.quit();
        driver = null;
    }
}
