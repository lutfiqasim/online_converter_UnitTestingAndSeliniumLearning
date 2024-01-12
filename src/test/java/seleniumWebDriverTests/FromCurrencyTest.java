package seleniumWebDriverTests;

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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class FromCurrencyTest {

    WebDriver webDriver;

    @BeforeEach
    void setUp() {
        webDriver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        webDriver.quit();
    }

    @Disabled("Test is disabled because it is included in the parametrized test below")
    @DisplayName("Testing from usd to usd")
    @Test
    void testWithUSD() {
        webDriver.get("http://localhost:8080/");
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement amountField = webDriver.findElement(By.cssSelector("#amount"));
        amountField.sendKeys("100");
        WebElement fromCurrencyelement = webDriver.findElement(By.cssSelector("#from"));
        Select fromSelect = new Select(fromCurrencyelement);
        fromSelect.selectByValue("USD");
        WebElement toCurrencyelement = webDriver.findElement(By.cssSelector("#to"));
        Select toSelect = new Select(toCurrencyelement);
        toSelect.selectByValue("USD");
        //convert btn
        WebElement convertBtn = webDriver.findElement(By.cssSelector("#convertCurrency"));
        convertBtn.click();
        // Verify the result and conversion rate
        WebElement resultInput = webDriver.findElement(By.cssSelector("#result"));
        WebElement conversionRateInput = webDriver.findElement(By.cssSelector("#conversionRate"));
        //wait: https://www.selenium.dev/documentation/webdriver/waits/
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(conversionRateInput, "value", "")));
        //assertions
        assertNotEquals("", conversionRateInput.getAttribute("value"));
        String conversionRate = conversionRateInput.getAttribute("value");
        assertEquals(100 * Double.valueOf(conversionRate), Double.valueOf(resultInput.getAttribute("value")));
    }


    @ParameterizedTest(name = "Testing with From currency: {0}")
    @MethodSource("fromCurrencies")
    void testWithDifferentFromCurrency() {
        webDriver.get("http://localhost:8080/");
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement amountField = webDriver.findElement(By.cssSelector("#amount"));
        amountField.sendKeys("100");
        WebElement fromCurrencyelement = webDriver.findElement(By.cssSelector("#from"));
        Select fromSelect = new Select(fromCurrencyelement);
        fromSelect.selectByValue("ILS");
        WebElement toCurrencyelement = webDriver.findElement(By.cssSelector("#to"));
        Select toSelect = new Select(toCurrencyelement);
        toSelect.selectByValue("USD");
        //convert btn
        WebElement convertBtn = webDriver.findElement(By.cssSelector("#convertCurrency"));
        convertBtn.click();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        // Verify the result and conversion rate
        WebElement resultInput = webDriver.findElement(By.cssSelector("#result"));
        WebElement conversionRateInput = webDriver.findElement(By.cssSelector("#conversionRate"));
        //wait: https://www.selenium.dev/documentation/webdriver/waits/
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(conversionRateInput, "value", "")));
        //assertions
        assertNotEquals("", conversionRateInput.getAttribute("value"));
        String conversionRate = conversionRateInput.getAttribute("value");
        if (conversionRate.contains(","))
            conversionRate = conversionRate.replace(',', '.');
        assertEquals(100 * Double.valueOf(conversionRate), Double.valueOf(resultInput.getAttribute("value")));
    }

    private static List<String> fromCurrencies() {
        // You can provide different currencies here
        return List.of("EUR", "USD", "ILS", "JOD", "RSD");
    }
}
