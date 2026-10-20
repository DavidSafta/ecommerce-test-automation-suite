import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ApiSteps {

    private static final String BASE_URL = "https://api.github.com";
    private Response response;

    private RequestSpecification request() {
        RequestSpecification spec = given()
                .header("Accept", "application/vnd.github+json")
                .header("X-GitHub-Api-Version", "2022-11-28");

        String token = System.getenv("GITHUB_TOKEN");
        if (token != null && !token.isEmpty()) {
            spec = spec.header("Authorization", "Bearer " + token);
        }
        return spec;
    }

    @Given("a GET request is sent to {string}")
    public void a_get_request_is_sent_to(String endpoint) {
        response = request().when().get(BASE_URL + endpoint);
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.statusCode());
    }

    @And("the response field {string} should be {string}")
    public void the_response_field_should_be(String fieldPath, String expectedValue) {
        assertEquals(expectedValue, response.jsonPath().getString(fieldPath));
    }

    @And("the response should contain a field {string}")
    public void the_response_should_contain_a_field(String fieldPath) {
        assertNotNull(response.jsonPath().get(fieldPath));
    }
}