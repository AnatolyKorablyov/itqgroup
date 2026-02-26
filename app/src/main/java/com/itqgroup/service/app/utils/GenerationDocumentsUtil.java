package com.itqgroup.service.app.utils;

import com.itqgroup.service.api.DocumentDto;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static io.restassured.RestAssured.given;

@Slf4j
public class GenerationDocumentsUtil {

    private String serviceUrl;
    private int documentsForCreate;

    public GenerationDocumentsUtil(String serviceUrl, int n) {
        this.serviceUrl = serviceUrl + "/document";
        this.documentsForCreate = n;
    }

    @EventListener
    public void handleContextRefresh(ApplicationReadyEvent event) {
        log.info("Application ready");
        DocumentDto dto = new DocumentDto();
        dto.setTitle("Generation Documents");
        dto.setAuthor("Gennady");
        for (int i = 0; i < documentsForCreate; i++) {
            dto.setId(UUID.randomUUID().toString());
            sendCreate(serviceUrl, dto);
        }
    }

    private void sendCreate(String url, Object body) {
        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(url)
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}
