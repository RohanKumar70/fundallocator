package com.rohan.fundallocator.backend.controller;

import com.rohan.fundallocator.backend.model.AllocationResult;
import com.rohan.fundallocator.backend.model.RiskLevel;
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
    public List<AllocationResult> allocate(
            @RequestParam double totalMoney,
            @RequestParam RiskLevel riskLevel) {
        return allocationService.allocate(totalMoney, riskLevel);
    }
}
