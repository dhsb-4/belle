package com.t248.lmf.redis.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name ="orders_line")
public class OrdersLine implements Serializable {
  @Id
  @Column(name = "odd_id")
  private Long oddId;
  @Column(name = "odd_order_id")
  private Long oddOrderId;
  @Column(name = "odd_prod_id")
  private Long oddProdId;
  @Transient
  private Product product;
  @Column(name = "odd_count")
  private Long oddCount;
  @Column(name = "odd_unit")
  private String oddUnit;
  @Column(name = "odd_price")
  private double oddPrice;
}
