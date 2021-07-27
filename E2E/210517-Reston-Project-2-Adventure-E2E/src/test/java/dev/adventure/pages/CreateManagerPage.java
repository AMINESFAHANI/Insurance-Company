package dev.adventure.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateManagerPage {

    private WebDriver webDriver;

    public CreateManagerPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id = "nameInput")
    public WebElement name;

    @FindBy(id = "usernameInput")
    public WebElement username;

    @FindBy(id = "passwordInput" )
    public WebElement password;

    @FindBy(id = "passwordConfirmInput")
    public WebElement passwordConfirm;

    @FindBy(id = "createButton")
    public WebElement createButton;




}
