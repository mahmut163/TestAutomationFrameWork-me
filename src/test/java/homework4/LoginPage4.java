package homework4;

import com.unitedcoder.configutility.ApplicationConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobjectmoduel.TestUtility;

public class LoginPage4 {
    WebDriver driver;
    TestUtility testUtility;
    String configFile="config-qa.properties";

    @FindBy(id ="username")
    WebElement userNameField;

    @FindBy(id ="password")
    WebElement passWordField;

    @FindBy(name = "login")
    WebElement loginButton;

    public LoginPage4(WebDriver driver) {
        this.driver = driver;
        testUtility=new TestUtility(driver);
        PageFactory.initElements(driver,this);

    }
    //action
    public void enterUserName(String username){
        testUtility.waitelement(userNameField);
        userNameField.sendKeys(username);
    }
    public void enterpassword(String password){
        testUtility.waitelement(passWordField);
        passWordField.sendKeys(password);
    }
    public void clickloginbutton(){
       // testUtility.waitelement(loginButton);
        loginButton.click();
    }
    public void login(){
        enterUserName(ApplicationConfig.readFromConfigProperties(configFile,"username"));
        enterpassword(ApplicationConfig.readFromConfigProperties(configFile,"password"));
        clickloginbutton();
    }
    public void login2(String userName,String password){
        enterUserName(userName);
        enterpassword(password);

        clickloginbutton();
    }

}
