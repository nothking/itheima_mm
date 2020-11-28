package com.itheima.mm.controller;

import com.itheima.framework.Controller;
import com.itheima.framework.RequestMapping;
import com.itheima.mm.entity.Result;
import com.itheima.mm.service.DictService;
import com.itheima.mm.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author cl
 * @Date 2020/11/23 09:10:18
 */

@Controller
public class DictController {

    DictService ds = new DictService();
    Result result = null;

    @RequestMapping("/dict/teset")
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

    @RequestMapping("/dict/findAll")
    public void FindAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            List list = ds.findAll();
            result = new Result(true,"OK",list);
        }catch (Exception e){
            e.printStackTrace();
            result = new Result(false,"服务器繁忙",null);
        }finally {
            JsonUtils.printResult(resp,result);
        }
    }
}
