package com.t248.lmf.redis.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name ="cst_lost")
public class CstLost implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "lstId")//id
  private Long lstId;
  @Column(name = "lst_cust_no")//id
  private String lstCustNo;
  @Column(name = "lst_cust_name")//id
  private String lstCustName;
  @Column(name = "lst_cust_manager_id")//id
  private Long lstCustManagerId;
  @Column(name = "lst_cust_manager_name")//id
  private String lstCustManagerName;
  @Column(name = "lst_last_order_date")//id
  private java.sql.Timestamp lstLastOrderDate;
  @Column(name = "lst_lost_date")//id
  private java.sql.Timestamp lstLostDate;
  @Column(name = "lst_delay")//id
  private String lstDelay;
  @Column(name = "lst_reason")//id
  private String lstReason;
  @Column(name = "lst_status")//id
  private String lstStatus;
}
