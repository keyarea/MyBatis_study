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
    
    @Test
    public void test05() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherDao teacherDao = sqlSession.getMapper(TeacherDao.class);
        try{
            Teacher teacher = new Teacher();
            teacher.setId(1);
            teacher.setName("王五");
            teacherDao.updateTeacher(teacher);
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test04() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherDao teacherDao = sqlSession.getMapper(TeacherDao.class);
        try{
            Teacher teacher = new Teacher();
            teacher.setId(1);
            //teacher.setName("%a%");
            List<Teacher> teachers = teacherDao.getTeacherByConditionChoose(teacher);
            System.out.println(teachers);
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test03() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherDao teacherDao = sqlSession.getMapper(TeacherDao.class);
        try{
            List<Teacher> teachers = teacherDao.getTeacherByIdIn(Arrays.asList(1, 2, 3, 4, 5));
            System.out.println(teachers);

        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test02() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherDao teacherDao = sqlSession.getMapper(TeacherDao.class);
        try {
            Teacher teacher = new Teacher();
            //teacher.setId(1);
            teacher.setName("%a%");
            List<Teacher> teachers = teacherDao.getTeacherByCondition(teacher);
            System.out.println(teachers);
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test01() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherDao teacherDao = sqlSession.getMapper(TeacherDao.class);

        try {
            Teacher teacher = teacherDao.getTeacherById(1);
            System.out.println(teacher);
        }finally {
            sqlSession.close();
        }
    }

}
