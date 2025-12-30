package com.rohan.fundallocator.backend.service;
import com.rohan.fundallocator.backend.client.StockApiClient;
import com.rohan.fundallocator.backend.model.Stock;
import com.rohan.fundallocator.backend.util.RiskCategorizer;
import com.rohan.fundallocator.backend.model.RiskLevel;
import com.rohan.fundallocator.backend.util.VolatilityCalculator;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class StockService {

    private final StockApiClient stockApiClient;

    public StockService(StockApiClient stockApiClient) {
        this.stockApiClient = stockApiClient;
    }

    public Stock analyzeStock(String symbol) {

        List<Map<String, String>> timeSeries =
                stockApiClient.fetchTimeSeries(symbol);

        if (timeSeries.isEmpty()) {
            return new Stock(symbol, 0.0, 0.0, RiskLevel.LOW);
        }

        // Extract close prices safely
        List<Double> closePrices = timeSeries.stream()
                .map(entry -> entry.get("close"))
                .filter(c -> c != null && !c.isBlank())
                .map(Double::parseDouble)
                .toList();

        if (closePrices.size() < 2) {
            double price = closePrices.isEmpty() ? 0.0 : closePrices.get(0);
            return new Stock(symbol, price, 0.0, RiskLevel.LOW);
        }

        // Latest close = current price
        double currentPrice = closePrices.get(0);

        List<Double> prices = stockApiClient.fetchHistoricalCloses(symbol);
        double volatility = VolatilityCalculator.calculateVolatility(prices);
        RiskLevel riskLevel = RiskCategorizer.categorize(volatility);

        return new Stock(symbol, currentPrice, volatility, riskLevel);



    }
    public double getCurrentPrice(String symbol) {
        return StockApiClient.fetchCurrentPrice(symbol);
    }
    public Stock buildStock(String symbol) {

        double currentPrice = StockApiClient.fetchCurrentPrice(symbol);

        List<Double> prices = stockApiClient.fetchHistoricalCloses(symbol);
        double volatility = VolatilityCalculator.calculateVolatility(prices);

        RiskLevel riskLevel = RiskCategorizer.categorize(volatility);

        return new Stock(symbol, currentPrice, volatility, riskLevel);
    }
}
