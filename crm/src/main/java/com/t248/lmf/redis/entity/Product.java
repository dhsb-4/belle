package com.t248.lmf.redis.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name ="product")
public class Product implements Serializable {
  @Id
  @Column(name = "prod_id")
  private Long prodId;
  @Column(name = "prod_name")
  private String prodName;
  @Column(name = "prod_type")
  private String prodType;
  @Column(name = "prod_batch")
  private String prodBatch;
  @Column(name = "prod_unit")
  private String prodUnit;
  @Column(name = "prod_price")
  private double prodPrice;
  @Column(name = "prod_memo")
  private String prodMemo;

}
