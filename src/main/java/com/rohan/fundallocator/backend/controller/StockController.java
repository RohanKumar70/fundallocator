package com.rohan.fundallocator.backend.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rohan.fundallocator.backend.model.Stock;
import com.rohan.fundallocator.backend.service.StockService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/{symbol}")
    public Stock getStock(@PathVariable String symbol) {
        return stockService.analyzeStock(symbol);
    }
}

