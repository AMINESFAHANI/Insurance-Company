package dev.adventure.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClaimsPage {

    private WebDriver driver;

    public ClaimsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "loginNavigation")
    public WebElement logoutButton;

    @FindBy(id = "claim")
    public WebElement claim;

    @FindBy(id = "amount")
    public WebElement amount;

    @FindBy(id = "claimButton")
    public WebElement claimButton;

    @FindBy(id = "claimTableBody")
    public WebElement tableBody;

    @FindBy(id = "claimsNavigation")
    public WebElement claimsNavigation;

    @FindBy(id = "plansNavigation")
    public WebElement plansNavigation;

    @FindBy(id = "plan")
    public WebElement planBody;
    
    @FindBy(id = "pending")
    public WebElement viewPending;

    @FindBy(id = "completed")
    public WebElement viewCompleted;

    @FindBy(id = "all")
    public WebElement viewAll;

    @FindBy(id = "currentView")
    public WebElement currentView;

    @FindBy(id = "count")
    public WebElement count;
}