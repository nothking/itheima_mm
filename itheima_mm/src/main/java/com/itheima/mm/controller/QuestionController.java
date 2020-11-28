package com.itheima.mm.controller;

import com.itheima.framework.Controller;
import com.itheima.framework.RequestMapping;
import com.itheima.mm.entity.PageResult;
import com.itheima.mm.entity.QueryPageBean;
import com.itheima.mm.entity.Result;
import com.itheima.mm.pojo.Catalog;
import com.itheima.mm.pojo.Course;
import com.itheima.mm.pojo.Question;
import com.itheima.mm.service.QuestionService;
import com.itheima.mm.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author cl
 * @Date 2020/11/22 16:57:13
 */
@Controller
public class QuestionController {

    QuestionService qs = new QuestionService();
    Result result = null;
    @RequestMapping("/question/findListByPage")
    public void findListByPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        QueryPageBean queryPageBean = JsonUtils.parseJSON2Object(req, QueryPageBean.class);
        try{
            PageResult pageResult = qs.findListByPage(queryPageBean);
            result = new Result(true,"OK",pageResult);
        }catch ( Exception e ){
            e.printStackTrace();
            result = new Result(false,"服务器繁忙",null);
        }finally {
            JsonUtils.printResult(resp,result);
        }
    }
//    @RequestMapping("/question/findClassListByPage")
//    public void findClassListByPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        Map map = JsonUtils.parseJSON2Object(req, Map.class);
//        try{
//            PageResult pageResult = qs.findClassListByPage(map);
//            result = new Result(true,"OK",pageResult);
//        }catch ( Exception e ){
//            e.printStackTrace();
//            result = new Result(false,"服务器繁忙",null);
//        }finally {
//            JsonUtils.printResult(resp,result);
//        }
//    }

    @RequestMapping("/question/add")
    public void QuestionAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Question question = JsonUtils.parseJSON2Object(req, Question.class);
        try{
            Boolean flag = qs.QuestionAdd(question);
            result = new Result(true,flag?"OK":"添加失败",null);
        }catch ( Exception e ){
            e.printStackTrace();
            result = new Result(false,"服务器繁忙",null);
        }finally {
            JsonUtils.printResult(resp,result);
        }
    }



}
