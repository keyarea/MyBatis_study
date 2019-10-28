package net.qipo.test;

import net.qipo.bean.Cat;
import net.qipo.bean.Employee;
import net.qipo.bean.Key;
import net.qipo.dao.CatDao;
import net.qipo.dao.EmployeeDao;
import net.qipo.dao.KeyDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
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
    public void test06() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        KeyDao keyDao = sqlSession.getMapper(KeyDao.class);
        try {
            Key key = keyDao.getKeyByID(1);
            System.out.println(key);
        }finally {

            sqlSession.close();
        }
    }

    /**
     * 默认mybatis自动封装结果集；
     * 1、按照列名和属性一一对应的规则（不区分大小写）;
     * 2、如果不一一对应；
     *      开启驼峰命名法（满足驼峰命名的规则  aaa_bbb aaaBbb）
     *      起别名
     */
    @Test
    public void test05() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CatDao catDao = sqlSession.getMapper(CatDao.class);
        try {
            Cat cat = catDao.getCatById(1);
            System.out.println(cat);
        }finally {

            sqlSession.close();
        }
    }

    @Test
    public void test04() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
        try {
            Map<Integer, Employee> allEmpReturnMap = employeeDao.getAllEmpReturnMap();
            System.out.println(allEmpReturnMap);
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test03() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
        try {
            Map<String, Object> empByIdReturnMap = employeeDao.getEmpByIdReturnMap(1);
            System.out.println(empByIdReturnMap);
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test02() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
        try {
            List<Employee> allEmps = employeeDao.getAllEmps();
            for (Employee employee : allEmps){
                System.out.println(employee);
            }
        }finally {
            sqlSession.close();
        }
    }


    @Test
    public void testInsert() {
        // 获取和数据库的一次会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 自动提交设置
        // SqlSession sqlSession = sqlSessionFactory.openSession(true);
        
        EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
        try {
            Employee employee = new Employee(null, "admin", "admon@qq.com", 0);
            int i = employeeDao.insertEmployee(employee);
            System.out.println(i);
            System.out.println(employee.getId());
        }finally {
            //手动提交
            sqlSession.commit();
            sqlSession.close();
        }
    }

    /**
     * 测试查询
     * @throws IOException
     */
    @Test
    public void test01() throws IOException {
        // 1. SqlSessionFactory
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        initSqlSessionFactory();

        // 2. SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
        try {
            Employee emp = employeeDao.getEmpById(1);
            System.out.println(emp);
        }finally {
            sqlSession.close();
        }
    }
}
