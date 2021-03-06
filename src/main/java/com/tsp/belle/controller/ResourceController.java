package com.tsp.belle.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tsp.belle.entity.Resource;
import com.tsp.belle.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * (Resource)表控制层
 *
 * @author likewindz
 * @since 2020-03-19 15:10:04
 */
@RestController
@RequestMapping("resource")
public class ResourceController extends ApiController {
    /**
     * 服务对象
     */
    @Autowired
    private ResourceService resourceService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param resource 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<Resource> page, Resource resource) {
        return success(this.resourceService.page(page, new QueryWrapper<>(resource)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.resourceService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param resource 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody Resource resource) {
        return success(this.resourceService.save(resource));
    }

    /**
     * 修改数据
     *
     * @param resource 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody Resource resource) {
        return success(this.resourceService.updateById(resource));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.resourceService.removeByIds(idList));
    }
}