import com.codeborne.selenide.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static org.junit.Assert.assertTrue;

public class FirstSmokeTest {

    @Before
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
    }

    @Test
    public void productsSectionShouldBeVisible() {
        HomePage homePage = new HomePage();
        homePage.openPage();
        assertTrue(homePage.isProductsContainerDisplayed());
    }

    @After
    public void tearDown() {
        closeWebDriver();
    }
}