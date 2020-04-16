package com.t248.lmf.redis.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name ="storage")
public class Storage implements Serializable {
  @Id
  @Column(name = "stk_id")
  private Long stkId;
  @Column(name = "stk_prod_id")
  private Long stkProdId;
  @Transient
  private String stkProd;
  @Column(name = "stk_warehouse")
  private String stkWarehouse;
  @Column(name = "stk_ware")
  private String stkWare;
  @Column(name = "stk_count")
  private Long stkCount;
  @Column(name = "stk_memo")
  private String stkMemo;
}
