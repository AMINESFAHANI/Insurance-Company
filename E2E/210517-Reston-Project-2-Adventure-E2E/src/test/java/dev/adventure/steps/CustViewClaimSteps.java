package dev.adventure.steps;

import dev.adventure.Config;
import dev.adventure.runners.BasicRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class CustViewClaimSteps {

    String path = Config.server;

    public void pause(Integer milliseconds){
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };

    @Given("The customer is logged in")
    public void the_customer_is_logged_in() {
        BasicRunner.driver.get(path);
        pause(500);
        BasicRunner.loginPage.userName.sendKeys("Tester3");
        BasicRunner.loginPage.password.sendKeys("Tester3");
        BasicRunner.loginPage.loginButton.click();
    }

    @When("The customer navigates to the claim page")
    public void the_customer_navigates_to_the_claim_page() {
        BasicRunner.claimsPage.claimsNavigation.click();
    }
    @Then("The customer should see their claims")
    public void the_customer_should_see_their_claims() {
        String claimText = BasicRunner.claimsPage.tableBody.getAttribute("innerHTML");
        WebDriverWait wait = new WebDriverWait(BasicRunner.driver,5);
        wait.until(ExpectedConditions.attributeContains(BasicRunner.claimsPage.tableBody, "innerHTML", "Broken sword"));
        Assert.assertFalse(claimText.isEmpty());
    }

}
