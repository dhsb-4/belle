package com.t248.lmf.redis.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Setter
@Getter
@Entity
@Table(name = "sys_role")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class Role implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "role_id")
  private long roleId;
  @Column(name="role_name")
  private String roleName;
  @Column(name="role_desc")
  private String roleDesc;
  @Column(name="role_flag")
  private long roleFlag;

  @OneToMany(targetEntity = User.class,fetch = FetchType.LAZY,cascade = CascadeType.REMOVE,mappedBy = "role")
  @JsonIgnore
  private List<User> users=new ArrayList<User>();
  @ManyToMany
  @JoinTable(name = "sys_role_right",joinColumns = @JoinColumn(name = "rf_role_id"),inverseJoinColumns = @JoinColumn(name = "rf_right_code"))
  @OrderBy(value = "right_code")
  @JsonIgnore
  private List<Right> rights=new ArrayList<Right>();

  public Role(String roleName, String roleDesc, Long roleFlag) {
    this.roleName = roleName;
    this.roleDesc = roleDesc;
    this.roleFlag = roleFlag;
  }

  public Role() {
  }

  @Override
  public String toString() {
    users.forEach(i-> System.out.println(i.toString()));
    return "Role{" +
            "roleId=" + roleId +
            ", roleName='" + roleName + '\'' +
            ", roleDesc='" + roleDesc + '\'' +
            ", roleFlag=" + roleFlag +
            '}';
  }


}



