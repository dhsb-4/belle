package com.t248.lmf.redis.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name ="orders")
public class Orders implements Serializable {
  @Id
  @Column(name = "odr_id")
  private Long odrId;
  @Column(name = "odr_customer_no")
  private String odrCustomerNo;
  @Column(name = "odr_date")
  private java.sql.Timestamp odrDate;
  @Column(name = "odr_addr")
  private String odrAddr;
  @Column(name = "odr_status")
  private String odrStatus;
  @Transient
  private double oddPrice;
}
