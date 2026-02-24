package com.itqgroup.service.app.domain.repository;

import com.itqgroup.service.app.domain.model.DocumentSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<DocumentSummary, String> {
}
