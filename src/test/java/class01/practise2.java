package class01;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class practise2 {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver1.exe");
        driver = new ChromeDriver();
    }



        // Your test methods go here

        @Test
        public void yourTestMethod() {
            // Your test code here
            driver.get("https://example.com");
            // Perform browser automation actions
        }


        // @AfterClass method and other test methods, if needed


    }


