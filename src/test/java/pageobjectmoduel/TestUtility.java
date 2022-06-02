package pageobjectmoduel;

import com.github.javafaker.Faker;
import com.unitedcoder.configutility.ApplicationConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestUtility {

    private int timeout=Integer.parseInt(ApplicationConfig.
            readFromConfigProperties("config-qa.properties","timeout"));

    private WebDriver driver ;
    Faker faker=new Faker();

    public TestUtility(WebDriver driver) {
        this.driver = driver;
    }
    public void waitelement(WebElement element){
        WebDriverWait wait=new WebDriverWait(driver,50);
        wait.until(ExpectedConditions.visibilityOf(element));

    }
    public void sleep(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public String generateFirstName(){
        String firstName=faker.name().firstName();
        return firstName;
    }

    public String generateLastName(){
        String lastName=faker.name().lastName();
        return lastName;
    }
    public String generateProductName(){
        String proname=faker.commerce().productName();
        return proname;
    }


    public void waitForAlertPresent(){
        WebDriverWait wai=new WebDriverWait(driver,timeout);
        wai.until(ExpectedConditions.alertIsPresent());
    }

}
