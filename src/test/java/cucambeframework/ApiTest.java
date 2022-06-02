package cucambeframework;
import com.unitedcoder.configutility.ApplicationConfig;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;


import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
public class ApiTest {
    Response response;
    String configFile="config-qa.properties";
    @Given("user should be able to send request for get specific customer information")
    public void userShouldBeAbleToSendRequestForGetSpecificCustomerInformation() {
                response=given().baseUri("http://ec2-18-237-72-27.us-west-2.compute.amazonaws.com:8080")
               .auth().basic("testautomation","automation123!").when().get("/customer/339");
//        String  baseURL= ApplicationConfig.readFromConfigProperties(configFile,"api.baseurl");
//        String  username=ApplicationConfig.readFromConfigProperties(configFile,"api.username");
//        String password=ApplicationConfig.readFromConfigProperties(configFile,"api.password");
//        int  port=Integer.parseInt(ApplicationConfig.readFromConfigProperties(configFile,"api.port"));
//        response= RestAssured.given().auth().basic(username,password)
//                .when().get(baseURL+":"+port+"/customer/339");
        System.out.println(response.getBody().prettyPrint());
    }

    @Then("user should get information about the customer")
    public void userShouldGetInformationAboutTheCustomer() {

         response.then().assertThat().statusCode(200).body("id",equalTo(339));
      //  int responseCode=response.getStatusCode();
       // Assert.assertEquals(responseCode,200);

    }
}
