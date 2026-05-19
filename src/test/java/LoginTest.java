import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test
    public void shouldLoginWithConfigCredentials() {
        LoginPage loginPage = new LoginPage(driver, wait);

        loginPage.openLoginPage(baseUrl);
        loginPage.enterEmail(LoadConfig.getEmail());
        loginPage.enterPassword(LoadConfig.getPassword());
        loginPage.submit();

        wait.until(ExpectedConditions.urlContains("route=account/account"));
        assertEquals("My Account", loginPage.getMyAccountHeadingText());
    }
}
