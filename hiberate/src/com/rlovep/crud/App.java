package com.rlovep.crud;

import java.util.Date;

import org.junit.Test;

import com.rlovep.hello.Employee;

public class App {
	
	private EmployeeDaoImpl empDao = new EmployeeDaoImpl();

	@Test
	public void testFindById() {
		System.out.println(empDao.findById(2));
	}

	@Test
	public void testGetAll() {
		System.out.println(empDao.getAll());
	}

	@Test
	public void testGetAllString() {
		System.out.println(empDao.getAll("peace"));
	}

	@Test
	public void testGetAllIntInt() {
		System.out.println(empDao.getAll(3, 2));
	}

	@Test
	public void testSave() {
		Employee employee=new Employee();
		employee.setEmpName("peace4");
		employee.setWorkDate(new Date());
		empDao.save(employee);
	}

	@Test
	public void testUpdate() {
		Employee emp = new Employee();
		emp.setEmpId(2);
		emp.setEmpName("new jack");
		
		empDao.update(emp);
	}

	@Test
	public void testDelete() {
		empDao.delete(3);
	}

}
