package cucambeframework;


import browserutils.JenkinsBrowserSetup;
import com.unitedcoder.configutility.ApplicationConfig;
import com.unitedcoder.cubecartautomation.TestBase;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Arrays;

public class Hook extends TestBase {
    final static String configFile="config-qa.properties";
    final static String url= ApplicationConfig.readFromConfigProperties(configFile,"qaurl");


    @Before
    public void setup(){
           ChromeOptions chromeOptions=new ChromeOptions();
            JenkinsBrowserSetup jenkinsBrowserSetup=new JenkinsBrowserSetup();
            boolean useHeadless=jenkinsBrowserSetup.setHeadlessModeIfLinux(chromeOptions);
        WebDriverManager.chromedriver().setup();
            if(!useHeadless){
               // System.setProperty("webdriver.chrome.driver","c:\\webdriver\\chromedriver.exe");

            }
            driver=new ChromeDriver(chromeOptions);
            if(!useHeadless){
                driver.manage().window().maximize();
            }
            driver.get(url);

    }



        @After
        public void tearDown(){
            driver.close();
            driver.quit();
        }
    }
