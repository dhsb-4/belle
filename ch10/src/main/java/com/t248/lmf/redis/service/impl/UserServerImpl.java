package com.t248.lmf.redis.service.impl;

import com.t248.lmf.redis.entity.Role;
import com.t248.lmf.redis.entity.User;
import com.t248.lmf.redis.repository.UserRepository;
import com.t248.lmf.redis.service.IUserService;
import org.springframework.cache.annotation.Cacheable;
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

@Service("iUserService")
/*
缓存设置：当我们需要的缓存越来越多，
可以使用@CacheConfig(cacheNames={"myCache"})注解来统一指定value的值
此时可以省略value
如果方法上依旧使用了value，那么依然以方法的value值为准
 */

public class UserServerImpl implements IUserService {
    @Resource
    private UserRepository userRepository;

    @Override
    public User login(String usrName, String usrPassword) {
        return userRepository.findByUsrNameAndUsrPassword(usrName,usrPassword);
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long usrId) {
        userRepository.delete(usrId);
    }

    @Override
    public User updateUser(User user) {
        User user1 =userRepository.findByUsrId(user.getUsrId());
        user1.setRole(user.getRole());
        user1.setUsrFlag(user.getUsrFlag());
        user1.setUsrName(user.getUsrName());
        user1.setUsrPassword(user.getUsrPassword());
        return userRepository.save(user1);
    }

    @Override
    @Cacheable(value = "getUser",keyGenerator = "keyGenerator")
    public User getUser(Long usrId) {
        return userRepository.findByUsrId(usrId);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findPageByMap(Map param, Pageable pageable) {
        return userRepository.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(param.get("usrName")!=null){
                    predicates.add(cb.like(root.get("usrName"),"%"+param.get("usrName")+"%"));
                }
                if (param.get("roleId")!=null){
                    predicates.add(cb.equal(root.get("usrRoleId"),param.get("roleId")));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        },pageable);
    }

    @Override
    public User findUserByUsrName(String usrName) {
        return userRepository.findUserByUsrName(usrName);
    }

    @Override
    public Page<User> findUsers(String usrName, Long roleId, Pageable pageable) {
        Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(usrName!=null&&!usrName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("usrName"),"%"+usrName+"%"));
                }
                if(roleId!=null&& roleId.longValue()!=0L){
                    Role role = new Role();
                    role.setRoleId(roleId);
                    predicates.add(criteriaBuilder.equal(root.get("role"),role));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return userRepository.findAll(specification,pageable);
    }
}
