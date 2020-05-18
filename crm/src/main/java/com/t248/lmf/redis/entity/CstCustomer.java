package com.t248.lmf.redis.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
@Setter
@Getter
@Entity
@Table(name ="cst_customer")
public class CstCustomer implements Serializable {
  @Id
  @Column(name = "cust_id")//id
  private Long custId;
  @Column(name = "cust_no")//客户编号
  private String custNo;
  @Column(name = "cust_name")//客户名称
  private String custName;
  @Column(name = "cust_region")//地区
  private String custRegion;
  @Column(name = "cust_manager_id")//客户经理编号
  private Long custManagerId;
  @Column(name = "cust_manager_name")//客户经理
  private String custManagerName;
  @Column(name = "cust_level")//客户等级id
  private Long custLevel;
  @Column(name = "cust_level_label")//客户等级
  private String custLevelLabel;
  @Column(name = "cust_satisfy")//满意度
  private Long custSatisfy;
  @Column(name = "cust_credit")//信用度
  private Long custCredit;
  @Column(name = "cust_addr")//地址
  private String custAddr;
  @Column(name = "cust_zip")//邮政编码
  private String custZip;
  @Column(name = "cust_tel")//电话
  private String custTel;
  @Column(name = "cust_fax")//传真
  private String custFax;
  @Column(name = "cust_website")//网址
  private String custWebsite;
  @Column(name = "cust_licence_no")//营业执照注册号
  private String custLicenceNo;
  @Column(name = "cust_chieftain")//法人
  private String custChieftain;
  @Column(name = "cust_bankroll")//注册资金
  private Long custBankroll;
  @Column(name = "cust_turnover")//营业额
  private Long custTurnover;
  @Column(name = "cust_bank")//开户银行
  private String custBank;
  @Column(name = "cust_bank_account")//银行账号
  private String custBankAccount;
  @Column(name = "cust_local_tax_no")//地税登记号
  private String custLocalTaxNo;
  @Column(name = "cust_national_tax_no")//国税登记号
  private String custNationalTaxNo;
  @Column(name = "cust_status")//客户状态
  private String custStatus;
  @Transient
  private double price;
}
