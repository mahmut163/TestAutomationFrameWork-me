package testngframework;

import com.unitedcoder.cubecartautomation.TestBase;
import com.unitedcoder.exceldemo.ExcelUtility;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjectmoduel.DashboardPage;
import pageobjectmoduel.LoginPage;

public class DateProviderDemo extends TestBase {

    ExcelUtility excelUtility;
    @BeforeClass
    public void setup(){
        browserSetUp();
        //testUtility=new TestUtility(driver);
        excelUtility=new ExcelUtility();
        System.out.println(excelUtility.readFromExcelCell("Test_Data\\doc1.xlsx","Login-Info",1,0));
    }
    @Test(dataProvider = "logininfo2")
    public void roleBaseTest(String username, String password){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.login2(username,password);
        DashboardPage dashboardPage=new DashboardPage(driver);
        dashboardPage.logout();
    }
    @AfterClass
    public void teardown(){
        driver.close();
        driver.quit();
    }
    @DataProvider
    public Object[][] logininfo(){
        Object[][] logindate=new Object[][]{
                {"testautomation1","automation123!"},
                {"testautomation2","automation123!"}
        };
        return logindate;
    }
    @DataProvider
    public Object[][] logininfo2(){
        Object[][] logindate2=new Object[][]{
                {excelUtility.readFromExcelCell("Test_Data/doc1.xlsx","Login-Info",1,0),
                       excelUtility.readFromExcelCell("Test_Data\\doc1.xlsx","Login-Info",1,1)},
                {excelUtility.readFromExcelCell("Test_Data\\doc1.xlsx","Login-Info",2,0),
                       excelUtility.readFromExcelCell("Test_Data\\doc1.xlsx","Login-Info",2,1)}
        };
        return logindate2;
    }

}
