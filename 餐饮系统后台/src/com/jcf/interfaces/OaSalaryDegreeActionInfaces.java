package com.jcf.interfaces;

public interface OaSalaryDegreeActionInfaces {

	
	//增加工资表信息
	public String insertSalary(
			String salary_degree_money,
			String salary_degree_name,
			String salary_degree_flag,
			String department_id
			);
	//删除工资表信息
	public String deleteSalary(String salary_degree_name);
	//改工资表信息
	public String updateSalary(
			String salary_degree_name,
			String salary_degree_money,
			String salary_degree_flag,
			String department_id
			);
	//查工资表信息
	public String querySalary(String salary_degree_name,String page);
}
