package com.itqgroup.service.app.application.Impl;

import com.itqgroup.service.api.ApprovedStatus;
import com.itqgroup.service.api.ConcurrencyParameters;
import com.itqgroup.service.app.application.ConcurrencyApplicationService;
import com.itqgroup.service.app.concurrency.ConcurrencyRunnable;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ConcurrencyApplicationServiceImpl implements ConcurrencyApplicationService {
    @Value("${application.service.url}")
    private String SERVICE_URL;

    private final Map<String, List<Map<String, ApprovedStatus>>> concurrencyThreadsStatuses;

    @Override
    public Map<String, List<Map<String, ApprovedStatus>>> checkConcurrency(ConcurrencyParameters concurrencyParameters) {
        List<Thread> threadsList = new ArrayList<>();
        concurrencyThreadsStatuses.clear();
        String url = SERVICE_URL + "/approved";
        for (int i = 0; i < concurrencyParameters.getThreads(); i++) {
            ConcurrencyRunnable concurrencyRunnable = new ConcurrencyRunnable(url, concurrencyParameters.getAttempts(),
                    concurrencyParameters.getId(), String.valueOf(i), concurrencyThreadsStatuses);
            Thread thread = new Thread(concurrencyRunnable);
            thread.start();
            threadsList.add(thread);
        }
        while (!threadsList.isEmpty()) {
            Iterator<Thread> iterator = threadsList.iterator();
            while (iterator.hasNext()) {
                Thread thread = iterator.next();
                if (!thread.isAlive()) {
                    iterator.remove();
                }
            }
        }
        return concurrencyThreadsStatuses;
    }
}
