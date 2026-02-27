package com.itqgroup.service.app.application;

import com.itqgroup.service.api.ApprovedStatus;
import com.itqgroup.service.api.ConcurrencyParameters;

import java.util.List;
import java.util.Map;

public interface ConcurrencyApplicationService {
    Map<String, List<Map<String, ApprovedStatus>>> checkConcurrency(ConcurrencyParameters concurrencyParameters);
}
