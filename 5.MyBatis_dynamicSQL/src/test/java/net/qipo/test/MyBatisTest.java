package net.qipo.test;

import net.qipo.bean.Cat;
import net.qipo.bean.Employee;
import net.qipo.bean.Key;
import net.qipo.bean.Lock;
import net.qipo.dao.CatDao;
import net.qipo.dao.EmployeeDao;
import net.qipo.dao.KeyDao;
import net.qipo.dao.LockDao;
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

    /**
     * 分步查询集合
     *
     * 一般在工作的时候：写成两个方法；
     * 推荐写的是连接查询
     */
    @Test
    public void test09() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        LockDao lockDao = sqlSession.getMapper(LockDao.class);
        try {
            Lock lock = lockDao.getLockByIdByStep(3);
            System.out.println(lock);
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 分步查询
     */
    @Test
    public void test08() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        KeyDao keydao = sqlSession.getMapper(KeyDao.class);
        try {
            Key key = keydao.getKeyByIDSimple(3);
            System.out.println(key);
            // 在只需要一点信息的情况下，会造成严重的性能浪费

            // 按需加载，需要的时候再去查询；全局开启按需加载策略
            // 延迟加载：不着急查询
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test07() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        LockDao lockDao = sqlSession.getMapper(LockDao.class);
        try{
            Lock lock = lockDao.getLockById(3);
            System.out.println(lock);
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 联合查询情况下
     *
     * 1、使用级联属性封装联合查询后的所有结果
     */
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
