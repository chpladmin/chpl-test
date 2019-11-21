package gov.healthit.chpl.aqa.stepDefinitions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;
import gov.healthit.chpl.aqa.stepDefinitions.Hooks;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 		11/21/2019	Palak Patel	: Using properties file
 */	

/** Base class for step definition files. */
public class Base {
    private EventFiringWebDriver driver;
    private WebDriverWait wait;
    private String url; 
    private String filePath = System.getProperty("filePath");
    private String apikey; 
    protected static final long TIMEOUT = 30;
    protected static final long LONG_TIMEOUT = 120;
    private static final int MAX_RETRYCOUNT = 8;
    private static final int LAST_MODIFIED_WINDOW_MILLIS = 15000;
    protected static final int SLEEP_TIME = 5000;
    protected static final int DEBOUNCE_TIME = 500;
    protected static final String CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static Properties prop= new Properties();
    
    /** Default constructor. */
    public Base() {
        super();
        this.driver = Hooks.getDriver();
        this.setWait(new WebDriverWait(this.driver, TIMEOUT));
        FileInputStream fs;
		try {
			fs = new FileInputStream(System.getProperty("user.dir") + File.separator + "prop.properties");
			prop.load(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
        this.setUrl(prop.getProperty("url"));
        if (StringUtils.isEmpty(this.filePath)) {
            String tempDirectory = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources";
            this.setFilePath(tempDirectory);
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(final EventFiringWebDriver driver) {
        this.driver = driver;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(final String filePath) {
        this.filePath = filePath;
    }

    public String getUrl() {
        return prop.getProperty("url");
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public void setWait(final WebDriverWait wait) {
        this.wait = wait;
    }

    public String getApikey() {
        return prop.getProperty("apikey");
    }

    public void setApikey(final String apikey) {
        this.apikey = apikey;
    }

    /**
     * Checks whether the specified file is downloaded or not by iterating 8 times where each iteration has a wait of 5 seconds.
     * @param fileName expected downloaded file name
     * @throws FileNotFoundException if the expected file not found
     */
    public void checkIfFileIsDownloaded(final String fileName)throws FileNotFoundException {
        String downloadFileName = null;
        boolean fileFound = false;
        int retryCount = 0;
        while (!fileFound && retryCount <= MAX_RETRYCOUNT) {
            try {
                File[] files = Hooks.getDownloadDirectory().listFiles();
                for (File file : files) {
                    downloadFileName = file.getName();
                    if (downloadFileName.startsWith(fileName)) {
                        fileFound = true;
                        break;
                    }
                    Thread.sleep(SLEEP_TIME);
                    retryCount++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!fileFound) {
            throw new FileNotFoundException("File: " + fileName + " not downloaded");
        }
    }

    /**
     * Checks if the recent downloaded file is downloaded completely.
     * file name starts with name of the file and ends with extension.
     * @param fileName is the name of file
     * @param ext expected extension of the file
     * @throws FileNotFoundException if the expected file not found
     */
    public void checkCompleteFileDownload(final String fileName, final String ext) throws FileNotFoundException {
        boolean foundFile = false;
        int retryCount = 0;
        try {
            while (!foundFile && retryCount <= MAX_RETRYCOUNT) {
                File[] files = Hooks.getDownloadDirectory().listFiles();
                for (int i = 0; i < files.length; i++) {
                    Long currentTime = System.currentTimeMillis();
                    if ((files[i].getName().startsWith(fileName) && files[i].getName().endsWith(ext)) && ((currentTime - files[i].lastModified()) < LAST_MODIFIED_WINDOW_MILLIS)) {
                        foundFile = true;
                        break;
                    }
                }
                if (!foundFile) {
                    retryCount++;
                    Thread.sleep(SLEEP_TIME);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!foundFile) {
            throw new FileNotFoundException("File not downloaded completely");
        }
    }

    /**
     * Navigate to a specific environment.
     * @param env test environment in which tests will be run
     * @return envURL
     */
    public String getEnvUrl(final String env) {
        String envUrl;
        switch (env) {
        case "DEV": envUrl = "https://chpl.ahrqdev.org";
        break;
        case "STG": envUrl = "https://chpl.ahrqstg.org";
        break;
        case "PROD": envUrl = "https://chpl.healthit.gov";
        break;
        default: envUrl = getUrl();
        break;
        }
        return envUrl;
    }

    /**
     * Get Current Date.
     * @return date in MM/dd/yyyy format
     */
    public String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        String dateFormatted = dateFormat.format(date);
        return dateFormatted;
    }

    /**
     * Get Date with Time Zone.
     * @param date in MMMM dd,yyyy with time stamp and GMT time zone
     * @throws ParseException if unable to parse the date
     * @return date
     */
    public Date getFormatedDateTimeZone(final String date) throws ParseException {
        Date dateInstance = null;
        if (date != null) {
        DateFormat dateFormat = new SimpleDateFormat("MMMM dd,yyyy hh:mm:ss a z");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
           dateInstance = dateFormat.parse(date);
        }
        return dateInstance;
    }
}

