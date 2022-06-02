package homework4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobjectmoduel.TestUtility;

public class DashboardPage4 {
    WebDriver driver;
    TestUtility testUtility;


    @FindBy(id="nav_products")
    WebElement productsLink;

    @FindBy(linkText="Customer List")
    WebElement customersLink;

    @FindBy(id="nav_categories")
    WebElement catogorieslink;
    @FindBy(linkText = "Manufacturers")
    WebElement manufactureLink;

    @FindBy(linkText = "Orders")
    WebElement ordersLink;

    @FindBy(css="i.fa.fa-sign-out")
    WebElement logoutLink;


    public DashboardPage4(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        testUtility=new TestUtility(driver);
    }

    public boolean verifyLogin(){
        testUtility.waitelement(productsLink);
        return productsLink.isDisplayed();
    }

    public void clickOnProductsLink(){
        testUtility.waitelement(productsLink);
        productsLink.click();
    }

    public void clickOnCustomerListLink(){
        testUtility.waitelement(customersLink);
       // Actions actions=new Actions(driver);
        //actions.moveToElement(customersLink).build().perform();

        customersLink.click();
    }

    public void clickOnCategoryLink(){
        testUtility.waitelement(catogorieslink);
        catogorieslink.click();
    }
    public void  clickONManufactureLink(){
        testUtility.waitelement(manufactureLink);
        manufactureLink.click();

    }
    public void clickOnOrdersLink(){
        testUtility.waitelement(ordersLink);
        ordersLink.click();
    }

    public void logout(){
        testUtility.waitelement(logoutLink);
        logoutLink.click();
    }
}
