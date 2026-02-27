package com.itqgroup.service.app.domain.service;

import com.itqgroup.service.api.DocumentsParameters;
import com.itqgroup.service.app.domain.model.DocumentSummary;
import com.itqgroup.service.app.domain.repository.Pagination;

import java.util.List;

public interface DocumentDomainService {

    DocumentSummary getById(String id);
    long getCountDocs();
    List<DocumentSummary> getByIds(List<String> ids);
    List<String> getIdsByStatus(String status);
    void create(DocumentSummary summary);
    void submitted(DocumentSummary summary);
    List<DocumentSummary> findAllByPagination(Pagination pagination);
    List<DocumentSummary> getByParameters(DocumentsParameters parameters);
}
