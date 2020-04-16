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
@Table(name ="cst_linkman")
public class CstLinkman implements Serializable {
  @Id
  @Column(name = "lkm_id")//id
  private Long lkmId;
  @Column(name = "lkm_cust_no")//客户编号
  private String lkmCustNo;
  @Column(name = "lkm_cust_name")//
  private String lkmCustName;
  @Column(name = "lkm_name")//
  private String lkmName;
  @Column(name = "lkm_sex")//
  private String lkmSex;
  @Column(name = "lkm_postion")//
  private String lkmPostion;
  @Column(name = "lkm_tel")//
  private String lkmTel;
  @Column(name = "lkm_mobile")//
  private String lkmMobile;
  @Column(name = "lkm_memo")//
  private String lkmMemo;
}
