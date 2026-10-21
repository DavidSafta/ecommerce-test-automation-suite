import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public abstract class BasePage {

    protected void acceptConsentIfPresent() {
        SelenideElement consentButton = $("button.fc-cta-consent");
        try {
            consentButton.shouldBe(visible, Duration.ofSeconds(5)).click();
            $(".fc-consent-root").should(disappear, Duration.ofSeconds(5));
        } catch (AssertionError ignored) {
            // bannerul nu a apărut, continuăm normal
        }
    }
}