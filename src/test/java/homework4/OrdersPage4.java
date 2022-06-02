package homework4;

import com.unitedcoder.exceldemo.ExcelUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class OrdersPage4 {


    WebDriver driver;
    TestUtility4 testUtility4;
    ExcelUtility excelUtility;
    String excelFile = "Test_Data/doc1.xlsx";

    @FindBy(linkText = "Create Order")
    WebElement creatOrderLink;
    @FindBy(id = "o_status")
    WebElement orderStatusField;
    @FindBy(linkText = "Billing")
    WebElement bilingLink;
    @FindBy(id = "sum_country")
    WebElement bilingCountryField;
    @FindBy(xpath = "(//input[@rel='product_quantity'])[1]")
    WebElement quantityField;
    @FindBy(id = "ajax_name")
    WebElement productNameField;
    @FindBy(id = "ajax_price")
    WebElement unitPriceField;
    @FindBy(id = "discount_type")
    WebElement discountField;
    @FindBy(xpath = "//select[@rel='tax_id']")
    WebElement taxField;
    @FindBy(xpath = "(//i[@title='Add'])[1]")
    WebElement addButton;
    @FindBy(xpath = "//input[@value='Save']")
    WebElement saveButton;
    @FindBy(linkText = "Inventory")
    WebElement InventoryLink;
    @FindBy(xpath = "(//input[@rel=\"line_price\"])[1]")
    WebElement priceField;
    ////table[@id='order-builder']//input[@name='inv_add[0][product_quantity]']
    @FindBy(xpath = "(//tfoot//input[@rel='product_quantity'])[1]")
    WebElement quantityField2;
    @FindBy(xpath = "//tfoot//input[@id='ajax_name']")
    WebElement productNameField2;
    @FindBy(xpath = "//tfoot//input[@id='ajax_price']")
    WebElement unitPriceField2;
    @FindBy(xpath = "(//tfoot//input[@rel='line_price'])[1]")
    WebElement priceField2;
    @FindBy(css = "div.success")
    WebElement ordersSuccessSms;
    @FindAll(@FindBy(css = "i.fa.fa-trash"))
    List<WebElement> deleteIconList;
    @FindBy(css = "div.success")
    WebElement deleteOrdersSuccessSms;

    public OrdersPage4(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        testUtility4 = new TestUtility4(driver);
        excelUtility = new ExcelUtility();
    }

    public void createOrders() {
        testUtility4.waitelement(creatOrderLink);
        creatOrderLink.click();
        testUtility4.waitelement(orderStatusField);
        Select select = new Select(orderStatusField);
        select.selectByValue("3");
        testUtility4.sleep();
        bilingLink.click();
        testUtility4.waitelement(bilingCountryField);
        Select select1 = new Select(bilingCountryField);
        select1.selectByValue("528");
        testUtility4.sleep();
        InventoryLink.click();
        testUtility4.waitelement(quantityField);
        quantityField.clear();
        testUtility4.sleep();
        quantityField.sendKeys(excelUtility.readFromExcelCell(excelFile, "order-info", 1, 0));
        testUtility4.waitelement(productNameField);
        productNameField.sendKeys(excelUtility.readFromExcelCell(excelFile, "order-info", 1, 1));
        testUtility4.waitelement(unitPriceField);
        unitPriceField.clear();
        unitPriceField.sendKeys(excelUtility.readFromExcelCell(excelFile, "order-info", 1, 2));
        testUtility4.waitelement(priceField);
        priceField.click();

        testUtility4.sleep();
        Select select2 = new Select(discountField);
        select2.selectByValue("p");
        //testUtility4.sleep();
        Select select3 = new Select(taxField);
        select3.selectByValue("12");
        addButton.click();
        testUtility4.sleep();
        quantityField2.clear();
        quantityField2.sendKeys(excelUtility.readFromExcelCell(excelFile, "order-info", 2, 0));
        testUtility4.waitelement(productNameField2);
        productNameField2.sendKeys(excelUtility.readFromExcelCell(excelFile, "order-info", 2, 1));
        testUtility4.waitelement(unitPriceField2);
        unitPriceField2.clear();
        unitPriceField2.sendKeys(excelUtility.readFromExcelCell(excelFile, "order-info",
                2, 2));
        testUtility4.waitelement(priceField2);
        priceField2.click();

        testUtility4.sleep();
        Select select4 = new Select(discountField);
        select4.selectByValue("p");
        testUtility4.sleep();
        Select select5 = new Select(taxField);
        select5.selectByValue("12");
        saveButton.click();
    }

    public boolean verifyOrderSuccess() {

        return ordersSuccessSms.getText().contains("Order successfully created.");
    }

    public void deleteAnyOrders() {
        int number = (int) (Math.random() * deleteIconList.size());
        WebElement deleteIcon = driver.findElement(By.xpath("//*[@id=\"orders\"]/table/tbody/tr["+number+"]//" +
                "ancestor::table/tbody/tr["+number+"]/td[8]/a[3]/i"));
        Actions actions = new Actions(driver);
        actions.moveToElement(deleteIcon).click().perform();
        testUtility4.sleep();
        driver.switchTo().alert().accept();
        driver.switchTo().defaultContent();
    }
    public boolean verifyDeleteOrderes(){
        return  deleteOrdersSuccessSms.getText().contains("Order successfully deleted.");

    }
}
