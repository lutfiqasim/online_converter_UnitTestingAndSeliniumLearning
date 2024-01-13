package seleniumTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestingWithDifferentCurrencyAmounts {
    private WebDriver webDriver;

    @BeforeEach
    void setUp() {
        webDriver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        webDriver.quit();
    }

    @Test
    void NoAmountGiven() {
        webDriver.get("http://localhost:8080/");
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement amountField = webDriver.findElement(By.cssSelector("#amount"));
        WebElement fromCurrencyElement = webDriver.findElement(By.cssSelector("#from"));
        Select fromSelect = new Select(fromCurrencyElement);
        fromSelect.selectByValue("ILS");
        WebElement toCurrencyElement = webDriver.findElement(By.cssSelector("#to"));
        Select toSelect = new Select(toCurrencyElement);
        toSelect.selectByValue("USD");

        //convert btn
        WebElement convertBtn = webDriver.findElement(By.cssSelector("#convertCurrency"));
        convertBtn.click();

        //getting results
        WebElement resultInput = webDriver.findElement(By.cssSelector("#result"));
        WebElement conversionRateInput = webDriver.findElement(By.cssSelector("#conversionRate"));

        assertEquals("", resultInput.getAttribute("value"));
        assertEquals("", conversionRateInput.getAttribute("value"));
        assertEquals("Enter valid amount", amountField.getAttribute("placeholder"));
    }

    @ParameterizedTest(name = "Testing with amount: {0}")
    @MethodSource("givenAmount")
    void TestingWithDifferentAmounts(String amount) {
        webDriver.get("http://localhost:8080/");
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement amountField = webDriver.findElement(By.cssSelector("#amount"));
        amountField.sendKeys(amount);
        WebElement fromCurrencyElement = webDriver.findElement(By.cssSelector("#from"));
        Select fromSelect = new Select(fromCurrencyElement);
        fromSelect.selectByValue("ILS");
        WebElement toCurrencyElement = webDriver.findElement(By.cssSelector("#to"));
        Select toSelect = new Select(toCurrencyElement);
        toSelect.selectByValue("USD");
        //convert btn
        WebElement convertBtn = webDriver.findElement(By.cssSelector("#convertCurrency"));
        convertBtn.click();

        // Verify the result and conversion rate
        WebElement resultInput = webDriver.findElement(By.cssSelector("#result"));
        WebElement conversionRateInput = webDriver.findElement(By.cssSelector("#conversionRate"));
        // waiting time so api call is done and page is updated with maximum of 10s https://www.selenium.dev/documentation/webdriver/waits/
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(conversionRateInput, "value", "")));
        //assertions
        assertNotEquals("", conversionRateInput.getAttribute("value"));
        String conversionRate = conversionRateInput.getAttribute("value");
        if (conversionRate.contains(","))
            conversionRate = conversionRate.replace(",", ".");
        //assert equals with .05 delta because of how many decimals are required for the  to-currency
        assertEquals(Double.parseDouble(amount) * Double.parseDouble(conversionRate), Double.parseDouble(resultInput.getAttribute("value"))
                , 0.05);
    }

    private static List<String> givenAmount() {
        // You can provide different currencies here
        return Arrays.asList("100", "0.5", "32432", "1000000");
    }

}
