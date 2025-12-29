package com.rohan.fundallocator.backend.util;
import com.rohan.fundallocator.backend.model.RiskLevel;

public class RiskCategorizer {

    public static RiskLevel categorize(double volatility) {
        if (volatility < 2.0) {
            return RiskLevel.LOW;
        } else if (volatility < 5.0) {
            return RiskLevel.MEDIUM;
        } else {
            return RiskLevel.HIGH;
        }
    }
}
