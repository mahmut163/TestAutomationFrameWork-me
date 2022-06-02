package testngframework;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ExpectExceptionDemo {
    WebDriver driver;
    @Test(expectedExceptions = ArithmeticException.class)
    public void test(){

        System.out.println("aabs");
        throw new ArithmeticException();

    }
    //negativ teslerde ixletimiz,,

@Test(expectedExceptions = NoSuchElementException.class)

public void test2(){
    System.setProperty("webdriver.chrome.driver", "c:\\webdriver\\chromedriver.exe");
    driver=new ChromeDriver();
    driver.manage().window().maximize();
    driver.get("https://www.google.com/");
    driver.findElement(By.name("qgt"));

    }
}
