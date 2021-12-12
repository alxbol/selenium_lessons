package tests;

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
    public void countriesTest() {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        //Читаем со страницы страны и добавляем их в список сущностей Country
        List<Country> countries = new ArrayList<>();
        List<WebElement> allCountriesElementList = driver.findElements(By.cssSelector("tr.row"));
        for (WebElement countryElement : allCountriesElementList) {
            Country country = new Country();
            List<WebElement> td = countryElement.findElements(By.cssSelector("td"));
            country.setName(td.get(4).getText())
                    .setZonesCount(Integer.parseInt(td.get(5).getText()));
            countries.add(country);
        }

        // Берем из списка Country только названия стран
        List<String> actualCountryNamesList = new ArrayList<>();
        for (Country country : countries) {
            actualCountryNamesList.add(country.getName());
        }
        // Проверяем список названий стран с таким же списком отсортированным по алфавиту
        Assert.assertEquals(actualCountryNamesList, actualCountryNamesList.stream().sorted().toList());

        //Создаем список Country в котором зон больше чем 0
        List<Country> countriesWithMultipleZones = countries.stream().filter(c -> c.getZonesCount() > 0).collect(Collectors.toList());
        for (Country countryWithMultipleZones : countriesWithMultipleZones) {
            driver.findElement(By.linkText(countryWithMultipleZones.getName())).click();
            // По аналогии со странами, проверяем список зон
            List<String> actualZonesList = new ArrayList<>();
            List<WebElement> zonesElementList = driver.findElements(By.cssSelector("#table-zones > tbody > tr > td:nth-child(3)"));
            for (WebElement zoneElement : zonesElementList) {
                actualZonesList.add(zoneElement.getText());
            }
            actualZonesList.remove(actualZonesList.size() - 1);
            Assert.assertEquals(actualZonesList, actualZonesList.stream().sorted().toList());
            driver.navigate().back();
        }
    }

    @Test
    public void zonesTest() {
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        List<WebElement> countriesElementlist = driver.findElements(By.cssSelector("#content > form > table > tbody > tr > td:nth-child(3) > a"));
        for (int i = 0; i < countriesElementlist.size(); i++) {
            driver.findElement(By.cssSelector(String.format("#content > form > table > tbody > tr:nth-child(%s) > td:nth-child(3) > a", i + 2))).click();
            List<WebElement> selectList = driver.findElements(By.cssSelector("td:nth-child(3) > select > option[selected='selected']"));
            List<String> actualZoneList = new ArrayList<>();
            for (WebElement select : selectList) {
                actualZoneList.add(select.getText());
            }
            Assert.assertEquals(actualZoneList, actualZoneList.stream().sorted().toList());
            driver.navigate().back();
        }
    }

    @AfterClass
    public void stop() {
        driver.quit();
        driver = null;
    }
}
