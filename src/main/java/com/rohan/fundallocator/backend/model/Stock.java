package com.rohan.fundallocator.backend.model;

public class Stock {

    private String symbol;
    private String name;
    private double volatility;
    private RiskLevel riskLevel;

    // Constructor for basic stock info (API response)
    public Stock(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    // Full constructor (after calculations)
    public Stock(String symbol, String name, double volatility, RiskLevel riskLevel) {
        this.symbol = symbol;
        this.name = name;
        this.volatility = volatility;
        this.riskLevel = riskLevel;
    }

    // Getters
    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public double getVolatility() {
        return volatility;
    }

    public RiskLevel getRiskLevel() {
        return riskLevel;
    }

    // Setters
    public void setVolatility(double volatility) {
        this.volatility = volatility;
    }

    public void setRiskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
    }
}
