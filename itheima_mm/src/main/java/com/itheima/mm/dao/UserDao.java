package com.itheima.mm.dao;

import com.itheima.mm.pojo.User;

import java.util.List;

/**
 * @Author cl
 * @Date 2020/11/20 14:40:23
 */
public interface UserDao {
    List<User> findAll();
    void addUser(User user);
    User findUserByUserName(User user);
}
