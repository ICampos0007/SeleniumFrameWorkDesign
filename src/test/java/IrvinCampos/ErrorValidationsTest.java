package IrvinCampos;

import IrvinCampos.PageObjects.CartProductsPage;
import IrvinCampos.PageObjects.CheckoutPage;
import IrvinCampos.PageObjects.ConfirmationPage;
import IrvinCampos.PageObjects.ProductCatalogue;
import IrvinCampos.TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidationsTest extends BaseTest {


    @Test(groups = {"ErrorHandling"})
    public void submitOrderWrong() throws IOException {
        String productName = "ZARA COAT 3";
        ProductCatalogue productCatalogue = landingPage.loginApplication("tester123@qa.com", "Atit");
        Assert.assertEquals("Incorrect email  password.",landingPage.getErrorMessage());

    }

    @Test
    public void productErrorValidation() throws IOException {
        String productName = "ZARA COAT 3";
        ProductCatalogue productCatalogue = landingPage.loginApplication("cozy@gmail.com", "Atgatgnite1!");

        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        CartProductsPage cartProductsPage =  productCatalogue.goToCartPage();

        Boolean match = cartProductsPage.getCartProductNameMatch(productName);
        Assert.assertTrue(match);

    }
}
