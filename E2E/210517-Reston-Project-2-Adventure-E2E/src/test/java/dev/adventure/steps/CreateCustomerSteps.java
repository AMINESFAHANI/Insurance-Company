package dev.adventure.steps;

import dev.adventure.Config;
import dev.adventure.runners.BasicRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateCustomerSteps {

    String path = Config.server;

    @Given("The customer is on the create account page")
    public void the_customer_is_on_the_create_account_page() {
        BasicRunner.driver.get(path);
        BasicRunner.explicitWait.until(ExpectedConditions.elementToBeClickable(BasicRunner.loginPage.createCustomer));
        BasicRunner.loginPage.createCustomer.click();
    }

    @When("The customer enters their name as {string}")
    public void the_customer_enters_their_name(String name){
        BasicRunner.explicitWait.until(ExpectedConditions.elementToBeClickable(BasicRunner.createUserPage.name));
        BasicRunner.createUserPage.name.sendKeys(name);
    }

    @When("The customer enters their username as {string}")
    public void the_customer_enters_their_username(String name){
        BasicRunner.explicitWait.until(ExpectedConditions.elementToBeClickable(BasicRunner.createUserPage.username));
        BasicRunner.createUserPage.username.sendKeys(name);
    }

    @When("The customer enters their password as {string}")
    public void the_customer_enters_their_password(String name){
        BasicRunner.explicitWait.until(ExpectedConditions.elementToBeClickable(BasicRunner.createUserPage.password));
        BasicRunner.createUserPage.password.sendKeys(name);
        BasicRunner.createUserPage.passwordConfirm.sendKeys(name);
    }

    @When("The customer enters their occupation as {string}")
    public void the_customer_enters_their_occupation(String name){
        BasicRunner.explicitWait.until(ExpectedConditions.elementToBeClickable(BasicRunner.createUserPage.occupation));
        BasicRunner.createUserPage.occupation.sendKeys(name);
    }

    @When("The customer fills out the correct fields with appropriate information")
    public void the_customer_fills_form(){
        BasicRunner.explicitWait.until(ExpectedConditions.elementToBeClickable(BasicRunner.createUserPage.name));
        BasicRunner.createUserPage.name.sendKeys("createUserHappyPath");
        BasicRunner.createUserPage.username.sendKeys("createUserHappyPath");
        BasicRunner.createUserPage.occupation.sendKeys("createUserHappyPath");
        BasicRunner.createUserPage.password.sendKeys("createUserHappyPath");
        BasicRunner.createUserPage.passwordConfirm.sendKeys("createUserHappyPath");
        WebElement plans = BasicRunner.createUserPage.planSelector;
        Select dropPlans = new Select(plans);
        dropPlans.selectByVisibleText("gold plan");
    }

    @When("The customer clicks the create account button")
    public void attempt_create_account(){
        BasicRunner.explicitWait.until(ExpectedConditions.elementToBeClickable(BasicRunner.createUserPage.createButton));
        BasicRunner.createUserPage.createButton.click();
    }

    @Then("The customer should be notified that the account was created")
    public void account_creation_confirm(){
        BasicRunner.explicitWait.until(ExpectedConditions.alertIsPresent());
        Alert alert = BasicRunner.driver.switchTo().alert();
        Assert.assertTrue(alert.getText().contains("Successfully"));
        alert.dismiss();
    }

    @Then("The customer should be shown an error popup containing Empty")
    public void the_customer_should_be_shown_an_error_popup() {
        BasicRunner.explicitWait.until(ExpectedConditions.alertIsPresent());
        Alert alert = BasicRunner.driver.switchTo().alert();
        Assert.assertTrue(alert.getText().contains("Empty"));
        alert.dismiss();
    }

    @When("The customer selects a plan")
    public void the_customer_selects_a_plan() {
        BasicRunner.explicitWait.until(ExpectedConditions.elementToBeClickable(BasicRunner.createUserPage.planSelector));
        WebElement plans = BasicRunner.createUserPage.planSelector;
        Select dropPlans = new Select(plans);
        dropPlans.selectByVisibleText("gold plan");
    }

    @When("The customer enters the correct information except plan")
    public void almost_correct_info(){
        BasicRunner.explicitWait.until(ExpectedConditions.elementToBeClickable(BasicRunner.createUserPage.name));
        BasicRunner.createUserPage.name.sendKeys("createUserHappyPath");
        BasicRunner.createUserPage.username.sendKeys("createUserHappyPath");
        BasicRunner.createUserPage.occupation.sendKeys("createUserHappyPath");
        BasicRunner.createUserPage.password.sendKeys("createUserHappyPath");
        BasicRunner.createUserPage.passwordConfirm.sendKeys("createUserHappyPath");
    }

}
