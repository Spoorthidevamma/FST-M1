package Activities;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;
import java.net.MalformedURLException;
import java.net.URL;

public class GoogleTaskActivity {
    // Declare Android driver
    AndroidDriver<MobileElement> driver;

    @BeforeClass
    public void setUp() throws MalformedURLException, InterruptedException {
        // Set the Desired Capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "<device name>");
        caps.setCapability("platformName", "Android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("appPackage", "com.google.android.apps.tasks");
        caps.setCapability("appActivity", ".ui.TaskListsActivity");
        caps.setCapability("noReset", true);

        // Instantiate Appium Driver
        URL appServer = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver<>(appServer, caps);
        Thread.sleep(5000);
    }
    @Test
    public void Test() throws InterruptedException {

        driver.findElement(MobileBy.id("com.google.android.apps.tasks:id/tasks_fab")).click();
        Thread.sleep(5000);
        driver.findElement(MobileBy.id("com.google.android.apps.tasks:id/add_task_title")).sendKeys("Complete Activity with Google Tasks");
        driver.findElement(MobileBy.id("com.google.android.apps.tasks:id/add_task_done")).click();

        driver.findElement(MobileBy.id("com.google.android.apps.tasks:id/tasks_fab")).click();
        Thread.sleep(5000);
        driver.findElement(MobileBy.id("com.google.android.apps.tasks:id/add_task_title")).sendKeys("Complete Activity with Google Keep");
        driver.findElement(MobileBy.id("com.google.android.apps.tasks:id/add_task_done")).click();

        driver.findElement(MobileBy.id("com.google.android.apps.tasks:id/tasks_fab")).click();
        Thread.sleep(5000);
        driver.findElement(MobileBy.id("com.google.android.apps.tasks:id/add_task_title")).sendKeys("Complete the second Activity Google Keep");
        driver.findElement(MobileBy.id("com.google.android.apps.tasks:id/add_task_done")).click();

        Thread.sleep(5000);
        List<MobileElement> taskSize = driver.findElementsById("com.google.android.apps.tasks:id/task_name");
        System.out.println("The number of tasks added are : "+taskSize.size() );
        Assert.assertEquals(3, taskSize.size());

    }

    @AfterClass
    public void afterClass() {

        driver.quit();
    }
}