package com.itheima.mm.service;

import com.itheima.mm.dao.CourseDao;
import com.itheima.mm.dao.QuestionDao;
import com.itheima.mm.dao.UserDao;
import com.itheima.mm.entity.PageResult;
import com.itheima.mm.entity.QueryPageBean;
import com.itheima.mm.pojo.Course;
import com.itheima.mm.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

/**
 * @Author cl
 * @Date 2020/11/21 10:02:33
 */
public class CourseService {
    public static CourseDao getdao(){
        CourseDao mapper = null;
        try{
            SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
            mapper = sqlSession.getMapper(CourseDao.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return mapper;
    }
    public Boolean CourseAdd(Course course) throws Exception{

        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        CourseDao mapper = sqlSession.getMapper(CourseDao.class);
        Integer integer = mapper.AddCourse(course);
        SqlSessionFactoryUtils.commitAndClose(sqlSession);
        return integer>0;
    }

    public PageResult findListByPage(QueryPageBean queryPageBean) throws Exception {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        CourseDao mapper = sqlSession.getMapper(CourseDao.class);
        Map queryParams = queryPageBean.getQueryParams();
        if (queryParams!=null){
            String  status = queryParams.get("status")+"";
            if("".equalsIgnoreCase(status)){
                queryParams.put("status",0);
            }
        }
        List rows = mapper.findListByPage(queryPageBean);
        Long total = mapper.countTotal();
        return new PageResult(total,rows);
    }

    public Boolean CourseUpdate(Course course) throws Exception {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        CourseDao mapper = sqlSession.getMapper(CourseDao.class);
        Integer rows =  mapper.CourseUpdate(course);
        SqlSessionFactoryUtils.commitAndClose(sqlSession);
        return  rows>0 ;
    }

    public Boolean CourseDelete(int id) throws Exception {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        CourseDao mapper = sqlSession.getMapper(CourseDao.class);
        Integer rows =  mapper.CourseDelete(id);
        SqlSessionFactoryUtils.commitAndClose(sqlSession);
        return  rows>0 ;
    }

    public List findAllCourse() throws Exception{
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        CourseDao mapper = sqlSession.getMapper(CourseDao.class);
        List list = mapper.findAllCourse();
        SqlSessionFactoryUtils.commitAndClose(sqlSession);
        return list;
    }

    public List findAll() throws Exception{
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        CourseDao mapper = sqlSession.getMapper(CourseDao.class);
        List list = mapper.findAll();
        SqlSessionFactoryUtils.commitAndClose(sqlSession);
        return list;
    }
}
