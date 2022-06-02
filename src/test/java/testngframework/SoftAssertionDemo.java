package testngframework;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class SoftAssertionDemo {

    SoftAssert softAssert=new SoftAssert();
    @Test
    public void dummyTest(){
        System.out.println(" login ");
        Assert.assertEquals(10,10);
        System.out.println(" open browser ");
        Assert.assertEquals(10,10);
        System.out.println(" add product ");
        softAssert.assertEquals(10,10);

        System.out.println(" ad customer");
        // add product bilan add customer dependes on amas ama add product stop bopkasa
        //add customer ham magmay kalaidu,,xuga soft assertion ixlitimez,
        Assert.assertEquals(10,10);
        softAssert.assertAll();
    }
}
