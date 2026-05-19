import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;

public class DropdownAndFileUploadTest extends BaseTest {

    @Test
    public void shouldSelectProductOptionFromDropdown() {
        DropdownAndFileUploadPage page = new DropdownAndFileUploadPage(driver, wait);

        page.openProductWithUploadPage(baseUrl);
        page.selectProductOptionByVisibleText("Blue (+$3.60)");

        assertTrue(page.getSelectedProductOptionText().contains("Blue"));
    }

    @Test
    public void shouldUploadImageFile() {
        DropdownAndFileUploadPage page = new DropdownAndFileUploadPage(driver, wait);

        page.openProductWithUploadPage(baseUrl);
        page.uploadFile(getUploadImagePath());

        assertTrue(page.acceptUploadAlertAndGetText().contains("Your file was successfully uploaded"));
    }

    private String getUploadImagePath() {
        File[] candidates = new File[]{
                new File("test-files/test.png"),
                new File("tests/selenium-assignment/test-files/test.png"),
                new File("/home/selenium/tests/selenium-assignment/test-files/test.png")
        };

        for (File candidate : candidates) {
            if (candidate.exists()) {
                return candidate.getAbsolutePath();
            }
        }

        return candidates[0].getAbsolutePath();
    }
}
