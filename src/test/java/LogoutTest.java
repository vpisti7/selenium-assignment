import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.assertEquals;

public class LogoutTest extends BaseTest {

    @Test
    public void shouldLogoutAfterSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver, wait);
        LogoutPage logoutPage = new LogoutPage(driver, wait);

        loginPage.openLoginPage(baseUrl);
        loginPage.enterEmail(LoadConfig.getEmail());
        loginPage.enterPassword(LoadConfig.getPassword());
        loginPage.submit();

        wait.until(ExpectedConditions.urlContains("route=account/account"));
        assertEquals("My Account", loginPage.getMyAccountHeadingText());

        logoutPage.logoutFromNavbar();

        wait.until(ExpectedConditions.urlContains("route=account/logout"));
        assertEquals("Account Logout", logoutPage.getLogoutHeadingText());
    }
}
