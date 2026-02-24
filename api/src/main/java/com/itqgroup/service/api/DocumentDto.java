package com.itqgroup.service.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDto implements Serializable {

  private String id;
  private String author;
  private String title;
  private Date createDate = new Date();
}
