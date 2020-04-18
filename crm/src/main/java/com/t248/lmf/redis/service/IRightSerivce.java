package com.t248.lmf.redis.service;

import com.t248.lmf.redis.entity.*;
import java.util.List;

public interface IRightSerivce {
    public List<Right> findRightsByRoles(Role role);
    public List<Right> findAllRights();
    public List<Right> findByRightParentCode(String ROOT_MENU);
    public List<Right> findByAll1();
    public List<Right> findByAll();
}
