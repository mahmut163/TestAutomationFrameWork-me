package testngframework;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNGDemo2 {

    @Test(description = "this is for test CubeCart function",groups = "regression")
    public void test(){
        System.out.println("test");
        Assert.assertEquals(10,10);
    }

    @Test(dependsOnMethods = "test",alwaysRun = true ,groups = "regression")
    public void test1(){
        System.out.println("always run");
        Assert.assertEquals(100,100);
    }
    @Test(timeOut = 3000,groups = "smoke test")
    public void login(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Login Test");
        Assert.assertEquals(100,100);
    }
    @Test(expectedExceptions = NumberFormatException.class,enabled = false)
    public void test2(){
        String s="100B";
        System.out.println(Integer.parseInt(s));
    }
    @Test(invocationCount = 2,groups = "regression")
    public void test3(){
        System.out.println("test3 run multiple times");
        System.out.println(Math.random()*100);
        Assert.assertEquals(10,10);
    }
}