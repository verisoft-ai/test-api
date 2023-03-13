package co.verisoft.fw.visualRegressionTracker;

import co.verisoft.fw.extensions.jupiter.VisualRegressionTrackerExtension;
import co.verisoft.fw.visual_regression_tracker.VRTParameterResolver;
import co.verisoft.fw.visual_regression_tracker.VRTResponseStatusEnum;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.visual_regression_tracker.sdk_java.TestRunResult;
import io.visual_regression_tracker.sdk_java.VisualRegressionTracker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.IOException;
import org.junit.jupiter.api.extension.ExtensionContext.Store;

/**
 * class with test example of how to use VisualRegressionTrackerExtension to compare screens with VRT
 *
 * @author Efrat Cohen
 * @since March 2023
 */
// Required extensions
@ExtendWith({VisualRegressionTrackerExtension.class, VRTParameterResolver.class})
public class VisualRegressionTrackerTest {

    @Test
    public void UsingVRTTestExample(ExtensionContext context) throws InterruptedException, IOException {
        // Use any driver you need, here is simple chrome driver example
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        // Navigate to URL you want to take screenshot to compare with VRT
        driver.get("site.url");

        // Get the VisualRegressionTracker instance from ExtensionContext store
        Store store = context.getStore(ExtensionContext.Namespace.GLOBAL);
        VisualRegressionTracker vrt = (VisualRegressionTracker) store.get("VRT_instance");

        // Track new image in current page to compare with the baseline image name
        TestRunResult result = vrt.track(
                // Send current image page name - in the same build we can use several images names.
                // The image name you send here means that this image baseline will compare with
                "page_name",
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64));

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
