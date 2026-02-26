package com.itqgroup.service.app.application.Impl;

import com.itqgroup.service.api.ConcurrencyParameters;
import com.itqgroup.service.app.application.ConcurrencyApplicationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ConcurrencyApplicationServiceImpl implements ConcurrencyApplicationService {
    @Override
    public void checkConcurrency(ConcurrencyParameters concurrencyParameters) {
        // TODO
    }
}
