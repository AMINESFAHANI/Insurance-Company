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

public class CustUpdatePlan {

    String path = Config.server;

    public void pause(Integer milliseconds){
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };

    @Given("the customer is on the plans page")
    public void plansPage() {
        BasicRunner.driver.get(path);
        pause(500);
        BasicRunner.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        BasicRunner.loginPage.userName.sendKeys("Tester3");
        BasicRunner.loginPage.password.sendKeys("Tester3");
        BasicRunner.loginPage.loginButton.click();
        FluentWait wait = new FluentWait(BasicRunner.driver);
        wait.withTimeout(10, TimeUnit.SECONDS);
        wait.pollingEvery(1, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.urlContains("claim"));
        BasicRunner.claimsPage.plansNavigation.click();
        wait.until(ExpectedConditions.attributeContains(BasicRunner.plansPage.plan, "innerHTML", "Plan"));
    };

    @When("the customer clicks the {string} button")
    public void clickPlans(String plan) {
        switch (plan) {
            case "bronze":
                BasicRunner.plansPage.bronze.click();
                break;
            case "silver":
                BasicRunner.plansPage.silver.click();
                break;
            case "gold":
                BasicRunner.plansPage.gold.click();
                break;
            default:
                break;
        };
    };

    @Then("{string} is reflected on the navigation bar")
    public void checkPlan(String plan) {
        FluentWait wait = new FluentWait(BasicRunner.driver);
        wait.withTimeout(10, TimeUnit.SECONDS);
        wait.pollingEvery(1, TimeUnit.SECONDS);
        switch (plan) {
            case "bronze":
                wait.until(ExpectedConditions.attributeContains(BasicRunner.plansPage.plan, "innerHTML", "Bronze Plan"));
                break;
            case "silver":
                wait.until(ExpectedConditions.attributeContains(BasicRunner.plansPage.plan, "innerHTML", "Silver Plan"));
                break;
            case "gold":
                wait.until(ExpectedConditions.attributeContains(BasicRunner.plansPage.plan, "innerHTML", "Gold Plan"));
                break;
        };
    };
};
