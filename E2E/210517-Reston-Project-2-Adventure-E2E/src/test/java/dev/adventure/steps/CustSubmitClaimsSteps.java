package dev.adventure.steps;

import dev.adventure.Config;
import dev.adventure.runners.BasicRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import java.util.concurrent.TimeUnit;

public class CustSubmitClaimsSteps {

    String path = Config.server;

    @Given("the customer is on the claims page")
    public void claimsPortal() {
        BasicRunner.driver.get(path);
        BasicRunner.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        BasicRunner.loginPage.userName.sendKeys("Tester3");
        BasicRunner.loginPage.password.sendKeys("Tester3");
        BasicRunner.loginPage.loginButton.click();
        FluentWait wait = new FluentWait(BasicRunner.driver);
        wait.withTimeout(10, TimeUnit.SECONDS);
        wait.pollingEvery(1, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.urlContains("claim"));
    }

    @When("the customer selects a claim in the dropdown")
    public void claimDropdown() {
        WebElement dropdown = BasicRunner.claimsPage.claim;
        Select dropClaims = new Select(dropdown);
        dropClaims.selectByVisibleText("Broken Sword");
    }

    @When("the customer inputs a claim amount")
    public void claimAmount() {
        BasicRunner.claimsPage.amount.sendKeys("3");
    }

    @When("the customer clicks the submit button")
    public void claimSubmit() {
        BasicRunner.claimsPage.claimButton.click();
    }

    @Then("a confirmation window or popup appears")
    public void a_comfirmation_window_or_popup_appears() {
        Alert alert = BasicRunner.driver.switchTo().alert();
        String text = alert.getText();
        Assert.assertTrue(text.contains("sure"));
    }

    @When("the customer clicks the confirm button")
    public void the_customer_clicks_the_confirm_button() {
        Alert alert = BasicRunner.driver.switchTo().alert();
        alert.accept();
    }

    @Then("the claim appears in the claims section")
    public void the_claim_appears_in_the_claims_section() {
        WebDriverWait wait = new WebDriverWait(BasicRunner.driver,3);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = BasicRunner.driver.switchTo().alert();
        alert.dismiss();
        wait.until(ExpectedConditions.attributeContains(BasicRunner.claimsPage.tableBody, "innerHTML", "3"));
        String tableBody = BasicRunner.claimsPage.tableBody.getAttribute("innerHTML");
        Assert.assertTrue(tableBody.contains("3"));
    }

    @When("the customer clicks the back button")
    public void the_customer_clicks_the_back_button() {
        Alert alert = BasicRunner.driver.switchTo().alert();
        alert.dismiss();
    }

    @Then("the confirmation window or popup disappears")
    public void the_confirmation_window_or_popup_disappears(){
        try {
            Alert alert = BasicRunner.driver.switchTo().alert();
        }catch(NoAlertPresentException e){
            Assert.assertTrue(true);
        }

    }
}
