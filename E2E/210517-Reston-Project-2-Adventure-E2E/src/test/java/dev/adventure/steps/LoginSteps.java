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

public class LoginSteps {

    String path = Config.server;

    public void pause(Integer milliseconds){
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };

    @Given("the customer is at the login portal")
    public void loginPortal() {
        BasicRunner.driver.get(path);
        pause(500);
    }

    @When("the customer enters their correct username")
    public void enterUserName() {
        BasicRunner.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        BasicRunner.loginPage.userName.sendKeys("Tester3");
    }

    @When("the customer enters their correct password")
    public void enterRightPassword() {
        BasicRunner.loginPage.password.sendKeys("Tester3");
    }

    @When("the customer enters their incorrect password")
    public void enterWrongPassword() {
        BasicRunner.loginPage.password.sendKeys("Tester4");
    }

    @When("the customer clicks the login button")
    public void clickSubmit() {
        BasicRunner.loginPage.loginButton.click();
    }

    @Then("the browser navigates to the customer UI")
    public void isClaims() {
        // Write code here that turns the phrase above into concrete actions
        FluentWait wait = new FluentWait(BasicRunner.driver);
        wait.withTimeout(10, TimeUnit.SECONDS);
        wait.pollingEvery(1, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.urlContains("claim"));

        String title = BasicRunner.driver.getTitle();
        Assert.assertEquals(title, "Adventure Insurance Claims");
    }

    @Then("an error message is displayed under the login box")
    public void errorMessage() {
        FluentWait wait = new FluentWait(BasicRunner.driver);
        wait.withTimeout(10, TimeUnit.SECONDS);
        wait.pollingEvery(1, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.attributeContains(BasicRunner.loginPage.message, "innerText", "Noperino!"));
        String message = BasicRunner.loginPage.message.getAttribute("innerText");
        System.out.println(message);
        Assert.assertNotEquals("", message);
    }
}
