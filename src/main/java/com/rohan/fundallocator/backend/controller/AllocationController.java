package com.rohan.fundallocator.backend.controller;
import com.rohan.fundallocator.backend.model.AllocationResult;
import com.rohan.fundallocator.backend.service.AllocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/allocate")
public class AllocationController {

    private final AllocationService allocationService;

    public AllocationController(AllocationService allocationService) {
        this.allocationService = allocationService;
    }
    @PostMapping
    public List<AllocationResult> allocate(
            @RequestParam List<String> symbols,
            @RequestParam double amount
    ) {
        return allocationService.allocateFunds(symbols, amount);
    }
}