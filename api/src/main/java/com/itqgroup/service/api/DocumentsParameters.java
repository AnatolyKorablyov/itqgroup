package com.itqgroup.service.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentsParameters {

  private String author;
  private String status;
  private Date start_date;
  private Date end_date;
}
