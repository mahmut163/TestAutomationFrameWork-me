package homework4;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobjectmoduel.TestUtility;

import java.util.List;

public class CustomerPage4 {

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
    @FindBy(linkText = "Customer Groups")
            WebElement customerGroupLink;
    @FindAll(
            @FindBy(xpath = "//*[@id=\"group-list\"]//div"))
            List<WebElement> customerGroupList;
    @FindBy(xpath = "//fieldset[@id='group-list']/legend")

            WebElement availableGroup;
    @FindBy(id = "group_add_name")
             WebElement groupNameField;
    @FindBy(id = "group_add_description")
            WebElement discriptionContent;
    @FindBy(name = "save")
            WebElement GroupSaveButton;
    @FindBy(linkText = "Search Customers")
            WebElement customerSearchLink;
    @FindBy(xpath = "//input[@id='customer_id']")
            WebElement searchField;
    @FindBy(xpath = "(//input[@value='Go'])[1]")
            WebElement goButton;
    @FindBy(css = "div.success")
            WebElement customerSuccessSMS;



    String customerName = null;

    public CustomerPage4(WebDriver driver) {
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
        testUtility.waitelement(addCustomerLink);
        addCustomerLink.click();
        testUtility.waitelement(customerFirstNameField);
        customerName = testUtility.generateFirstName();
        customerFirstNameField.sendKeys(firstName);
        testUtility.waitelement(customerLastNameField);
        customerLastNameField.sendKeys(lastName);
        testUtility.waitelement(customerEmailField);
        customerEmailField.sendKeys(email);
        testUtility.waitelement(saveButton);
        saveButton.click();
    }
    public void clickCustomerGroupLink(){
        testUtility.waitelement(customerGroupLink);
        customerGroupLink.click();
    }
    public void createMultipleCustomerGroup() {
        clickCustomerGroupLink();
        for (int i = 1; i < 3; i++) {
            testUtility.waitelement(availableGroup);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.location");// returns current window location
            js.executeScript("window.scrollBy(0,1400)");
            testUtility.sleep();
            groupNameField.sendKeys(testUtility.generateFirstName());
            testUtility.sleep();
            discriptionContent.sendKeys(testUtility.generateLastName());
            GroupSaveButton.click();
        }
    }
        public void addMultipleCustomer() {
        for (int j=1;j<3;j++) {
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
        }
        public void searchCustomer(){
        testUtility.waitelement(customerSearchLink);
        customerSearchLink.click();
        testUtility.sleep();
        searchField.sendKeys("tarim"+ Keys.ENTER);
        goButton.click();
        }
        public boolean verifaySearchedCustomer(){
        return customerSuccessSMS.getText().contains("Customer accounts matching 'tarim'");
        }


    public boolean verifyCustomerAddedSuccessfully() {

        return driver.getPageSource().contains(customerName);
    }
    public boolean verifyCustomerAddedSuccessfullyData(String email) {

        return driver.getPageSource().contains(email);
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
    public boolean viewCustomerGroups(){
        testUtility.waitelement(availableGroup);
     return  customerGroupList.size()>1;
    }
}



