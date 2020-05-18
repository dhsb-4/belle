package com.t248.lmf.redis.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name ="sys_right")
public class Right implements Serializable {
  @Id
  @Column(name = "right_code")
  private String rightCode;
  @Column(name = "right_parent_code")
  private String rightParentCode;
  @Column(name = "right_type")
  private String rightType;
  @Column(name = "right_text")
  private String rightText;
  @Column(name = "right_url")
  private String rightUrl;
  @Column(name = "right_tip")
  private String rightTip;
  @ManyToMany(mappedBy = "rights")
  @JsonIgnore
  private Set<Role> roles=new HashSet<Role>();
}
