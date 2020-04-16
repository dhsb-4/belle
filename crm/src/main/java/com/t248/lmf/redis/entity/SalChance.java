package com.t248.lmf.redis.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name ="sal_chance")
public class SalChance implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="chc_id")
  private Long chcId;
  @Column(name="chc_source")
  private String chcSource;
  @Column(name="chc_cust_name")
  private String chcCustName;
  @Column(name="chc_title")
  private String chcTitle;
  @Column(name="chc_rate")
  private Long chcRate;
  @Column(name="chc_linkman")
  private String chcLinkman;
  @Column(name="chc_tel")
  private String chcTel;
  @Column(name="chc_desc")
  private String chcDesc;
  @Column(name="chc_create_id")
  private Long chcCreateId;
  @Column(name="chc_create_by")
  private String chcCreateBy;
  @Column(name="chc_create_date")
  private String chcCreateDate;
  @Column(name="chc_due_id")
  private Long chcDueId;
  @Column(name="chc_due_to")
  private String chcDueTo;
  @Column(name="chc_due_date")
  private String chcDueDate;
  @Column(name="chc_status")
  private String chcStatus;
  @Column(name = "chc_level")
  private String chcLevel;
  @Column(name = "chc_dq")
  private Integer chcDq;
}
