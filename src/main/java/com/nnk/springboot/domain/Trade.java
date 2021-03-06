package com.nnk.springboot.domain;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Entity
@Data
@Builder
@Table(name = "trade")
@AllArgsConstructor
public class Trade {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer tradeId;
  @Column
  private String account;
  @Column
  private String type;
  @Column
  private Double buyQuantity;
  @Column
  private Double sellQuantity;
  @Column
  private Double buyPrice;
  @Column
  private Double sellPrice;
  @Column
  private String benchmark;
  @Column
  private Timestamp tradeDate;
  @Column
  private String security;
  @Column
  private String status;
  @Column
  private String trader;
  @Column
  private String book;
  @Column
  private String creationName;
  @Column
  private Timestamp creationDate;
  @Column
  private String revisionName;
  @Column
  private Timestamp revisionDate;
  @Column
  private String dealName;
  @Column
  private String dealType;
  @Column
  private String sourceListId;
  @Column
  private String side;

  public Trade() {

  }

  public Trade(String account, String type) {
    this.account = account;
    this.type = type;
  }
}
