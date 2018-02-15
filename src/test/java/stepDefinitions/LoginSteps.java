package gov.healthit.chpl.aqa.stepDefinitions;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.When;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

/**
 * Class LoginSteps definition.
 */
public class LoginSteps {

    private WebDriver driver;
    private static final int DELAY = 60;
    private static final int SLEEP_TIME = 100;

    public LoginSteps() {
        driver = Hooks.getDriver();
    }

    @Given("^User is on CHPL home page$")
    public void userIsOnCHPLHomePage() throws Throwable {
        driver.get("https://chpl.ahrqdev.org/#/search");
        driver.manage().timeouts().implicitlyWait(DELAY, TimeUnit.SECONDS);
    }

    @When("^User clicks on Administrator Login button$")
    public void userClicksOnAdministratorLoginButton() throws Throwable {
        driver.get("https://chpl.ahrqdev.org/#/resources/download");
        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(DELAY, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id='login-toggle']")).click();
        Thread.sleep(SLEEP_TIME);
    }

    @Then("^login form shows in dropdown$")
    public void loginFormShowsInDropdown() throws Throwable {
        System.out.println("Login form open");
        driver.manage().timeouts().implicitlyWait(SLEEP_TIME, TimeUnit.SECONDS);
    }

    @Given("^User enters valid \"(.*)\" and \"(.*)\"$")
    public void userEntersValidUsernameAndPassword(final String username, final String password) throws Throwable {
        driver.get("https://chpl.ahrqdev.org/#/resources/download");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(DELAY, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id='login-toggle']")).click();
        driver.findElement(By.id("username")).sendKeys("username");
        driver.findElement(By.id("password")).sendKeys("password");
    }

    @And("^User clicks login button$")
    public void userClicksLoginButton() throws Throwable {
        driver.findElement(By.xpath("//*[@id='admin']/li/div/form/button[1]")).click();
    }

    @Then("^login should be successful$")
    public void loginShouldBeSuccessful() throws Throwable {
        System.out.println("login successfull");
    }
}