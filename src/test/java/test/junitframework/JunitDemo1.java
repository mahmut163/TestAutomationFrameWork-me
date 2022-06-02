package test.junitframework;

import org.junit.*;
import org.junit.rules.TestName;

public class JunitDemo1 {
    @BeforeClass
    // before class junitta qukum static bolixi kerak
    public  static  void setup(){
        System.out.println(" before class only run once");

    }
    @Before
    public  void  beforeeverytest(){
        System.out.println(" run before every test");

    }
    @Test
    public void test1(){

        System.out.println(" quare root test");
        Assert.assertTrue(Math.sqrt(64)==8);
    }
    //@Ignore
    @Test
    public void test2(){

        System.out.println(" compare two number");
        String a1="name";
        String a2="Name";
       // Assert.assertEquals(a1,a2);
    }
  @Rule
    public TestName name=new TestName();
    @Test
    public void test3(){

        System.out.println(" compare two root");
        System.out.println(name.getMethodName()+"  started and passed");
    }
    @After
    public void end(){
        System.out.println(" runs after every test");
    }
    @AfterClass
    public static void tearDown(){
        System.out.println("Runs only once after class");
    }


}
