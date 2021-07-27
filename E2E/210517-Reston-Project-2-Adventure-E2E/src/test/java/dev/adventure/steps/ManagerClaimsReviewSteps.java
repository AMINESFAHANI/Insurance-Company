package dev.adventure.steps;

import dev.adventure.Config;
import dev.adventure.runners.BasicRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ManagerClaimsReviewSteps {

    String path = Config.server;
    int count;

    private int readCount() {
        String countText = BasicRunner.claimsPage.count.getAttribute("innerHTML");
        String[] numberText = countText.split("Claims: ");
        int number = Integer.parseInt(numberText[1]);
        return number;
    };

    @When("an accept button is clicked")
    public void clickAccept() {
        count = readCount();
        List<WebElement> acceptButtons = BasicRunner.driver.findElements(By.className("accept"));
        acceptButtons.get(1).click();
        FluentWait wait = new FluentWait(BasicRunner.driver);
        wait.withTimeout(10, TimeUnit.SECONDS);
        wait.pollingEvery(1, TimeUnit.SECONDS);
        String newCount = "Claims: " + (count - 1);
        wait.until(ExpectedConditions.attributeContains(BasicRunner.claimsPage.count, "innerHTML", newCount));
    };

    @When("a deny button is clicked")
    public void clickDeny() {
        count = readCount();
        List<WebElement> denyButtons = BasicRunner.driver.findElements(By.className("deny"));
        denyButtons.get(1).click();
        FluentWait wait = new FluentWait(BasicRunner.driver);
        wait.withTimeout(10, TimeUnit.SECONDS);
        wait.pollingEvery(1, TimeUnit.SECONDS);
        String newCount = "Claims: " + (count - 1);
        wait.until(ExpectedConditions.attributeContains(BasicRunner.claimsPage.count, "innerHTML", newCount));
    };

    @Then("the claim disappears")
    public void claimDisappears() {
        int current = readCount();
        Assert.assertEquals(count - 1, current);
    };
}
