package net.qipo.dao;

import net.qipo.bean.Employee;

public interface EmployeeDao {

    public Employee getEmpById(Integer id);
    public int updateEmployee(Employee employee);
    public int deleteEmployee(Integer id);
    public int insertEmployee(Employee employee);
}
