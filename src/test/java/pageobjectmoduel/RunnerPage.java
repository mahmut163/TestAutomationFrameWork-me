package pageobjectmoduel;

import com.unitedcoder.cubecartautomation.TestBase;
import org.junit.*;

public class RunnerPage extends TestBase {
    static LoginPage loginPage;
     static DashboardPage dashboardPage;
    static CustomerPage customerPage;
    ProductsPage productsPage;
    TestUtility testUtility;
    @BeforeClass
    public static void setup(){
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
    @Test
    public void viewCustomer(){
        Assert.assertTrue(customerPage.viewCustomers());
    }
    @Test
    public void deleteCustomer(){
        dashboardPage.clickOnCustomerListLink();
        customerPage.deleteCustomer();
        Assert.assertTrue(customerPage.verifyCustomerDeletedSuccessfully());
    }
@AfterClass
    public static void teardown(){
        dashboardPage.logout();
        driver.close();
        driver.quit();
}

}
