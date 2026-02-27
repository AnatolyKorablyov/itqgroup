package com.itqgroup.service.app.controller;

import com.itqgroup.service.api.ApprovedStatus;
import com.itqgroup.service.api.ConcurrencyParameters;
import com.itqgroup.service.app.application.ConcurrencyApplicationService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
@Transactional
public class ConcurrencyController {

    private final ConcurrencyApplicationService concurrencyApplicationService;
    
    @PostMapping(path = "/concurrency")
    public ResponseEntity<Object> create(@Valid @RequestBody ConcurrencyParameters concurrencyParameters) {
        Instant start = Instant.now();
        Map<String, List<Map<String, ApprovedStatus>>> result = concurrencyApplicationService.checkConcurrency(concurrencyParameters);
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Execution time in milliseconds: " + timeElapsed.toMillis());
        return ResponseEntity.ok(result);
    }
}
