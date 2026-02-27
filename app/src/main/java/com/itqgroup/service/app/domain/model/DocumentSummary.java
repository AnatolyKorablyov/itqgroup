package com.itqgroup.service.app.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import java.util.Date;


@Entity(name = "document_summary")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Proxy(lazy = false)
public class DocumentSummary {

  @Id
  private String id;

  @Column(name = "number")
  private long number;

  @Column(name = "author")
  private String author;

  @Column(name = "title")
  private String title;

  @Column(name = "status")
  private String status;

  @Column(name = "create_date")
  private Date createDate;

  @Column(name = "update_date")
  private Date updateDate;
}
