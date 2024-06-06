package IrvinCampos;

import IrvinCampos.PageObjects.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class SubmitOrderTest {
    public static void main(String[] args) {
        String productName = "ZARA COAT 3";
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        LandingPage landingPage = new LandingPage(driver);
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        landingPage.goTo();
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
}
