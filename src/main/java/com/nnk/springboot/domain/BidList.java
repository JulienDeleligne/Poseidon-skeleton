package com.nnk.springboot.domain;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "bidlist")
@AllArgsConstructor
public class BidList {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer bidListId;
  @Column
  @NotBlank(message = "Account is mandatory")
  private String account;
  @Column
  @NotBlank(message = "Type is mandatory")
  private String type;
  @Column
  private Double bidQuantity;
  @Column
  private Double askQuantity;
  @Column
  private Double bid;
  @Column
  private Double ask;
  @Column
  private String benchmark;
  @Column
  private Timestamp bidListDate;
  @Column
  private String commentary;
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

  public BidList(String account, String type, double bidQuantity) {
    this.account = account;
    this.type = type;
    this.bidQuantity = bidQuantity;
  }

  public BidList() {

  }
}
