package com.t248.lmf.redis.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name ="sys_role_right")
public class SysRoleRight implements Serializable {
  @Id
  @Column(name = "rf_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long rfId;
  @Column(name = "rf_role_id")
  private Long rfRoleId;
  @Column(name = "rf_right_code")
  private String rfRightCode;
}
