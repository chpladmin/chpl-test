package gov.healthit.chpl.aqa.stepDefinitions;

import static org.testng.Assert.assertTrue;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gov.healthit.chpl.aqa.pageObjects.LoginPage;
import gov.healthit.chpl.aqa.pageObjects.SearchPage;

/**
 * 		11/21/2019	Palak Patel	: Updating to pick credentials from properties file
 * 								: Updated Login Page assertion
 */	


/**
 * Class LoginSteps definition.
 */
public class LoginSteps extends Base {
    /**
     * Verify login attempt was successful.
     * @param name as AQA Admin, AQA ONC or AQA ACB
     */
    @Then("^I should be logged in to CHPL as \"([^\"]*)\"$")
    public void verifyLoginWasSuccessful(final String name) {
        String actualString = LoginPage.loggedinUserName(getDriver()).getText();
        assertTrue(actualString.contains(name));
    }

    /**
     * Log the user in as given ROLE.
     * @param role as ROLE_ADMIN, ROLE_ONC or ROLE_ACB
     */
    @Given("^I'm logged in as \"([^\"]*)\"$")
    public void logInAsRole(final String role) {
        String username = null;
        String password = null;
        
        if (role.equalsIgnoreCase("ROLE_ADMIN")) {
            username = prop.getProperty("roleAdminUsername");
            password = prop.getProperty("roleAdminPassword");
        } else if (role.equalsIgnoreCase("ROLE_ONC")) {
            username = prop.getProperty("roleOncUsername");
            password = prop.getProperty("roleOncPassword"); 
        } else if (role.equalsIgnoreCase("ROLE_ACB")) {
            username = prop.getProperty("roleAcbUsername");
            password = prop.getProperty("roleAcbPassword");
        }
        getDriver().get(prop.getProperty("url") + "#/search");
        //pop up the login/out section
        getWait().until(ExpectedConditions.not(ExpectedConditions.visibilityOf(SearchPage.pendingMask(getDriver()))));
        getWait().until(ExpectedConditions.visibilityOf(LoginPage.loginLogoutPopUp(getDriver())));
        getWait().until(ExpectedConditions.elementToBeClickable(LoginPage.loginLogoutPopUp(getDriver())));
        LoginPage.loginLogoutPopUp(getDriver()).click();
        //when it's popped up we can see the username
        getWait().until(ExpectedConditions.visibilityOf(LoginPage.username(getDriver())));
        LoginPage.username(getDriver()).sendKeys(username);
        LoginPage.password(getDriver()).sendKeys(password);
        LoginPage.loginButton(getDriver()).click();
        getWait().until(ExpectedConditions.visibilityOf(LoginPage.logoutButton(getDriver())));
        WebElement button = LoginPage.loginLogoutPopUp(getDriver());
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", button);
    }

    /**
     * Log the user in as given ROLE in a given environment.
     * @param role as ROLE_ADMIN, ROLE_ONC or ROLE_ACB
     * @param tEnv test environment in which tests will be run
     */
    @Given("^I'm logged in as \"([^\"]*)\" on \"([^\"]*)\"$")
    public void logInAsRoleOnGivenEnv(final String role, final String tEnv) {

        String username = prop.getProperty("roleAdminUsername");
        String password = prop.getProperty("roleAdminPassword");

        this.getEnvUrl(tEnv);
        getDriver().get(getEnvUrl(tEnv) + "#/search");

        getWait().until(ExpectedConditions.visibilityOf(LoginPage.loginLogoutPopUp(getDriver())));
        LoginPage.loginLogoutPopUp(getDriver()).click();

        getWait().until(ExpectedConditions.visibilityOf(LoginPage.username(getDriver())));
        LoginPage.username(getDriver()).sendKeys(username);
        LoginPage.password(getDriver()).sendKeys(password);
        LoginPage.loginButton(getDriver()).click();
        getWait().until(ExpectedConditions.visibilityOf(LoginPage.logoutButton(getDriver())));
    }
}
