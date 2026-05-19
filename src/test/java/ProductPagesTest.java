import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StaticPagesTest extends BaseTest {

    @Test
    public void shouldShowExpectedHeadingsPricesAndTitlesOnProductPages() {
        StaticPages staticPages = new StaticPages(driver, wait);

        for (int i = 0; i < StaticPages.PRODUCT_PATHS.length; i++) {
            staticPages.openProductPage(baseUrl, i);

            assertEquals(StaticPages.EXPECTED_HEADINGS[i], staticPages.getProductHeadingText());
            assertEquals(StaticPages.EXPECTED_PRICES[i], staticPages.getProductPriceText());
            assertEquals(StaticPages.EXPECTED_TITLES[i], staticPages.getProductTitleText());
        }
    }
}
