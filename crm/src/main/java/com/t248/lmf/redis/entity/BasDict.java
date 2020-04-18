package com.t248.lmf.redis.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name ="bas_dict")
public class BasDict implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "dict_id")
  private Long dictId;
  @Column(name = "dict_type")
  private String dictType;
  @Column(name = "dict_item")
  private String dictItem;
  @Column(name = "dict_value")
  private String dictValue;
  @Column(name = "dict_is_editable")
  private Long dictIsEditable;
  @Transient
  private Integer count;
}
