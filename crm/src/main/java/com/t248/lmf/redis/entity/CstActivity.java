package com.t248.lmf.redis.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name ="cst_activity")
public class CstActivity implements Serializable {
  @Id
  @Column(name = "atv_id")
  private long atvId;
  @Column(name = "atv_cust_no")
  private String atvCustNo;
  @Column(name = "atv_cust_name")
  private String atvCustName;
  @Column(name = "atv_date")
  private String atvDate;
  @Column(name = "atv_place")
  private String atvPlace;
  @Column(name = "atv_title")
  private String atvTitle;
  @Column(name = "atv_desc")
  private String atvDesc;
}
