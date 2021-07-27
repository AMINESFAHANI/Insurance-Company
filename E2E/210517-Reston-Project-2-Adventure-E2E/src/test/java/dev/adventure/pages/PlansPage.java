package dev.adventure.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PlansPage {

    private WebDriver driver;

    public PlansPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "plan")
    public WebElement plan;

    @FindBy(id = "choose3")
    public WebElement bronze;

    @FindBy(id = "choose2")
    public WebElement silver;

    @FindBy(id = "choose1")
    public WebElement gold;
}