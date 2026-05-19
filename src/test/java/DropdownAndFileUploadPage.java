import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DropdownAndFileUploadPage extends BasePage {
    private static final String PRODUCT_WITH_UPLOAD_PATH = "index.php?route=product/product&product_id=42";

    private static final By PRODUCT_OPTION_SELECT = By.id("input-option217");
    private static final By UPLOAD_BUTTON = By.id("button-upload222");
    private static final By FILE_INPUT = By.cssSelector("#form-upload input[name='file']");

    public DropdownAndFileUploadPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void openProductWithUploadPage(String baseUrl) {
        open(baseUrl + PRODUCT_WITH_UPLOAD_PATH);
    }

    public void selectProductOptionByVisibleText(String optionText) {
        Select select = new Select(waitForVisible(PRODUCT_OPTION_SELECT));
        select.selectByVisibleText(optionText);
    }

    public String getSelectedProductOptionText() {
        Select select = new Select(waitForVisible(PRODUCT_OPTION_SELECT));
        return select.getFirstSelectedOption().getText();
    }

    public void uploadFile(String filePath) {
        click(UPLOAD_BUTTON);
        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(FILE_INPUT));
        fileInput.sendKeys(filePath);
    }

    public String acceptUploadAlertAndGetText() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        alert.accept();
        return alertText;
    }
}
