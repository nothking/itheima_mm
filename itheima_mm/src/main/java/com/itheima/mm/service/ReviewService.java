package com.itheima.mm.service;

import com.itheima.mm.constants.Constants;
import com.itheima.mm.dao.QuestionDao;
import com.itheima.mm.dao.ReviewDao;
import com.itheima.mm.dao.ReviewLogDao;
import com.itheima.mm.pojo.ReviewLog;
import com.itheima.mm.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author cl
 * @Date 2020/11/27 11:17:58
 */
public class ReviewService {
    public Boolean add(ReviewLog reviewLog) throws Exception {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        QuestionDao questionDao = sqlSession.getMapper(QuestionDao.class);
        Map<String, Integer> paramMap = new HashMap<>();
        paramMap.put("id", reviewLog.getQuestionId());
        if(reviewLog.getStatus() == Constants.QUESTION_REVIEWED){
            // 审核通过【试题状态就是1，审核状态就是1】
            paramMap.put("status", Constants.QUESTION_PUBLISHED);
            paramMap.put("reviewStatus", reviewLog.getStatus());
        }else if(reviewLog.getStatus() == Constants.QUESTION_REJECT_REVIEW){
            // 审核拒绝【试题状态就是0，审核状态就是2】
            paramMap.put("status", Constants.QUESTION_PRE_PUBLISH);
            paramMap.put("reviewStatus", reviewLog.getStatus());
        }
        questionDao.updateStatus(paramMap);

        //1.2 插入审核日志表
        ReviewLogDao reviewLogDao = sqlSession.getMapper(ReviewLogDao.class);
        reviewLogDao.add(reviewLog);

        SqlSessionFactoryUtils.commitAndClose(sqlSession);

        return true;
    }
}
