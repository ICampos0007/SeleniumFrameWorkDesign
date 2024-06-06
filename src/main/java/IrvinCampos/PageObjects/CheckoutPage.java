package IrvinCampos.PageObjects;

import IrvinCampos.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractComponent {
    WebDriver driver;
    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css = "[placeholder='Select Country']")
    WebElement country;

    @FindBy(css = ".actions a")
    WebElement submit;
    //button[contains(.,'United States')])[1]
    @FindBy(xpath = "//button[@class='ta-item list-group-item ng-star-inserted'][6]")
    WebElement countrySelect;

    By results = By.cssSelector(".ta-results");

    public void selectCountry(String countryName) {
        Actions actions = new Actions(driver);
        actions.sendKeys(country,countryName).build().perform();
        waitForElementToAppear(By.cssSelector(".ta-results"));
        countrySelect.click();

    }

    public ConfirmationPage submitOrder() {
        submit.click();
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        return confirmationPage;
    }

}
