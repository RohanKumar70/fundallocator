package com.rohan.fundallocator.backend.service;
import com.rohan.fundallocator.backend.model.AllocationResult;
import org.springframework.stereotype.Service;
import com.rohan.fundallocator.backend.model.Stock;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class AllocationService {

    private final StockService stockService;

    public AllocationService(StockService stockService) {
        this.stockService = stockService;
    }

    public List<AllocationResult> allocateFunds(List<String> symbols, double totalAmount) {

        double perStockAmount = totalAmount / symbols.size();

        return symbols.stream()
                .map(stockService::analyzeStock)
                .map(stock -> new AllocationResult(
                        stock.getSymbol(),
                        stock.getRiskLevel(),
                        perStockAmount
                ))
                .collect(Collectors.toList());
    }
}