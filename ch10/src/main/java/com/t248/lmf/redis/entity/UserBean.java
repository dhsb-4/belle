package com.t248.lmf.redis.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="sys_user")
public class UserBean implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="usr_id")
    private long usrId;
    @Column(name="usr_name")
    private String usrName;
    @Column(name="usr_password")
    private String usrPassword;
    //  @Column(name="usr_role_id")
//  private long usrRoleId;
    @Column(name="usr_flag")
    private long usrFlag;

    @ManyToOne(targetEntity = Role.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "usr_role_id")
    private Role role;

    public UserBean() {
    }

    public UserBean(String usrName, String usrPassword, long usrFlag, Role role) {
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

    public long getUsrId() {
        return usrId;
    }

    public void setUsrId(long usrId) {
        this.usrId = usrId;
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public String getUsrPassword() {
        return usrPassword;
    }

    public void setUsrPassword(String usrPassword) {
        this.usrPassword = usrPassword;
    }

    public long getUsrFlag() {
        return usrFlag;
    }

    public void setUsrFlag(long usrFlag) {
        this.usrFlag = usrFlag;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
