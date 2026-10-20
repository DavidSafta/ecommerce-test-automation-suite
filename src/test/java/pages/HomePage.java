import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class HomePage {

    @FindBy(id = "search_product")
    private WebElement searchInput;

    @FindBy(id = "submit_search")
    private WebElement searchButton;

    @FindBy(css = ".features_items")
    private WebElement productsContainer;

    @FindBy(css = ".features_items .title")
    private WebElement resultsTitle;

    @FindBy(css = ".features_items .product-image-wrapper")
    private List<WebElement> searchResults;

    public void openPage() {
        open("https://automationexercise.com/products");
        acceptConsentIfPresent();
        PageFactory.initElements(getWebDriver(), this);
    }

    private void acceptConsentIfPresent() {
        SelenideElement consentButton = $("button.fc-cta-consent");
        try {
            consentButton.shouldBe(visible, Duration.ofSeconds(5)).click();
            $(".fc-consent-root").should(disappear, Duration.ofSeconds(5));
        } catch (AssertionError ignored) {
            // bannerul nu a apărut, continuăm normal
        }
    }

    public void searchProduct(String productName) {
        searchInput.sendKeys(productName);
        searchButton.click();
    }

    public boolean isProductsContainerDisplayed() {
        return productsContainer.isDisplayed();
    }

    public boolean hasSearchResults() {
        return resultsTitle.getText().equalsIgnoreCase("Searched Products")
                && !searchResults.isEmpty();
    }
    public void addFirstProductToCart() {
        $(".features_items .add-to-cart").click();
        $("#cartModal").shouldBe(visible, Duration.ofSeconds(10));
    }

    public String getFirstProductName() {
        return getWebDriver().findElement(
                org.openqa.selenium.By.cssSelector(".features_items .productinfo p")).getText();
    }
}