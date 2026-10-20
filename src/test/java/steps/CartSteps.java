import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.Assert.assertTrue;

public class CartSteps {

    private final HomePage homePage = new HomePage();
    private final CartPage cartPage = new CartPage();
    private String addedProductName;

    @When("the user adds the first product to the cart")
    public void the_user_adds_the_first_product_to_the_cart() {
        addedProductName = homePage.getFirstProductName();
        homePage.addFirstProductToCart();
    }

    @Then("the product should appear in the cart")
    public void the_product_should_appear_in_the_cart() {
        cartPage.openPage();
        assertTrue(cartPage.cartContains(addedProductName));
    }
}