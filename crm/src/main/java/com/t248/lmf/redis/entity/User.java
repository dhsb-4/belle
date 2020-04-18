package com.t248.lmf.redis.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name ="sys_user")
public class User implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="usr_id")
  private Long usrId;
  @Column(name="usr_name")
  private String usrName;
  @Column(name="usr_password")
  private String usrPassword;
//  @Column(name="usr_role_id")
//  private long usrRoleId;
  @Column(name="usr_flag")
  private Long usrFlag;
  @OneToMany(targetEntity = User.class,fetch = FetchType.LAZY,cascade = CascadeType.REMOVE,mappedBy = "role")
  @ManyToOne(targetEntity = Role.class,fetch = FetchType.EAGER)
  @JoinColumn(name = "usr_role_id")
  private Role role;

  public User() {
  }

  public User(String usrName, String usrPassword, long usrFlag, Role role) {
    this.usrName = usrName;
    this.usrPassword = usrPassword;
    this.usrFlag = usrFlag;
    this.role = role;
  }

  @Override
  public String toString() {
    return "User{" +
            "usrId=" + usrId +
            ", usrName='" + usrName + '\'' +
            ", usrPassword='" + usrPassword + '\'' +
            ", usrFlag=" + usrFlag +
            ", role=" + role.getRoleName() +
            '}';
  }

}
