package browserutils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Arrays;

public class JenkinsBrowserSetup {
    public boolean setHeadlessModeIfLinux(ChromeOptions chromeOptions){
        System.out.println("Using headless browser mode");
        String osname=System.getProperty("os.name");
        WebDriverManager.chromedriver().setup();
        // System.setProperty("webdriver.chrome.driver","/usr/bin/chromedriver");
        if(osname.toLowerCase().contains("linux")){

            chromeOptions.addArguments(Arrays.asList("--headless","--disable-gpu"));
            chromeOptions.addArguments("window-size=1200,1100");
            return true;
        }
        else
            return false;
    }

}
