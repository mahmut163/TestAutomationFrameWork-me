package cucambeframework;

import com.unitedcoder.configutility.ApplicationConfig;
import databasetest.ConnectionType;
import databasetest2.ConnectionManager;
import databasetest2.DataAccess;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.sql.Connection;

public class DataBaseTestSteps {
    String configFile="config-qa.properties";
    Connection connection=null;
    boolean  isProductExist;


    @Given("a user has read access to the cc_CubeCart_Inventory table")
    public void aUserHasReadAccessToTheCc_CubeCart_InventoryTable() {
        String dbUrl= ApplicationConfig.readFromConfigProperties(configFile,"qa.dbUrl");
        String dbPort=ApplicationConfig.readFromConfigProperties(configFile,"qa.dbPort");
        String defaultDatabase=ApplicationConfig.readFromConfigProperties(configFile,"qa.defaultDatabase");
        String userName=ApplicationConfig.readFromConfigProperties(configFile,"qa.userName");
        String password=ApplicationConfig.readFromConfigProperties(configFile,"qa.passWord");
        connection= ConnectionManager.connectToDataBaseServer(dbUrl,dbPort,defaultDatabase,userName,password, ConnectionType.MYSQL);

//        scenario.log("UserName"+userName);
//        scenario.log("Password"+password);
    }

    @When("the user query the cc_CubeCart_Inventory table")
    public void theUserQueryTheCc_CubeCart_InventoryTable() {
        DataAccess dataAccess=new DataAccess();
        String productName="English Book2";
        // scenario.log("Product to search"+productName);
        isProductExist=dataAccess.getProductName(productName,connection);
    }

    @Then("the user should see the product info")
    public void theUserShouldSeeTheProductInfo() {
        Assert.assertTrue(isProductExist);
    }
}