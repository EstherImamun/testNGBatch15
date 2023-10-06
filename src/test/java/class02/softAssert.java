package class02;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class softAssert {
    // go to syntax hrms
    // send no credentials and click on login button
    // verify the error message is username cannot be empty

    public static WebDriver driver;

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

    @Test
    public void testCase(){
        WebElement loginBtn= driver.findElement(By.xpath("//input[@type='submit']"));
        loginBtn.click();
        // get the text
       WebElement error=driver.findElement(By.xpath("//span[text()='Username cannot be empty']"));
       String actualError =error.getText();
       // String that contains my expected error
        String expectedError= "Username cannot be empty";

        // for soft assertion
        SoftAssert soft=new SoftAssert();
        // compare
        soft.assertEquals(actualError, expectedError);

        // login button is displayed or not
        boolean disp=loginBtn.isDisplayed();
        System.out.println("the state of the display login is "+disp);// just to show you that it doesn't stop executing
        // even after there is a failed assertion.
        // call soft assertion
        soft.assertTrue(disp);

       // Once you have made all the assertions in the test case, use soft.assertAll() at the end.
        // it also determines whether to pass or fail the assertions, so it's like a judge method.
        soft.assertAll();// this line of code is always important at the end of using a soft assertion.
    }

}
