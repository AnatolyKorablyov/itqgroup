package com.itqgroup.service.app.controller;

import com.itqgroup.service.api.ConcurrencyParameters;
import com.itqgroup.service.app.application.ConcurrencyApplicationService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
@Transactional
public class ConcurrencyController {

    private final ConcurrencyApplicationService concurrencyApplicationService;
    
    @PostMapping(path = "/concurrency")
    public ResponseEntity<Object> create(@Valid @RequestBody ConcurrencyParameters concurrencyParameters) {
        concurrencyApplicationService.checkConcurrency(concurrencyParameters);
        return ResponseEntity.ok().build();
    }
}
