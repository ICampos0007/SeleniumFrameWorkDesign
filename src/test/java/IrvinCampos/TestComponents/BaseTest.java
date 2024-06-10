package IrvinCampos.TestComponents;

import IrvinCampos.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public LandingPage landingPage;

    public WebDriver initializeDriver() throws IOException {
        // Properties class
        Properties properties = new Properties();
        String propertiesFilePath = System.getProperty("user.dir") + "/src/main/resources/test.properties";
        try (FileInputStream fileInputStream = new FileInputStream(propertiesFilePath)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Properties file not found at path: " + propertiesFilePath);
        }

        String browserName = properties.getProperty("browser");

        if (browserName.contains("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.contains("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browserName.contains("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }
    @BeforeMethod
    public LandingPage launchApplication() throws IOException {
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }

    public WebDriver initializeDriver2() throws IOException {


//        properties class
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"//src//main//resources//test.properties");
        properties.load(fileInputStream);
        String browserName= properties.getProperty("browser");

        if(browserName.contains("chrome")) {
            WebDriverManager.chromedriver().setup();
             driver = new ChromeDriver();

        } else if (browserName.contains("firefox")) {
            System.setProperty("webdriver.gecko.driver","C:/Users/irvin/Desktop//selenium-server/geckodriver.exe");
             driver = new FirefoxDriver();
        }
        else if (browserName.contains("edge")) {
            System.setProperty("webdriver.edge.driver","C:/Users/irvin/Desktop//selenium-server/msedgedriver.exe");
             driver = new EdgeDriver();

        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return  driver;
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
