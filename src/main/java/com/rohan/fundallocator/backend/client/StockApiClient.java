package com.rohan.fundallocator.backend.client;

import com.rohan.fundallocator.backend.model.Stock;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

@Component
public class StockApiClient {

    private final String API_KEY = "9vYAHl45vQhb3mWNiC6XQmpfYB0ft3Fo";
    private final String SP500_URL = "https://financialmodelingprep.com/api/v3/sp500_constituent?apikey=" + API_KEY;
    private final String HISTORICAL_URL_TEMPLATE = "https://financialmodelingprep.com/api/v3/historical-price-full/%s?timeseries=252&apikey=" + API_KEY;

    private final RestTemplate restTemplate = new RestTemplate();

    // 1️⃣ Fetch list of S&P 500 stocks
    public List<Stock> getSp500Stocks() {
        List<Stock> stockList = new ArrayList<>();

        // Make API call
        List<Map<String, Object>> response = restTemplate.getForObject(SP500_URL, List.class);

        for (Map<String, Object> stockMap : response) {
            String symbol = (String) stockMap.get("symbol");
            String name = (String) stockMap.get("name");

            stockList.add(new Stock(symbol, name, 0.0, null));
        }
        return stockList;
    }

    // 2️⃣ Fetch historical prices for one stock
    public List<Double> getHistoricalPrices(String symbol) {
        String url = String.format(HISTORICAL_URL_TEMPLATE, symbol);

        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        List<Map<String, Object>> historical = (List<Map<String, Object>>) response.get("historical");

        List<Double> prices = new ArrayList<>();
        if (historical != null) {
            for (Map<String, Object> day : historical) {
                prices.add(((Number) day.get("close")).doubleValue());
            }
        }
        return prices;
    }
}
