package com.itqgroup.service.app.concurrency;

import com.itqgroup.service.api.ApprovedStatus;
import com.itqgroup.service.api.StatusTransferParameters;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Slf4j
public class ConcurrencyRunnable implements Runnable {
    private String approveUrl;
    private int attempts;
    private String id;
    private String threadName;
    private Map<String, List<Map<String, ApprovedStatus>>> concurrencyThreadsStatuses;

    public ConcurrencyRunnable(String approveUrl, int attempts, String id, String thread,
                               Map<String, List<Map<String, ApprovedStatus>>> concurrencyThreadsStatuses) {
        this.attempts = attempts;
        this.approveUrl = approveUrl;
        this.id = id;
        this.threadName = thread;
        this.concurrencyThreadsStatuses = concurrencyThreadsStatuses;
    }

    public void run() {
        try {
            StatusTransferParameters stp = new StatusTransferParameters();
            List<String> ids = new ArrayList<>();
            ids.add(id);
            stp.setIds(ids);
            stp.setAuthor("concurrency");
            List<Map<String, ApprovedStatus>> attemptsStatuses = new ArrayList<>();
            for (int i = 0; i < attempts; i++) {
                Map<String, ApprovedStatus> approvedStatusMap = transferStatus(approveUrl, stp);
                attemptsStatuses.add(approvedStatusMap);
            }
            concurrencyThreadsStatuses.put(threadName, attemptsStatuses);
        }
        catch (Exception e) {
            log.error("Exception {}", e);
        }
    }

    Map<String, ApprovedStatus> transferStatus(String url, Object body) {
        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(url)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(new TypeRef<Map<String, ApprovedStatus>>() {
                });
    }
}
