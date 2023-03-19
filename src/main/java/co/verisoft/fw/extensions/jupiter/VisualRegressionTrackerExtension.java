package co.verisoft.fw.extensions.jupiter;

import co.verisoft.fw.store.StoreManager;
import co.verisoft.fw.store.StoreType;
import co.verisoft.fw.visual_regression_tracker.VisualRegressionTrackerSingleton;
import io.visual_regression_tracker.sdk_java.VisualRegressionTracker;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.IOException;

/**
 * Extension to compare screenshots in pixels with VisualRegressionTracker
 * enable to ignore areas which do not need to be compared, sending a comparison level in pixels e.c.
 *
 * <b>for this feature you need:</b>
 * 1. first you have to install docker desktop for visual-regression-tracker on your machine
 *    in this link: <a href="https://github.com/Visual-Regression-Tracker/Visual-Regression-Tracker"></a> go to setup, download docker and complete the manual steps.
 * 2. once you finish installations, you need to fill the resources/visual-regression-tracker.properties values according the values you got during the installations
 * after these steps, you can use this extension in every class you want to use screens compare with VisualRegressionTracker.
 *
 * <b>Pay attention:</b> when using VRT - the first running is important to take a baseline image and store it in VRT build, it happens automatically
 * and there is no image compare. in the second running and onward images will be compared with the baseline image
 *
 * this class creates the VRT object based on the Singleton VisualRegressionTrackerSingleton class to init the VRT object just once in every running.
 * You can see an example of how to use this extension and take screenshots compare in this project path file: src/main/test/java/visualRegressionTracker/VisualRegressionTrackerTest
 *
 * @author Efrat Cohen
 * @since March 2023
 */
public class VisualRegressionTrackerExtension implements BeforeEachCallback, AfterEachCallback {

    /**
     * create a VRT instance from the singleton.getInstance() to init this object once in very running
     * store the instance in StoreManager global parameter to enable this object in other functions here and in the tests that use this extension
     * start the vrt
     *
     * @param context - ExtensionContext to store the VRT object
     * @throws IOException - IOException
     * @throws InterruptedException - InterruptedException
     */
    @Override
    public void beforeEach(ExtensionContext context) throws IOException, InterruptedException {
        VisualRegressionTracker vrt = new VisualRegressionTracker(VisualRegressionTrackerSingleton.getInstance());
        StoreManager.getStore(StoreType.GLOBAL).putValueInStore("VRT_instance", vrt);
        vrt.start();
    }


    /**
     * get the VRT instance from StoreManager
     * stop the VRT
     *
     * @param context - ExtensionContext
     * @throws IOException - IOException
     * @throws InterruptedException - InterruptedException
     */
    @Override
    public void afterEach(ExtensionContext context) throws IOException, InterruptedException {
        VisualRegressionTracker vrt = StoreManager.getStore(StoreType.GLOBAL).getValueFromStore("VRT_instance");
        vrt.stop();
    }
}
