package com.itqgroup.service.app.domain.service.Impl;

import com.itqgroup.service.app.domain.model.HistorySummary;
import com.itqgroup.service.app.domain.repository.HistoryRepository;
import com.itqgroup.service.app.domain.service.HistoryDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class HistoryDomainServiceImpl implements HistoryDomainService {

    private static final String CMD_LOG = "projecting {}";
    private final HistoryRepository repository;

    @Override
    public List<HistorySummary> getByDocId(String id) {
        return repository.findAllByDocId(id);
    }

    @Override
    public void create(HistorySummary summary) {
        log.info(CMD_LOG, summary);
        repository.save(summary);
        log.debug("SUCCESS SAVE: {}", summary);
    }
}
