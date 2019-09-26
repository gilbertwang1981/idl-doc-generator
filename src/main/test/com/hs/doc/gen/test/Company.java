package com.hs.doc.gen.test;

import java.util.List;

public class Company {
	private String companyCode;
	private List<Employee> employee;
	private Employee monitor;
	
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public List<Employee> getEmployee() {
		return employee;
	}
	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}
	public Employee getMonitor() {
		return monitor;
	}
	public void setMonitor(Employee monitor) {
		this.monitor = monitor;
	}
}
