package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.MutableCapabilities;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void initializeDriver() {
        String browser = System.getProperty("browser", "chrome");
        String cloud = System.getProperty("cloud", "false");
        String bsUser = System.getProperty("bs_user");
        String bsKey = System.getProperty("bs_key");

        try {
            if (cloud.equalsIgnoreCase("true")) {
                MutableCapabilities capabilities = new MutableCapabilities();

                if (browser.equalsIgnoreCase("chrome")) {
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.setPlatformName("Windows 11");
                    chromeOptions.setBrowserVersion("latest");
                    capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                } else if (browser.equalsIgnoreCase("firefox")) {
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setPlatformName("Windows 11");
                    firefoxOptions.setBrowserVersion("latest");
                    capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
                }

                // BrowserStack-specific options
                Map<String, Object> bstackOptions = new HashMap<>();
                bstackOptions.put("os", "Windows");
                bstackOptions.put("osVersion", "11");
                bstackOptions.put("projectName", "BookstoreTests");
                bstackOptions.put("buildName", "CrossBrowser-Build-1");
                bstackOptions.put("sessionName", "CrossBrowserTest - " + browser);

                capabilities.setCapability("bstack:options", bstackOptions);

                driver.set(new RemoteWebDriver(
                        new URL("https://" + bsUser + ":" + bsKey + "@hub.browserstack.com/wd/hub"),
                        capabilities));
            } else {
                if (browser.equalsIgnoreCase("chrome")) {
                    driver.set(new ChromeDriver());
                } else if (browser.equalsIgnoreCase("firefox")) {
                    driver.set(new FirefoxDriver());
                } else {
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
                }
            }

            getDriver().manage().window().maximize();

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize remote driver: " + e.getMessage(), e);
        }
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
