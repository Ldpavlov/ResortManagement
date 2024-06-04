package com.example.model;

public enum Country {
    BULGARIA(0.105),
    USA(0.15),
    NORWAY(0.099),
    AUSTRIA(0.202);

    private final double electricityPrice;

    Country(double electricityPrice) {
        this.electricityPrice = electricityPrice;
    }

    public double getElectricityPrice() {
        return electricityPrice;
    }

}