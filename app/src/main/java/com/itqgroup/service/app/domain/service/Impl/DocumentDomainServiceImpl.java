package com.itqgroup.service.app.domain.service.Impl;

import com.itqgroup.service.app.domain.model.DocumentSummary;
import com.itqgroup.service.app.domain.repository.DocumentRepository;
import com.itqgroup.service.app.domain.service.DocumentDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DocumentDomainServiceImpl implements DocumentDomainService {

    private static final String CMD_LOG = "projecting {}";
    private final DocumentRepository repository;

    @Override
    public DocumentSummary getById(String id) {
        return repository.getById(id);
    }

    @Override
    public void create(DocumentSummary summary) {
        log.info(CMD_LOG, summary);
        repository.save(summary);
        log.debug("SUCCESS SAVE: {}", summary);
    }

    @Override
    public void submitted(DocumentSummary summary){
        log.info(CMD_LOG, summary);
        repository.save(summary);
        log.debug("SUCCESS SAVE: {}", summary);
    }
}
