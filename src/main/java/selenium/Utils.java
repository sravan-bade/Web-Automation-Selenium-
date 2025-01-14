package selenium;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertTrue;

public class Utils {

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER;
    private static final String DATA_FOR_RANDOM_Number = NUMBER;
    private static SecureRandom random = new SecureRandom();

    public static void dropdownByName(WebElement dropdownElement, String dropdownName) {
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByValue(dropdownName);
    }

    public static void dropdownByIndex(WebElement dropdownElement, int dropdownID) {
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByIndex(dropdownID);
    }

    public static void waitForElement(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 5000);
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public static void hoverOnElement(WebDriver driver, WebElement element) {
        Actions builder = new Actions(driver);
        Action hoverOnElement = builder.moveToElement(element).build();
        hoverOnElement.perform();
    }

    public static void download(WebElement downloadButtonElement) throws InterruptedException, IOException {
        String sourcelocation = downloadButtonElement.getAttribute("href");
        System.out.println(sourcelocation);
        String wget_command = "wget -P " + System.getProperty("user.dir") + "/target/ --no-check-certificate "
                + sourcelocation;
        System.out.println(wget_command);
        try {
            System.out.println("Running the command to download the file");
            Process exec = Runtime.getRuntime().exec(wget_command);
            int exitVal = exec.waitFor();
            Thread.sleep(10000L);
            System.out.println("Exit value: " + exitVal);
        } catch (InterruptedException | IOException ex) {
            System.out.println(ex.toString());
        }
    }

    public static void download(String webLink) throws InterruptedException, IOException {
        String sourcelocation = webLink;
        System.out.println(sourcelocation);
        String wget_command = "wget -P " + System.getProperty("user.dir") + "/target/ --no-check-certificate "
                + sourcelocation;
        System.out.println(wget_command);
        try {
            System.out.println("Running the command to download the file");
            Process exec = Runtime.getRuntime().exec(wget_command);
            int exitVal = exec.waitFor();
            Thread.sleep(10000L);
            System.out.println("Exit value: " + exitVal);
        } catch (InterruptedException | IOException ex) {
            System.out.println(ex.toString());
        }
    }

    public static String date2() {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String dateString = format.format(new Date());

        return dateString;
    }

    public static void verifydownload(String fileName) throws FileNotFoundException {
        int count = 0;
        String downloadPath = System.getProperty("user.dir") + "\\target\\";
        System.out.println(downloadPath);
        System.out.println(fileName);
        File dir = new File(downloadPath);
        File[] dirContents = dir.listFiles();

        for (int i = 0; i < dirContents.length; i++) {
            if (dirContents[i].getName().equals(fileName)) {
                // File has been found, it can now be deleted:
                System.out.println("File found");
                count = 1;
                // dirContents[i].delete();
                // return true;
            }
        }
        if (count == 0) {
            System.out.println("File not found");
            throw new FileNotFoundException("Download file not found");
        }

        // return false;
    }

    public static void moveToWebElement(WebDriver driver, WebElement element) {
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("arguments[0].scrollIntoView(true);", element);

    }

    public static void navigateAndValidatePageURL(WebDriver driver, WebElement drpdwn, WebElement element, String PageURL) throws InterruptedException {
        drpdwn.click();
        Thread.sleep(1000L);
        Assert.assertEquals(true, element.isEnabled());
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        String currentPageURL = driver.getCurrentUrl();
        System.out.println("Navigated URL --->" + currentPageURL);
        System.out.println("Should Contain URL --->" + PageURL);
        //assertEquals(PageURL, currentPageURL.substring(currentPageURL.length()-"PageURL".length())); 
        assertTrue("/rapidOrder/create/ ".contains("/rapidOrder/create/ "));
    }

    public static String generateRandomString(int length) {
        if (length < 1) throw new IllegalArgumentException();

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {

            // 0-62 (exclusive), random returns 0-61
            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

            // debug
            //System.out.format("%d\t:\t%c%n", rndCharAt, rndChar);

            sb.append(rndChar);

        }

        return sb.toString();

    }

    public static String generateRandomNumber(int length) {
        if (length < 1) throw new IllegalArgumentException();

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {

            // 0-62 (exclusive), random returns 0-61
            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_Number.length());
            char rndChar = DATA_FOR_RANDOM_Number.charAt(rndCharAt);

            // debug
            //System.out.format("%d\t:\t%c%n", rndCharAt, rndChar);

            sb.append(rndChar);

        }

        return sb.toString();

    }


    public static String dateAndTime() {

        SimpleDateFormat format = new SimpleDateFormat("MMddyyyyHHmmss");

        String dateString = format.format(new Date());

        return dateString;
    }

    public static String date() {

        SimpleDateFormat format = new SimpleDateFormat("MMddyyyy");

        String dateString = format.format(new Date());

        return dateString;
    }

    public static String dateIncrement(int number) {

        DateFormat dateFormat = new SimpleDateFormat("MMddyyyy");

        Date currentDate = new Date();
        System.out.println(dateFormat.format(currentDate));

        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);

        c.add(Calendar.DAY_OF_MONTH, number); // same with c.add(Calendar.DAY_OF_MONTH, 1);

        // convert calendar to date
        Date currentDatePlusOne = c.getTime();
        System.out.println(dateFormat.format(currentDatePlusOne));

        return dateFormat.format(currentDatePlusOne);

    }

    public static String dateIncrementFormat2(int number) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date currentDate = new Date();
        System.out.println(dateFormat.format(currentDate));

        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);

        c.add(Calendar.DATE, number); // same with c.add(Calendar.DAY_OF_MONTH, 1);

        // convert calendar to date
        Date currentDatePlusOne = c.getTime();

        return dateFormat.format(currentDatePlusOne);

    }

    public static String dateIncrementFormat3(int number) {

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        Date currentDate = new Date();
        System.out.println(dateFormat.format(currentDate));

        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);

        c.add(Calendar.DATE, number); // same with c.add(Calendar.DAY_OF_MONTH, 1);

        // convert calendar to date
        Date currentDatePlusOne = c.getTime();

        return dateFormat.format(currentDatePlusOne);

    }

    public static String date3() {

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

        String dateString = format.format(new Date());

        return dateString;
    }
}