package com.itqgroup.service.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentOutDto implements Serializable {

  private String id;
  private long number;
  private String author;
  private String title;
  private String status;
  private Date createDate;
  private Date updateDate;
  private List<HistoryOutDto> history;
}
