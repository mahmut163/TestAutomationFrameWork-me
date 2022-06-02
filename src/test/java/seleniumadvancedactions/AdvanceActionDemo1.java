package seleniumadvancedactions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AdvanceActionDemo1 {
    WebDriver driver;
    @BeforeClass

    public void setup(){
        System.setProperty("webdriver.chrome.driver","c:\\webdriver\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.com.tr");
    }
    @Test
    public void amazonTest(){
       WebElement accountList=driver.findElement(By.id("nav-link-accountList"));
        Actions actions=new Actions(driver);
       actions.moveToElement(accountList).build().perform();

       WebElement searchBox=driver.findElement(By.id("twotabsearchtextbox"));
        //actions.moveToElement(searchBox).click().keyDown(Keys.SHIFT).sendKeys("iphone").perform();
        actions.moveToElement(searchBox).click().keyDown(Keys.SHIFT).sendKeys("iphone"+Keys.ENTER).build().perform();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @AfterClass
    public void tearDown(){
        driver.close();
        driver.quit();
    }


}
