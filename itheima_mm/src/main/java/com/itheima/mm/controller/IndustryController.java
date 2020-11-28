package com.itheima.mm.controller;

import com.itheima.framework.Controller;
import com.itheima.framework.RequestMapping;
import com.itheima.mm.entity.Result;
import com.itheima.mm.pojo.Industry;
import com.itheima.mm.service.IndustryService;
import com.itheima.mm.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author cl
 * @Date 2020/11/22 20:40:21
 */
@Controller
public class IndustryController {

    IndustryService is = new IndustryService();
    Result result = null;
    @RequestMapping("/industry/test")
    public void test(HttpServletRequest req, HttpServletResponse resp){

    }

    @RequestMapping("/industry/findAll")
    public void findAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try{
            List list = is.findAll();
            result = new Result(true,"OK",list);
        }catch (Exception e){
            e.printStackTrace();
            result = new Result(false,"服务器繁忙",null);
        }finally {
            JsonUtils.printResult(resp,result);
        }

    }
}
