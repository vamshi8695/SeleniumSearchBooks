package stepDefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import utils.DriverFactory;

public class SearchBookSteps {

    WebDriver driver = DriverFactory.getDriver();
    HomePage homePage = new HomePage(driver);

    @When("the user enters {string} in the search box")
    public void user_enters_book_name(String bookName) {
        homePage.enterSearch(bookName);
    }

    @When("clicks the search button")
    public void user_clicks_search() {
        homePage.clickSearch();
    }

    @Then("the book results should be displayed")
    public void verify_book_results() {
        Assert.assertTrue("No search results displayed", homePage.areSearchResultsDisplayed());
    }
}
