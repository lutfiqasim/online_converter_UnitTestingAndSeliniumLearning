package com.softwareTesting.online_converter;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class TestFile {



    @ParameterizedTest
    @CsvFileSource(resources = "/rate.csv")
//    @CsvFileSource(resources = "C:\\Users\\hp\\IdeaProjects\\online_converter_UnitTestingAndSeliniumLearning\\rate.csv")
    @DisplayName("Test reading data from CSV file")
    void testReadDataFromCSV(String fromCurrency, String toCurrency, double expectedExchangeRate) {
        // Given
        ExchangeRate exchangeRate = new ExchangeRate(fromCurrency, toCurrency, expectedExchangeRate);


        exchangeRate.setExchangeRate(0.87);

        // When
        String actualFromCurrency = exchangeRate.getFromCurrencyName();
        String actualToCurrency = exchangeRate.getToCurrencyName();
        double actualExchangeRate = exchangeRate.getExchangeRate();





//         Then
        assertEquals(fromCurrency, actualFromCurrency);
        assertEquals(toCurrency, actualToCurrency);
        assertEquals(expectedExchangeRate, actualExchangeRate, 0.0001); // Adjust delta as needed
    }

}

