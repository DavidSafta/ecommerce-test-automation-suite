import com.codeborne.selenide.Configuration;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class Hooks {

    @Before
    public void setUp() {
        String remoteUrl = System.getenv("SELENIUM_REMOTE_URL");

        if (remoteUrl != null && !remoteUrl.isEmpty()) {
            Configuration.remote = remoteUrl;
        }

        Configuration.browser = "chrome";
        Configuration.timeout = 15000;
    }

    @After
    public void tearDown() {
        closeWebDriver();
    }
}