package dev.adventure.steps;

import dev.adventure.runners.BasicRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import dev.adventure.Config;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;

public class LogoutSteps {

    String path = Config.server;

    @Given("the customer is logged into the UI")
    public void claimsPortal() {
        BasicRunner.driver.get(path + "/html/claims.html");
    }

    @When("the customer clicks the logout button")
    public void clickLogout() {
        BasicRunner.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        BasicRunner.claimsPage.logoutButton.click();
    }

    @Then("the browser returns to the login portal")
    public void toLogin() {
        String title = BasicRunner.driver.getTitle();
        Assert.assertEquals("Adventure Insurance Login", title);
    }
}
