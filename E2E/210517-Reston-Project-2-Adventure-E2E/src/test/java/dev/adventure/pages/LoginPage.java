package dev.adventure.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "uname")
    public WebElement userName;

    @FindBy(id = "psw")
    public WebElement password;

    @FindBy(id = "loginButton")
    public WebElement loginButton;

    @FindBy(id = "managerButton")
    public WebElement managerButton;

    @FindBy(id = "message")
    public WebElement message;

    @FindBy(id = "createCustomerButton")
    public WebElement createCustomer;

    @FindBy(id = "createManagerButton")
    public WebElement createManager;
}