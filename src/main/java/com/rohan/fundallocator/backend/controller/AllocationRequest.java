package com.rohan.fundallocator.backend.controller;

import com.rohan.fundallocator.backend.model.RiskLevel;
import java.util.List;

public class AllocationRequest {

    private List<String> symbols;
    private double totalAmount;
    private RiskLevel riskLevel;

    public AllocationRequest() {} // REQUIRED

    public List<String> getSymbols() {
        return symbols;
    }
    public void setSymbols(List<String> symbols) {
        this.symbols = symbols;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public RiskLevel getRiskLevel() {
        return riskLevel;
    }
    public void setRiskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
    }
}
