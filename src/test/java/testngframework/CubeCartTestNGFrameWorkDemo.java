package testngframework;

import com.unitedcoder.cubecartautomation.TestBase;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjectmoduel.*;

public class CubeCartTestNGFrameWorkDemo extends TestBase {
    static LoginPage loginPage;
     static DashboardPage dashboardPage;
    static CustomerPage customerPage;
    ProductsPage productsPage;
    TestUtility testUtility;
    @BeforeClass
    public  void setup(){
        browserSetUp();
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
