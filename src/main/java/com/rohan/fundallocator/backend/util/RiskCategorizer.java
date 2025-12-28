package com.rohan.fundallocator.backend.util;
import com.rohan.fundallocator.backend.model.RiskLevel;

public class RiskCategorizer {

    public static RiskLevel determineRisk(double volatility) {
        if (volatility < 0.2) return RiskLevel.LOW;
        if (volatility <= 0.35) return RiskLevel.MEDIUM;
        return RiskLevel.HIGH;
    }
}
