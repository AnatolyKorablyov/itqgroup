package com.itqgroup.service.app.configuration;

import com.itqgroup.service.api.Status;
import com.itqgroup.service.app.cron.CronJobManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ScheduledFuture;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class CronWorkerConfiguration {

    @Value("${application.worker.batch.size}")
    private int BATCH_SIZE;

    @Value("${application.worker.cron.expression}")
    private String CRON_EXPRESSION;

    @Value("${application.worker.enabled}")
    private Boolean WORKER_ENABLED;

    private final CronJobManager cronJobManager;

    @Value("${application.service.url}")
    private String SERVICE_URL;

    @Bean
    public ScheduledFuture submitWorker() {
        if (!WORKER_ENABLED) {
            return null;
        }
        String url = SERVICE_URL + "/submitted";
        return cronJobManager.addTaskByStatus(url, Status.DRAFT.toString(), CRON_EXPRESSION, BATCH_SIZE);
    }

    @Bean
    public ScheduledFuture approveWorker() {
        if (!WORKER_ENABLED) {
            return null;
        }
        String url = SERVICE_URL + "/approved";
        return cronJobManager.addTaskByStatus(url, Status.SUBMITTED.toString(), CRON_EXPRESSION, BATCH_SIZE);
    }
}
