package com.t248.lmf.redis.service.impl;

import com.t248.lmf.redis.entity.Role;
import com.t248.lmf.redis.entity.User;
import com.t248.lmf.redis.repository.RoleRepository;
import com.t248.lmf.redis.service.IRoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("iRoleService")
public class RoleServiceImpl implements IRoleService {

    @Resource
    private RoleRepository repository;

    @Override
    public List<Role> findAllRoles() {
        return repository.findAll();
    }
    @Override
    public List<Role> findRolesByRoleNameLike(String roleName) {
        List<Role> list=null;
        if(roleName!=null){
            list=repository.findRolesByRoleNameLike("%"+roleName+"%");
        }else {
            list=repository.findAll();
        }
        return list;
    }

    @Override
    public Role findRoleByUsers(User user) {
        return repository.findRoleByUsers(user);
    }

    @Override
    public Page<Role> findPageByMap(Long userId,String usrName, Pageable pageable) {
        Specification<Role> specification = new Specification<Role>() {
            @Override
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(usrName!=null&&!usrName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("roleName"),"%"+usrName+"%"));
                }
                if(userId!=null&& userId.longValue()!=0L){
                    predicates.add(criteriaBuilder.equal(root.get("roleId"),userId));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return repository.findAll(specification,pageable);
    }
}
