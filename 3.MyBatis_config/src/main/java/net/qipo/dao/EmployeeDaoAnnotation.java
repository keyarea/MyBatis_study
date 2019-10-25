package net.qipo.dao;

import net.qipo.bean.Employee;
import org.apache.ibatis.annotations.Select;

public interface EmployeeDaoAnnotation {

    @Select("SELECT * FROM t_employee WHERE id=#{id}")
    public Employee getEmpById(Integer id);

    public int updateEmployee(Employee employee);

    public int deleteEmployee(Integer id);

    public int insertEmployee(Employee employee);
}
