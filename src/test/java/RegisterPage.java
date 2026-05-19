import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends BasePage {
    private static final String REGISTER_PATH = "index.php?route=account/register";

    private static final By FIRST_NAME_INPUT = By.id("input-firstname");
    private static final By LAST_NAME_INPUT = By.id("input-lastname");
    private static final By EMAIL_INPUT = By.id("input-email");
    private static final By TELEPHONE_INPUT = By.id("input-telephone");
    private static final By PASSWORD_INPUT = By.id("input-password");
    private static final By PASSWORD_CONFIRM_INPUT = By.id("input-confirm");
    private static final By NEWSLETTER_RADIO = By.name("newsletter");
    private static final By AGREE_CHECKBOX = By.name("agree");
    private static final By SUBMIT_BUTTON = By.cssSelector("input[type='submit']");
    private static final By SUCCESS_HEADING = By.cssSelector("#content h1");

    public RegisterPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void openRegisterPage(String baseUrl) {
        open(baseUrl + REGISTER_PATH);
    }

    public void enterFirstName(String firstName) {
        type(FIRST_NAME_INPUT, firstName);
    }

    public void enterLastName(String lastName) {
        type(LAST_NAME_INPUT, lastName);
    }

    public void enterEmail(String email) {
        type(EMAIL_INPUT, email);
    }

    public void enterTelephone(String telephone) {
        type(TELEPHONE_INPUT, telephone);
    }

    public void enterPassword(String password) {
        type(PASSWORD_INPUT, password);
    }

    public void enterPasswordConfirm(String passwordConfirm) {
        type(PASSWORD_CONFIRM_INPUT, passwordConfirm);
    }

    public void selectNewsletter() {
        click(NEWSLETTER_RADIO);
    }

    public void acceptAgreement() {
        click(AGREE_CHECKBOX);
    }

    public void submit() {
        click(SUBMIT_BUTTON);
    }

    public String getSuccessHeadingText() {
        return getText(SUCCESS_HEADING);
    }

}
