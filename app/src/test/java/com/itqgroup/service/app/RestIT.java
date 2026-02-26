package com.itqgroup.service.app;

import com.itqgroup.service.api.DocumentDto;
import com.itqgroup.service.api.DocumentOutDto;
import com.itqgroup.service.api.DocumentsParameters;
import com.itqgroup.service.api.StatusTransferParameters;
import com.itqgroup.service.app.domain.model.DocumentSummary;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ContextConfiguration
@Slf4j
class RestIT extends BaseTest {

  private int port;
  private String serviceUrl;

  @DisplayName("Server start")
  @Test
  void loadContext() {}

  @LocalServerPort
  void initRoutes(int port) {
    this.port = port;
    this.serviceUrl = this.server + port;
  }

  private String server = "http://localhost:";
  private String createDocumentEndpoint = "/document";
  private String getDocumentsEndpoint = "/documents";
  private String submittedEndpoint = "/submitted";
  private String approvedEndpoint = "/approved";
  private String documentByIdEndpoint = "/documentById";

  @DisplayName("create document")
  @Test
  void createDocument() {
    String url = serviceUrl + createDocumentEndpoint;
    DocumentDto documentDto = new DocumentDto();
    documentDto.setId(UUID.randomUUID().toString());
    documentDto.setTitle("title");
    documentDto.setAuthor("author");
    create(url, documentDto);
  }

  @DisplayName("get generated documents")
  @Test
  void getGeneratedDocuments() {
    Date startDate = Date.from(new Date().toInstant().minus(5, ChronoUnit.MINUTES));
    Date endDate = new Date();
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("author", "Gennady");
    parameters.put("status", "draft");
    parameters.put("start_date", startDate);
    parameters.put("end_date", endDate);
    String url = serviceUrl + getDocumentsEndpoint;

    List<DocumentSummary> documentSummaries = getDocuments(url, parameters);
    assertEquals(100, documentSummaries.size());
  }

  @DisplayName("create document get with history")
  @Test
  void createDocumentGetHistory() {
    String url = serviceUrl + createDocumentEndpoint;
    DocumentDto documentDto = new DocumentDto();
    String id = UUID.randomUUID().toString();
    documentDto.setId(id);
    documentDto.setTitle("title");
    documentDto.setAuthor("author");
    create(url, documentDto);
    String submitUrl = serviceUrl + submittedEndpoint;
    StatusTransferParameters stp = new StatusTransferParameters();
    List<String> ids = new ArrayList<>();
    ids.add(id);
    stp.setIds(ids);
    stp.setAuthor("Vasya");
    submitted(submitUrl, stp);
    String documentByIdUrl = serviceUrl + documentByIdEndpoint;
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("id", id);
    DocumentOutDto documentOutDto = getDocument(documentByIdUrl, parameters);
    String aproveUrl = serviceUrl + approvedEndpoint;
    approve(aproveUrl, stp);
    documentOutDto = getDocument(documentByIdUrl, parameters);
    assertEquals(documentOutDto.getHistory().size(), 2);
  }
}
