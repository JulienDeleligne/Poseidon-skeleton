package com.nnk.springboot.domain;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "curvepoint")
@AllArgsConstructor
public class CurvePoint {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column
  @NotNull(message = "must not be null")
  private Integer curveId;
  @Column
  private Timestamp asOfDate;
  @Column
  private Double term;
  @Column
  private Double value;
  @Column
  private Timestamp creationDate;

  public CurvePoint() {

  }

  public CurvePoint(int curveId, double term, double value) {
    this.curveId = curveId;
    this.term = term;
    this.value = value;
  }
}
