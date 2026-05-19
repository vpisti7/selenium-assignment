import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected String baseUrl;
    protected String browser;

    private static final String DEFAULT_REMOTE_URL = "http://selenium-hub:4444/wd/hub";
    private static final String DEFAULT_BASE_URL = "";
    private static final String SCREENSHOT_DIRECTORY = "build/screenshots";

    @Rule
    public TestRule browserRule = new TestRule() {
        @Override
        public Statement apply(final Statement base, Description description) {
            return new Statement() {
                @Override
                public void evaluate() throws Throwable {
                    List<Throwable> failures = new ArrayList<Throwable>();

                    for (String configuredBrowser : LoadConfig.getBrowsers()) {
                        browser = configuredBrowser;

                        try {
                            setUpBrowser(configuredBrowser);
                            base.evaluate();
                        } catch (Throwable throwable) {
                            takeScreenshotOnFailure(description, configuredBrowser, throwable);
                            failures.add(new AssertionError("Test failed on browser: " + configuredBrowser, throwable));
                        } finally {
                            tearDownBrowser();
                        }
                    }

                    if (!failures.isEmpty()) {
                        AssertionError error = new AssertionError("Test failed on " + failures.size() + " browser(s).");
                        for (Throwable failure : failures) {
                            error.addSuppressed(failure);
                        }
                        throw error;
                    }
                }
            };
        }
    };

    private void setUpBrowser(String configuredBrowser) throws Exception {
        baseUrl = LoadConfig.get("baseUrl", DEFAULT_BASE_URL);

        driver = new RemoteWebDriver(
                new URL(LoadConfig.get("seleniumRemoteUrl", DEFAULT_REMOTE_URL)),
                createCapabilities(configuredBrowser)
        );
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1800, 960));

        wait = new WebDriverWait(driver, 10);
    }

    private void tearDownBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private Capabilities createCapabilities(String configuredBrowser) {
        if ("chrome".equalsIgnoreCase(configuredBrowser)) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments(Arrays.asList(
                    "--headless",
                    "--disable-gpu",
                    "--no-sandbox",
                    "--disable-dev-shm-usage",
                    "--window-size=1800,960"
            ));
            return options;
        }

        if ("firefox".equalsIgnoreCase(configuredBrowser)) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments(Arrays.asList("-headless", "--width=1800", "--height=960"));
            options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            return options;
        }

        throw new IllegalArgumentException("Unsupported browser in config.properties: " + configuredBrowser);
    }

    private void takeScreenshotOnFailure(Description description, String configuredBrowser, Throwable failure) {
        if (!(driver instanceof TakesScreenshot)) {
            return;
        }

        try {
            File screenshotDirectory = new File(SCREENSHOT_DIRECTORY);
            if (!screenshotDirectory.exists()) {
                screenshotDirectory.mkdirs();
            }

            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File targetFile = new File(screenshotDirectory, createScreenshotFileName(description, configuredBrowser));

            Files.copy(sourceFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Screenshot saved: " + targetFile.getAbsolutePath());
        } catch (IOException screenshotException) {
            failure.addSuppressed(screenshotException);
        }
    }

    private String createScreenshotFileName(Description description, String configuredBrowser) {
        String className = description.getClassName() == null ? "UnknownClass" : description.getClassName();
        String methodName = description.getMethodName() == null ? "unknownTest" : description.getMethodName();

        return sanitizeFileName(className + "-" + methodName + "-" + configuredBrowser + ".png");
    }

    private String sanitizeFileName(String fileName) {
        return fileName.replaceAll("[^a-zA-Z0-9._-]", "_");
    }
}
