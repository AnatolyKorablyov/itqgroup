package com.itqgroup.service.app;

import com.itqgroup.service.api.DocumentDto;
import com.itqgroup.service.api.DocumentOutDto;
import com.itqgroup.service.api.StatusTransferParameters;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
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
  private String documentEndpoint = "/document";
  private String submittedEndpoint = "/submitted";
  private String approvedEndpoint = "/approved";
  private String documentByIdEndpoint = "/documentById";

  @DisplayName("create document")
  @Test
  void createDocument() {
    String url = serviceUrl + documentEndpoint;
    DocumentDto documentDto = new DocumentDto();
    documentDto.setId(UUID.randomUUID().toString());
    documentDto.setTitle("title");
    documentDto.setAuthor("author");
    create(url, documentDto);
  }

  @DisplayName("create document get with history")
  @Test
  void createDocumentGetHistory() {
    String url = serviceUrl + documentEndpoint;
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

  }
}
