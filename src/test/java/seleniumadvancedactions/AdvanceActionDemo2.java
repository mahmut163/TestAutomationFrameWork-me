package seleniumadvancedactions;
import com.unitedcoder.configutility.ScreenShotUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AdvanceActionDemo2 {
    WebDriver driver;


    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "c:\\webdriver\\chromedriver.exe");
        driver=new ChromeDriver();

        driver.manage().window().maximize();

    }

    @Test
    public void dragAndDrop() {
        driver.get("https://jqueryui.com/droppable/");
        WebElement iframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iframe);
        WebElement draggable = driver.findElement(By.id("draggable"));
        WebElement droppable = driver.findElement(By.id("droppable"));
        Actions actions = new Actions(driver);
        actions.dragAndDrop(draggable, droppable).perform();
        // actions.clickAndHold(draggable).moveToElement(droppable).release().build().perform();
        actions.dragAndDropBy(draggable, 20, 1).dragAndDrop(draggable, droppable).build().perform();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(droppable.getText().contains("Dropped!"));
    }

    @Test
    public void menuTest() {
        driver.get("https://jqueryui.com/menu/");
        WebElement iframe = driver.findElement(By.cssSelector(".demo-frame"));
        driver.switchTo().frame(iframe);
        WebElement musicMenu = driver.findElement(By.id("ui-id-9"));
        Actions actions = new Actions(driver);
        waitMethod(musicMenu);
        actions.moveToElement(musicMenu).build().perform();
        WebElement rockMenu = driver.findElement(By.id("ui-id-10"));
        waitMethod(rockMenu);
        actions.moveToElement(rockMenu).build().perform();
        WebElement alternativeMenu = driver.findElement(By.id("ui-id-11"));
        waitMethod(alternativeMenu);
        actions.moveToElement(alternativeMenu).build().perform();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(alternativeMenu.getText().contains("Alternative"));


    }
    @Test
    public void resizable(){
        driver.get("https://jqueryui.com/resizable/");
        WebElement iframe = driver.findElement(By.cssSelector(".demo-frame"));
        driver.switchTo().frame(iframe);
        WebElement resizableTable=driver.findElement(By.id("resizable"));
        int cordinate1=resizableTable.getSize().getWidth();
        System.out.println(" first location of x is "+cordinate1);
        WebElement resizable=driver.findElement(By.xpath("//*[@id=\"resizable\"]/div[3]"));
        waitMethod(resizable);
        Actions actions=new Actions(driver);
        actions.clickAndHold(resizable).moveToElement(resizable,125,55).release().build().perform();
        sleep(2);
        //WebElement afterMove=driver.findElement(By.xpath("//div[@style='width: 275px; height: 205px;']"));
        int cordinate2=resizableTable.getSize().getWidth();
        System.out.println(" after move to the location of x is " +cordinate2);
        Assert.assertTrue(cordinate1!=cordinate2);
    }
    @Test
    public void selectIteams() {
        driver.get("https://jqueryui.com/selectable/");
        WebElement iframe = driver.findElement(By.cssSelector(".demo-frame"));
        driver.switchTo().frame(iframe);
        List<WebElement> iteams=driver.findElements(By.xpath("//*[@class=\"ui-selectable\"]"));
        int clickAccount=0;
        for (WebElement iteam:iteams){
            waitMethod(iteam);
            iteam.click();
            clickAccount++;
        }
        Assert.assertTrue(clickAccount==iteams.size());

    }
    @Test
    public void multipleWindowsTest(){
        driver.get("https://seleniummastertutorial.com/");
        WebElement newWindowButton=driver.findElement(By.id("windowsButton"));
        newWindowButton.click();
        String currentWindow=driver.getWindowHandle();
        for (String childWindow:driver.getWindowHandles()){
            if (!childWindow.equalsIgnoreCase(currentWindow)){
                driver.switchTo().window(childWindow);
                WebElement gobackButton=driver.findElement(By.cssSelector(".btn.btn-primary"));
                Assert.assertTrue(gobackButton.isDisplayed());

            }

        }
    }
    @Test
    public void iteratormultipleWindowsTest(){
        driver.get("https://seleniummastertutorial.com/");
        WebElement newWindowButton=driver.findElement(By.id("windowsButton"));
        newWindowButton.click();
        Set<String> windows=driver.getWindowHandles();
        Iterator<String> iterator=windows.iterator();
        String currentWindow1=iterator.next();
        String newWindow2=iterator.next();
        driver.switchTo().window(newWindow2);
        WebElement gobackButton=driver.findElement(By.cssSelector(".btn.btn-primary"));
        Assert.assertTrue(gobackButton.isDisplayed());
    }
    @Test
    public  void multipleLinkTest() {
        driver.get("https://jqueryui.com/");
        List<WebElement> links = driver.findElements(By.xpath("//*[@id=\"sidebar\"]//a"));
        List<String> urls = new ArrayList<>();
        for (WebElement eachLink : links) {
            String url = eachLink.getAttribute("href");
            urls.add(url);
        }
        int urlCount = 0;
        for (String eachurl : urls) {
            driver.navigate().to(eachurl);
            ScreenShotUtility screenShotUtility=new ScreenShotUtility();
            screenShotUtility.takeScreenshot("image",
                    eachurl.replace("https://jqueryui.com/", "".replaceAll("/", "")), driver);
            urlCount++;

            if (urlCount >= 5) {
                break;
            }
        }
        Assert.assertTrue(urls.size()>1);
    }
    @Test
    public void verifyBrokenLink(){
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        List<WebElement> links=driver.findElements(By.cssSelector("li[class=\"gf-li\"] a"));
        for (WebElement eachlink2:links){
            String url=eachlink2.getAttribute("href");
            HttpURLConnection connection= null;
            try {
                connection = (HttpURLConnection) new URL(url).openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                connection.setRequestMethod("HEAD");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }
            try {
                connection.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            int respondCode=0;
            try {
                respondCode=connection.getResponseCode();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(respondCode);
            if (respondCode>400)
                System.out.println("The Link with Test "+eachlink2.getText()+ " " +
                        "is broken link"+" with response Code "+respondCode);


        }
    }

    public  void waitMethod(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void sleep(int seconds){
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void tearDown() {
        driver.close();
        driver.quit();
    }

}
