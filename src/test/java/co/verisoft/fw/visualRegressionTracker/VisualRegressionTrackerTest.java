package co.verisoft.fw.visualRegressionTracker;

import co.verisoft.fw.extensions.jupiter.VisualRegressionTrackerExtension;
import co.verisoft.fw.store.StoreManager;
import co.verisoft.fw.store.StoreType;
import co.verisoft.fw.visual_regression_tracker.VRTResponseStatusEnum;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.visual_regression_tracker.sdk_java.TestRunResult;
import io.visual_regression_tracker.sdk_java.VisualRegressionTracker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.IOException;
import java.net.Inet4Address;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * class with test example of how to use VisualRegressionTrackerExtension to compare screens with VRT
 *
 * @author Efrat Cohen
 * @since March 2023
 */
// Required extension
@ExtendWith(VisualRegressionTrackerExtension.class)
public class VisualRegressionTrackerTest {

    /**
     * check if there is an instance of VRT running on localhost
     *
     * @return true if running, otherwise return false
     */
    private boolean checkIfVrtIsRunningOnLocalhost() {
        // Set the URL of your VRT instance - usually localhost:8080
        String url = "http://localhost:num/";
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        try {
            HttpResponse response = client.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            // Check if the response indicates that VRT is running
            return (statusCode == 200);
        } catch (Exception e) {
            // If an exception is thrown, VRT is not running
            return false;
        }
    }


    @Test
    @Disabled
    public void UsingVRTTestExample() throws InterruptedException, IOException {

        // Check if VRT is running on localhost if not - skip the test
        boolean isVrtRunning = checkIfVrtIsRunningOnLocalhost();
        Assumptions.assumeTrue(isVrtRunning);

        // Use any driver you need, here is simple chrome driver example.
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        // Navigate to URL you want to take screenshot to compare with VRT
        driver.get("site.url");

        // Get the VisualRegressionTracker instance from StoreManager
        VisualRegressionTracker vrt = StoreManager.getStore(StoreType.GLOBAL).getValueFromStore("VRT_instance");

        // Track new image in current page to compare with the baseline image name
        TestRunResult result = vrt.track(
                // Send current image page name - in the same build we can use several images names.
                // The image name you send here means that this image baseline will compare with
                "page_name",
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64));

        // Here some examples for assert the track image result - you can use whatever you need in your current project
        // Check if the new image as no diff with the baseline image - the result track response status is OK
        Assertions.assertEquals(result.getTestRunResponse().getStatus().name(), VRTResponseStatusEnum.OK.toString());
        // Example of how to check if the result track response status is UNRESOLVED - image has diff
        Assertions.assertNotSame(result.getTestRunResponse().getStatus().name(), VRTResponseStatusEnum.UNRESOLVED.toString());

        // Navigate to another URL or do whatever you need to navigate to another page you need to compare
        driver.get("site.url");

        // Track new image in current page to compare with the baseline image name
        result = vrt.track(
                // Send current image page name
                "another_page_name",
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64));

        // Check if the new image as no diff with the baseline image - the result track response status is OK
        Assertions.assertEquals(result.getTestRunResponse().getStatus().name(), VRTResponseStatusEnum.OK.toString());
        // Example of how to check if the result track response status is UNRESOLVED - image has diff
        Assertions.assertNotSame(result.getTestRunResponse().getStatus().name(), VRTResponseStatusEnum.UNRESOLVED.toString());

        // Quit the browser
        driver.quit();
    }

}
