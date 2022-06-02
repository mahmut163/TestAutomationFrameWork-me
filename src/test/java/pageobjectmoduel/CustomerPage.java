package pageobjectmoduel;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CustomerPage {

    WebDriver driver;
    TestUtility testUtility;

    @FindBy(linkText = "Add Customer")
    WebElement addCustomerLink;

    @FindBy(id = "cust-firstname")
    WebElement customerFirstNameField;
    @FindBy(id = "cust-lastname")
    WebElement customerLastNameField;
    @FindBy(id = "cust-email")
    WebElement customerEmailField;
    @FindBy(xpath = "//input[@value=\"Save\"]")
    WebElement saveButton;
    @FindBy(css = "i.fa.fa-trash")
    WebElement deleteIcon;
    @FindBy(xpath = "//*[contains(text(),'Customer successfully deleted.')]")
    WebElement successMessageForDelete;

    @FindAll(
            @FindBy(xpath = "//*[@id=\"customer-list\"]/table/tbody/tr")
    )
    List<WebElement> customersList;

    @FindBy(xpath = "//*[@id=\"customer-list\"]/table")
    WebElement customerListTable;

    String customerName = null;

    public CustomerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        testUtility = new TestUtility(driver);
    }

    public void addCustomer() {
        testUtility.waitelement(addCustomerLink);
        addCustomerLink.click();
        testUtility.waitelement(customerFirstNameField);
        customerName = testUtility.generateFirstName();
        customerFirstNameField.sendKeys(customerName);
        testUtility.waitelement(customerLastNameField);
        customerLastNameField.sendKeys(testUtility.generateLastName());
        testUtility.waitelement(customerEmailField);
        customerEmailField.sendKeys("abc" + System.currentTimeMillis() + "@gmail.com");
        testUtility.waitelement(saveButton);
        saveButton.click();
    }
    public void addCustomerData(String firstName, String lastName,String email) {
        //testUtility.waitelement(addCustomerLink);
        addCustomerLink.click();
        //testUtility.waitelement(customerFirstNameField);
        customerName = testUtility.generateFirstName();
        customerFirstNameField.sendKeys(firstName);
        //testUtility.waitelement(customerLastNameField);
        customerLastNameField.sendKeys(lastName);
        //testUtility.waitelement(customerEmailField);
        customerEmailField.sendKeys(email);
       // testUtility.waitelement(saveButton);
        saveButton.click();
    }

    public boolean verifyCustomerAddedSuccessfully() {
        return driver.getPageSource().contains(customerName);
    }

    public void deleteCustomer() {
        testUtility.sleep();
        deleteIcon.click();
        testUtility.waitForAlertPresent();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public boolean verifyCustomerDeletedSuccessfully() {
        testUtility.waitelement(successMessageForDelete);
        return successMessageForDelete.isDisplayed();
    }

    public boolean viewCustomers() {
        testUtility.waitelement(customerListTable);
        return customersList.size() > 1;

    }
}



