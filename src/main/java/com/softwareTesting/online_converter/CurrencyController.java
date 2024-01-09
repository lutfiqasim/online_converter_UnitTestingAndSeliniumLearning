package com.softwareTesting.online_converter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CurrencyController {

    @Autowired
    private CurrencyRepository currencyRepository;
    private List<Currency> currencyList = new ArrayList<>();

    //Note remember to move it to environment variable or to properties
    @Value("${apiKey}")
    private String API_KEY;
    private static final String API_ENDPOINT = "https://v6.exchangerate-api.com/v6/%s/pair/%s/%s";

    @Autowired
    public CurrencyController(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
        currencyList = currencyRepository.findAll();
    }

    @GetMapping("/")
    public ModelAndView convert() {
        currencyList = currencyRepository.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("converter.html");
        modelAndView.addObject("currencies", currencyList);
        return modelAndView;
    }

    @PostMapping("/exchangeCurrencies")
    public ResponseEntity<FromToResponse> convertValues(@RequestBody FromToRequestData requestData) {
        if (!validateRequest(requestData)) {
            return ResponseEntity.badRequest().body(new FromToResponse("Please check entered values"));
        }
        if (currencyList == null || currencyList.isEmpty()) {
            return ResponseEntity.badRequest().body(new FromToResponse("Unable to connect to server"));
        }
        String fromCurrency = requestData.getFromCurrency();
        String toCurrency = requestData.getToCurrency();
        double amount = requestData.getAmount();
        double rate = 0.0;
        try {
            rate = consumeApi(fromCurrency, toCurrency);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(new FromToResponse("Please check entered values" + e.getMessage()));
        }
        if (rate == 0.0) {
            return ResponseEntity.badRequest().body(new FromToResponse("Please check entered values"));
        }

        return ResponseEntity.ok(successResponse(toCurrency, rate, amount));

    }

    public double consumeApi(String fromCurrency, String toCurrency) throws IOException {
        String apiURL = String.format(API_ENDPOINT, API_KEY, fromCurrency, toCurrency);
        URL url = new URL(apiURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (InputStream inputStream = connection.getInputStream()) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                //Use Gson to parse Json
                JsonParser jsonParser = new JsonParser();
                JsonObject jsonObject = jsonParser.parse(inputStreamReader).getAsJsonObject();
                JsonElement exchangeRateElement = jsonObject.get("conversion_rate");
                if (exchangeRateElement != null && exchangeRateElement.isJsonPrimitive()) {
                    return exchangeRateElement.getAsDouble();
                } else {
                    throw new IllegalStateException("Unexpected JSON format: " + jsonObject);
                }
            }
        } else {
            throw new IOException("Failed to fetch data. HTTP error code: " + responseCode);
        }
    }


    public boolean validateRequest(FromToRequestData requestData) {
        if (requestData.getFromCurrency().isBlank() || requestData.getFromCurrency().isEmpty()) {
            return false;
        } else if (requestData.getToCurrency().isBlank() || requestData.getToCurrency().isEmpty()) {
            return false;
        } else if (requestData.getAmount() < 0 || currencyRepository.findById(requestData.getToCurrency()) == null
                || currencyRepository.findById(requestData.getFromCurrency()) == null) {
            return false;
        } else {
            return true;
        }

    }

    public FromToResponse successResponse(String toCurrency, double exchangeRate, double amount) {
        FromToResponse fromToResponse = new FromToResponse();
        Currency targetCurrency = currencyList.stream()
                .filter(currency -> toCurrency.equals(currency.getCode()))
                .findFirst()
                .orElse(null);
        double result = exchangeRate * amount;
        String format = targetCurrency.formatAmount(result);
        fromToResponse.setResult(format);
        fromToResponse.setExchangeRate(exchangeRate);
        fromToResponse.setResponseCode("success");
        return fromToResponse;
    }

    public void setCurrencyRepository(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

}
