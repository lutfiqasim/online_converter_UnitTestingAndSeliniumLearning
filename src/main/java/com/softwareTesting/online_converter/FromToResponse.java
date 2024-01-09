package com.softwareTesting.online_converter;

public class FromToResponse {
    String responseCode;
    private String result = "";
    private double exchangeRate = 0.0;

    public FromToResponse() {
    }

    public FromToResponse(String responseCode) {
        this.responseCode = responseCode;
    }

    public FromToResponse(String result, double exchangeRate, String responseCode) {
        this.result = result;
        this.exchangeRate = exchangeRate;
        this.responseCode = responseCode;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    @Override
    public String toString() {
        return "FromToResponse{" +
                "responseCode='" + responseCode + '\'' +
                ", result='" + result + '\'' +
                ", exchangeRate=" + exchangeRate +
                '}';
    }
}
