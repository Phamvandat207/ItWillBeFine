package com.ifi.data;

import com.ifi.entity.Employee;

import javax.ejb.Local;
import java.util.List;

@Local
public interface EmployeeDAO {
    List<Employee> findAllEmployee();
}
