import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProductPagesTest extends BaseTest {

    @Test
    public void shouldShowExpectedHeadingsPricesAndTitlesOnProductPages() {
        ProductPages productPages = new ProductPages(driver, wait);

        for (int i = 0; i < ProductPages.PRODUCT_PATHS.length; i++) {
            productPages.openProductPage(baseUrl, i);

            assertEquals(ProductPages.EXPECTED_HEADINGS[i], productPages.getProductHeadingText());
            assertEquals(ProductPages.EXPECTED_PRICES[i], productPages.getProductPriceText());
            assertEquals(ProductPages.EXPECTED_TITLES[i], productPages.getPageTitle());
        }
    }
}
