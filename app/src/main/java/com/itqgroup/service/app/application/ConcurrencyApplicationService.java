package com.itqgroup.service.app.application;

import com.itqgroup.service.api.ConcurrencyParameters;

public interface ConcurrencyApplicationService {
    void checkConcurrency(ConcurrencyParameters concurrencyParameters);
}
