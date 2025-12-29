package com.rohan.fundallocator.backend.controller;
import com.rohan.fundallocator.backend.model.AllocationResult;
import com.rohan.fundallocator.backend.service.AllocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AllocationController {

    private final AllocationService allocationService;

    public AllocationController(AllocationService allocationService) {
        this.allocationService = allocationService;
    }

    @PostMapping("/allocate")
    public List<AllocationResult> allocate(@RequestBody AllocationRequest request) {
        System.out.println("Received symbols: " + request.getSymbols());
        System.out.println("Received amount: " + request.getTotalAmount());
        System.out.println("Received risk: " + request.getRiskLevel());
        return allocationService.allocate(
                request.getSymbols(),
                request.getTotalAmount(),
                request.getRiskLevel()
        );
    }
}