package cucambeframework;
import com.unitedcoder.cubecartautomation.TestBase;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageobjectmoduel.ProductsPage;


public class MultipleProducSteps extends TestBase {

    ProductsPage productsPage;
    private String productName;
    private String productCode;


    @And("click on Add product link to fill out {string} {string} and other information")
    public void clickOnAddProductLinkToFillOutAndOtherInformation(String arg0, String arg1) {
        productName=arg0;
        productCode=arg1;
        productsPage=new ProductsPage(driver);
        productsPage.addProduct(productName,productCode);
    }

    @Then("product should be created")
    public void productShouldBeCreated() {
        Assert.assertTrue(productsPage.verifyProductAdded());
    }


}

