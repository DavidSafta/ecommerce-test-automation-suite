import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class CartPage {

    @FindBy(css = "td.cart_description h4 a")
    private List<WebElement> productNames;

    public void openPage() {
        open("https://automationexercise.com/view_cart");
        PageFactory.initElements(getWebDriver(), this);
    }

    public boolean cartContains(String productName) {
        return productNames.stream()
                .anyMatch(e -> e.getText().equalsIgnoreCase(productName));
    }
}