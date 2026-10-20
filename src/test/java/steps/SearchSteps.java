import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.Assert.assertTrue;

public class SearchSteps {

    private final HomePage homePage = new HomePage();

    @Given("the user is on the home page")
    public void the_user_is_on_the_home_page() {
        homePage.openPage();
    }

    @When("the user searches for {string}")
    public void the_user_searches_for(String productName) {
        homePage.searchProduct(productName);
    }

    @Then("search results should be displayed")
    public void search_results_should_be_displayed() {
        assertTrue(homePage.hasSearchResults());
    }
}