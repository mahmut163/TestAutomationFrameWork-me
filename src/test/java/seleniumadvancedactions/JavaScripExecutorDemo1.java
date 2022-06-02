package seleniumadvancedactions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class JavaScripExecutorDemo1 {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "c:\\webdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @Test
    public void javaScripWindowScrollTest() {
        driver.get("https://jqueryui.com/");
        // aldi bilan define javascrip executor
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.location");// returns current window location
        //js.executeScript("window.scrollBy(0,1200)");
        for (int i = 0; i < 10; i++) {
            js.executeScript("window.scrollBy(0,400)");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            WebElement widget = driver.findElement(By.linkText("Widget Factory"));
            Assert.assertTrue(widget.isDisplayed());

        }
    }
    @Test
    public void javaScriptClickTest(){
        driver.get("https://jqueryui.com/toggle/");
        WebElement iframe=driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iframe);
        WebElement togName=driver.findElement(By.id("effect"));
        WebElement toggleButton=driver.findElement(By.id("button"));
        JavascriptExecutor javascriptExecutor=(JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].click()",toggleButton);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertFalse(togName.isDisplayed());


    }
}
