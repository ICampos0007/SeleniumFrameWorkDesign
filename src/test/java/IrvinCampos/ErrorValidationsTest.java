package IrvinCampos;

import IrvinCampos.PageObjects.ProductCatalogue;
import IrvinCampos.TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ErrorValidationsTest extends BaseTest {
    @Test
    public void submitOrderWrong() throws IOException {
        String productName = "ZARA COAT 3";
        ProductCatalogue productCatalogue = landingPage.loginApplication("tester123@qa.com", "Atit");
        Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());

    }
}
