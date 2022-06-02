package pageobjectmoduel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
    WebDriver driver;
    TestUtility testUtility;


    @FindBy(id="nav_products")
    WebElement productsLink;

    @FindBy(linkText="Customer List")
    WebElement customersLink;

    @FindBy(id="nav_categories")
    WebElement catogorieslink;

    @FindBy(xpath = "//i[@class=\"fa fa-sign-out\"]")
    WebElement logoutLink;


    public DashboardPage(WebDriver driver) {
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

        customersLink.click();
    }

    public void clickOnCategoryLink(){
        testUtility.waitelement(catogorieslink);
        catogorieslink.click();
    }

    public void logout(){
       // testUtility.waitelement(logoutLink);
        logoutLink.click();
    }
}
