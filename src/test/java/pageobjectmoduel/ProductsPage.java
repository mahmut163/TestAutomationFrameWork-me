package pageobjectmoduel;

import com.unitedcoder.exceldemo.ExcelUtility;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {

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
    @FindBy(css="i.fa.fa-trash")
    WebElement deleteIcon;
    @FindBy(xpath="//*[contains(text(),'Product(s) successfully deleted.')]")
    WebElement successfullyDeletedMessage;


    String productName = null;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        excelUtility = new ExcelUtility();
        testUtility = new TestUtility(driver);
    }


    public void addProduct() {
        productName = excelUtility.readFromExcelCell(excelFile, "Product_Info", 1, 0);
        String productCode = excelUtility.readFromExcelCell(excelFile, "Product_Info", 1, 1);
        String productWeight = excelUtility.readFromExcelCell(excelFile, "Product_Info", 1, 2);
        String stockLevel = excelUtility.readFromExcelCell(excelFile, "Product_Info", 1, 3);

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
    public void addProduct(String productName,String productCode) {
       // testUtility.waitelement(addProductLink);
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
    public void deleteProduct(){
       testUtility.waitelement(deleteIcon);
        deleteIcon.click();
        testUtility.sleep();
        Alert alert1=driver.switchTo().alert();
        alert1.accept();
    }

    public boolean verifyProductDeleted(){

        if (successfullyDeletedMessage.isDisplayed())
            return true;
        else
            return false;
    }





}

