package com.rohan.fundallocator.backend.service;

import com.rohan.fundallocator.backend.client.StockApiClient;
import com.rohan.fundallocator.backend.model.Stock;
import com.rohan.fundallocator.backend.util.RiskCategorizer;
import com.rohan.fundallocator.backend.util.VolatilityCalculator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    private final StockApiClient stockApiClient;

    public StockService(StockApiClient stockApiClient) {
        this.stockApiClient = stockApiClient;
    }

    public List<Stock> getStocksWithRisk() {

        List<Stock> stocks = stockApiClient.getSp500Stocks();

        for (Stock stock : stocks) {
            var prices = stockApiClient.getHistoricalPrices(stock.getSymbol());

            double volatility = VolatilityCalculator.calculateVolatility(prices);
            stock.setVolatility(volatility);
            stock.setRiskLevel(RiskCategorizer.determineRisk(volatility));
        }

        return stocks;
    }
}
