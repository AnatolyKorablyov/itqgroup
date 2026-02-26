package com.itqgroup.service.app.application;

import com.itqgroup.service.api.DocumentOutDto;
import com.itqgroup.service.api.DocumentsParameters;
import com.itqgroup.service.app.domain.model.DocumentSummary;

import java.util.List;
import java.util.Optional;

public interface DocumentQueryService {

    List<DocumentSummary> getDocumentsByFilter(DocumentsParameters parameters);
//    List<DocumentSummary> getDocuments(List<String> ids);

    List<DocumentSummary> getDocuments(Optional<List<String>> ids,
                                       Optional<Integer> offset,
                                       Optional<Integer> limit,
                                       Optional<String> sortOrder);

    DocumentOutDto getDocumentById(String documentId);
    List<String> getIdsByStatus(String status);
}
