package com.itqgroup.service.app.controller;

import com.itqgroup.service.api.DocumentDto;
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

    @PostMapping(path = "/submitted")
    public ResponseEntity<Object> submitted(@Valid @RequestBody StatusTransferParameters statusTransferParameters) {
        return ResponseEntity.ok(applicationService.submitted(statusTransferParameters));
    }

    @PostMapping(path = "/approved")
    public ResponseEntity<Object> approved(@Valid @RequestBody StatusTransferParameters statusTransferParameters) {
        return ResponseEntity.ok(applicationService.approved(statusTransferParameters));
    }

    @GetMapping(path = "/documentById")
    public ResponseEntity documentById(@RequestParam String id) {
        return ResponseEntity.ok(queryService.getDocumentById(id));
    }
//
//    @GetMapping(path = "/documentsByIds")
//    public ResponseEntity<List<DocumentOutDto>> getDocuments(@RequestParam(name="sortOrder", required = false) String sortOrder) {
//        return ResponseEntity.ok(documentQueryService.getSortedDocuments(initiator, sort, limit, sortOrder));
//    }

    @GetMapping(path = "/documents")
    public ResponseEntity<List<DocumentSummary>> getDocuments(@RequestParam(name="initiator", required = false) String initiator,
                                                              @RequestParam String sort,
                                                              @RequestParam int limit,
                                                              @RequestParam(name="sortOrder", required = false) String sortOrder) {
        return ResponseEntity.ok(queryService.getDocuments());
    }
}
