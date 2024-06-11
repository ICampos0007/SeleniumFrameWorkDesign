package IrvinCampos.AbstractComponents;

import IrvinCampos.PageObjects.CartProductsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {
    WebDriver driver;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(css = "[routerlink*='cart']")
    WebElement cartButton;

    @FindBy(css = "[routerlink*='myorders']")
    WebElement orderButton;

    public CartProductsPage goToCartPage() {
        cartButton.click();
        CartProductsPage cartProductsPage = new CartProductsPage(driver);
        return cartProductsPage;
    }

    public OrderPage goToOrderPage() {
        orderButton.click();
        OrderPage orderPage = new OrderPage(driver);
        return orderPage;
    }
    public void waitForElementToAppear(By findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForWebElementToAppear(WebElement findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }


    public void WaitForElementToDisappear(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
}
