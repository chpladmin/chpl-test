package gov.healthit.chpl.aqa.stepDefinitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.healthit.chpl.aqa.pageObjects.DpManagementPage;
import gov.healthit.chpl.aqa.pageObjects.ListingDetailsPage;

/**
 * Class UploadListingsRegularlySteps definition.
 */

public class UploadListingsRegularlySteps {
    private WebDriver driver;
    private String url = System.getProperty("url");
    private String filePath = System.getProperty("filePath");
    private static final int TIMEOUT = 30;
    private static final int LONG_TIMEOUT = 90;
    //    private static final DateFormat DATEFORMAT = new SimpleDateFormat("MMdd");
    //    private static final DateFormat DATEFORMATV = new SimpleDateFormat("dd");
    private static final String CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * Constructor creates new driver.
     */
    public UploadListingsRegularlySteps() {
        driver = Hooks.getDriver();
        if (StringUtils.isEmpty(url)) {
            url = "http://localhost:3000/";
        }
        if (StringUtils.isEmpty(filePath)) {
            String tempDirectory = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources";
            filePath = tempDirectory;
        }
    }

    /**
     * Navigate to Upload Certified Products page.
     */
    @And("^I am on Upload Certified Products page$")
    public void iAmOnUploadCertifiedProductsPage() {
        DpManagementPage.dpManagementLink(driver).click();
    }

    /**
     * Upload a listing.
     * @param edition is listing edition
     */
    @When("^I upload a \"([^\"]*)\" listing$")
    public void iUploadAlisting(final String edition) {
        DpManagementPage.chooseFileButton(driver).sendKeys(filePath + File.separator + edition + "_Test_SLI.csv");
        DpManagementPage.uploadFileButton(driver).click();
    }

    /**
     * Assert upload success message.
     */
    @Then("^I see upload successful message$")
    public void uploadSuccessText() {
        String successText = DpManagementPage.uploadSuccessfulText(driver).getText();
        assertTrue(successText.contains("was uploaded successfully"));
    }

    /**
     * Navigate to Confirm Pending Products page.
     */
    @When("^I go to Confirm Pending Products Page$")
    public void loadConfirmPendingProductsPage() {
        DpManagementPage.confirmPendingProductsLink(driver).click();
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(DpManagementPage.pendingListingsTable(driver)));
    }

    /**
     * Confirm uploaded listing.
     * @param edition is listing edition
     * @param testChplId is chpl id of listing to confirm
     * @throws Exception if there is an exception
     */
    @And("^I confirm \"([^\"]*)\" listing with CHPL ID \"([^\"]*)\"$")
    public void confirmUploadedListing(final String edition, final String testChplId) throws Exception {
        Calendar now = Calendar.getInstance();

        String newPid = "" + CHARS.charAt(now.get(Calendar.MONTH))
        + CHARS.charAt(now.get(Calendar.DAY_OF_MONTH))
        + CHARS.charAt(now.get(Calendar.HOUR_OF_DAY))
        + CHARS.charAt(now.get(Calendar.MINUTE));

        String newVid = "" + CHARS.charAt(now.get(Calendar.MINUTE))
        + CHARS.charAt(now.get(Calendar.SECOND));

        String confListingId = edition.substring(2) + ".05.05.1447." + newPid + "." + newVid + ".00.1.180707";

        try {
            WebDriverWait shortWait = new WebDriverWait(driver, TIMEOUT);
            WebDriverWait longWait = new WebDriverWait(driver, LONG_TIMEOUT);
            longWait.until(ExpectedConditions.visibilityOf(DpManagementPage.inspectButtonForUploadedListing(driver, testChplId)));
            longWait.until(ExpectedConditions.elementToBeClickable(DpManagementPage.inspectButtonForUploadedListing(driver, testChplId)));
            DpManagementPage.inspectButtonForUploadedListing(driver, testChplId).click();

            DpManagementPage.nextOnInspectButton(driver).click();

            if (DpManagementPage.isProductNewDivElementPresent(driver)) {
                DpManagementPage.createNewProductOptionOnInspect(driver).click();
            }
            DpManagementPage.nextOnInspectButton(driver).click();

            if (DpManagementPage.isVersionNewDivElementPresent(driver)) {
                DpManagementPage.createNewVersionOptionOnInspect(driver).click();
            }
            DpManagementPage.nextOnInspectButton(driver).click();

            DpManagementPage.editOnInspectButton(driver).click();

            DpManagementPage.productIdOnInspect(driver).clear();
            DpManagementPage.productIdOnInspect(driver).sendKeys(newPid);

            DpManagementPage.productVersionOnInspect(driver).clear();
            DpManagementPage.productVersionOnInspect(driver).sendKeys(newVid);

            DpManagementPage.saveCpOnInspect(driver).click();
            shortWait.until(ExpectedConditions.textToBePresentInElement(DpManagementPage.inspectModalLabel(driver), confListingId));

            DpManagementPage.confirmButtonOnInspect(driver).click();

            WebElement button = DpManagementPage.yesOnConfirm(driver);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);

            longWait.until(ExpectedConditions.visibilityOf(DpManagementPage.updateSuccessfulToastContainer(driver)));

            driver.get(url + "/#/product/" + confListingId);
            longWait.until(ExpectedConditions.visibilityOf(ListingDetailsPage.mainContent(driver)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            takeScreenshot(confListingId);
        }
    }

    /**
     * Take a screenshot.
     * @param hash a string that will be inserted into the filename to avoid overwriting images
     * @throws Exception if there is an exception
     */
    public void takeScreenshot(final String hash) throws Exception {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(filePath + File.separator + "failed-test-" + hash + ".png"));
    }

    /**
     * Load listing details to verify listing was uploaded successfully.
     * @param ed - edition digits in CHPL ID
     */
    @Then("^I see that listing was uploaded successfully to CHPL and listing details load as expected for uploaded \"([^\"]*)\" listing$")
    public void verifyUploadWasSuccessful(final String ed) {
        String testListingName = "New product";
        String actualString = ListingDetailsPage.listingName(driver).getText();
        assertEquals(actualString, testListingName);
    }
}
