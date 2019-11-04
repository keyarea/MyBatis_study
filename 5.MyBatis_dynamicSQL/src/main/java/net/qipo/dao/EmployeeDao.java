package net.qipo.dao;

import net.qipo.bean.Employee;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface EmployeeDao {

    /**
     * key为id主键，value是这条记录封装好的对象
     */
    @MapKey("id")
    public Map<Integer, Employee> getAllEmpReturnMap();
    /**
     * 列名作为key，值作为value
     */
    public Map<String, Object> getEmpByIdReturnMap(Integer id);
    public List<Employee> getAllEmps();
    public Employee getEmpById(Integer id);
    public int updateEmployee(Employee employee);
    public int deleteEmployee(Integer id);
    public int insertEmployee(Employee employee);
}
