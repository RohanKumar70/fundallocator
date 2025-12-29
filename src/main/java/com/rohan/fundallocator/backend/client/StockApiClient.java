package com.rohan.fundallocator.backend.client;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;

@Component
public class StockApiClient {

    private static final String API_KEY = "a6cf0888eeff46d2a583ad6c70814d10";
    private static final String BASE_URL = "https://api.twelvedata.com";

    private final RestTemplate restTemplate = new RestTemplate();

    @SuppressWarnings("unchecked")

    public List<Map<String, String>> fetchTimeSeries(String symbol) {

        String url = BASE_URL
                + "/time_series"
                + "?symbol=" + symbol
                + "&interval=1day"
                + "&outputsize=60"
                + "&apikey=" + API_KEY;

        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        if (response == null || !response.containsKey("values")) {
            return List.of();
        }

        return (List<Map<String, String>>) response.get("values");
    }
}