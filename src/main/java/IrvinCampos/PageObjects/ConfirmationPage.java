package IrvinCampos.PageObjects;

import IrvinCampos.AbstractComponents.AbstractComponent;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends AbstractComponent {
    WebDriver driver;
    public ConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        // Initialize WebElements with PageFactory
        PageFactory.initElements(driver,this);
    }
    @FindBy(css = ".hero-primary")
    WebElement confirmedOrder;

    public String getVerifyConfirmationMessage() {
        return confirmedOrder.getText();
    }
}
