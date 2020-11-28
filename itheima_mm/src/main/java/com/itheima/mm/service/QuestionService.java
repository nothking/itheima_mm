package com.itheima.mm.service;

import com.itheima.mm.constants.Constants;
import com.itheima.mm.dao.QuestionDao;
import com.itheima.mm.dao.QuestionItemDao;
import com.itheima.mm.dao.QuestionTagDao;
import com.itheima.mm.dao.UserDao;
import com.itheima.mm.entity.PageResult;
import com.itheima.mm.entity.QueryPageBean;
import com.itheima.mm.pojo.*;
import com.itheima.mm.utils.CollectionUtils;
import com.itheima.mm.utils.DateUtils;
import com.itheima.mm.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author cl
 * @Date 2020/11/22 17:03:42
 */
public class QuestionService {

    PageResult pageResult = null;

    public static QuestionDao getdao(){
        QuestionDao mapper = null;
        try{
            SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
            mapper = sqlSession.getMapper(QuestionDao.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return mapper;
    }

    public PageResult findListByPage(QueryPageBean queryPageBean) throws Exception {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        QuestionDao mapper = sqlSession.getMapper(QuestionDao.class);
        List list = mapper.findListByPage(queryPageBean);
        Long total = mapper.CountQuestion(queryPageBean);
        SqlSessionFactoryUtils.commitAndClose(sqlSession);
        return pageResult = new PageResult(total,list);
    }


    public Boolean QuestionAdd(Question question) throws Exception {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        QuestionDao mapper = sqlSession.getMapper(QuestionDao.class);

        // 补全数据[创建时间、题目状态写0、审核状态写0]
        question.setCreateDate(DateUtils.parseDate2String(new Date()));
        question.setStatus(Constants.QUESTION_PRE_PUBLISH);
        question.setReviewStatus(Constants.QUESTION_PRE_REVIEW);
        mapper.QuestionAdd(question);

        //1.2 插入选项表t_question_item，question_id字段要补全（需要获取插入的id值）
        List<QuestionItem> questionItemList = question.getQuestionItemList();
        //1.2.1 需要获取试题选项列表属性集合，需要遍历
        if (CollectionUtils.isNotEmpty(questionItemList)) {
            QuestionItemDao questionItemDao = sqlSession.getMapper(QuestionItemDao.class);
            // 不为空才遍历选项列表
            for (QuestionItem questionItem : questionItemList) {
                // //1.2.2 遍历时插入选项表； 选项内容为空不插入
                if(questionItem.getContent() != null && !"".equals(questionItem.getContent())){
                    // 插入[question_id字段要补全]
                    questionItem.setQuestionId(question.getId());
                    questionItemDao.add(questionItem);
                }
            }
        }


        //1.3 插入tr_question_tag表，question_id字段要补全（需要获取插入的id值）
        List<Tag> tagList = question.getTagList();
        //1.3.1 获取标签列表，需要遍历
        if (CollectionUtils.isNotEmpty(tagList)) {
            QuestionTagDao questionTagDao = sqlSession.getMapper(QuestionTagDao.class);
            for (Tag tag : tagList) {
                // map作为参数
                Map<String, Integer> paramMap = new HashMap<>();
                paramMap.put("question_id", question.getId());
                paramMap.put("tag_id", tag.getId());
                // //1.3.2 遍历时插入关系表
                questionTagDao.add(paramMap);
            }
        }

        SqlSessionFactoryUtils.commitAndClose(sqlSession);
        return true;
    }


}
