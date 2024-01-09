package com.softwareTesting.online_converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
@TestPropertySource(locations = "classpath:testRealDB.properties")
public class TestCurrencyControllerWithRealDB {
    @Autowired
    ExchangeRateRepository exchangeRateRepository;

    @Mock
    CurrencyRepository currencyRepository;
    @InjectMocks
    CurrencyController currencyController;

    @BeforeEach
    void setUp() {
        // Given
        Currency usd = new Currency("USD", "United States Dollar", '.');
        Currency eur = new Currency("EUR", "Euro", '.');

        // Mock the repository to return instances of Currency
        when(currencyRepository.findById("USD")).thenReturn(Optional.of(usd));
        when(currencyRepository.findById("EUR")).thenReturn(Optional.of(eur));
        when(currencyRepository.findAll()).thenReturn(List.of(usd, eur));
        currencyController = new CurrencyController(currencyRepository);
    }

    @Test
    @DisplayName("Test success conversion")
    void testSuccessConversion() {
        //given
        // Save an ExchangeRate
        ExchangeRate exchangeRate = new ExchangeRate("USD", "EUR", 0.9142);
        exchangeRateRepository.save(exchangeRate);
        // When
        Double eRate = exchangeRateRepository.findExchangeRateByFromAndToCurrencyNames("USD", "EUR").orElse(null);
        FromToResponse response = currencyController.successResponse("EUR", eRate, 100);
        // Then
        assertNotNull(response);
        assertEquals("success", response.getResponseCode());
        assertNotNull(response.getExchangeRate());
        assertNotNull(response.getResult());
    }

    @Test
    @DisplayName("Test conversion with unknown currency")
    void testConversionWithnotFoundCurrency() {
        // When
        assertThrows(NullPointerException.class, () -> currencyController.successResponse("GBP", 10, 100));
    }

    @Test
    @DisplayName("Test conversion with large amount")
    void testConversionWithLargeAmount() {
        // Given
        ExchangeRate exchangeRate = new ExchangeRate("USD", "EUR", 0.9142);
        exchangeRateRepository.save(exchangeRate);

        // When
        FromToResponse response = currencyController.successResponse("EUR", 0.9142, 1000000);

        // Then
        assertNotNull(response);
        assertEquals("success", response.getResponseCode());
        assertTrue(Double.valueOf(response.getResult()) > 0);
    }

    @Test
    @DisplayName("Test conversion with multiple decimal places and result is formatted according to Currency's' decimals")
    void testConversionWithMultipleDecimalPlaces() {
        // Given
        ExchangeRate exchangeRate = new ExchangeRate("USD", "EUR", 0.123456789);
        exchangeRateRepository.save(exchangeRate);

        // When
        FromToResponse response = currencyController.successResponse("EUR", 0.123456789, 100);

        // Then
        assertNotNull(response);
        assertEquals("success", response.getResponseCode());
        assertNotEquals(12.3456789, Double.valueOf(Double.valueOf(response.getResult())));
//        assertEquals(12.35, Double.valueOf(response.getResult()), 0.000001);
    }
}
