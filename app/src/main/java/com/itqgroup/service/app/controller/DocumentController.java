package com.itqgroup.service.app.controller;

import com.itqgroup.service.api.*;
import com.itqgroup.service.app.application.DocumentApplicationService;
import com.itqgroup.service.app.application.DocumentQueryService;
import com.itqgroup.service.app.domain.model.DocumentSummary;
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
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
@Transactional
public class DocumentController {

    private final DocumentQueryService queryService;
    private final DocumentApplicationService applicationService;
    
    @PostMapping(path = "/document")
    public ResponseEntity<Object> create(@Valid @RequestBody DocumentDto dto) {
        Instant start = Instant.now();
        String id = applicationService.create(dto);
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Execution time in milliseconds: " + timeElapsed.toMillis());
        return ResponseEntity.ok(id);
    }

    @GetMapping(path = "/documentById")
    public ResponseEntity<DocumentOutDto> documentById(@RequestParam String id) {
        Instant start = Instant.now();
        DocumentOutDto result = queryService.getDocumentById(id);
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Execution time in milliseconds: " + timeElapsed.toMillis());
        return ResponseEntity.ok(result);
    }

    @GetMapping(path = "/documentsByIds")
    public ResponseEntity<List<DocumentSummary>> getDocuments(@RequestParam Optional<List<String>> ids,
                                                              @RequestParam Optional<Integer> offset,
                                                              @RequestParam Optional<Integer> limit,
                                                              @RequestParam Optional<String> sortOrder) {
        Instant start = Instant.now();
        List<DocumentSummary> result = queryService.getDocuments(ids, offset, limit, sortOrder);
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Execution time in milliseconds: " + timeElapsed.toMillis());
        return ResponseEntity.ok(result);
    }

    @GetMapping(path = "/documents")
    public ResponseEntity<List<DocumentSummary>> getDocuments(DocumentsParameters parameters) {
        Instant start = Instant.now();
        List<DocumentSummary> result = queryService.getDocumentsByFilter(parameters);
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Execution time in milliseconds: " + timeElapsed.toMillis());
        return ResponseEntity.ok(result);
    }

    @PostMapping(path = "/submitted")
    public ResponseEntity<Object> submitted(@Valid @RequestBody StatusTransferParameters statusTransferParameters) {
        Instant start = Instant.now();
        Map<String, SubmittedStatus> result = applicationService.submitted(statusTransferParameters);
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Execution time in milliseconds: " + timeElapsed.toMillis());
        return ResponseEntity.ok(result);
    }

    @PostMapping(path = "/approved")
    public ResponseEntity<Object> approved(@Valid @RequestBody StatusTransferParameters statusTransferParameters) {
        Instant start = Instant.now();
        Map<String, ApprovedStatus> result = applicationService.approved(statusTransferParameters);
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Execution time in milliseconds: " + timeElapsed.toMillis());
        return ResponseEntity.ok(result);
    }


}
