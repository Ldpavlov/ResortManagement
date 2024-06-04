package com.example.common;

public interface FinancialOperations {
    double getMaintenanceCosts();

    void addSkiPassType(String passType, double price);

    void recordSkiPassSale(String passType, int quantity);

    double getSkiPassPrice(String passType);

    double calculateTotalRevenueFromSkiPasses();

    double calculateTotalCosts();

    double calculateProfit();
}
