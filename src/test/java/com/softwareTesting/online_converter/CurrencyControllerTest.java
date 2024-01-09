package com.softwareTesting.online_converter;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CurrencyControllerTest {

    @InjectMocks
    private CurrencyController currencyController;
    @Mock
    private CurrencyRepository currencyRepository;


    @BeforeEach
    public void setup() {

        // Initialize the mock objects
        MockitoAnnotations.openMocks(this);
        // Given
        Currency usd = new Currency("USD", "United States Dollar", '.');
        Currency eur = new Currency("EUR", "Euro", '.');
        List<Currency> currencyList = List.of(usd, eur);
        when(currencyRepository.findAll()).thenReturn(currencyList);
        when(currencyRepository.findById("USD")).thenReturn(java.util.Optional.of(new Currency("USD", "United States Dollar", '.')));
        when(currencyRepository.findById("EUR")).thenReturn(java.util.Optional.of(new Currency("EUR", "Euro", '.')));
        // Set the mocked repository in the controller
//        currencyController = new CurrencyController(currencyRepository);
    }

    @Test
    @DisplayName("Requesting indexPage test")
    void convert() {
        // given
        when(currencyRepository.findAll()).thenReturn(new ArrayList<Currency>());
        // when
        ModelAndView modelAndView = currencyController.convert();
        // then
        assertEquals("converter.html", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel());
    }

    @Test
    @DisplayName("Test invalid from currency")
    void testMyValidation_InvalidFromCurrency() {
        //given
        FromToRequestData request = new FromToRequestData("", "USD", 100);
        //when
        boolean result = currencyController.validateRequest(request);
        //then
        assertFalse(result);
    }

    @Test
    @DisplayName("Test valid from currency")
    void testMyValidations_ValidRequest() {
        //given
        FromToRequestData requestData = new FromToRequestData("ISR", "USD", 100);
        //when
        boolean result = currencyController.validateRequest(requestData);
        //then
        assertTrue(result);
    }

    @Test
    @DisplayName("Test validate with negative amount")
    void testConversionWithNegativeAmount() {
        // When
        boolean response = currencyController
                .validateRequest(new FromToRequestData("USD", "EUR", -100));

        // Then
        assertNotNull(response);
        assertFalse(response);
    }

    @Test
    @DisplayName("Test failed currency conversion - invalid currencies")
    void testConvertValues_InvalidCurrencies() throws IOException {
        // Given
        FromToRequestData requestData = new FromToRequestData("", "", 100);

        // When
        ResponseEntity<FromToResponse> response = currencyController.convertValues(requestData);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Please check entered values", response.getBody().getResponseCode());
    }
}
