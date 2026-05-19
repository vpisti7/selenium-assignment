import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
    private static final String LOGIN_PATH = "index.php?route=account/login";

    private static final By EMAIL_INPUT = By.id("input-email");
    private static final By PASSWORD_INPUT = By.id("input-password");
    private static final By SUBMIT_BUTTON = By.cssSelector("input[type='submit']");
    private static final By MY_ACCOUNT_HEADING = By.cssSelector("#content h2");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void openLoginPage(String baseUrl) {
        open(baseUrl + LOGIN_PATH);
    }

    public void enterEmail(String email) {
        type(EMAIL_INPUT, email);
    }

    public void enterPassword(String password) {
        type(PASSWORD_INPUT, password);
    }

    public void submit() {
        click(SUBMIT_BUTTON);
    }

    public String getMyAccountHeadingText() {
        return getText(MY_ACCOUNT_HEADING);
    }
}
