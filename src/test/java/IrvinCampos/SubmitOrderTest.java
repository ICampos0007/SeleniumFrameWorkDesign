package IrvinCampos;

import IrvinCampos.AbstractComponents.OrderPage;
import IrvinCampos.PageObjects.*;
import IrvinCampos.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    String productName = "ZARA COAT 3";
    @Test
    public void submitOrder() throws IOException {
//        LandingPage landingPage = launchApplication();
        ProductCatalogue productCatalogue = landingPage.loginApplication("cozy@gmail.com", "Atgatgnite1!");

        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        CartProductsPage cartProductsPage =  productCatalogue.goToCartPage();

        Boolean match = cartProductsPage.getCartProductNameMatch(productName);
        Assert.assertTrue(match);
       CheckoutPage checkoutPage = cartProductsPage.goToCheckOut();
        checkoutPage.selectCountry("uni");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        String confirmMessage = confirmationPage.getVerifyConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void orderHistoryTest() {
        ProductCatalogue productCatalogue = landingPage.loginApplication("cozy@gmail.com", "Atgatgnite1!");
        OrderPage orderPage = productCatalogue.goToOrderPage();
        Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
    }
}
