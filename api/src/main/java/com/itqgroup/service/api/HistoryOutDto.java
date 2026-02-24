package com.itqgroup.service.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryOutDto implements Serializable {

  private String id;
  private String idDocument;
  private String author;
  private Action action;
  private String comment;
  private Date date;
}
