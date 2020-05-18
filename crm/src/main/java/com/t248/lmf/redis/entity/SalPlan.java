package com.t248.lmf.redis.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name ="sal_plan")
public class SalPlan implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="pla_id")
  private Long plaId;
  @Column(name="pla_chc_id")
  private Long plaChcId;
  @Column(name="pla_date")
  private java.sql.Timestamp plaDate;
  @Column(name="pla_todo")
  private String plaTodo;
  @Column(name="pla_result")
  private String plaResult;
}
