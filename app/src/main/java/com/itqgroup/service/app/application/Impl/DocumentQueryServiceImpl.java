package com.itqgroup.service.app.application.Impl;

import com.itqgroup.service.api.DocumentOutDto;
import com.itqgroup.service.app.application.DocumentQueryService;
import com.itqgroup.service.app.domain.repository.HistoryRepository;
import com.itqgroup.service.app.utils.DocumentMapper;
import com.itqgroup.service.app.domain.model.DocumentSummary;
import com.itqgroup.service.app.domain.repository.DocumentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DocumentQueryServiceImpl implements DocumentQueryService {

    private final DocumentRepository documentRepository;
    private final HistoryRepository historyRepository;
    private final DocumentMapper mapper = Mappers.getMapper(DocumentMapper.class);

    private static String ASCENDING = "asc";

    @Override
    public List<DocumentSummary> getDocuments() {
        return documentRepository.findAll();
    }

    @Override
    public DocumentOutDto getDocumentById(String documentId) {
        try {
            return mapper.map(documentRepository.getOne(documentId));
        } catch (EntityNotFoundException ex) {
            log.info("synchronization: document by id not found");
            throw ex;
        }
    }
}
