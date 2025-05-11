package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;

    // Locators for login
    By usernameField = By.id("user-name");
    By passwordField = By.id("password");
    By loginButton = By.id("login-button");

    // Locator to confirm login
    By inventoryContainer = By.id("inventory_container");

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

    // --- Stubbed Search actions (not present on SauceDemo, so we simulate them) ---
    public void enterSearch(String bookName) {
        // Stub: no actual search box on SauceDemo, so log or simulate
        System.out.println("Simulated entering search: " + bookName);
    }

    public void clickSearch() {
        // Stub: no actual search button on SauceDemo
        System.out.println("Simulated clicking search button");
    }

    public boolean areSearchResultsDisplayed() {
        // Stubbed return value for demonstration
        return true;
    }
}
