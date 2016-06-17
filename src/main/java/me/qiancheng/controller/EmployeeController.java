package me.qiancheng.controller;

import java.util.List;

import me.qiancheng.dao.EmployeeDao;
import me.qiancheng.model.Employee;
import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeDao employeeDao;
	private static final String PAGE_RESULT = "home";
	
	@RequestMapping(value = "/list", method = GET)
	public String list(ModelMap model) {
		List<Employee> list = employeeDao.getAll();
		model.put("employeeList", list);
		return PAGE_RESULT;
	}
	
	@RequestMapping(value = "/save", method = GET)
	public String save(ModelMap model, Employee employee) {
		boolean result = employeeDao.save(employee);
		model.put("success", result);
		return PAGE_RESULT;
	}
	
	@RequestMapping(value = "/info/{employeeId:[1-9]\\d*}", method = GET)
	public String info(ModelMap model, @PathVariable Integer employeeId) {
		Employee employee = employeeDao.queryById(employeeId);
		model.put("employee", employee);
		return PAGE_RESULT;
	}
	
	@RequestMapping(value = "/json/save", method = POST)
	public @ResponseBody boolean save(@RequestBody Employee employee) {
		boolean result = employeeDao.save(employee);
		return result;
	}
	
	@RequestMapping(value = "/json/info/{employeeId:[1-9]\\d*}", method = POST)
	public @ResponseBody Employee info(@PathVariable Integer employeeId) {
		Employee employee = employeeDao.queryById(employeeId);
		return employee;
	}
	
}