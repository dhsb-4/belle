package com.t248.lmf.redis.service;

import com.t248.lmf.redis.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IUserService {

    public User login(String usrName, String usrPassword);
    public User addUser(User user);
    public void deleteUser(Long usrId);
    public User updateUser(User user);
    public User getUser(Long usrId);
    public List<User> findAllUsers();
    public User findByUsrId(Long usrId);
    public Page<User> findPageByMap(Map param, Pageable pageable);
    public User findUserByUsrName(String usrName);
    public Page<User> findUsers(String userName, Long roleId, Pageable pageable);
    public Page<User> findUser(String usrName, Long roleId, Pageable pageable);
}
