package testngframework;


import org.testng.Assert;
import org.testng.annotations.*;
import static java.lang.Math.*;

public class TestNGDemo1 {
    @BeforeSuite
    public void beforeSuitTest(){
        System.out.println("before suite test will run first");
    }
    @BeforeTest
    public void beforeTest(){
        System.out.println("before test will run after suite");
    }
    @BeforeGroups
    public void beforeGroups(){
        System.out.println("before groups test");
    }
    @BeforeClass
    public void beforeClass(){
        System.out.println("before class");
    }
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("before test");
    }

    @Test(enabled = true,groups = "smoke test")
    public void addProductTest(){
        System.out.println("this is for adding new product");
        Assert.assertTrue("add product".contains("product"));
    }
    @Test(priority=1 ,dependsOnMethods = "viewCustomer")
    public void deleteProduct(){
        System.out.println("this test is for delete product");
        Assert.assertTrue(max(100,90)==100);
    }
    @Test(priority = 2 ,dependsOnGroups = "smoke test")
    public void viewCustomer(){
        System.out.println("this test is for view customer list");
        Assert.assertTrue(min(20,30)==20);
    }
    @AfterMethod
    public void afterMethod(){
        System.out.println("after method");
        Assert.assertTrue(sqrt(64)==8);
    }
    @AfterClass
    public void afterClass(){
        System.out.println("after class");
    }
    @AfterGroups
    public void afterGroups(){
        System.out.println("after groups");
    }
    @AfterTest
    public void afterTest(){
        System.out.println("after test");
    }
    @AfterSuite
    public void afterSuit(){
        System.out.println("after suite");
    }

}