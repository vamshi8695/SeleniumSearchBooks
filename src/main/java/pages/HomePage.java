package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    WebDriver driver;

    // Locators for login
    By usernameField = By.id("user-name");
    By passwordField = By.id("password");
    By loginButton = By.id("login-button");

    //hi git
    // Locator to confirm login
    By inventoryContainer = By.id("inventory_container");

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-input")));

    // Mock locators for search (not present on SauceDemo)
    By searchInput = By.id("search-input"); // Custom/mock locator
    By searchButton = By.id("search-button"); // Custom/mock locator

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // --- Login actions ---
    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public boolean isLoginSuccessful() {
        return driver.getCurrentUrl().contains("inventory") &&
               driver.findElements(inventoryContainer).size() > 0;
    }

    // --- Search actions (mock implementation) ---
    public void enterSearch(String bookName) {
        driver.findElement(searchInput).sendKeys(bookName); // May throw error if element doesn't exist
    }

    public void clickSearch() {
        driver.findElement(searchButton).click(); // May throw error if element doesn't exist
    }

    public boolean areSearchResultsDisplayed() {
        // Mock check: adapt this to your actual search result behavior or element
        return driver.getPageSource().toLowerCase().contains("results")
            || driver.getPageSource().toLowerCase().contains("no results");
    }
}
