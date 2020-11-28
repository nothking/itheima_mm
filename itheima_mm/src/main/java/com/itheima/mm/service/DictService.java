package com.itheima.mm.service;

import com.itheima.mm.dao.DictDao;
import com.itheima.mm.dao.IndustryDao;
import com.itheima.mm.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @Author cl
 * @Date 2020/11/23 09:12:47
 */
public class DictService {
    public List findAll() throws Exception{
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        DictDao mapper = sqlSession.getMapper(DictDao.class);
        List list = mapper.findAll();
        SqlSessionFactoryUtils.commitAndClose(sqlSession);
        return list;
    }
}
