package com.rohan.fundallocator.backend.model;

public class Stock {

    private String symbol;
    private double currentPrice;
    private double volatility;
    private RiskLevel riskLevel;

    public Stock(String symbol, double currentPrice, double volatility, RiskLevel riskLevel) {
        this.symbol = symbol;
        this.currentPrice = currentPrice;
        this.volatility = volatility;
        this.riskLevel = riskLevel;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public double getVolatility() {
        return volatility;
    }

    public RiskLevel getRiskLevel() {
        return riskLevel;
    }
}
