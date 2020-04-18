package com.example.aaa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Table(name ="sys_user")
@Entity
public class SysUser implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="usr_id")
  private int usrId;
  @Column(name="usr_name")
  private String usrName;
  @Column(name="usr_password")
  private String usrPassword;
/*  @Column(name="usr_role_id")
  private int usrRoleId;*/
  @Column(name="usr_flag")
  private int usrFlag;
  @ManyToOne(targetEntity = SysRole.class,fetch = FetchType.EAGER)
  @JoinColumn(name = "usr_role_id")
  private SysRole role;
}
