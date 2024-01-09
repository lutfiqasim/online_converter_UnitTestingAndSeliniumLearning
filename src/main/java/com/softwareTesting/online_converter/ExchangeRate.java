package com.softwareTesting.online_converter;

import jakarta.persistence.*;

@Entity(name = "exchange_rate")
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_currency_name", nullable = false)
    private String fromCurrencyName;

    @Column(name = "to_currency_name", nullable = false)
    private String toCurrencyName;

    @Column(name = "exchange_rate", nullable = false)
    private Double exchangeRate;

    public ExchangeRate() {
    }

    public ExchangeRate(String fromCurrencyName, String toCurrencyName, Double exchangeRate) {
        this.fromCurrencyName = fromCurrencyName;
        this.toCurrencyName = toCurrencyName;
        this.exchangeRate = exchangeRate;
    }

    // Getters and setters for the fields

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromCurrencyName() {
        return fromCurrencyName;
    }

    public void setFromCurrencyName(String fromCurrencyName) {
        this.fromCurrencyName = fromCurrencyName;
    }

    public String getToCurrencyName() {
        return toCurrencyName;
    }

    public void setToCurrencyName(String toCurrencyName) {
        this.toCurrencyName = toCurrencyName;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

}
