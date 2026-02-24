package com.itqgroup.service.app.domain.service.Impl;

import com.itqgroup.service.app.domain.model.StatementRegisterSummary;
import com.itqgroup.service.app.domain.repository.StatementRegisterRepository;
import com.itqgroup.service.app.domain.service.StatementRegisterDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StatementRegisterDomainServiceImpl implements StatementRegisterDomainService {

    private static final String CMD_LOG = "projecting {}";
    private final StatementRegisterRepository repository;

    @Override
    public void create(StatementRegisterSummary summary) {
        log.info(CMD_LOG, summary);
        repository.save(summary);
        log.debug("SUCCESS SAVE: {}", summary);
    }
}
