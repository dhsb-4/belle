package com.example.aaa.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Setter
@Getter
@Table(name ="sys_role")
public class SysRole implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "role_id")
  private int roleId;
  @Column(name="role_name")
  private String roleName;
  @Column(name="role_desc")
  private String roleDesc;
  @Column(name="role_flag")
  private int roleFlag;
  @OneToMany(mappedBy = "role",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
  private List<SysUser> users = new ArrayList<SysUser>();
}
