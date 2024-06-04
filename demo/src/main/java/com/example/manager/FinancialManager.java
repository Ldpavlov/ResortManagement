package com.example.manager;

import java.util.HashMap;
import java.util.Map;

import com.example.common.FinancialOperations;
import com.example.model.Country;

public class FinancialManager implements FinancialOperations {
    private Map<String, Double> skiPassPrices;
    private Map<String, Integer> skiPassSales;
    private double maintenanceCosts;
    private double electricityConsumed;
    private Country country;

    public FinancialManager(double maintenanceCosts, double electricityConsumed, Country country) {
        this.skiPassPrices = new HashMap<>();
        this.skiPassSales = new HashMap<>();
        this.maintenanceCosts = maintenanceCosts;
        this.electricityConsumed = electricityConsumed;
        this.country = country;
    }

    @Override
    public double getMaintenanceCosts() {
        return maintenanceCosts;
    }

    @Override
    public void addSkiPassType(String passType, double price) {
        skiPassPrices.put(passType, price);
    }

    @Override
    public void recordSkiPassSale(String passType, int quantity) {
        int currentSales = skiPassSales.getOrDefault(passType, 0);
        skiPassSales.put(passType, currentSales + quantity);
    }

    @Override
    public double getSkiPassPrice(String passType) {
        return skiPassPrices.getOrDefault(passType, 0.0);
    }

    @Override
    public double calculateTotalRevenueFromSkiPasses() {
        double totalRevenue = 0.0;
        for (Map.Entry<String, Integer> entry : skiPassSales.entrySet()) {
            double price = skiPassPrices.get(entry.getKey());
            int quantity = entry.getValue();
            totalRevenue += price * quantity;
        }
        return totalRevenue;
    }

    @Override
    public double calculateTotalCosts() {
        double electricityCosts = country.getElectricityPrice() * electricityConsumed;
        return electricityCosts + maintenanceCosts;
    }

    @Override
    public double calculateProfit() {
        double totalRevenue = calculateTotalRevenueFromSkiPasses();
        double totalCosts = calculateTotalCosts();
        return totalRevenue - totalCosts;
    }
}
