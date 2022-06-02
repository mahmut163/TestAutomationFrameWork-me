package homework4;

import com.unitedcoder.cubecartautomation.TestBase;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class JunitRun2 extends TestBase {
    static LoginPage4 loginPage4;
    static DashboardPage4 dashboardPage4;
     static CustomerPage4 customerPage4;
    ProductsPage4 productsPage4;
    TestUtility4 testUtility4;
    @BeforeClass
    public static void setUp(){
        browserSetUp();
        loginPage4=new LoginPage4(driver);
        loginPage4.login();
        dashboardPage4=new DashboardPage4(driver);
        customerPage4=new CustomerPage4(driver);


    }
    @Test
    public void viewCustomerList(){
        dashboardPage4.clickOnCustomerListLink();
        Assert.assertTrue(customerPage4.viewCustomers());
    }
    @Test
    public void createCustomerGroup(){
        dashboardPage4.clickOnCustomerListLink();
        customerPage4.clickCustomerGroupLink();
        customerPage4.createMultipleCustomerGroup();
        Assert.assertTrue(customerPage4.viewCustomerGroups());
    }
    @Test
    public void addMultipleCustomers(){
        dashboardPage4.clickOnCustomerListLink();
        customerPage4.addMultipleCustomer();
        Assert.assertTrue(customerPage4.verifyCustomerAddedSuccessfully());
    }
    @Test
    public void searchCustomer(){
        dashboardPage4.clickOnCustomerListLink();
        customerPage4.searchCustomer();
        Assert.assertTrue(customerPage4.verifaySearchedCustomer());

    }
    @AfterClass
    public static void tearDown(){
        dashboardPage4.logout();
        driver.close();
        driver.quit();
    }
}
