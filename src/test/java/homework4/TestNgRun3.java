package homework4;

import com.unitedcoder.cubecartautomation.TestBase;
import com.unitedcoder.exceldemo.ExcelUtility;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNgRun3 extends TestBase {

    DashboardPage4 dashboardPage4;
    LoginPage4 loginPage4;
    ManufacturePage4 manufacturePage4;
    ExcelUtility excelUtility;
    TestUtility4 testUtility4;
    String filename = "C:\\Users\\Matros\\IdeaProjects\\cubecartapplication2011\\Test_Data\\doc1.xlsx";

    @BeforeClass
    public void setUP() {
        browserSetUp();
        loginPage4 = new LoginPage4(driver);
        loginPage4.login();
        dashboardPage4 = new DashboardPage4(driver);
        excelUtility = new ExcelUtility();

    }

    @Test(dataProvider = "manufacturerInfo")
    public void addMultipleManufacturer(String manuName, String urls) {
        dashboardPage4.clickONManufactureLink();
        manufacturePage4 = new ManufacturePage4(driver);
        manufacturePage4.addMultipleManufacturer(manuName, urls);
    }

    @DataProvider
    public Object[][] manufacturerInfo() {

        Object[][] manufactururDate = new Object[][]{
                {excelUtility.readFromExcelCell(filename, "manu-info", 1, 0),
                        excelUtility.readFromExcelCell(filename, "manu-info", 1, 1)},
                {excelUtility.readFromExcelCell(filename, "manu-info", 2, 0),
                        excelUtility.readFromExcelCell(filename, "manu-info", 2, 1)},

                {excelUtility.readFromExcelCell(filename, "manu-info", 3, 0),
                        excelUtility.readFromExcelCell(filename, "manu-info", 3, 1)}

        };
        return manufactururDate;
    }

    @Test
    public void deleteAnyManufacturers() {
        dashboardPage4.clickONManufactureLink();
        manufacturePage4 = new ManufacturePage4(driver);
        manufacturePage4.deleteAnyManufacturer();
        Assert.assertTrue(manufacturePage4.verifyManufacturerDeleted());
    }

    @Test
    public void createOrder() {
        dashboardPage4.clickOnOrdersLink();
        OrdersPage4 ordersPage4 = new OrdersPage4(driver);
        ordersPage4.createOrders();
        Assert.assertTrue(ordersPage4.verifyOrderSuccess());
    }

    @Test
    public void deleteAnyOrder() {
        dashboardPage4.clickOnOrdersLink();
        OrdersPage4 ordersPage4 = new OrdersPage4(driver);
        ordersPage4.deleteAnyOrders();
        Assert.assertTrue(ordersPage4.verifyDeleteOrderes());



        }
        @Test
        public void addMultipleCustomer(){
        dashboardPage4.clickOnCustomerListLink();
        CustomerPage4 customerPage4=new CustomerPage4(driver);
        customerPage4.addMultipleCustomer();
        Assert.assertTrue(customerPage4.verifyCustomerAddedSuccessfully());

        }

    @AfterClass
    public void tearDown () {
        dashboardPage4.logout();
        driver.close();
        driver.quit();
    }
    }
