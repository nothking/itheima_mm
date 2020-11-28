package com.itheima.mm.controller;

import com.itheima.framework.Controller;
import com.itheima.framework.RequestMapping;
import com.itheima.mm.entity.Result;
import com.itheima.mm.service.CompanyService;
import com.itheima.mm.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author cl
 * @Date 2020/11/23 09:26:44
 */
@Controller
public class CompanyController {

    CompanyService cs = new CompanyService();
    Result result = null;
    @RequestMapping("/company/test")
    public void Test(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            result = new Result(true,"OK",null);
        }catch (Exception e){
            e.printStackTrace();
            result = new Result(false,"服务器繁忙",null);
        }finally {
            JsonUtils.printResult(resp,result);
        }
    }

    @RequestMapping("/company/findAll")
    public void FindAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            List list = cs.findAll();
            result = new Result(true,"OK",list);
        }catch (Exception e){
            e.printStackTrace();
            result = new Result(false,"服务器繁忙",null);
        }finally {
            JsonUtils.printResult(resp,result);
        }
    }
}
