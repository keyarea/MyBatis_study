package net.qipo.test;

import net.qipo.Dao.EmployeeDao;
import net.qipo.bean.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisTest {

    @Test
    public void test01() throws IOException {
        // 1, 根据全局配置文件创建出一个sqlSessionFactory
        // SqlSessionFactory: 是sqlSession的工厂,负责创建SqlSession对象;
        // SqlSession:Sql会话(代表和数据库的一次会话);
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);


        Employee employee;
        // 2, 获取和数据库的一次会话;
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            // 3, 使用sqlSession操作数据库, 先获取到dao接口的实现
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            // 4, 调用之前的方法
            employee = employeeDao.getEmpById(1);
        }finally {
            sqlSession.close();
        }
        System.out.println(employee);
    }
}
