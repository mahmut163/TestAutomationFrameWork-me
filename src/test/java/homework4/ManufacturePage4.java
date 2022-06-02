package homework4;

import com.unitedcoder.cubecartautomation.TestBase;
import com.unitedcoder.exceldemo.ExcelUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ManufacturePage4 {

    WebDriver driver;
    TestUtility4 testUtility4;
    String excelFile = "Test_Data/doc1.xlsx";
    ExcelUtility excelUtility;

    @FindBy(linkText = "Add Manufacturer")
    WebElement addManufactoreLink;
    @FindBy(id = "manu_name")
    WebElement manufactureNameField;
    @FindBy(id = "manu_site")
    WebElement urlAddressField;
    @FindBy(xpath = "//input[@value='Submit']")
    WebElement submitButton;
    @FindBy(css = "div.success")
    WebElement manufacturerSuccessSMS;
    @FindAll(@FindBy(css = ".fa.fa-trash"))
    List<WebElement> deleteIconList;
    @FindBy(linkText = "View All")
    WebElement ViewAllButton;
    @FindBy(css = "div.success")
    WebElement manufacturerDeletedSMS;

    public ManufacturePage4(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        testUtility4 = new TestUtility4(driver);
        excelUtility = new ExcelUtility();
    }

    public void addMultipleManufacturer(String manufacturerName, String url) {
        // testUtility4.waitelement(addManufactoreLink);
        addManufactoreLink.click();
        testUtility4.waitelement(manufactureNameField);
        manufactureNameField.sendKeys(manufacturerName);
        testUtility4.waitelement(urlAddressField);
        urlAddressField.sendKeys(url);
        submitButton.click();
    }

    public boolean verifyManufacturerAdded() {

        return manufacturerSuccessSMS.getText().contains("Manufacturer added.");
    }

    public void deleteAnyManufacturer() {
        ViewAllButton.click();
        testUtility4.sleep();
        int number = (int) (Math.random() * deleteIconList.size());
        WebElement deleteIcon = driver.findElement(By.xpath("//*[@id=\"manufacturers\"]/table/tbody/tr["+number+"]/td[2]/a[2]/i"));
        Actions actions = new Actions(driver);
        actions.moveToElement(deleteIcon).click().perform();
        testUtility4.sleep();
        driver.switchTo().alert().accept();
        driver.switchTo().defaultContent();


    }
    public boolean verifyManufacturerDeleted(){

        return manufacturerDeletedSMS.getText().contains("Manufacturer deleted.");
    }
}
