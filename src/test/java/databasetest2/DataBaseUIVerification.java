package databasetest2;

import com.unitedcoder.configutility.ApplicationConfig;
import databasetest.ConnectionManager;
import databasetest.ConnectionType;
import databasetest.DataAccess;
import databasetest.TestDataHelperClass;
import homework4.CustomerPage4;
import homework4.DashboardPage4;
import homework4.TestUtility4;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjectmoduel.LoginPage;

import java.sql.Connection;

public class DataBaseUIVerification {
    WebDriver driver;
    TestUtility4 testUtility4;
    LoginPage loginPage;
    DashboardPage4 dashboardPage4;
    CustomerPage4 customerPage4;
    Connection connection=null;
    TestDataHelperClass testDataHelperClass;
    final static String configFile="config-qa.properties";
    String dbUrl= ApplicationConfig.readFromConfigProperties(configFile,"qa.dbUrl");
    String dbPort=ApplicationConfig.readFromConfigProperties(configFile,"qa.dbPort");
    String defaultDatabase=ApplicationConfig.readFromConfigProperties(configFile,"qa.defaultDatabase");
    String userName=ApplicationConfig.readFromConfigProperties(configFile,"qa.userName");
    String password=ApplicationConfig.readFromConfigProperties(configFile,"qa.passWord");


    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.chrome.driver","c:\\webdriver\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://cubecartqa1.unitedcoderschool.com/admin_xrmx7f.php");
        loginPage=new LoginPage(driver);
         loginPage.login();
        System.out.println(userName+password);

      connection= databasetest.ConnectionManager.connectToDataBaseServer(dbUrl,dbPort,defaultDatabase,
              userName,password, ConnectionType.MYSQL);

    }
    @Test(description = "add customer",groups = "add customer")
    public void addCustomerTest(){
       dashboardPage4=new DashboardPage4(driver);
       dashboardPage4.clickOnCustomerListLink();
        String fristName="tarhan"+System.currentTimeMillis();
        String lasttName="garhan"+System.currentTimeMillis();
        String email="alxir"+System.currentTimeMillis()+"@gmail.com";
        testDataHelperClass=new TestDataHelperClass(email);
        customerPage4=new CustomerPage4(driver);
        customerPage4.addCustomerData(fristName,lasttName,email);
        Assert.assertTrue(customerPage4.verifyCustomerAddedSuccessfullyData(testDataHelperClass.getCustomerEmail()));
    }
    @Test(dependsOnGroups = "add customer")
    public void verifyAddcustomerDataBaseTest(){
        databasetest.DataAccess dataAccess=new DataAccess();
        boolean iscustomerExist= dataAccess.getCustomer(testDataHelperClass.getCustomerEmail(),connection);
        Assert.assertTrue(iscustomerExist);
    }
   @AfterClass
    public void tearDown(){
       driver.close();
       driver.quit();
      ConnectionManager.closeDataBaseConnection(connection);

   }
}
