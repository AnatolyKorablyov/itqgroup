package com.itqgroup.service.app.configuration;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StatusControllerAdvice {

  @ExceptionHandler({EntityNotFoundException.class})
  public ResponseEntity<Object> handleQuery() {
    return ResponseEntity.status(404).body("object not found");
  }

}
