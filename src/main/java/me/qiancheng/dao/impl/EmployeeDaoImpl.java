package me.qiancheng.dao.impl;

import me.qiancheng.dao.EmployeeDao;
import me.qiancheng.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Employee> getAll() {
		String sql = "SELECT * FROM Employee";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class));
	}

	@Override
	public Employee queryById(int id) {
		String sql = "SELECT * FROM Employee WHERE id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Employee.class));
	}

	@Override
	public boolean save(Employee employee) {
		String sql = "INSERT INTO Employee(id, name, age, mail) VALUES(?, ?, ?, ?)";
		int result = jdbcTemplate.update(sql, null, employee.getName(), employee.getAge(), employee.getMail());
		return result > 0;
	}

}