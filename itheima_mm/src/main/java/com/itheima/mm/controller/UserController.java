package com.itheima.mm.controller;

import com.itheima.framework.Controller;
import com.itheima.framework.RequestMapping;
import com.itheima.mm.entity.Result;
import com.itheima.mm.pojo.Course;
import com.itheima.mm.pojo.User;
import com.itheima.mm.service.UserService;
import com.itheima.mm.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author cl
 * @Date 2020/11/20 15:50:08
 */
@Controller
public class UserController {

    UserService Uservice = new UserService();
    Result result = null;
    @RequestMapping("/test")
    public void Tes(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    }

    @RequestMapping("/user/login")
    public void Login(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try{
            User user = JsonUtils.parseJSON2Object(req, User.class);
            User get = Uservice.findUserByUserName(user);
            if (get!=null){
                result = new Result(true,"loginOk",null);
                req.getSession().setAttribute("user",get);
            }else {
                result = new Result(false,"用户名或密码有误",null);
            }
        }catch (Exception e){
            e.printStackTrace();
            result = new Result(false,"服务器繁忙，请稍后再试",null);
        }finally {
            JsonUtils.printResult(resp,result);
        }

    }

    @RequestMapping("/user/logout")
    public void Logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try{
            String username = req.getParameter("username");
            req.getSession().removeAttribute(username);
            result = new Result(true,"OK",null);
        }catch (Exception e){
            e.printStackTrace();
            result = new Result(false,"服务器繁忙，请稍后再试",null);
        }finally {
            JsonUtils.printResult(resp,result);
        }
    }




}
