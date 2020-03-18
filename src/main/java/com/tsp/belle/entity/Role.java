package com.tsp.belle.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;



import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * (Role)表实体类
 *
 * @author likewindz
 * @since 2020-03-18 18:47:25
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
public class Role extends Model<Role> {
    //id
    @TableId
    private Long roleId;
    //角色
    private String roleName;
    //拥有权限
    private String roleDesc;
    //备注
    private String roleRemark;



    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }
    }