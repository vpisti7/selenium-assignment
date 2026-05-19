import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StaticPages extends BasePage {
    public static final String[] PRODUCT_PATHS = {
            "index.php?route=product/product&product_id=40",
            "index.php?route=product/product&product_id=43",
            "index.php?route=product/product&product_id=42",
            "index.php?route=product/product&product_id=30",
            "index.php?route=product/product&path=57&product_id=49"
    };

    public static final String[] EXPECTED_HEADINGS = {
            "iPhone",
            "MacBook",
            "Apple Cinema 30\"",
            "Canon EOS 5D",
            "Samsung Galaxy Tab 10.1"
    };

    public static final String[] EXPECTED_PRICES = {
            "$123.20",
            "$602.00",
            "$110.00",
            "$98.00",
            "$241.99"
    };

    public static final String[] EXPECTED_TITLES = {
            "iPhone",
            "MacBook",
            "Apple Cinema 30",
            "sdf",
            "Samsung Galaxy Tab 10.1"
    };

    public static final By PRODUCT_HEADING = By.xpath("//div[@id='content']//div[contains(@class,'col-sm-4')]//h1");
    public static final By PRODUCT_PRICE = By.xpath("//div[@id='content']//div[contains(@class,'col-sm-4')]//ul[contains(@class,'list-unstyled')]//h2");

    public StaticPages(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void openProductPage(String baseUrl, int productIndex) {
        open(baseUrl + PRODUCT_PATHS[productIndex]);
    }

    public String getProductHeadingText() {
        return getText(PRODUCT_HEADING);
    }

    public String getProductPriceText() {
        return getText(PRODUCT_PRICE);
    }

    public String getProductTitleText() {
        return getTitle();
    }
}
