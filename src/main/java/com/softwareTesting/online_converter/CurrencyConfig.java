package com.softwareTesting.online_converter;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CurrencyConfig {
    @Bean
    ApplicationRunner applicationRunner(CurrencyRepository currencyRepository, ExchangeRateRepository exchangeRateRepository) {
        return args -> {
            //if database is empty generate currency codes
            if (currencyRepository.count() == 0) {
                List<Currency> currencies = createCurrencyList();
                currencyRepository.saveAll(currencies);
            }
            if (exchangeRateRepository.count() == 0) {
                List<ExchangeRate> exchangeRateList = createExchangeList();
                exchangeRateRepository.saveAll(exchangeRateList);
            }
        };
    }

    private List<Currency> createCurrencyList() {
        List<Currency> currencies = new ArrayList<>();

        addCurrency(currencies, "AED", "UAE Dirham");
        addCurrency(currencies, "AFN", "Afghan Afghani");
        addCurrency(currencies, "ALL", "Albanian Lek");
        addCurrency(currencies, "AMD", "Armenian Dram");
        addCurrency(currencies, "ANG", "Netherlands Antillian Guilder");
        addCurrency(currencies, "AOA", "Angolan Kwanza");
        addCurrency(currencies, "ARS", "Argentine Peso");
        addCurrency(currencies, "AWG", "Aruban Florin");
        addCurrency(currencies, "AZN", "Azerbaijani Manat");
        addCurrency(currencies, "BAM", "Bosnia and Herzegovina Mark");
        addCurrency(currencies, "BBD", "Barbados Dollar");
        addCurrency(currencies, "BDT", "Bangladeshi Taka");
        addCurrencyWithDifferentFormat(currencies, "BWP", "Botswana pula", '.');
        addCurrency(currencies, "BGN", "Bulgarian Lev");
        addCurrency(currencies, "BHD", "Bahraini Dinar", 3);
        addCurrency(currencies, "BIF", "Burundian Franc", 0);
        addCurrency(currencies, "BMD", "Bermudian Dollar");
        addCurrencyWithDifferentFormat(currencies, "BND", "Brunei Dollar", '.');
        addCurrency(currencies, "BOB", "Bolivian Boliviano");
        addCurrency(currencies, "BRL", "Brazilian Real");
        addCurrency(currencies, "BSD", "Bahamian Dollar");
        addCurrency(currencies, "BTN", "Bhutanese Ngultrum");
        addCurrency(currencies, "BYN", "Belarusian Ruble");
        addCurrency(currencies, "BZD", "Belize Dollar");
        addCurrencyWithDifferentFormat(currencies, "CAD", "Canadian Dollar", '.');
        addCurrency(currencies, "CDF", "Congolese Franc");
        addCurrency(currencies, "CHF", "Swiss Franc");
        addCurrency(currencies, "CLP", "Chilean Peso", 0);
        addCurrencyWithDifferentFormat(currencies, "CNY", "Chinese Renminbi", '.');
        addCurrency(currencies, "COP", "Colombian Peso");
        addCurrency(currencies, "CRC", "Costa Rican Colon");
        addCurrency(currencies, "CUP", "Cuban Peso");
        addCurrency(currencies, "CVE", "Cape Verdean Escudo");
        addCurrency(currencies, "CZK", "Czech Koruna");
        addCurrency(currencies, "DJF", "Djiboutian Franc", 0);
        addCurrency(currencies, "DKK", "Danish Krone");
        addCurrencyWithDifferentFormat(currencies, "DOP", "Dominican Peso", '.');
        addCurrency(currencies, "DZD", "Algerian Dinar");
        addCurrency(currencies, "EGP", "Egyptian Pound");
        addCurrency(currencies, "ERN", "Eritrean Nakfa");
        addCurrency(currencies, "ETB", "Ethiopian Birr");
        addCurrencyWithDifferentFormat(currencies, "EUR", "Euro", '.');
        addCurrency(currencies, "FJD", "Fiji Dollar");
        addCurrency(currencies, "FKP", "Falkland Islands Pound");
        addCurrency(currencies, "FOK", "Faroese Króna");
        addCurrencyWithDifferentFormat(currencies, "GBP", "Pound Sterling", '.');
        addCurrency(currencies, "GEL", "Georgian Lari");
        addCurrency(currencies, "GGP", "Guernsey Pound");
        addCurrency(currencies, "GHS", "Ghanaian Cedi");
        addCurrency(currencies, "GIP", "Gibraltar Pound");
        addCurrency(currencies, "GMD", "Gambian Dalasi");
        addCurrency(currencies, "GNF", "Guinean Franc", 0);
        addCurrencyWithDifferentFormat(currencies, "GTQ", "Guatemalan Quetzal", '.');
        addCurrency(currencies, "GYD", "Guyanese Dollar");
        addCurrencyWithDifferentFormat(currencies, "HKD", "Hong Kong Dollar", '.');
        addCurrency(currencies, "HNL", "Honduran Lempira");
        addCurrency(currencies, "HRK", "Croatian Kuna");
        addCurrency(currencies, "HTG", "Haitian Gourde");
        addCurrency(currencies, "HUF", "Hungarian Forint");
        addCurrency(currencies, "IDR", "Indonesian Rupiah");
        addCurrencyWithDifferentFormat(currencies, "ILS", "Israeli New Shekel", '.');
        addCurrency(currencies, "IMP", "Manx Pound");
        addCurrencyWithDifferentFormat(currencies, "INR", "Indian Rupee", '.');
        addCurrency(currencies, "IQD", "Iraqi Dinar", 3);
        addCurrency(currencies, "IRR", "Iranian Rial");
        addCurrency(currencies, "ISK", "Icelandic Króna", 0);
        addCurrency(currencies, "JEP", "Jersey Pound");
        addCurrency(currencies, "JMD", "Jamaican Dollar");
        addCurrency(currencies, "JOD", "Jordanian Dinar", 3);
        addCurrency(currencies, "JPY", "Japanese Yen", 0, '.');
        addCurrencyWithDifferentFormat(currencies, "KES", "Kenyan Shilling", '.');
        addCurrency(currencies, "KGS", "Kyrgyzstani Som");
        addCurrency(currencies, "KHR", "Cambodian Riel");
        addCurrency(currencies, "KID", "Kiribati Dollar");
        addCurrency(currencies, "KMF", "Comorian Franc", 0);
        addCurrency(currencies, "KRW", "South Korean Won", 0, '.');
        addCurrency(currencies, "KWD", "Kuwaiti Dinar", 3);
        addCurrency(currencies, "KYD", "Cayman Islands Dollar");
        addCurrency(currencies, "KZT", "Kazakhstani Tenge");
        addCurrency(currencies, "LAK", "Lao Kip");
        addCurrencyWithDifferentFormat(currencies, "LBP", "Lebanese Pound", '.');
        addCurrencyWithDifferentFormat(currencies, "LKR", "Sri Lanka Rupee", '.');
        addCurrency(currencies, "LRD", "Liberian Dollar");
        addCurrency(currencies, "LSL", "Lesotho Loti");
        addCurrency(currencies, "LYD", "Libyan Dinar", 3);
        addCurrency(currencies, "MAD", "Moroccan Dirham");
        addCurrency(currencies, "MDL", "Moldovan Leu");
        addCurrency(currencies, "MGA", "Malagasy Ariary");
        addCurrency(currencies, "MKD", "Macedonian Denar");
        addCurrency(currencies, "MMK", "Burmese Kyat");
        addCurrency(currencies, "MNT", "Mongolian Tögrög");
        addCurrency(currencies, "MOP", "Macanese Pataca");
        addCurrency(currencies, "MRU", "Mauritanian Ouguiya");
        addCurrency(currencies, "MUR", "Mauritian Rupee");
        addCurrency(currencies, "MVR", "Maldivian Rufiyaa");
        addCurrency(currencies, "MWK", "Malawian Kwacha");
        addCurrencyWithDifferentFormat(currencies, "MXN", "Mexican Peso", '.');
        addCurrencyWithDifferentFormat(currencies, "MYR", "Malaysian Ringgit", '.');
        addCurrency(currencies, "MZN", "Mozambican Metical");
        addCurrency(currencies, "NAD", "Namibian Dollar");
        addCurrencyWithDifferentFormat(currencies, "NGN", "Nigerian Naira", '.');
        addCurrencyWithDifferentFormat(currencies, "NIO", "Nicaraguan Córdoba", '.');
        addCurrency(currencies, "NOK", "Norwegian Krone");
        addCurrencyWithDifferentFormat(currencies, "NPR", "Nepalese Rupee", '.');
        addCurrencyWithDifferentFormat(currencies, "NZD", "New Zealand Dollar", '.');
        addCurrency(currencies, "OMR", "Omani Rial", 3);
        addCurrency(currencies, "PAB", "Panamanian Balboa");
        addCurrency(currencies, "PEN", "Peruvian Sol");
        addCurrency(currencies, "PGK", "Papua New Guinean Kina");
        addCurrencyWithDifferentFormat(currencies, "PHP", "Philippine Peso", '.');
        addCurrencyWithDifferentFormat(currencies, "PKR", "Pakistani Rupee", '.');
        addCurrency(currencies, "PLN", "Polish Złoty");
        addCurrency(currencies, "PYG", "Paraguayan Guarani", 0);
        addCurrency(currencies, "QAR", "Qatari Riyal");
        addCurrency(currencies, "RON", "Romanian Leu");
        addCurrency(currencies, "RSD", "Serbian Dinar");
        addCurrency(currencies, "RUB", "Russian Ruble");
        addCurrency(currencies, "RWF", "Rwandan Franc", 0);
        addCurrency(currencies, "SAR", "Saudi Riyal");
        addCurrency(currencies, "SBD", "Solomon Islands Dollar");
        addCurrency(currencies, "SCR", "Seychellois Rupee");
        addCurrency(currencies, "SDG", "Sudanese Pound");
        addCurrency(currencies, "SEK", "Swedish Krona");
        addCurrencyWithDifferentFormat(currencies, "SGD", "Singapore Dollar", '.');
        addCurrency(currencies, "SHP", "Saint Helena Pound");
        addCurrency(currencies, "SLL", "Sierra Leonean Leone");
        addCurrency(currencies, "SOS", "Somali Shilling");
        addCurrency(currencies, "SRD", "Surinamese Dollar");
        addCurrency(currencies, "SSP", "South Sudanese Pound");
        addCurrency(currencies, "STN", "São Tomé and Príncipe Dobra");
        addCurrency(currencies, "SYP", "Syrian Pound");
        addCurrency(currencies, "SZL", "Eswatini Lilangeni");
        addCurrencyWithDifferentFormat(currencies, "THB", "Thai Baht", '.');
        addCurrency(currencies, "TJS", "Tajikistani Somoni");
        addCurrency(currencies, "TMT", "Turkmenistan Manat");
        addCurrency(currencies, "TND", "Tunisian Dinar", 3);
        addCurrency(currencies, "TOP", "Tongan Pa'anga");
        addCurrency(currencies, "TRY", "Turkish Lira");
        addCurrency(currencies, "TTD", "Trinidad and Tobago Dollar");
        addCurrency(currencies, "TVD", "Tuvaluan Dollar");
        addCurrencyWithDifferentFormat(currencies, "TWD", "New Taiwan Dollar", '.');
        addCurrencyWithDifferentFormat(currencies, "TZS", "Tanzanian Shilling", '.');
        addCurrency(currencies, "UAH", "Ukrainian Hryvnia");
        addCurrency(currencies, "UGX", "Ugandan Shilling", 0, '.');
        addCurrencyWithDifferentFormat(currencies, "USD", "United States Dollar", '.');
        addCurrency(currencies, "UYU", "Uruguayan Peso");
//        addCurrency(currencies, "UYW", "Unidad previsional", 4);
        addCurrency(currencies, "UZS", "Uzbekistani So'm");
        addCurrency(currencies, "VES", "Venezuelan Bolívar Soberano");
        addCurrency(currencies, "VND", "Vietnamese Đồng", 0);
        addCurrency(currencies, "VUV", "Vanuatu Vatu", 0);
        addCurrency(currencies, "WST", "Samoan Tala");
        addCurrency(currencies, "XAF", "Central African CFA Franc", 0);
        addCurrency(currencies, "XCD", "East Caribbean Dollar");
        addCurrency(currencies, "XDR", "Special Drawing Rights");
        addCurrency(currencies, "XOF", "West African CFA franc", 0);
        addCurrency(currencies, "XPF", "CFP Franc", 0);
        addCurrency(currencies, "YER", "Yemeni Rial");
        addCurrency(currencies, "ZAR", "South African Rand");
        addCurrency(currencies, "ZMW", "Zambian Kwacha");
        return currencies;
    }

    private void addCurrency(List<Currency> currencies, String code, String name) {
        Currency currency = new Currency(code, name);
        currencies.add(currency);
    }

    private void addCurrency(List<Currency> currencies, String code, String name, int numberOfDecimal) {
        Currency currency = new Currency(code, name, numberOfDecimal);
        currencies.add(currency);
    }

    private void addCurrency(List<Currency> currencies, String code, String name, int numberOfDecimal, char format) {
        Currency currency = new Currency(code, name, numberOfDecimal, format);
        currencies.add(currency);
    }

    private void addCurrencyWithDifferentFormat(List<Currency> currencies, String code, String name, char format) {
        Currency currency = new Currency(code, name, format);
        currencies.add(currency);
    }

    private List<ExchangeRate> createExchangeList() {
        List<ExchangeRate> exchangeRateList = new ArrayList<>();
        exchangeRateList.add(new ExchangeRate("USD", "AED", 3.6725));
        exchangeRateList.add(new ExchangeRate("USD", "ILS", 3.6818));
        exchangeRateList.add(new ExchangeRate("USD", "JOD", 0.709));
        exchangeRateList.add(new ExchangeRate("USD", "EGP", 30.8417));
        exchangeRateList.add(new ExchangeRate("USD", "ERN", 30.8417));
        exchangeRateList.add(new ExchangeRate("USD", "AUD", 1.4902));
        exchangeRateList.add(new ExchangeRate("USD", "CAD", 1.3354));
        exchangeRateList.add(new ExchangeRate("USD", "EUR", 0.9142));
        exchangeRateList.add(new ExchangeRate("USD", "GBP", 0.7872));
        exchangeRateList.add(new ExchangeRate("USD", "JPY", 144.7989));
        exchangeRateList.add(new ExchangeRate("USD", "KRW", 1315.4298));
        exchangeRateList.add(new ExchangeRate("USD", "INR", 83.1800));
        exchangeRateList.add(new ExchangeRate("USD", "MXN", 16.9386));
        exchangeRateList.add(new ExchangeRate("USD", "CNY", 7.1566));
        exchangeRateList.add(new ExchangeRate("USD", "BRL", 4.8918));
        return exchangeRateList;
    }
}
