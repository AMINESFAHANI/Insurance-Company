package dev.adventure.steps;

import dev.adventure.Config;
import dev.adventure.runners.BasicRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class CreateManagerSteps {

    String path = Config.server;

    @Given("The manager is on the create account page")
    public void the_manager_is_on_the_create_account_page() {
        BasicRunner.driver.get(path);
        BasicRunner.explicitWait.until(ExpectedConditions.elementToBeClickable(BasicRunner.loginPage.createManager));
        BasicRunner.loginPage.createManager.click();
    }

    @When("The manager enters appropriate information")
    public void the_manager_enters_appropriate_information() {
        BasicRunner.explicitWait.until(ExpectedConditions.elementToBeClickable(BasicRunner.createManagerPage.name));
        BasicRunner.createManagerPage.name.sendKeys("validInput");
        BasicRunner.createManagerPage.username.sendKeys("validInput2");
        BasicRunner.createManagerPage.password.sendKeys("validInput");
        BasicRunner.createManagerPage.passwordConfirm.sendKeys("validInput");
    }

    @When("The manager clicks the create account button")
    public void the_manager_clicks_the_create_account_button() {
        BasicRunner.explicitWait.until(ExpectedConditions.elementToBeClickable(BasicRunner.createManagerPage.createButton));
        BasicRunner.createManagerPage.createButton.click();
    }

    @When("The manager enters their name as {string}")
    public void the_manager_enters_their_name_as(String string) {
        BasicRunner.explicitWait.until(ExpectedConditions.elementToBeClickable(BasicRunner.createManagerPage.name));
        BasicRunner.createManagerPage.name.sendKeys(string);
    }
    @When("The manager enters their username as {string}")
    public void the_manager_enters_their_username_as(String string) {
        BasicRunner.explicitWait.until(ExpectedConditions.elementToBeClickable(BasicRunner.createManagerPage.username));
        BasicRunner.createManagerPage.username.sendKeys(string);
    }
    @When("The manager enters their password as {string}")
    public void the_manager_enters_their_password_as(String string) {
        BasicRunner.explicitWait.until(ExpectedConditions.elementToBeClickable(BasicRunner.createManagerPage.password));
        BasicRunner.createManagerPage.password.sendKeys(string);
        BasicRunner.createManagerPage.passwordConfirm.sendKeys(string);
    }

    @Then("The manager should be notified that something {string} went wrong.")
    public void the_manager_should_be_notified_that_something_went_wrong(String string) {
        BasicRunner.explicitWait.until(ExpectedConditions.alertIsPresent());
        Alert alert = BasicRunner.driver.switchTo().alert();
        Assert.assertTrue(alert.getText().contains(string));
        alert.dismiss();
    }

    @Then("The manager should be notified the account was created and should be redirected to the login page")
    public void the_manager_should_be_notified_the_account_was_created_and_should_be_redirected_to_the_login_page() {
        BasicRunner.explicitWait.until(ExpectedConditions.alertIsPresent());
        Alert alert = BasicRunner.driver.switchTo().alert();
        System.out.println(alert.getText());
        Assert.assertTrue(alert.getText().contains("You successfully made yourself a manager!"));
        alert.dismiss();
    }

    @Then("The manager should be asked to confirm")
    public void the_manager_should_be_asked_to_confirm() {
        BasicRunner.explicitWait.until(ExpectedConditions.alertIsPresent());
        Alert alert = BasicRunner.driver.switchTo().alert();
        Assert.assertTrue(alert.getText().contains("Are you sure you want to submit this information?"));
        alert.accept();
    }

    @Given("The manager is on the login screen")
    public void the_manager_is_on_the_login_screen() {
        BasicRunner.driver.get(path);
    }



}
