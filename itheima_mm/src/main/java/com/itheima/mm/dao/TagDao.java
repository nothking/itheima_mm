package com.itheima.mm.dao;

import com.itheima.mm.pojo.Tag;

import java.util.List;

/**
 * @Author cl
 * @Date 2020/11/22 20:36:38
 */
public interface TagDao {

    /**
     * 新增试题-学科列表-标签列表获取（一对多关联）
     * @param courseId
     * @return
     */
    List<Tag> findByCourseId(Integer courseId);
}
