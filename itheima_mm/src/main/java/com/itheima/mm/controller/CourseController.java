package com.itheima.mm.controller;

import com.itheima.framework.Controller;
import com.itheima.framework.RequestMapping;
import com.itheima.mm.entity.PageResult;
import com.itheima.mm.entity.QueryPageBean;
import com.itheima.mm.entity.Result;
import com.itheima.mm.pojo.Course;
import com.itheima.mm.pojo.User;
import com.itheima.mm.service.CourseService;
import com.itheima.mm.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author cl
 * @Date 2020/11/21 10:01:00
 */
@Controller
public class CourseController {
    CourseService cs = new CourseService();
    Result result = null;
    @RequestMapping("/course/add")
    public void CourseAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try{
            Course course = JsonUtils.parseJSON2Object(req, Course.class);
            User user = (User)req.getSession().getAttribute("user");
            course.setUserId(user.getId());
            Boolean flag = cs.CourseAdd(course);
            result = new Result(flag,flag ? "OK":"服务器繁忙，请稍后再试",null);
        }catch (Exception e){
            e.printStackTrace();
            result = new Result(false,"服务器繁忙，请稍后再试",null);
        }finally {
            JsonUtils.printResult(resp,result);
        }
    }

    @RequestMapping("/course/findListByPage")
    public void findListByPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            QueryPageBean queryPageBean = JsonUtils.parseJSON2Object(req, QueryPageBean.class);
            PageResult pageResult = cs.findListByPage(queryPageBean);
            result = new Result(true,"OK",pageResult);
        }catch (Exception e){
            e.printStackTrace();
            result = new Result(false,"服务器繁忙，请稍后再试",null);
        }finally {
            JsonUtils.printResult(resp,result);
        }
    }


    @RequestMapping("/course/update")
    public void CourseUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Course course = JsonUtils.parseJSON2Object(req, Course.class);
            Boolean flag = cs.CourseUpdate(course);
            result = new Result(true,flag?"OK":"服务器繁忙，请稍后再试",null);
        }catch (Exception e){
            e.printStackTrace();
            result = new Result(false,"服务器繁忙，请稍后再试",null);
        }finally {
            JsonUtils.printResult(resp,result);
        }
    }

    @RequestMapping("/course/delete")
    public void CourseDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Course course = JsonUtils.parseJSON2Object(req, Course.class);
//            String id = req.getParameter("id");

            Boolean flag = cs.CourseDelete(course.getId());

            result = new Result(true,flag?"OK":"服务器繁忙，请稍后再试",null);
        }catch (Exception e){
            e.printStackTrace();
            result = new Result(false,"服务器繁忙，请稍后再试",null);
        }finally {
            JsonUtils.printResult(resp,result);
        }
    }

    @RequestMapping("/course/findAllCourse")
    public void findAllCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try{
            List list = cs.findAllCourse();
            result = new Result(true,"OK",list);
        }catch ( Exception e ){
            e.printStackTrace();
            result = new Result(false,"服务器繁忙",null);
        }finally {
            JsonUtils.printResult(resp,result);
        }
    }

    @RequestMapping("/course/findAll")
    public void findAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try{
            List list = cs.findAll();
            result = new Result(true,"OK",list);
        }catch ( Exception e ){
            e.printStackTrace();
            result = new Result(false,"服务器繁忙",null);
        }finally {
            JsonUtils.printResult(resp,result);
        }
    }
}
