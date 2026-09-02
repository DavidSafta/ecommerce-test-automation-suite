import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class HomePage {

    @FindBy(id = "search_product")
    private WebElement searchInput;

    @FindBy(id = "submit_search")
    private WebElement searchButton;

    @FindBy(css = ".features_items")
    private WebElement productsContainer;

    public void openPage() {
        open("https://automationexercise.com");
        PageFactory.initElements(getWebDriver(), this);
    }

    public void searchProduct(String productName) {
        searchInput.sendKeys(productName);
        searchButton.click();
    }

    public boolean isProductsContainerDisplayed() {
        return productsContainer.isDisplayed();
    }
}