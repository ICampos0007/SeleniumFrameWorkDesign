package IrvinCampos.stepDefinitions;

import IrvinCampos.PageObjects.*;
import IrvinCampos.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class StepDefinitionImpl extends BaseTest {
    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public ConfirmationPage confirmationPage;
    @Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page() throws IOException {
        landingPage = launchApplication();
    }
    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_username_password(String username, String password) {
        productCatalogue = landingPage.loginApplication(username, password);
    }
    @When("^I add product (.+) to Cart$")
    public void i_add_product_to_cart(String productName) throws InterruptedException {
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
    }

    @When("^Checkout (.+) and submit the order$")
    public void checkout_submit_order(String productName) {
        CartProductsPage cartProductsPage =  productCatalogue.goToCartPage();

        Boolean match = cartProductsPage.getCartProductNameMatch(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartProductsPage.goToCheckOut();
        checkoutPage.selectCountry("uni");
        confirmationPage = checkoutPage.submitOrder();
    }

    @Then("{string} message is displayed on Confirmation page")
    public void message_display_confirmationPage(String string) {
        String confirmMessage = confirmationPage.getVerifyConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
    }

}
