package com.itqgroup.service.app.domain.service.Impl;

import com.itqgroup.service.api.DocumentsParameters;
import com.itqgroup.service.app.domain.model.DocumentSummary;
import com.itqgroup.service.app.domain.repository.DocumentRepository;
import com.itqgroup.service.app.domain.repository.Pagination;
import com.itqgroup.service.app.domain.service.DocumentDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


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
    public List<DocumentSummary> getByIds(List<String> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public List<String> getIdsByStatus(String status) {
        return repository.findIdsByStatus(status);
    }

    public List<DocumentSummary> findAllByPagination(Pagination pagination) {
        try {
            return repository.findAll(pagination).getContent();
        } catch (Exception exception) {
            log.warn("find all by pagination Exception: {}", exception);
            return null;
        }
    }

    public List<DocumentSummary> getByParameters(DocumentsParameters parameters) {
        try {
            if (parameters == null) {
                return null;
            }
            else if (parameters.getStatus() != null && parameters.getAuthor() != null
                    && parameters.getStart_date() != 0L && parameters.getEnd_date() != 0L) {
                Date startDate = new Date(parameters.getStart_date());
                Date endDate = new Date(parameters.getEnd_date());
                return repository.findAllByParameters(parameters.getStatus(), parameters.getAuthor(), startDate, endDate);
            }
            else if (parameters.getStatus() != null) {
                return repository.findAllByStatus(parameters.getStatus());
            }
            else if (parameters.getAuthor() != null) {
                return repository.findAllByAuthor(parameters.getAuthor());
            }
            else if (parameters.getStart_date() != 0L && parameters.getEnd_date() != 0L) {
                Date startDate = new Date(parameters.getStart_date());
                Date endDate = new Date(parameters.getEnd_date());
                return repository.findAllByDates(startDate, endDate);
            }
            return null;
        } catch (Exception exception) {
            log.warn("find all by parameters Exception: {}", exception);
            return null;
        }
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
