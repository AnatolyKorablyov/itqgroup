package com.itqgroup.service.app.cron;

import com.itqgroup.service.api.StatusTransferParameters;
import com.itqgroup.service.app.application.DocumentQueryService;
import io.restassured.http.ContentType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

import static io.restassured.RestAssured.given;

@Slf4j
@Service
@RequiredArgsConstructor
public class CronJobManager {

    private final TaskScheduler executor;
    private final DocumentQueryService queryService;


    public ScheduledFuture<?> addTaskByStatus(String url, String status, String expression, int batchSize) {
        log.info("ADD CRON TASK {}", expression);
        Runnable task = createTask(url, status, batchSize);
        return scheduling(task, expression);
    }

    private Runnable createTask(String url, String status, int batchSize) {
        Runnable task = () -> {
            log.info("CRON TASK {}", status);

            List<String> ids = queryService.getIdsByStatus(status);
            List<String> idsForRequest = new ArrayList<>();
            while (ids.size() != 0) {
                int end = batchSize;
                if (end > ids.size() - 1) {
                    end = ids.size() - 1;
                }
                idsForRequest = ids.subList(0, end);
                ids = ids.subList(end, ids.size() - 1);

                StatusTransferParameters stp = new StatusTransferParameters();
                stp.setIds(idsForRequest);
                stp.setAuthor("cron worker");
                transferStatus(url, stp);
            }
        };
        return task;
    }

    void transferStatus(String url, Object body) {
        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(url)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    private ScheduledFuture<?> scheduling(final Runnable task, String cronExpression) {
        ScheduledFuture<?> scheduledFuture = executor.schedule(task, new CronTrigger(cronExpression));
        return scheduledFuture;
    }
}
