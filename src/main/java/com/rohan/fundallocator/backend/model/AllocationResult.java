package com.rohan.fundallocator.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AllocationResult {

    private final String symbol;
    private final RiskLevel riskLevel;
    private final double allocatedAmount;
    private double currentPrice;
    private double volatility;

    // Constructor
    public AllocationResult(String symbol, RiskLevel riskLevel, double allocatedAmount, double currentPrice, double volatility) {
        this.symbol = symbol;
        this.riskLevel = riskLevel;
        this.allocatedAmount = allocatedAmount;
        this.currentPrice = currentPrice;
        this.volatility = volatility;
    }

    // Getters
    public String getSymbol() {
        return symbol;
    }

    public RiskLevel getRiskLevel() {
        return riskLevel;
    }

    public double getAllocatedAmount() {
        return allocatedAmount;
    }
    public double getCurrentPrice() {
        return currentPrice;
    }
    public double getVolatility() {
        return volatility;
    }
}
