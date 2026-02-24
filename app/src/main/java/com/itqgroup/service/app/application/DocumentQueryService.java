package com.itqgroup.service.app.application;

import com.itqgroup.service.api.DocumentOutDto;
import com.itqgroup.service.app.domain.model.DocumentSummary;

import java.util.List;

public interface DocumentQueryService {

    List<DocumentSummary> getDocuments();

    DocumentOutDto getDocumentById(String documentId);
}
