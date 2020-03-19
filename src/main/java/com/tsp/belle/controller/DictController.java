package com.tsp.belle.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tsp.belle.constants.ResultCode;
import com.tsp.belle.entity.Dict;
import com.tsp.belle.exception.BelleException;
import com.tsp.belle.service.DictService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Dict)表控制层
 *
 * @author likewindz
 * @since 2020-03-19 15:10:04
 */
@RestController
@RequestMapping("dict")
public class DictController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private DictService dictService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param dict 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<Dict> page, Dict dict) {
        return success(this.dictService.page(page, new QueryWrapper<>(dict)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.dictService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param dict 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody Dict dict) {
        return success(this.dictService.save(dict));
    }

    /**
     * 修改数据
     *
     * @param dict 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody Dict dict) {
        return success(this.dictService.updateById(dict));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {

        return success(this.dictService.removeByIds(idList));
    }

    @GetMapping("z")
    public R delete1(@RequestParam("idList") List<Long> idList) {
        throw BelleException.error(ResultCode.user_not_login);
    }
}