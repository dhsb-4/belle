package com.tsp.belle.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;



import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * (Dict)表实体类
 *
 * @author likewindz
 * @since 2020-03-18 18:47:25
 */
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
@Data
public class Dict extends Model<Dict> {
    //id
    @TableId(type = IdType.AUTO)
    private Long dictId;
    //类型
    private String dictType;
    //名字
    private String dictName;
    //实际值
    private String dictValue;
    
    private Long dictParentId;



    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.dictId;
    }
    }