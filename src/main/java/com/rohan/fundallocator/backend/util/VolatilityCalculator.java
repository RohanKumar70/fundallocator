package com.rohan.fundallocator.backend.util;

import java.util.List;

public class VolatilityCalculator {

    public static double calculateVolatility(List<Double> prices) {
        if (prices == null || prices.size() < 2) return 0.0;

        // 1️⃣ Compute daily returns
        double[] returns = new double[prices.size() - 1];
        for (int i = 1; i < prices.size(); i++) {
            returns[i - 1] = (prices.get(i) - prices.get(i - 1)) / prices.get(i - 1);
        }

        // 2️⃣ Compute mean of returns
        double mean = 0.0;
        for (double r : returns) mean += r;
        mean /= returns.length;

        // 3️⃣ Compute standard deviation
        double sumSq = 0.0;
        for (double r : returns) sumSq += Math.pow(r - mean, 2);
        double stdDev = Math.sqrt(sumSq / (returns.length - 1));

        // 4️⃣ Annualize (approx. 252 trading days)
        System.out.println(stdDev);
        System.out.println(stdDev * Math.sqrt(252));
        return stdDev * Math.sqrt(252);

    }
}