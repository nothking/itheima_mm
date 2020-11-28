package com.itheima.mm.service;

import com.itheima.mm.dao.UserDao;
import com.itheima.mm.pojo.User;
import com.itheima.mm.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

/**
 * @Author cl
 * @Date 2020/11/20 16:24:56
 */
public class UserService {


    public User findUserByUserName(User user) throws Exception{
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        UserDao mapper =  sqlSession.getMapper(UserDao.class);
        return mapper.findUserByUserName(user);
    }
}
