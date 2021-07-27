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

public class ManagerClaimsViewsSteps {

    String path = Config.server;

    @Given("a manager is on the claims page")
    public void managerClaims() {
        BasicRunner.driver.get(path);
        BasicRunner.loginPage.userName.sendKeys("username");
        BasicRunner.loginPage.password.sendKeys("password");
        BasicRunner.loginPage.managerButton.click();
        FluentWait wait = new FluentWait(BasicRunner.driver);
        wait.withTimeout(10, TimeUnit.SECONDS);
        wait.pollingEvery(1, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.urlContains("claim"));
        wait.until(ExpectedConditions.attributeContains(BasicRunner.claimsPage.count, "innerHTML", "Claims:"));
    }

    @Then("the pending claims view is shown")
    public void isPendingClaimsView() {
        String view = BasicRunner.claimsPage.currentView.getAttribute("innerText");
        Assert.assertEquals("Current View: pending", view);
    }

    @When("the completed button is clicked")
    public void clickCompleted() {
        BasicRunner.claimsPage.viewCompleted.click();
    }

    @Then("the completed claims view is shown")
    public void isCompletedClaimsView() {
        String view = BasicRunner.claimsPage.currentView.getAttribute("innerText");
        Assert.assertEquals("Current View: completed", view);
    }

    @When("the all button is clicked")
    public void clickAll() {
        BasicRunner.claimsPage.viewAll.click();
    }

    @Then("the all claims view is shown")
    public void isAllClaimsView() {
        String view = BasicRunner.claimsPage.currentView.getAttribute("innerText");
        Assert.assertEquals("Current View: all", view);
    }

    @When("the pending button is clicked")
    public void clickPending() {
        BasicRunner.claimsPage.viewPending.click();
    }
}
