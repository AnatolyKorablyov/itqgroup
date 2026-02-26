package com.itqgroup.service.app.controller;

import com.itqgroup.service.api.DocumentDto;
import com.itqgroup.service.api.DocumentOutDto;
import com.itqgroup.service.api.DocumentsParameters;
import com.itqgroup.service.api.StatusTransferParameters;
import com.itqgroup.service.app.application.DocumentApplicationService;
import com.itqgroup.service.app.application.DocumentQueryService;
import com.itqgroup.service.app.domain.model.DocumentSummary;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        applicationService.create(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/documentById")
    public ResponseEntity<DocumentOutDto> documentById(@RequestParam String id) {
        return ResponseEntity.ok(queryService.getDocumentById(id));
    }

//    @GetMapping(path = "/documentsByIds")
//    public ResponseEntity<List<DocumentSummary>> getDocuments(@RequestParam List<String> ids) {
//        return ResponseEntity.ok(queryService.getDocuments(ids));
//    }

    @GetMapping(path = "/documentsByIds")
    public ResponseEntity<List<DocumentSummary>> getDocuments(@RequestParam Optional<List<String>> ids,
                                                              @RequestParam Optional<Integer> offset,
                                                              @RequestParam Optional<Integer> limit,
                                                              @RequestParam Optional<String> sortOrder) {
        return ResponseEntity.ok(queryService.getDocuments(ids, offset, limit, sortOrder));
    }

    @GetMapping(path = "/documents")
    public ResponseEntity<List<DocumentSummary>> getDocuments(DocumentsParameters parameters) {
        return ResponseEntity.ok(queryService.getDocumentsByFilter(parameters));
    }

    @PostMapping(path = "/submitted")
    public ResponseEntity<Object> submitted(@Valid @RequestBody StatusTransferParameters statusTransferParameters) {
        return ResponseEntity.ok(applicationService.submitted(statusTransferParameters));
    }

    @PostMapping(path = "/approved")
    public ResponseEntity<Object> approved(@Valid @RequestBody StatusTransferParameters statusTransferParameters) {
        return ResponseEntity.ok(applicationService.approved(statusTransferParameters));
    }


}
