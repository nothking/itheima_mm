package com.itheima.mm.dao;

import com.itheima.mm.entity.QueryPageBean;
import com.itheima.mm.pojo.Course;

import java.util.List;
import java.util.Map;

/**
 * @Author cl
 * @Date 2020/11/21 10:04:20
 */
public interface CourseDao {
    Integer AddCourse(Course course);

    List findListByPage(QueryPageBean queryPageBean);

    Long countTotal();

    Integer CourseUpdate(Course course);

    Integer CourseDelete(Integer id);

    List findAllCourse();

    List findAll();
}
