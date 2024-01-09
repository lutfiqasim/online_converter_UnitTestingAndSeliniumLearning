package com.softwareTesting.online_converter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Currency {

    @Id
    private String code;
    private String name;

    //default is 2
    private int numberOfDecimals = 2;

    //default is comma
    private char seperator = ',';


    // Add a default constructor
    public Currency() {
        // Default constructor
    }

    public Currency(String code, String name) {
        this.code = code;
        this.name = name;

    }

    public Currency(String code, String name, int numberOfDecimals) {
        this.code = code;
        this.name = name;
        this.numberOfDecimals = numberOfDecimals;
    }

    public Currency(String code, String name, char seperator) {
        this.code = code;
        this.name = name;
        this.seperator = seperator;
    }

    public Currency(String code, String name, int numberOfDecimals, char seperator) {
        this.code = code;
        this.name = name;
        this.numberOfDecimals = numberOfDecimals;
        this.seperator = seperator;
    }


    public int getNumberOfDecimals() {
        return numberOfDecimals;
    }

    public void setNumberOfDecimals(int numberOfDecimals) {
        this.numberOfDecimals = numberOfDecimals;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSeperator() {
        return seperator;
    }

    public void setSeperator(char seperator) {
        this.seperator = seperator;
    }

    public String formatAmount(double amount) {
        // Create a format string based on the specified number of decimals
        String formatString = "%." + numberOfDecimals + "f";

        // Format the amount using the format string
        String formattedAmount = String.format(formatString, amount);

        // If the separator is a comma, replace the decimal point with a comma
        if (seperator == ',') {
            formattedAmount = formattedAmount.replace('.', ',');
        }

        return formattedAmount;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", numberOfDecimals=" + numberOfDecimals +
                ", seperator=" + seperator +
                '}';
    }
}
