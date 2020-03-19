package com.tsp.belle.entity;

import lombok.Data;



import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * (Dict)表实体类
 *
 * @author likewindz
 * @since 2020-03-19 15:10:04
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
public class Dict extends Model<Dict> {
    //id
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