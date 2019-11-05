package net.qipo.test;

import net.qipo.bean.Teacher;
import net.qipo.dao.TeacherDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MyBatisTest {
    SqlSessionFactory sqlSessionFactory;

    @Before
    public void initSqlSessionFactory() throws IOException {
        // 1. SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 二级缓存体验
     */
    @Test
    public void test05() {
        // 第一个会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherDao teacherDao = sqlSession.getMapper(TeacherDao.class);
        Teacher teacher = teacherDao.getTeacherById(1);
        System.out.println(teacher);

        sqlSession.close();

        // 第二个会话
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        TeacherDao teacherDao1 = sqlSession1.getMapper(TeacherDao.class);
        Teacher teacher1 = teacherDao1.getTeacherById(1);
        System.out.println(teacher1);

        System.out.println(teacher == teacher1);

        sqlSession1.close();

    }


    /**
     * 一级缓存失效的几种情况：
     * 一级缓存是SqlSession级别的缓存；
     *      1、不同的SqlSession使用不同的一级缓存；
     *          只有在同一个sqlSession期间查询到的数据会保存在这个sqlSession的缓存当中；
     *          下次用这个sqlSession查询会在缓存中拿
     *      2、同一个方法，不同的参数，由于可能之前没查询过，所以还会发新的sql；
     *      3、在这个sqlSession期间执行上任何一次增删改操作，增删改操作会把缓存清空；
     *      4、手动清空缓存；
     *
     *  每次查询：先看一级缓存中有没有，如果没有就去发送新的sql；每个sqlSession拥有自己的一级缓存
     */
    @Test
    public void test04() {
        // 第一个会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherDao teacherDao = sqlSession.getMapper(TeacherDao.class);
        Teacher teacher = teacherDao.getTeacherById(1);
        System.out.println(teacher);

        System.out.println("手动清空缓存");
        // 清空当前sqlSession的一级缓存
        sqlSession.clearCache();

        Teacher teacher1 = teacherDao.getTeacherById(1);
        System.out.println(teacher1);
    }


    @Test
    public void test03() {
        // 第一个会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherDao teacherDao = sqlSession.getMapper(TeacherDao.class);
        Teacher teacher = teacherDao.getTeacherById(1);
        System.out.println(teacher);

        // 执行任何一个增删改查操作
        Teacher teacher2 = new Teacher();
        teacher2.setId(3);
        teacher2.setName("111");
        teacherDao.updateTeacher(teacher2);

        Teacher teacher1 = teacherDao.getTeacherById(1);
        System.out.println(teacher1);
    }


    @Test
    public void test02() {
        // 第一个会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherDao teacherDao = sqlSession.getMapper(TeacherDao.class);
        Teacher teacher = teacherDao.getTeacherById(1);
        System.out.println(teacher);

        // 第二个会话
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        TeacherDao teacherDao1 = sqlSession1.getMapper(TeacherDao.class);
        Teacher teacher1 = teacherDao1.getTeacherById(1);
        System.out.println(teacher1);

        System.out.println(teacher == teacher1);

        sqlSession.close();
        sqlSession1.close();

    }

    /**
     * 一级缓存实验
     */
    @Test
    public void test01() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherDao teacherDao = sqlSession.getMapper(TeacherDao.class);

        try {
            Teacher teacher = teacherDao.getTeacherById(1);
            System.out.println(teacher);
            System.out.println("++++++++++++++++");
            Teacher teacher1 = teacherDao.getTeacherById(1);
            System.out.println(teacher1);
            System.out.println(teacher == teacher1);
        }finally {
            sqlSession.close();
        }
    }

}
