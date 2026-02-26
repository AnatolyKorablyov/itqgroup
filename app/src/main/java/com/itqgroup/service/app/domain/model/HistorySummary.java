package com.itqgroup.service.app.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import java.util.Date;

@Entity(name = "history_summary")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Proxy(lazy = false)
public class HistorySummary {

  @Id
  private String id;

  @Column(name = "id_document")
  private String idDocument;

  @Column(name = "author")
  private String author;

  @Column(name = "action")
  private String action;

  @Column(name = "comment")
  private String comment;

  @Column(name = "date")
  private Date date;

}
