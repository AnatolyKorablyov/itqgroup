package com.itqgroup.service.app.domain.service;

import com.itqgroup.service.app.domain.model.DocumentSummary;
import jakarta.persistence.EntityExistsException;

public interface DocumentDomainService {

    DocumentSummary getById(String id);
    void create(DocumentSummary summary);
    void submitted(DocumentSummary summary);
}
