import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogoutPage extends BasePage {
    private static final By MY_ACCOUNT_DROPDOWN = By.cssSelector("#top-links a[title='My Account']");
    private static final By LOGOUT_LINK = By.xpath("//ul[contains(@class,'dropdown-menu')]//a[normalize-space()='Logout']");
    private static final By LOGOUT_HEADING = By.cssSelector("#content h1");

    public LogoutPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void openMyAccountDropdown() {
        click(MY_ACCOUNT_DROPDOWN);
    }

    public void clickLogout() {
        click(LOGOUT_LINK);
    }

    public void logoutFromNavbar() {
        openMyAccountDropdown();
        clickLogout();
    }

    public String getLogoutHeadingText() {
        return getText(LOGOUT_HEADING);
    }
}
