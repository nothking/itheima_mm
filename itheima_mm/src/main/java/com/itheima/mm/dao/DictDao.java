package com.itheima.mm.dao;

import java.util.List;

/**
 * @Author cl
 * @Date 2020/11/23 09:12:31
 */
public interface DictDao {
    List findAll();


    /**
     * 根据pid查询省下面的市列表
     * @param pid
     * @return
     */
    List findByPid(Integer pid);
}
