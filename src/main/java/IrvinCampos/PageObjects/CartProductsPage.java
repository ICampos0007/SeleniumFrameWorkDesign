package IrvinCampos.PageObjects;

import IrvinCampos.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class CartProductsPage extends AbstractComponent {
    WebDriver driver;

    public CartProductsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".cartSection h3")
    List<WebElement> cartProducts;

    @FindBy(xpath = "//li[@class = 'totalRow']/button")
    WebElement checkoutButton;

    public void getCartList() {
    }

    public Boolean getCartProductNameMatch(String productName) {
        Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equals(productName));
        return match;
    }

    public CheckoutPage goToCheckOut() {
        checkoutButton.click();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        return checkoutPage;
    }
}
