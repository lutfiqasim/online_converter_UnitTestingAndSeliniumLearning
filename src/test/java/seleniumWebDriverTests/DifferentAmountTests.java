package seleniumWebDriverTests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferentAmountTests {

    WebDriver webDriver;

    @BeforeEach
    void setUp() {
        webDriver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        webDriver.quit();
    }

    @Test
    @DisplayName("Convert with no amount given")
    void TestWithNoAmountGiven() {
        webDriver.get("http://localhost:8080/");
        assertEquals("Currency Converter", webDriver.getTitle());
        //in order for the drop down list to be populated
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement amountField = webDriver.findElement(By.cssSelector("#amount"));

        // get select elements
        WebElement selectElementFrom = webDriver.findElement(By.cssSelector("#from"));
        Select fromCurrency = new Select(selectElementFrom);
        //select usd
        fromCurrency.selectByValue("USD");
        WebElement selectElementTo = webDriver.findElement(By.cssSelector("#to"));
        Select toCurrency = new Select(selectElementTo);
        toCurrency.selectByValue("EUR");
        //wait for 1 second
        //get conversion button
        WebElement convertBtn = webDriver.findElement(By.cssSelector("#convertCurrency"));
        convertBtn.click();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        // Verify the result and conversion rate
        WebElement resultInput = webDriver.findElement(By.cssSelector("#result"));
        WebElement conversionRateInput = webDriver.findElement(By.cssSelector("#conversionRate"));
        assertEquals("Enter valid amount", amountField.getAttribute("placeholder"));
        assertEquals("", resultInput.getText());
        assertEquals("", conversionRateInput.getText());

    }


    @ParameterizedTest(name = "Testing with differentAmount:{0}")
    @MethodSource("amountGiven")
    public void testWithDifferentAmount() {
    }

    private static List<String> amountGiven() {
        // You can provide different currencies here
        return List.of("100", "20", "54", "100000", "0.5");
    }
}
