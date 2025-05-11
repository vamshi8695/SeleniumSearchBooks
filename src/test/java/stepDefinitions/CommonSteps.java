package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;
import pages.HomePage;
import utils.ConfigReader;
import utils.DriverFactory;

public class CommonSteps {

    WebDriver driver;
    HomePage homePage;

    @Given("the user is on the bookstore homepage")
    public void user_on_homepage() {
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("baseURL"));
        homePage = new HomePage(driver);
        //JENKINS
    }

    @When("the user enters a valid username")
    public void enter_valid_username() {
        homePage.enterUsername(ConfigReader.get("username"));
    }

    @When("the user enters a valid password")
    public void enter_valid_password() {
        homePage.enterPassword(ConfigReader.get("password"));
    }

    @When("clicks the login button")
    public void click_login_button() {
        homePage.clickLogin();
    }

    @Then("the user should be redirected to the products page")
    public void verify_successful_login() {
        Assert.assertTrue("User was not redirected to products page", homePage.isLoginSuccessful());
    }
}
