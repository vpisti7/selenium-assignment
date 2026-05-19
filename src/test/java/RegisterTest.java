import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class RegisterTest extends BaseTest {

    @Test
    public void shouldRegisterWithUniqueEmail() {
        RegisterPage registerPage = new RegisterPage(driver, wait);
        String password = LoadConfig.getPassword();
        String uniqueEmail = "test-" + UUID.randomUUID().toString() + "@example.com";

        registerPage.openRegisterPage(baseUrl);
        registerPage.enterFirstName("Test");
        registerPage.enterLastName("User");
        registerPage.enterEmail(uniqueEmail);
        registerPage.enterTelephone("06123456789");
        registerPage.enterPassword(password);
        registerPage.enterPasswordConfirm(password);
        registerPage.selectNewsletter();
        registerPage.acceptAgreement();
        registerPage.submit();

        wait.until(ExpectedConditions.urlContains("route=account/success"));
        assertEquals("Your Account Has Been Created!", registerPage.getSuccessHeadingText());
    }
}
