package com.itqgroup.service.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusTransferParameters {

  private List<String> ids;
  private String author;
  private String comment = "";
}
