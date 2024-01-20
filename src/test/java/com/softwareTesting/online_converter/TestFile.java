package com.softwareTesting.online_converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TestFile {

    @Mock
    CurrencyRepository currencyRepository;
    @InjectMocks
    CurrencyController currencyController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/rate.csv")
//    @CsvFileSource(resources = "C:\\Users\\hp\\IdeaProjects\\online_converter_UnitTestingAndSeliniumLearning\\rate.csv")
    @DisplayName("Test reading data from CSV file")
    void testReadDataFromCSV(String fromCurrency, String toCurrency, double expectedExchangeRate) {
        // Given
        Currency cur1 = new Currency(fromCurrency, "cur1");
        Currency cur2 = new Currency(toCurrency, "cur2");

        // Mock the repository to return instances of Currency
        when(currencyRepository.findById(fromCurrency)).thenReturn(Optional.of(cur1));
        when(currencyRepository.findById(toCurrency)).thenReturn(Optional.of(cur2));
        when(currencyRepository.findAll()).thenReturn(List.of(cur1, cur2));
        currencyController = new CurrencyController(currencyRepository);

        ExchangeRate exchangeRate = new ExchangeRate(fromCurrency, toCurrency, expectedExchangeRate);

        // When
        String actualFromCurrency = exchangeRate.getFromCurrencyName();
        String actualToCurrency = exchangeRate.getToCurrencyName();
        double actualExchangeRate = exchangeRate.getExchangeRate();

        FromToResponse response = currencyController.successResponse(toCurrency, expectedExchangeRate, 100);
        //Then
        assertEquals(fromCurrency, actualFromCurrency);
        assertEquals(toCurrency, actualToCurrency);
        assertEquals(expectedExchangeRate, actualExchangeRate, 0.0001);
        assertEquals(expectedExchangeRate, response.getExchangeRate());
        if (response.getResult().contains(","))
            response.setResult(response.getResult().replace(',', '.'));
        assertEquals(100 * expectedExchangeRate, Double.parseDouble(response.getResult()), 0.5);
    }

}

