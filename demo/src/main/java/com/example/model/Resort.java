package com.example.model;

import java.util.ArrayList;
import java.util.List;

import com.example.manager.FinancialManager;

public class Resort {
    private String name;
    private int numberOfLifts;
    private int kmPistes;
    private Country country;
    private List<SkiSchool> skiSchools;
    private FinancialManager financialManager;
    private double percentageToResort;

    public Resort(String name, int numberOfLifts, int kmPistes, Country country, double maintenanceCosts,
            double electricityConsumed, double percentageToResort) {
        this.name = name;
        this.numberOfLifts = numberOfLifts;
        this.kmPistes = kmPistes;
        this.country = country;
        this.skiSchools = new ArrayList<>();
        this.percentageToResort = percentageToResort;
        this.financialManager = new FinancialManager(maintenanceCosts, electricityConsumed, country);
    }

    public double getPercentageToResort() {
        return this.percentageToResort;
    }

    public FinancialManager getFinancialManager() {
        return financialManager;
    }

    public double getProfit() {
        return financialManager.calculateProfit();
    }

    public void addSkiSchool(SkiSchool skiSchool) {
        skiSchools.add(skiSchool);
    }

    public void addSkiPassType(String passType, double price) {
        financialManager.addSkiPassType(passType, price);
    }

    public void recordSkiPassSale(String passType, int quantity) {
        financialManager.recordSkiPassSale(passType, quantity);
    }

    public double getPriceForSkiPassType(String passType) {
        return financialManager.getSkiPassPrice(passType);
    }

    public List<SkiSchool> getAllSkiSchools() {
        return skiSchools;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfLifts() {
        return numberOfLifts;
    }

    public int getKmPistes() {
        return kmPistes;
    }

    @Override
    public String toString() {
        return "Resort: " + name + ", Number of lifts: " + numberOfLifts + ", Km/Pistes: " + kmPistes + ", Country: "
                + country;
    }

}
