package IrvinCampos.PageObjects;

import IrvinCampos.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {
    WebDriver driver;
    public LandingPage (WebDriver driver) {
        super(driver);
        this.driver = driver;
        // Initialize WebElements with PageFactory
        PageFactory.initElements(driver,this);
    }
    @FindBy(id = "userEmail")
    WebElement userEmail;

    @FindBy(id = "userPassword")
    WebElement passwordElement;

    @FindBy(id = "login")
    WebElement submit;

    public ProductCatalogue loginApplication(String email, String password) {
        userEmail.sendKeys(email);
        passwordElement.sendKeys(password);
        submit.click();
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        return productCatalogue;
    }

    public void goTo() {
        driver.get("https://rahulshettyacademy.com/client/");
    }
}
