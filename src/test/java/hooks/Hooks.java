package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ExtentManager;
import utils.DriverFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Hooks {
    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    WebDriver driver;

    @Before
    public void setUp(Scenario scenario) {
        // Just initialize the driver – browser is picked up from system property
        DriverFactory.initializeDriver();
        driver = DriverFactory.getDriver();

        ExtentTest extentTest = extent.createTest(scenario.getName());
        test.set(extentTest);
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            // Take screenshot
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotPath = "test-output/screenshots/" + scenario.getName().replaceAll(" ", "_") + ".png";
            File dest = new File(screenshotPath);
            try {
                Files.createDirectories(dest.getParentFile().toPath());
                Files.copy(src.toPath(), dest.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING); // overwrite if exists
                test.get().fail("Scenario Failed").addScreenCaptureFromPath(screenshotPath);
                scenario.attach(Files.readAllBytes(dest.toPath()), "image/png", "Failure Screenshot");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            test.get().pass("Scenario Passed");
        }

        DriverFactory.quitDriver();
        extent.flush();
    }
}
