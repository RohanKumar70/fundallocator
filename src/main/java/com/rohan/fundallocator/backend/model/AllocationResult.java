package com.rohan.fundallocator.backend.model;

public class AllocationResult {

    private final String symbol;
    private final RiskLevel riskLevel;
    private final double allocatedAmount;

    // Constructor
    public AllocationResult(String symbol, RiskLevel riskLevel, double allocatedAmount) {
        this.symbol = symbol;
        this.riskLevel = riskLevel;
        this.allocatedAmount = allocatedAmount;
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
}
