package net.qipo.test;

import net.qipo.bean.Employee;
import net.qipo.dao.EmployeeDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

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
