package com.tsp.belle.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tsp.belle.entity.Picture;
import com.tsp.belle.service.PictureService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Picture)表控制层
 *
 * @author likewindz
 * @since 2020-03-19 15:10:04
 */
@RestController
@RequestMapping("picture")
public class PictureController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private PictureService pictureService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param picture 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<Picture> page, Picture picture) {
        return success(this.pictureService.page(page, new QueryWrapper<>(picture)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.pictureService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param picture 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody Picture picture) {
        return success(this.pictureService.save(picture));
    }

    /**
     * 修改数据
     *
     * @param picture 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody Picture picture) {
        return success(this.pictureService.updateById(picture));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.pictureService.removeByIds(idList));
    }
}