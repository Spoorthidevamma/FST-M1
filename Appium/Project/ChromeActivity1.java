package Activities;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;


public class ChromeActivity1 {
    AndroidDriver<MobileElement> driver;
    WebDriverWait wait;

    @BeforeClass
    public void beforeClass() throws MalformedURLException, InterruptedException {
        // Set the Desired Capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "<Your device name>");
        caps.setCapability("platformName", "Android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("appPackage", "com.android.chrome");
        caps.setCapability("appActivity", "com.google.android.apps.chrome.Main");
        caps.setCapability("noReset", true);

        // Instantiate Appium Driver
        URL appServer = new URL("http://0.0.0.0:4723/wd/hub");
        driver = new AndroidDriver<>(appServer, caps);
        wait = new WebDriverWait(driver, 5);

        // Navigate to the page
        driver.get("https://www.training-support.net/selenium");
        wait(50);
    }

    @Test

    public void ChromeActivity() throws InterruptedException {

        Thread.sleep(5000);
        driver.findElementByXPath("//android.widget.Button[@text='Get Started!']").click();

        Thread.sleep(5000);
        driver.findElement(MobileBy.AndroidUIAutomator("UiScrollable(UiSelector()).flingToEnd(3)"));
        driver.findElementByXPath("//android.view.View[contains(@text,'To-Do List')]").click();
        Thread.sleep(5000);
        driver.findElement(MobileBy.id("taskInput")).sendKeys("Test");

        driver.findElementByXPath("//android.widget.Button[@text='Add Task']").click();
        Thread.sleep(5000);
        Assert.assertTrue(driver.findElementById("tasksList").isDisplayed());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}