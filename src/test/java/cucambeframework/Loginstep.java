package cucambeframework;

import com.unitedcoder.cubecartautomation.TestBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pageobjectmoduel.DashboardPage;
import pageobjectmoduel.LoginPage;

public class Loginstep extends TestBase {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    @Given("user already on the login page")
    public void userAlreadyOnTheLoginPage() {
        //browserSetUp();
        loginPage=new LoginPage(driver);
        dashboardPage=new DashboardPage(driver);
    }

    @When("user enter valid username and password and click on the login button")
    public void userEnterValidUsernameAndPasswordAndClickOnTheLoginButton() {

        loginPage.login();
    }

    @Then("user should be able to login successfully")
    public void userShouldBeAbleToLoginSuccessfully() {

        Assert.assertTrue(dashboardPage.verifyLogin());
    }

    @When("user enter invalid username and password and click on the login button")
    public void userEnterInvalidUsernameAndPasswordAndClickOnTheLoginButton() {
       // dashboardPage.logout();

        loginPage.login2("test","test");
    }

    @Then("user should get error massage")
    public void userShouldGetErrorMassage() {

        Assert.assertTrue(loginPage.invalidLoginErrorValidation());
    }
}
