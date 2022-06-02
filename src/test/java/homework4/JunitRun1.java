package homework4;

import com.unitedcoder.cubecartautomation.TestBase;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class JunitRun1 extends TestBase {
    static LoginPage4 loginPage4;
    static DashboardPage4 dashboardPage4;
    ProductsPage4 productsPage4;
    TestUtility4 testUtility4;
    @BeforeClass
    public static void setUp(){
        browserSetUp();
        loginPage4=new LoginPage4(driver);
        loginPage4.login();
        dashboardPage4=new DashboardPage4(driver);

    }
    @Test
    public void addMultipleProduct(){
        dashboardPage4.clickOnProductsLink();
        productsPage4=new ProductsPage4(driver);
        productsPage4.addProduct();

    }
    @Test
    public void deleteAnyProducts(){
        dashboardPage4.clickOnProductsLink();
        productsPage4=new ProductsPage4(driver);
        productsPage4.deleteAnyProducts();
        Assert.assertTrue(productsPage4.verifyProductDeleted());


    }
    @AfterClass
    public static void tearDown(){
        dashboardPage4.logout();
     driver.close();
     driver.quit();
    }
}
