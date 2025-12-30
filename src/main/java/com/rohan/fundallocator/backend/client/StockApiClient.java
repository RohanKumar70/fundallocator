package com.rohan.fundallocator.backend.client;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class StockApiClient {

    private static final String BASE_URL = "https://api.twelvedata.com";
    private static final String API_KEY = "YOUR_API_KEY";

    private static RestTemplate restTemplate = null;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public StockApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Map<String, String>> fetchTimeSeries(String symbol) {

        String url = BASE_URL +
                "/time_series?symbol=" + symbol +
                "&interval=1day&outputsize=60&apikey=" + API_KEY;

        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        if (response == null || !response.containsKey("values")) {
            return List.of();
        }

        return (List<Map<String, String>>) response.get("values");
    }

    public static double fetchCurrentPrice(String symbol) {

        String url = BASE_URL +
                "/time_series?symbol=" + symbol +
                "&interval=1min&apikey=" + API_KEY;

        try {
            String response = restTemplate.getForObject(url, String.class);

            JsonNode root = objectMapper.readTree(response);
            JsonNode values = root.get("values");

            if (values != null && values.isArray() && values.size() > 0) {
                return values.get(0).get("close").asDouble();
            }

        } catch (Exception e) {
            System.err.println("Price fetch failed for " + symbol);
            e.printStackTrace();
        }

        return 0;
    }
    public List<Double> fetchHistoricalCloses(String symbol) {

        String url = BASE_URL +
                "/time_series?symbol=" + symbol +
                "&interval=1day&outputsize=30&apikey=" + API_KEY;

        List<Double> prices = new ArrayList<>();

        try {
            String response = restTemplate.getForObject(url, String.class);
            JsonNode root = objectMapper.readTree(response);
            JsonNode values = root.get("values");

            if (values != null && values.isArray()) {
                for (JsonNode node : values) {
                    prices.add(node.get("close").asDouble());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return prices;
    }

}
