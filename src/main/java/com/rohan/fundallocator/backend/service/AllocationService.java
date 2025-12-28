package com.rohan.fundallocator.backend.service;

import com.rohan.fundallocator.backend.client.StockApiClient;
import com.rohan.fundallocator.backend.model.AllocationResult;
import com.rohan.fundallocator.backend.model.RiskLevel;
import com.rohan.fundallocator.backend.model.Stock;
import com.rohan.fundallocator.backend.util.RiskCategorizer;
import com.rohan.fundallocator.backend.util.VolatilityCalculator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AllocationService {

    private final StockApiClient stockApiClient;

    public AllocationService(StockApiClient stockApiClient) {
        this.stockApiClient = stockApiClient;
    }

    public List<AllocationResult> allocate(double totalMoney, RiskLevel desiredRisk) {

        List<AllocationResult> results = new ArrayList<>();

        // 1️⃣ Get all S&P 500 stocks
        List<Stock> stocks = stockApiClient.getSp500Stocks();

        // 2️⃣ Calculate volatility & risk for each
        for (Stock stock : stocks) {
            List<Double> prices = stockApiClient.getHistoricalPrices(stock.getSymbol());
            double volatility = VolatilityCalculator.calculateVolatility(prices);
            stock.setVolatility(volatility);
            stock.setRiskLevel(RiskCategorizer.determineRisk(volatility));
        }

        // 3️⃣ Filter by desired risk
        List<Stock> filtered = stocks.stream()
                .filter(s -> s.getRiskLevel() == desiredRisk)
                .toList();

        if (filtered.isEmpty()) return results;

        // 4️⃣ Divide money equally
        double perStock = totalMoney / filtered.size();
        for (Stock stock : filtered) {
            results.add(new AllocationResult(stock.getSymbol(), stock.getRiskLevel(), perStock));
        }

        return results;
    }
}