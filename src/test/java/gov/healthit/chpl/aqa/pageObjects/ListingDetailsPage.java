package gov.healthit.chpl.aqa.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Class ListingDetailsPage definition.
 */
public class ListingDetailsPage {

    private WebDriver driver;
    private static WebElement element = null;
    
    public static WebElement acceessibilityStandardText(final WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id=\"panel-certification-criteria\"]/div[54]/div/div/div/table/tbody/tr[3]/td[2]/ul/li[1]"));
        return element;
    }
    
    public static WebElement additionalInfoAccordion(final WebDriver driver) {
        element = driver.findElement(By.id("details-additional-information"));
        return element;
    }
    
    public static WebElement certificationCriteriaAccordion(final WebDriver driver) {
        element = driver.findElement(By.id("details-certification-criteria"));
        return element;
    }
    
    public static WebElement certificationCriteriaA1ViewDetails(final WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id=\"panel-certification-criteria\"]/div[1]/div/div/span[2]/a"));
        return element;
    }

    public static WebElement certificationCriteriaC2ViewDetails(final WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id=\"panel-certification-criteria\"]/div[26]/div/div/span[2]/a"));
        return element;
    }

    public static WebElement certificationCriteriaC3ViewDetails(final WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id=\"panel-certification-criteria\"]/div[27]/div/div/span[2]/a"));
        return element;
    }

    public static WebElement certificationCriteriaC4ViewDetails(final WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id=\"panel-certification-criteria\"]/div[28]/div/div/span[2]/a"));
        return element;
    }

    public static WebElement certificationCriteriaG4ViewDetails(final WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id=\"panel-certification-criteria\"]/div[53]/div/div/span[2]/a"));
        return element;
    }
    
    public static WebElement certificationCriteriaG5ViewDetails(final WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id=\"panel-certification-criteria\"]/div[54]/div/div/span[2]/a"));
        return element;
    }
    
    public static WebElement certificationCriteriaF1ViewDetails(final WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id=\"panel-certification-criteria\"]/div[53]/div/div/span[2]/a"));
        return element;
    }
    
    public static WebElement cqmAccordion(final WebDriver driver) {
        element = driver.findElement(By.id("details-cqm"));
        return element;
    }
    
    public static WebElement disclosureUrl(final WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/div[1]/span/div[2]/a"));
        return element;
    }
    
    public static WebElement editCertifiedProductLink(final WebDriver driver) {
        element = driver.findElement(By.linkText("Edit Certified Product"));
        return element;
    }
    
    public static WebElement g1G2Accordion(final WebDriver driver) {
        element = driver.findElement(By.id("details-g1g2"));
        return element;
    }
    
    public static WebElement intendedUserDescriptionTitle(final WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id=\"panel-sed\"]/div/span/span[2]/h4"));
        return element;
    }

    public static WebElement listingName(final WebDriver driver) {
        element = driver.findElement(By.id("listingName"));
        return element;
    }
    
    public static WebElement qmsStandardText(final WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id=\"panel-certification-criteria\"]/div[53]/div/div/div/table/tbody/tr[3]/td[2]/ul/li"));
        return element;
    }

    public static WebElement sedAccordion(final WebDriver driver) {
        element = driver.findElement(By.id("details-sed"));
        return element;
    }
    
    public static WebElement downloadSEDDetailsButton(final WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id=\"sed-tasks-table\"]/tfoot/tr/td/button"));
        return element;
    }

    public static WebElement sEDCriteriaTable(final WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id=\"sed-ucd-processes-table\"]"));
        return element;
    }
    
    public static WebElement sedCertificationCriteria2(final WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id=\"sed-ucd-processes-table\"]/tbody/tr[1]/td[1]/ul/li[2]"));
        return element;
    }

    public static WebElement sedCertificationCriteria3(final WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id=\"sed-ucd-processes-table\"]/tbody/tr[1]/td[1]/ul/li[3]"));
        return element;
    }

    public static WebElement sedCertificationCriteria4(final WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id=\"sed-ucd-processes-table\"]/tbody/tr[1]/td[1]/ul/li[4]"));
        return element;
    }

    public static WebElement sedCertificationCriteria5(final WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id=\"sed-ucd-processes-table\"]/tbody/tr[1]/td[1]/ul/li[5]"));
        return element;
    }

    public static WebElement sedCertificationCriteria6(final WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id=\"sed-ucd-processes-table\"]/tbody/tr[1]/td[1]/ul/li[6]"));
        return element;
    }

    public static WebElement sedCertificationCriteria7(final WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id=\"sed-ucd-processes-table\"]/tbody/tr[1]/td[1]/ul/li[7]"));
        return element;
    }

    public static WebElement sedCertificationCriteria8(final WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id=\"sed-ucd-processes-table\"]/tbody/tr[1]/td[1]/ul/li[8]"));
        return element;
    }

    public static WebElement sedCertificationCriteria9(final WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id=\"sed-ucd-processes-table\"]/tbody/tr[1]/td[1]/ul/li[9]"));
        return element;
    }

    public static WebElement sedCertificationCriteria10(final WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id=\"sed-ucd-processes-table\"]/tbody/tr[1]/td[1]/ul/li[10]"));
        return element;
    }
    
    public static WebElement sedCertificationCriteria(final WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id=\"sed-ucd-processes-table\"]/tbody/tr[1]/td[1]/ul/li[1]"));
        return element;
    }

    public static WebElement ucdProcessText(final WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id=\"sed-ucd-processes-table\"]/tbody/tr/td[2]"));
        return element;
    }

    public static WebElement usabilityReportTitle(final WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id=\"panel-sed\"]/div/span/span[1]/h4"));
        return element;
    }

    public static WebElement surveillanceActivitiesAccordion(final WebDriver driver) {
        element = driver.findElement(By.id("details-surveillance-activities"));
        return element;
    }
    
}
