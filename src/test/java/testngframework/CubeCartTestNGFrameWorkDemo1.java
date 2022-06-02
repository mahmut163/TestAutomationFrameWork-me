package testngframework;

import com.unitedcoder.cubecartautomation.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pageobjectmoduel.*;
@Listeners(TestNGResultListener.class)

public class CubeCartTestNGFrameWorkDemo1  {
    static LoginPage loginPage;
     static DashboardPage dashboardPage;
    static CustomerPage customerPage;
    ProductsPage productsPage;
    TestUtility testUtility;
    WebDriver driver;
    @BeforeClass
    public  void setup(ITestContext context){
        System.setProperty("webdriver.chrome.driver","c:\\webdriver\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://cubecartqa1.unitedcoderschool.com/admin_xrmx7f.php");
        context.setAttribute("driver",driver);

        loginPage=new LoginPage(driver);
        loginPage.login();
        dashboardPage=new DashboardPage(driver);
        customerPage=new CustomerPage(driver);

    }
    @Test
    public void addProduct(){
        //Assert.assertTrue(dashboardPage.verifyLogin());
        dashboardPage.clickOnProductsLink();
        productsPage=new ProductsPage(driver);
        productsPage.addProduct();
        Assert.assertTrue(productsPage.verifyProductAdded());
    }
    @Test
    public void addCustomer(){
        dashboardPage.clickOnCustomerListLink();

        customerPage.addCustomer();
        Assert.assertTrue(customerPage.verifyCustomerAddedSuccessfully());
    }
    @Test(enabled = false)
    public void viewCustomer(){
        Assert.assertTrue(customerPage.viewCustomers());
    }
    @Test(enabled = false)
    public void deleteCustomer(){
        dashboardPage.clickOnCustomerListLink();
        customerPage.deleteCustomer();
        Assert.assertTrue(customerPage.verifyCustomerDeletedSuccessfully());
    }
@AfterClass
    public void teardown(){
        dashboardPage.logout();
        driver.close();
      //  driver.quit();
}

}
