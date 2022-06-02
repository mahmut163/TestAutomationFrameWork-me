package cucambeframework;

import com.unitedcoder.cubecartautomation.TestBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageobjectmoduel.DashboardPage;
import pageobjectmoduel.ProductsPage;

public class ProductSteps extends TestBase {
    DashboardPage dashboardPage;
    ProductsPage productsPage;
    @Given("admin user is on the dashboard page")
    public void adminUserIsOnTheDashboardPage() {
        dashboardPage=new DashboardPage(driver);
        dashboardPage.clickOnProductsLink();
        productsPage=new ProductsPage(driver);
    }
    @When("the user fills out a new product form")
    public void theUserFillsOutANewProductForm() {
        productsPage.addProduct("English Book2",
               "UTF_tyu"+System.currentTimeMillis());
    }
    @Then("a new product should be created")
    public void aNewProductShouldBeCreated() {
        Assert.assertTrue(productsPage.verifyProductAdded());
    }
//    @After()
//    public void after(){
//        dashboardPage.logout();
//    }


    @When("the user tries to delete an existing product")
    public void theUserTriesToDeleteAnExistingProduct() {
        productsPage.deleteProduct();
    }

    @Then("the product should be deleted")
    public void theProductShouldBeDeleted() {
        Assert.assertTrue(productsPage.verifyProductDeleted());
    }



}
