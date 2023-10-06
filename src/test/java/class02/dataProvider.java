package class02;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class dataProvider {
    //    Test Scenario:
//    navigate to syntax HRMS
//    login into the webiste using the following credentials and check for correct errors
//    a.username ="Admin"  , password="12345"  ---> Error Message ="Invalid credentials"
//    b.username= "ABCD"   , password ="Hum@nhrm123" -->Error Message ="Invalid credentials"
//    c.username= ""   ,   password ="Hum@nhrm123"   -->Error Message ="Username cannot be empty"
//    d.username= "Admin" ,password= ""  -->Error Message= "Password cannot be empty"

    public static WebDriver driver;

    // data provider
    @DataProvider(name = "credentials")
    public Object[][] data() {
        Object[][] loginData = {
                {"Admin", "12345", "Invalid credentials"},
                {"ABCD", "Hum@nhrm123", "Invalid credentials"},
                {"Admin", "", "Password cannot be empty"},
                {"", "Hum@nhrm123", "Username cannot be empty"}
        };
        return loginData;

    }

    @Test(dataProvider = "credentials")// connect tor test case with a data provider.
    public void invalidCredentials(String username, String password, String errorMSG){
        //finding the username text box
        WebElement userName = driver.findElement(By.xpath("//input[@id='txtUsername']"));
        //send username
        userName.sendKeys(username);

        WebElement passWord = driver.findElement(By.xpath("//input[@id='txtPassword']"));
        //send username
        passWord.sendKeys(password);

       // find the login button and click
        WebElement loginBtn= driver.findElement(By.xpath("//input[@type='submit']"));
        loginBtn.click();

        // get the error message
        WebElement error=driver.findElement(By.xpath("//span[@id='spanMessage']"));
        String actualError =error.getText();

        // Assertion
        Assert.assertEquals(actualError,errorMSG);
    }




    @BeforeMethod
    public void SetupBrowser(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(5000);
        //driver.quit();
    }

}

