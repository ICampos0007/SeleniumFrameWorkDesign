package IrvinCampos;

import IrvinCampos.PageObjects.LandingPage;
import IrvinCampos.PageObjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {
    public static void main(String[] args) {
//        new comment!
        String productName = "ZARA COAT 3";
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
//        driver.get("https://rahulshettyacademy.com/client/");
        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo();
        landingPage.loginApplication("cozy@gmail.com","Atgatgnite1!");
//        driver.findElement(By.id("userEmail")).sendKeys("cozy@gmail.com");
//        driver.findElement(By.id("userPassword")).sendKeys("Atgatgnite1!");
//        driver.findElement(By.id("login")).click();

//        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
//        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        List<WebElement> products = productCatalogue.getProductList();

//        WebElement prod = products.stream().filter(product ->
//                product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
//        driver.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        productCatalogue.addProductToCart(productName);

//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("toast-container"))));
//        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
//        ng-animating
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

        Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equals(productName));

        Assert.assertTrue(match);
        driver.findElement(By.xpath("//li[@class = 'totalRow']/button")).click();

        Actions actions = new Actions(driver);
        actions.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"uni").build().perform();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.xpath("(//button[contains(.,'United States')])[1]")).click();
        driver.findElement(By.cssSelector(".actions a")).click();

        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

        System.out.println(driver.findElement(By.cssSelector("Confirmation Number label[class='ng-star-inserted']")).getText());

        driver.close();
    }
}
