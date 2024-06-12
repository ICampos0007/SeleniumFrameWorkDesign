package IrvinCampos;

import IrvinCampos.AbstractComponents.OrderPage;
import IrvinCampos.PageObjects.*;
import IrvinCampos.TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    String productName = "ZARA COAT 3";
    @Test(dataProvider="getData", groups = {"Purchase"})
    public void submitOrder(String email, String password, String productName) throws IOException {
//        LandingPage landingPage = launchApplication();
        ProductCatalogue productCatalogue = landingPage.loginApplication(email, password);

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
    @DataProvider
    public Object[][] getData() {
        return new Object[][] {
                {"cozy@gmail.com", "Atgatgnite1!", "ZARA COAT 3"},
                {"Miya@gmail.com", "Jaymee1!", "ADIDAS ORIGINAL"}
        };
    }
}
