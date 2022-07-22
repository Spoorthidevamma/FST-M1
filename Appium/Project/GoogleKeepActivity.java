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
import java.util.List;
import java.net.MalformedURLException;
import java.net.URL;

public class GoogleKeepActivity {
    // Declare Android driver
    AndroidDriver<MobileElement> driver;

    @BeforeClass
    public void setUp() throws MalformedURLException, InterruptedException {
        // Set the Desired Capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "<device name>");
        caps.setCapability("platformName", "Android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("appPackage", "com.google.android.keep");
        caps.setCapability("appActivity", ".activities.BrowseActivity");
        caps.setCapability("noReset", true);

        // Instantiate Appium Driver
        URL appServer = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver<>(appServer, caps);
        Thread.sleep(5000);
    }
    @Test
    public void Test() throws InterruptedException {

        //Adding first activity
        //Thread.sleep(3000);
        driver.findElement(MobileBy.id("com.google.android.keep:id/new_note_button")).click();
        Thread.sleep(5000);
        driver.findElement(MobileBy.id("com.google.android.keep:id/editable_title")).sendKeys("Activity");
        driver.findElement(MobileBy.id("com.google.android.keep:id/edit_note_text")).sendKeys("Activity done");
       // driver.findElement(MobileBy.id("com.google.android.keep:id/add_task_done")).click();

        MobileElement backButton = driver.findElementByXPath("//android.widget.ImageButton[@content-desc='Open navigation drawer']");
        backButton.click();

        Thread.sleep(5000);
        List<MobileElement> tasksSize = driver.findElementsById("com.google.android.keep:id/browse_note_interior_content");
        System.out.println("The number of tasks added: "+tasksSize.size() );
        Assert.assertEquals(1, tasksSize.size());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}