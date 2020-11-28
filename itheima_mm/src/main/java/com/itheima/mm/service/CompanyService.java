package com.itheima.mm.service;

import com.itheima.mm.dao.CompanyDao;
import com.itheima.mm.dao.DictDao;
import com.itheima.mm.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @Author cl
 * @Date 2020/11/23 09:28:49
 */
public class CompanyService {
    public List findAll() throws Exception {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        CompanyDao mapper = sqlSession.getMapper(CompanyDao.class);
        List list = mapper.findAll();
        SqlSessionFactoryUtils.commitAndClose(sqlSession);
        return list;
    }
}
