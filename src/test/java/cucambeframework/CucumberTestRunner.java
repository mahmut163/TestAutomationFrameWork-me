package cucambeframework;


import com.unitedcoder.cubecartautomation.TestBase;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


    @RunWith(Cucumber.class)
    @CucumberOptions(
            plugin = {"pretty","html:target/cucumber-html-report8.html"
            ,"json:target/cucumber8.json",
                    "junit:target/cucumber8-results.xml"},
            features = {"src/test/resources"},
             // tags="@DatabaseTesting"
            tags="@RegressionTest"
            //tags="@ValidLogin"
            //tags = " @AddProduct"
            //tags = "@CustomerApiTest"
            //tags = "@AddMultipleProducts"
           // tags = "@DeleteProduct"
           // tags = "@unittest"
    )
    public class CucumberTestRunner extends TestBase {

        //terminal run method:mvn test -Dtest=CucumberTestRunner -DfailIfNoTests=false -DsuiteXMLFile=" "
        //${suiteXMLFile}
        //pom file plugin;src\test\java\resources\testng.xml
//        @BeforeClass
//        public  static void setup(){
//
//        }
//        @AfterClass
//        public  static void teardown(){
//
//            //driver.close();
//            driver.quit();
    //    }
    }

