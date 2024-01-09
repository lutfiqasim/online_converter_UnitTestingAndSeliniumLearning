package com.softwareTesting.online_converter;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import com.softwareTesting.online_converter.Currency;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
@TestPropertySource(locations = "classpath:testInMemoryDB.properties")
class ExchangeRateRepositoryTest {

    @Autowired
    ExchangeRateRepository exchangeRateRepository;

    @Test
    @DisplayName("Finding exchange rate against in memory db")
    void findExchangeRateByFromAndToCurrencyNames() {
        //given
        exchangeRateRepository.save(new ExchangeRate("USD", "EUR", 0.9142));//assuming exchange rate is 0.9142
        Double exchangeRate = exchangeRateRepository.findExchangeRateByFromAndToCurrencyNames("USD", "EUR").orElse(null);
        //then
        assertNotNull(exchangeRate);
        assertEquals(0.9142, exchangeRate);
    }


}

@DataJpaTest
@TestPropertySource(locations = "classpath:testRealDB.properties")
class ExchangeRateReposotryTestRealDB {

    @Autowired
    ExchangeRateRepository exchangeRateRepository;
    

    @Test
    void testSaveExchangeRate() {
        // Given
        ExchangeRate exchangeRate = new ExchangeRate("USD", "EUR", 0.9142);

        // When
        ExchangeRate savedExchangeRate = exchangeRateRepository.save(exchangeRate);

        // Then
        assertNotNull(savedExchangeRate.getId());
        assertEquals("USD", savedExchangeRate.getFromCurrencyName());
        assertEquals("EUR", savedExchangeRate.getToCurrencyName());
        //used delta because there might be some error with the double value
        assertEquals(0.9142, savedExchangeRate.getExchangeRate(), 0.0001);
    }

    @Test
    void testFindExchangeRateByFromAndToCurrencyNames_NotFound() {
        // When
        Optional<Double> foundExchangeRate = exchangeRateRepository.findExchangeRateByFromAndToCurrencyNames("USD", "EUR");

        // Then
        assertFalse(foundExchangeRate.isPresent());
    }

    @Test
    @DisplayName("Find exchange rate by from and to currency names")
    void testFindExchangeRateByFromAndToCurrencyNames() {
        // Given
        ExchangeRate exchangeRate = new ExchangeRate("USD", "EUR", 0.9142);
        exchangeRateRepository.save(exchangeRate);

        // When
        Optional<Double> foundExchangeRate = exchangeRateRepository.findExchangeRateByFromAndToCurrencyNames("USD", "EUR");

        // Then
        assertTrue(foundExchangeRate.isPresent());
        assertEquals(0.9142, foundExchangeRate.get(), 0.0001);
    }

}

