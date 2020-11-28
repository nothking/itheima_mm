import com.itheima.mm.dao.CourseDao;
import com.itheima.mm.dao.IndustryDao;
import com.itheima.mm.dao.QuestionDao;
import com.itheima.mm.dao.UserDao;
import com.itheima.mm.entity.QueryPageBean;
import com.itheima.mm.pojo.Course;
import com.itheima.mm.pojo.User;
import com.itheima.mm.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author cl
 * @Date 2020/11/20 14:49:41
 */
public class DBTest {

    public static UserDao getdao(){
        UserDao mapper = null;
        try{
            SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
             mapper = sqlSession.getMapper(UserDao.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return mapper;
    }

    public static CourseDao getCoursedao(){
        CourseDao mapper = null;
        try{
            SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
            mapper = sqlSession.getMapper(CourseDao.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return mapper;
    }

    @Test
    public void t1() throws Exception {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        UserDao udao = sqlSession.getMapper(UserDao.class);
        List<User> all = udao.findAll();
        all.forEach(System.out::println);
    }

    @Test
    public void t2() throws Exception {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        UserDao udao = sqlSession.getMapper(UserDao.class);
        User u = new User();
        u.setUsername("aaaaa");
        udao.addUser(u);
        SqlSessionFactoryUtils.commitAndClose(sqlSession);
        System.out.println(u);
        //com.itheima.mm.dao.UserDao
    }

    @Test
    public void t3(){
        UserDao dao = getdao();
        User u = new User();
        u.setUsername("admin");
        u.setPassword("admin");
        User userByUserName = dao.findUserByUserName(u);
        System.out.println(userByUserName);
    }

    @Test
    public void CourseAdd(){
        Course c = new Course();
        c.setName("C+s+");
        c.setIsShow(1);
        c.setUserId(1);
        c.setCreateDate("2019-08-08 00:00:00");
        Integer i = getCoursedao().AddCourse(c);

        System.out.println(c);
        System.out.println(i);
    }
    @Test
    public void t5(){
        CourseDao c = getCoursedao();
        Map map = new HashMap();
        map.put("offset",1);
        map.put("pageSize",5);
//        map.put("name",null);
        map.put("status",0);
//        List listByPage = c.findListByPage(map);
//        listByPage.forEach(System.out::println);
    }
    @Test
    public void t6() throws Exception{
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        QuestionDao qd = sqlSession.getMapper(QuestionDao.class);
        QueryPageBean qb = new QueryPageBean();
        qb.setCurrentPage(1);
        qb.setOffset(1);
        qb.setPageSize(3);
        Long aLong = qd.CountQuestion(qb);
        System.out.println(aLong);

    }
    @Test
    public void t7() throws  Exception{
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        IndustryDao mapper = sqlSession.getMapper(IndustryDao.class);
        List list = mapper.findAll();
        SqlSessionFactoryUtils.commitAndClose(sqlSession);
        list.forEach(System.out::println);
    }
}
