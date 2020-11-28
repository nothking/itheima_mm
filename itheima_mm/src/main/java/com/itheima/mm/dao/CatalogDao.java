package com.itheima.mm.dao;

import com.itheima.mm.pojo.Catalog;

import java.util.List;

/**
 * @Author cl
 * @Date 2020/11/22 20:35:25
 */
public interface CatalogDao {

    /**
     * 新增试题-学科列表-目录列表获取（一对多关联）
     * @param courseId
     * @return
     */
    List<Catalog> findByCourseId(Integer courseId);
}
