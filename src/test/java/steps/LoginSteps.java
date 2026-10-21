import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class LoginSteps {

    private final LoginPage loginPage = new LoginPage();

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        loginPage.openPage();
    }

    @When("the user logs in with email {string} and password {string}")
    public void the_user_logs_in_with(String email, String password) {
        loginPage.login(email, password);
    }

    @Then("the login error message should be {string}")
    public void the_login_error_message_should_be(String expectedMessage) {
        assertEquals(expectedMessage, loginPage.getLoginError());
    }
}