package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Task13Test extends TestBase {

    @Test
    public void task13Test() {
        app.mainPage.open();
        Assert.assertTrue(app.mainPage.isOnThisPage());

        for (int i = 0; i < 3; i++) {
            app.mainPage.clickProductByNumber(0);
            app.productPage.addProductToCart();
        }
        app.mainPage.clickCartLink();
        app.cartPage.removeAllProducts();
    }
}
