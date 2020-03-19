package com.tsp.belle.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tsp.belle.entity.LoginInfo;
import com.tsp.belle.service.LoginInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (LoginInfo)表控制层
 *
 * @author likewindz
 * @since 2020-03-18 18:47:25
 */
@RestController
@RequestMapping("loginInfo")
public class LoginInfoController extends ApiController {



    /**
     * 服务对象
     */
    @Resource
    private LoginInfoService loginInfoService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param loginInfo 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<LoginInfo> page, LoginInfo loginInfo) {
        return success(this.loginInfoService.page(page, new QueryWrapper<>(loginInfo)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.loginInfoService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param loginInfo 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody LoginInfo loginInfo) {
        return success(this.loginInfoService.save(loginInfo));
    }

    /**
     * 修改数据
     *
     * @param loginInfo 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody LoginInfo loginInfo) {
        return success(this.loginInfoService.updateById(loginInfo));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.loginInfoService.removeByIds(idList));
    }
}