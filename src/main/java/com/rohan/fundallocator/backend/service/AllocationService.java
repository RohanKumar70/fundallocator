package com.rohan.fundallocator.backend.service;
import com.rohan.fundallocator.backend.model.AllocationResult;
import com.rohan.fundallocator.backend.model.RiskLevel;
import com.rohan.fundallocator.backend.util.RiskCategorizer;
import com.rohan.fundallocator.backend.util.VolatilityCalculator;
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

    public List<AllocationResult> allocate(
            List<String> symbols,
            double totalAmount,
            RiskLevel userRisk
    ) {

        double perStock = totalAmount / symbols.size();
        List<AllocationResult> results = new ArrayList<>();

        for (String symbol : symbols) {

            Stock stock = stockService.buildStock(symbol);

            results.add(new AllocationResult(
                    stock.getSymbol(),
                    stock.getRiskLevel(),
                    perStock,
                    stock.getCurrentPrice(),
                    stock.getVolatility()
            ));
        }

        return results;
    }
}
