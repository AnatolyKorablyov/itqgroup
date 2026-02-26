package com.itqgroup.service.app;

import com.google.gson.Gson;
import com.itqgroup.service.api.ApprovedStatus;
import com.itqgroup.service.api.DocumentOutDto;
import com.itqgroup.service.api.SubmittedStatus;
import com.itqgroup.service.app.domain.model.DocumentSummary;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;


abstract class BaseTest extends TestContainers {
    protected Gson gson;

    void getStringResponseByViewId(String url, Map<String, ?> parameters) {
        given()
                .params(parameters)
                .contentType(ContentType.JSON)
                .get(url)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());
    }

    void create(String url, Object body) {
        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(url)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    Map<String, SubmittedStatus> submitted(String url, Object body) {
        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(url)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(new TypeRef<Map<String, SubmittedStatus>>() {
                });
    }

    Map<String, ApprovedStatus> approve(String url, Object body) {
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

    DocumentOutDto getDocument(String url, Map<String, ?> parameters) {
        return given()
                .params(parameters)
                .contentType(ContentType.JSON)
                .when()
                .get(url)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(new TypeRef<DocumentOutDto>() {
                });
    }

    List<DocumentSummary> getDocuments(String url, Map<String, ?> parameters) {
        return given()
                .params(parameters)
                .contentType(ContentType.JSON)
                .when()
                .get(url)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(new TypeRef<List<DocumentSummary>>() {
                });
    }

}
