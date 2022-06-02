package testngframework;

import com.unitedcoder.cubecartautomation.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjectmoduel.DashboardPage;
import pageobjectmoduel.LoginPage;
import pageobjectmoduel.ProductsPage;
import pageobjectmoduel.TestUtility;

public class AddMultipleProduct extends TestBase {
    TestUtility testUtility;
    @BeforeClass
    public void setUp(){
        browserSetUp();
        testUtility=new TestUtility(driver);
        LoginPage loginPage=new LoginPage(driver);
        loginPage.login();


    }
    @Test(dataProvider = "DateInfo")
    public void addProduct(String proName,String proCode){
        DashboardPage dashboardPage=new DashboardPage(driver);
        dashboardPage.clickOnProductsLink();
        ProductsPage productsPage=new ProductsPage(driver);
        productsPage.addProduct(proName,proCode);
        Assert.assertTrue(productsPage.verifyProductAdded());

    }
    @AfterClass
    public void tearDown(){
        driver.close();
        driver.quit();
    }
    @DataProvider

    public Object[][] DateInfo(){
        Object [][] dateProduct=new Object[][]{
                {testUtility.generateProductName(),"ABS-145"},

                {testUtility.generateProductName(),"acd-456"},

                {testUtility.generateProductName(),"qwe-142"}

        };
        return dateProduct;

    }
}
