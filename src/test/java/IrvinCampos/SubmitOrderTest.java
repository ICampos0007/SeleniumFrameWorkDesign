package IrvinCampos;

import IrvinCampos.AbstractComponents.OrderPage;
import IrvinCampos.PageObjects.*;
import IrvinCampos.TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    String productName = "ZARA COAT 3";
    @Test(dataProvider="getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String, String> input) throws IOException {
//        LandingPage landingPage = launchApplication();
        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(input.get("product"));
        CartProductsPage cartProductsPage =  productCatalogue.goToCartPage();

        Boolean match = cartProductsPage.getCartProductNameMatch(input.get("product"));
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
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("email","cozy@gmail.com");
        hashMap.put("password","Atgatgnite1!");
        hashMap.put("product","ZARA COAT 3");

        HashMap<String,String> hashMapTwo = new HashMap<>();
        hashMapTwo.put("email","Miya@gmail.com");
        hashMapTwo.put("password","Jaymee1!");
        hashMapTwo.put("product","ADIDAS ORIGINAL");



        return new Object[][] {
                {hashMap}, {hashMapTwo}
        };
    }
}
