package com.rohan.fundallocator.backend.service;
import com.rohan.fundallocator.backend.model.AllocationResult;
import com.rohan.fundallocator.backend.model.RiskLevel;
import org.springframework.stereotype.Service;
import com.rohan.fundallocator.backend.model.Stock;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;

@Service
public class AllocationService {

    private final StockService stockService;

    public AllocationService(StockService stockService) {
        this.stockService = stockService;
    }

    public List<AllocationResult> allocate(List<String> symbols, double totalAmount, RiskLevel riskLevel) {

        List<AllocationResult> results = new ArrayList<>();
        double perStock = totalAmount / symbols.size();

        for (String symbol : symbols) {
            double mockPrice = 150.0 + Math.random() * 100;
            double mockVolatility = Math.random() * .05;
            results.add(new AllocationResult(symbol, riskLevel, perStock, mockPrice, mockVolatility));
      }

        return results;
    }
}