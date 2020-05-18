package com.t248.lmf.redis.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name ="cst_service")
public class CstService implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "svr_id")
  private Long svrId;
  @Column(name = "svr_type")
  private String svrType;
  @Column(name = "svr_title")
  private String svrTitle;
  @Column(name = "svr_cust_no")
  private String svrCustNo;
  @Column(name = "svr_cust_name")
  private String svrCustName;
  @Column(name = "svr_status")
  private String svrStatus;
  @Column(name = "svr_request")
  private String svrRequest;
  @Column(name = "svr_create_id")
  private Long svrCreateId;
  @Column(name = "svr_create_by")
  private String svrCreateBy;
  @Column(name = "svr_create_date")
  private java.sql.Timestamp svrCreateDate;
  @Column(name = "svr_due_id")
  private Long svrDueId;
  @Column(name = "svr_due_to")
  private String svrDueTo;
  @Column(name = "svr_due_date")
  private java.sql.Timestamp svrDueDate;
  @Column(name = "svr_deal")
  private String svrDeal;
  @Column(name = "svr_deal_id")
  private Long svrDealId;
  @Column(name = "svr_deal_by")
  private String svrDealBy;
  @Column(name = "svr_deal_date")
  private String svrDealDate;
  @Column(name = "svr_result")
  private String svrResult;
  @Column(name = "svr_satisfy")
  private Long svrSatisfy=3L;
}
