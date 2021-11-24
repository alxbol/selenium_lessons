import models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task10Test {
    private WebDriver driver;

    private static List<Integer> rgbaParse(String input) {
        String[] colors = input.substring(5, input.length() - 1).split(",");
        return new ArrayList<>(Arrays.asList(
                Integer.parseInt(colors[0].trim()),
                Integer.parseInt(colors[1].trim()),
                Integer.parseInt(colors[2].trim())));
    }

    @BeforeClass
    public void start() {
        driver = new ChromeDriver();
    }

    @Test
    public void task10Test() {
        driver.get("http://localhost/litecart/en/");
        WebElement firstProductElement = driver.findElement(By.cssSelector("#box-campaigns > div > ul > li"));
        WebElement campaignPrice = firstProductElement.findElement(By.cssSelector(".campaign-price"));
        WebElement regularPrice = firstProductElement.findElement(By.cssSelector(".regular-price"));
        Product productFromMainPage = new Product();
        productFromMainPage.setName(firstProductElement.findElement(By.cssSelector(".name")).getText())
                .setCampaignPrice(campaignPrice.getText())
                .setCampaignPriceColor(campaignPrice.getCssValue("color"))
                .setCampaignPriceTextDecoration(campaignPrice.getCssValue("text-decoration"))
                .setCampaignPriceFontSize(campaignPrice.getCssValue("font-size"))
                .setRegularPrice(regularPrice.getText())
                .setRegularPriceColor(regularPrice.getCssValue("color"))
                .setRegularPriceTextDecoration(regularPrice.getCssValue("text-decoration"))
                .setRegularPriceFontSize(regularPrice.getCssValue("font-size"));

        firstProductElement.findElement(By.cssSelector("a")).click();
        WebElement campaignPriceFromDesc = driver.findElement(By.cssSelector(".campaign-price"));
        WebElement regularPriceFromDesc = driver.findElement(By.cssSelector(".regular-price"));
        Product productFromDescriptionPage = new Product();
        productFromDescriptionPage.setName(driver.findElement(By.cssSelector("h1.title")).getText())
                .setCampaignPrice(campaignPriceFromDesc.getText())
                .setCampaignPriceColor(campaignPriceFromDesc.getCssValue("color"))
                .setCampaignPriceTextDecoration(campaignPriceFromDesc.getCssValue("text-decoration"))
                .setCampaignPriceFontSize((campaignPriceFromDesc.getCssValue("font-size")))
                .setRegularPrice(regularPriceFromDesc.getText())
                .setRegularPriceColor(regularPriceFromDesc.getCssValue("color"))
                .setRegularPriceTextDecoration(regularPriceFromDesc.getCssValue("text-decoration"))
                .setRegularPriceFontSize(regularPriceFromDesc.getCssValue("font-size"));

//      а) на главной странице и на странице товара совпадает текст названия товара
//      б) на главной странице и на странице товара совпадают цены (обычная и акционная)
        Assert.assertEquals(productFromMainPage, productFromDescriptionPage);

//      в) обычная цена зачёркнутая и серая (можно считать, что "серый" цвет это такой, у которого в RGBa представлении одинаковые значения для каналов R, G и B)
        List<Integer> regularColorFromMainPage = rgbaParse(productFromMainPage.getRegularPriceColor());
        Assert.assertEquals(regularColorFromMainPage.get(0), regularColorFromMainPage.get(1));
        Assert.assertEquals(regularColorFromMainPage.get(1), regularColorFromMainPage.get(2));

        List<Integer> regularColorFromDescPage = rgbaParse(productFromDescriptionPage.getRegularPriceColor());
        Assert.assertEquals(regularColorFromDescPage.get(0), regularColorFromDescPage.get(1));
        Assert.assertEquals(regularColorFromDescPage.get(1), regularColorFromDescPage.get(2));
        Assert.assertEquals(productFromDescriptionPage.getRegularPriceTextDecoration().split(" ")[0], "line-through");

//      г) акционная жирная и красная (можно считать, что "красный" цвет это такой, у которого в RGBa представлении каналы G и B имеют нулевые значения)
//      (цвета надо проверить на каждой странице независимо, при этом цвета на разных страницах могут не совпадать)
        List<Integer> campaignColorFromMainPage = rgbaParse(productFromMainPage.getCampaignPriceColor());
        Assert.assertEquals((int) campaignColorFromMainPage.get(1), 0);
        Assert.assertEquals((int) campaignColorFromMainPage.get(2), 0);

        List<Integer> campaignColorFromDescPage = rgbaParse(productFromDescriptionPage.getCampaignPriceColor());
        Assert.assertEquals((int) campaignColorFromDescPage.get(1), 0);
        Assert.assertEquals((int) campaignColorFromDescPage.get(2), 0);

//      д) акционная цена крупнее, чем обычная (это тоже надо проверить на каждой странице независимо)
        double campaignPriceFontSize = Double.parseDouble(productFromMainPage.getCampaignPriceFontSize().substring(0, productFromMainPage.getCampaignPriceFontSize().length() - 2));
        double regularPriceFontSize = Double.parseDouble(productFromMainPage.getRegularPriceFontSize().substring(0, productFromMainPage.getRegularPriceFontSize().length() - 2));
        Assert.assertTrue(campaignPriceFontSize > regularPriceFontSize);

        double campaignPriceFontSizeDesc = Double.parseDouble(productFromDescriptionPage.getCampaignPriceFontSize().substring(0, productFromDescriptionPage.getCampaignPriceFontSize().length() - 2));
        double regularPriceFontSizeDesc = Double.parseDouble(productFromDescriptionPage.getRegularPriceFontSize().substring(0, productFromDescriptionPage.getRegularPriceFontSize().length() - 2));
        Assert.assertTrue(campaignPriceFontSizeDesc > regularPriceFontSizeDesc);
    }

    @AfterClass
    public void stop() {
        driver.quit();
        driver = null;
    }
}
