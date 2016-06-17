package me.qiancheng.dao;

import java.util.List;
import me.qiancheng.model.Employee;

public interface EmployeeDao {

	List<Employee> getAll();

	Employee queryById(int id);
	
	boolean save(Employee employee);
	
}