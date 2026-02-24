package com.itqgroup.service.app.domain.service;

import com.itqgroup.service.app.domain.model.DocumentSummary;
import com.itqgroup.service.app.domain.model.HistorySummary;

import java.util.List;

public interface HistoryDomainService {

    List<HistorySummary> getByDocId(String id);
    void create(HistorySummary summary);
}
