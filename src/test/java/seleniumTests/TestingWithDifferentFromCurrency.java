package seleniumTests;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestingWithDifferentFromCurrency {

    private WebDriver webDriver;

    private static ExtentReports extent;
    private static ExtentSparkReporter spark;
    private static ExtentTest test;

    @BeforeAll
    static void beforeAll() {
        extent = new ExtentReports();
        spark = new ExtentSparkReporter("target/Spark/TestingWithDifferentFromCurr.html");
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("Testing WithDifferent From Currencies");
        spark.config().setReportName("Extent report for different currency");
        extent.attachReporter(spark);
    }

    @BeforeEach
    void setUp() {
        webDriver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        webDriver.quit();
    }

    @AfterAll
    static void afterAll() {
        extent.flush();//to flush data into the report
    }

    @ParameterizedTest(name = "Testing with From currency: {0}")
    @MethodSource("fromCurrencies")
    void testWithDifferentFromCurrency(String fromCurrency) {
        //create a test node in the report
        test = extent.createTest("Different Currency test for currency: " + fromCurrency);
        //create a test step node in the report
        test.pass("Starting test with From currency:" + fromCurrency);
        webDriver.get("http://localhost:8080/");
        test.pass("URL is loaded");
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement amountField = webDriver.findElement(By.cssSelector("#amount"));
        amountField.sendKeys("100");
        WebElement fromCurrencyElement = webDriver.findElement(By.cssSelector("#from"));
        Select fromSelect = new Select(fromCurrencyElement);
        fromSelect.selectByValue(fromCurrency);
        WebElement toCurrencyElement = webDriver.findElement(By.cssSelector("#to"));
        Select toSelect = new Select(toCurrencyElement);
        toSelect.selectByValue("USD");
        test.info("Values are entered");
        //convert btn
        WebElement convertBtn = webDriver.findElement(By.cssSelector("#convertCurrency"));
        convertBtn.click();
        test.info("Values are submited to be converted");

//        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        // Verify the result and conversion rate
        WebElement resultInput = webDriver.findElement(By.cssSelector("#result"));
        WebElement conversionRateInput = webDriver.findElement(By.cssSelector("#conversionRate"));
        // waiting time so api call is done and page is updated with maximum of 10s https://www.selenium.dev/documentation/webdriver/waits/
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(conversionRateInput, "value", "")));

        try {
            assertNotEquals("", conversionRateInput.getAttribute("value"));
            test.pass("converted successfully");
        } catch (AssertionError e) {
            test.fail("failed to convert: " + e.getMessage());
            throw e;
        }
        //assertions
        String conversionRate = conversionRateInput.getAttribute("value");
        if (conversionRate.contains(","))
            conversionRate = conversionRate.replace(",", ".");
        try {
            assertEquals(100 * Double.valueOf(conversionRate), Double.valueOf(resultInput.getAttribute("value")));
            test.log(Status.PASS, "Test passed for from currency: " + fromCurrency);
        } catch (AssertionError e) {
            test.fail("Test failed to convert from:" + fromCurrency + ", (might be just floating point errors:)\n" + e.getMessage());
            throw e;
        }
    }

    private static List<String> fromCurrencies() {
        // You can provide different currencies here
        return Arrays.asList("EUR", "USD", "ILS", "SHP");
    }
}

