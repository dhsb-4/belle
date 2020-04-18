package com.t248.lmf.redis.repository;

import com.t248.lmf.redis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {
    public User findByUsrNameAndUsrPassword(String usrName, String usrPassword);

    public User findByUsrId(Long usrId);

    @Transactional
    @Query(value = "delete from sys_user where usr_id = ?1 ",nativeQuery = true)
    @Modifying
    public void delete(Long usrId);
    public User findUserByUsrName(String usrName);

//    @Query("select u.usrId as usrId,u.usrName as usrName,u.usrPassword as usrPassword," +
//            "u.usrRoleId as usrRolid,u.usrFlag as usrFlag,r.roleName as roleName" +
//            " from User u,Role r where u.usrRoleId=r.roleId and u.usrId=?1")
//    public UserInfo getUserInfo(Long usrId);

}
