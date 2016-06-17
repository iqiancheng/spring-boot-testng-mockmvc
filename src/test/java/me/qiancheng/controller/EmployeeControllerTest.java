package me.qiancheng.controller;

import me.qiancheng.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@TransactionConfiguration(defaultRollback = false)
@WebAppConfiguration
@ContextConfiguration({"/spring-context.xml", "/spring-mvc.xml"})
public class EmployeeControllerTest extends AbstractTestNGSpringContextTests {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	@BeforeMethod
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void testList() throws Exception {
		mockMvc.perform(get("/employee/list"))
			.andExpect(handler().methodName("list"))
			.andExpect(status().isOk())
			.andExpect(view().name("home"))
			.andExpect(forwardedUrl("/WEB-INF/pages/home.jsp"))
			.andExpect(model().attribute("employeeList", allOf(
				not(nullValue()), hasSize(greaterThan(0))
			))).andDo(print());
	}

	@Test
	public void testSave() throws Exception {
		mockMvc.perform(get("/employee/save")
			.param("name", "店小四")
			.param("age", "20")
			.param("mail", "dianxiaosi@yeah.net"))
			.andExpect(handler().methodName("save"))
			.andExpect(status().isOk())
			.andExpect(view().name("home"))
			.andExpect(forwardedUrl("/WEB-INF/pages/home.jsp"))
			.andExpect(model().attribute("success", is(true)))
			.andDo(print());
	}

	@Test
	public void testInfo() throws Exception {
		mockMvc.perform(get("/employee/info/{employeeId:[1-9]\\d*}", 2))
			.andExpect(handler().methodName("info"))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("/WEB-INF/pages/home.jsp"))
			.andExpect(model().attribute("employee",allOf(
				not(nullValue()), isA(Employee.class)
			))).andDo(print());
	}

	@Test
	public void testJsonSave() throws Exception {
		String employee = "{\"id\":null,\"age\":18,\"name\":\"店小六\",\"mail\":\"dianxiaoliu@163.com\"}";
		mockMvc.perform(post("/employee/json/save").contentType("application/json").content(employee))
			.andExpect(handler().methodName("save"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").value(true))
			.andDo(print());
	}

	@Test
	public void testJsonInfo() throws Exception {
		mockMvc.perform(post("/employee/json/info/{employeeId:[1-9]\\d*}", "2").accept("application/json"))
			.andExpect(handler().methodName("info"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(2))
			.andDo(print());
	}

}