package testngframework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjectmoduel.DashboardPage;
import pageobjectmoduel.LoginPage;


public class ParallelTest1 {

    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;

    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.chrome.driver","c:\\webdriver\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://cubecartqa1.unitedcoderschool.com/admin_xrmx7f.php");
        loginPage=new LoginPage(driver);
    }

    @Test
    public void verifyLoginTest(){
        Assert.assertTrue(dashboardPage.verifyLogin());
    }

    @Test
    public void Test1(){
        Assert.assertEquals(driver.getTitle(),"Admin Control Panel");
    }

    @AfterTest
    public void tearDown(){
        driver.close();
        driver.quit();
    }
}
