package com.itheima.mm.dao;

import com.itheima.mm.entity.QueryPageBean;
import com.itheima.mm.pojo.Course;
import com.itheima.mm.pojo.Question;

import java.util.List;
import java.util.Map;

/**
 * @Author cl
 * @Date 2020/11/22 17:04:56
 */
public interface QuestionDao {
    List findListByPage(QueryPageBean queryPageBean);

    Long CountQuestion(QueryPageBean queryPageBean);


    Integer QuestionAdd(Question question);

    void updateStatus(Map<String, Integer> paramMap);
}
