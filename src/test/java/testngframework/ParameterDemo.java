package testngframework;

import com.unitedcoder.cubecartautomation.TestBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjectmoduel.DashboardPage;
import pageobjectmoduel.LoginPage;

public class ParameterDemo extends TestBase{

    @BeforeClass
    public void setup(){
        browserSetUp();
    }
    @Test
    @Parameters({"username","password"})
    public void login(String username,String password){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.login2(username,password);

    }
    @AfterClass
    public void teardown(){
        DashboardPage dashboardPage=new DashboardPage(driver);
        dashboardPage.logout();
        driver.close();
        driver.quit();
    }

}
