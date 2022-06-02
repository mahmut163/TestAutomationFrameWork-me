package homework4;

import com.unitedcoder.exceldemo.ExcelUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobjectmoduel.TestUtility;

import java.util.List;

public class ProductsPage4 {

    WebDriver driver;
    TestUtility testUtility;//null
    String excelFile = "Test_Data/doc1.xlsx";
    ExcelUtility excelUtility;


    @FindBy(linkText = "Add Product")
    WebElement addProductLink;

    @FindBy(id = "name")
    WebElement productNameField;

    @FindBy(id = "product_code")
    WebElement productCodeField;

    @FindBy(id = "product_weight")
    WebElement productWeightField;

    @FindBy(id = "stock_level")
    WebElement stockLevelField;

    @FindBy(xpath = "//input[@value=\"Save\"]")
    WebElement saveButton;

    @FindBy(linkText = "View All")
    WebElement viewAllLink;

    @FindBy(css = "div.success")
    WebElement successfullMessage;
    @FindBy(css = "div.success")
    WebElement deleteSuccessSms;


    @FindAll(@FindBy(css = "i.fa.fa-trash"))
    List<WebElement> deleteIconList;

    String productName = null;

    public ProductsPage4(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        excelUtility = new ExcelUtility();
        testUtility = new TestUtility(driver);
    }


    public void addProduct() {
        for (int i=1;i<4;i++) {
            productName = excelUtility.readFromExcelCell(excelFile, "product-list", i, 0);
            String productCode = excelUtility.readFromExcelCell(excelFile, "product-list", i, 1);
            String productWeight = excelUtility.readFromExcelCell(excelFile, "product-list", i, 2);
            String stockLevel = excelUtility.readFromExcelCell(excelFile, "product-list", i, 3);

            testUtility.waitelement(addProductLink);
            addProductLink.click();
            testUtility.waitelement(productNameField);
            productNameField.sendKeys(productName);
            testUtility.waitelement(productCodeField);
            productCodeField.sendKeys(productCode);
            testUtility.waitelement(productWeightField);
            productWeightField.sendKeys(productWeight);
            testUtility.waitelement(stockLevelField);
            stockLevelField.sendKeys(stockLevel);
            testUtility.waitelement(saveButton);
            saveButton.click();
        }
    }
    public void deleteAnyProducts(){
        int number=(int)(Math.random()*deleteIconList.size());
        for (int j=1;j<4;j++){
        WebElement deleteIcon=driver.findElement(By.xpath("//*[@id='general']/table/tbody/tr["+number+"]/td//ancestor" +
                "::div/table/tbody/tr["+number+"]/td[11]/a[4]/i"));
            Actions actions=new Actions(driver);
            actions.moveToElement(deleteIcon).click().perform();
        testUtility.sleep();
        driver.switchTo().alert().accept();
        driver.switchTo().defaultContent();
//        if (deleteSuccessSms.isDisplayed())
//            System.out.println("deleted successfull");


        }


    }
    public void addProduct(String productName,String productCode) {
        testUtility.waitelement(addProductLink);
        addProductLink.click();
        testUtility.waitelement(productNameField);
        productNameField.sendKeys(productName);
        testUtility.waitelement(productCodeField);
        productCodeField.sendKeys(productCode);
        testUtility.waitelement(saveButton);
        saveButton.click();
    }

    public boolean verifyNewProductAdded() {
        testUtility.sleep();
        testUtility.waitelement(viewAllLink);
        viewAllLink.click();
        return driver.getPageSource().contains(productName);
    }

    public boolean verifyProductAdded() {
        testUtility.waitelement(successfullMessage);
        return successfullMessage.isDisplayed();
    }
    public boolean verifyProductDeleted() {
        testUtility.waitelement(deleteSuccessSms);
        return deleteSuccessSms.isDisplayed();
    }
}
