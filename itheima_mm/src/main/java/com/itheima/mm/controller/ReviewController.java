package com.itheima.mm.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.itheima.framework.Controller;
import com.itheima.framework.RequestMapping;
import com.itheima.mm.entity.Result;
import com.itheima.mm.pojo.ReviewLog;
import com.itheima.mm.pojo.User;
import com.itheima.mm.service.ReviewService;
import com.itheima.mm.utils.DateUtils;
import com.itheima.mm.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


/**
 * @Author cl
 * @Date 2020/11/27 10:29:05
 */
@Controller
public class ReviewController {

    Result result = null;

    ReviewService rs = new ReviewService();

//    /review/add
    @RequestMapping("/review/add")
    public void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ReviewLog reviewLog = JsonUtils.parseJSON2Object(req, ReviewLog.class);
        User user = (User) req.getSession().getAttribute("user");
        reviewLog.setUserId(user.getId());
        reviewLog.setCreateDate(DateUtils.parseDate2String(new Date()));
        try {
            Boolean flag = rs.add(reviewLog);
            result = new Result(flag,flag?"OK":"Error");
        }catch (Exception e){
            result = new Result(false,"Error");
        }finally {
            JsonUtils.printResult(resp,result);
        }

    }
}
