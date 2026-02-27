package com.itqgroup.service.app.application.Impl;

import com.itqgroup.service.api.*;
import com.itqgroup.service.app.application.DocumentApplicationService;
import com.itqgroup.service.app.domain.model.DocumentSummary;
import com.itqgroup.service.app.domain.model.HistorySummary;
import com.itqgroup.service.app.domain.model.StatementRegisterSummary;
import com.itqgroup.service.app.domain.service.DocumentDomainService;
import com.itqgroup.service.app.domain.service.HistoryDomainService;
import com.itqgroup.service.app.domain.service.StatementRegisterDomainService;
import com.itqgroup.service.app.utils.DocumentMapper;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.*;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class DocumentApplicationServiceImpl implements DocumentApplicationService {

  private static final String HANDLE_LOG = "handle dto {}";
  private static final String WARN_LOG = "Exception: {}";
  private final DocumentDomainService documentDomainService;
  private final HistoryDomainService historyDomainService;
  private final StatementRegisterDomainService statementRegisterDomainService;

  private final DocumentMapper mapper = Mappers.getMapper(DocumentMapper.class);

  @Override
  public String create(DocumentDto dto) throws EntityExistsException {
    log.debug(HANDLE_LOG, dto);
    DocumentSummary documentSummary = mapper.map(dto);
    documentSummary.setCreateDate(new Date());
    documentSummary.setStatus(Status.DRAFT.getStatusName());
    documentSummary.setNumber(documentDomainService.getCountDocs() + 1);
    documentDomainService.create(documentSummary);
    return dto.getId();
  }

  @Override
  public Map<String, SubmittedStatus> submitted(StatusTransferParameters stp) {
    log.debug(HANDLE_LOG, stp);
    Map<String, SubmittedStatus> agreements = new HashMap<>();
    for (String id : stp.getIds()) {
      try {
        DocumentSummary summary = documentDomainService.getById(id);
        if (summary == null) {
          agreements.put(id, SubmittedStatus.NOT_FOUND);
        } else if (summary.getStatus().equals(Status.DRAFT.toString())) {
          summary.setStatus(Status.SUBMITTED.getStatusName());
          summary.setUpdateDate(new Date());
          documentDomainService.submitted(summary);

          HistorySummary historySummary = getHistorySummary(id, stp.getAuthor(), stp.getComment());
          historySummary.setAction(Action.SUBMIT.getStatusName());
          historyDomainService.create(historySummary);

          agreements.put(id, SubmittedStatus.SUCCESS);
        } else {
          agreements.put(id, SubmittedStatus.CONFLICT);
        }
      } catch (Exception e) {
        log.warn(WARN_LOG, e.getMessage());
        agreements.put(id, SubmittedStatus.CONFLICT);
      }
    }
    return agreements;
  }

  @Override
  public Map<String, ApprovedStatus> approved(StatusTransferParameters stp) {
    log.debug(HANDLE_LOG, stp);
    Map<String, ApprovedStatus> agreements = new HashMap<>();

    for (String id : stp.getIds()) {
      try {
        DocumentSummary summary = documentDomainService.getById(id);
        if (summary == null) {
          agreements.put(id, ApprovedStatus.NOT_FOUND);
        } else if (summary.getStatus().equals(Status.SUBMITTED.toString())) {
          summary.setStatus(Status.APPROVED.getStatusName());
          summary.setUpdateDate(new Date());
          documentDomainService.submitted(summary);
          HistorySummary historySummary = getHistorySummary(id, stp.getAuthor(), stp.getComment());
          historySummary.setAction(Action.APPROVE.getStatusName());
          historyDomainService.create(historySummary);

          StatementRegisterSummary statementRegisterSummary = new StatementRegisterSummary();
          statementRegisterSummary.setId(UUID.randomUUID().toString());
          statementRegisterSummary.setIdDocument(id);
          statementRegisterSummary.setAuthor(stp.getAuthor());
          statementRegisterSummary.setDate(new Date());
          statementRegisterDomainService.create(statementRegisterSummary);

          agreements.put(id, ApprovedStatus.SUCCESS);
        } else {
          agreements.put(id, ApprovedStatus.CONFLICT);
        }
      } catch (Exception e) {
        log.warn(WARN_LOG, e.getMessage());
        agreements.put(id, ApprovedStatus.CONFLICT);
      }
    }
    return agreements;
  }

  private HistorySummary getHistorySummary(String id, String author, String comment) {
    HistorySummary historySummary = new HistorySummary();
    historySummary.setId(UUID.randomUUID().toString());
    historySummary.setIdDocument(id);
    historySummary.setDate(new Date());
    historySummary.setComment(comment);
    historySummary.setAuthor(author);
    return historySummary;
  }
}
