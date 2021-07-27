package dev.adventure.steps;

import dev.adventure.Config;
import dev.adventure.runners.BasicRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;

public class CustBrowsePlans {

    String path = Config.server;

    @When("the customer clicks the shop plans button")
    public void clickPlans() {
        BasicRunner.claimsPage.plansNavigation.click();
    }

    @Then("the browser navigates to the plans page")
    public void toPlans() {
        FluentWait wait = new FluentWait(BasicRunner.driver);
        wait.withTimeout(10, TimeUnit.SECONDS);
        wait.pollingEvery(1, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.urlContains("plans"));
        String title = BasicRunner.driver.getTitle();
        Assert.assertEquals("Adventure Insurance Plans", title);
    }
}
