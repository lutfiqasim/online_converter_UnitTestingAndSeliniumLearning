package seleniumTesting;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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

    @BeforeEach
    void setUp() {
        webDriver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        webDriver.quit();
    }

    @ParameterizedTest(name = "Testing with From currency: {0}")
    @MethodSource("fromCurrencies")
    void testWithDifferentFromCurrency(String fromCurrency) {
        webDriver.get("http://localhost:8080/");
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement amountField = webDriver.findElement(By.cssSelector("#amount"));
        amountField.sendKeys("100");
        WebElement fromCurrencyElement = webDriver.findElement(By.cssSelector("#from"));
        Select fromSelect = new Select(fromCurrencyElement);
        fromSelect.selectByValue(fromCurrency);
        WebElement toCurrencyElement = webDriver.findElement(By.cssSelector("#to"));
        Select toSelect = new Select(toCurrencyElement);
        toSelect.selectByValue("USD");
        //convert btn
        WebElement convertBtn = webDriver.findElement(By.cssSelector("#convertCurrency"));
        convertBtn.click();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        // Verify the result and conversion rate
        WebElement resultInput = webDriver.findElement(By.cssSelector("#result"));
        WebElement conversionRateInput = webDriver.findElement(By.cssSelector("#conversionRate"));
        //https://www.selenium.dev/documentation/webdriver/waits/
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(conversionRateInput, "value", "")));

        //assertions
        assertNotEquals("", conversionRateInput.getAttribute("value"));
        String conversionRate = conversionRateInput.getAttribute("value");
        assertEquals(100 * Double.valueOf(conversionRate), Double.valueOf(resultInput.getAttribute("value")));
    }

    private static List<String> fromCurrencies() {
        // You can provide different currencies here
        return Arrays.asList("EUR", "JPY", "ILS");
    }
}

