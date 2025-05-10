package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void initializeDriver() {
        String browser = System.getProperty("browser"); // gets -Dbrowser=chrome etc. from command line

        if (browser == null || browser.isEmpty()) {
            throw new RuntimeException("Browser name is not specified. Use -Dbrowser=chrome|firefox|edge");
        }

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Invalid browser: " + browser);
        }

        driver.manage().window().maximize();
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null; // ensure it can be re-initialized
        }
    }
}
