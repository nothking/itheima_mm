package com.itheima.mm.service;

import com.itheima.mm.dao.IndustryDao;
import com.itheima.mm.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @Author cl
 * @Date 2020/11/22 20:45:34
 */
public class IndustryService {

    public List findAll() throws Exception {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        IndustryDao mapper = sqlSession.getMapper(IndustryDao.class);
        List list = mapper.findAll();
        SqlSessionFactoryUtils.commitAndClose(sqlSession);
        return list;
    }

}
