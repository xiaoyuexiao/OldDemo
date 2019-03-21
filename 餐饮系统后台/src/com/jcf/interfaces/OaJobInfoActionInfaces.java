package com.jcf.interfaces;

public interface OaJobInfoActionInfaces {

	
	//增加工作信息
	public String insertJob(
			String basic_salary,
			String salary_degree_token,
			String years_salary,
			String years_ultimate,
			String job_info_flag,
			String department_id,
			String job_info_name,
			String power_info_token
			);
	//删除工作表信息
	public String deleteJob(String job_info_token);
	//改工作表信息
	public String updateJob(
			String job_info_token,
			String oa_job_info,
			String basic_salary,
			String salary_degree_token,
			String years_salary,
			String years_ultimate,
			String job_info_flag,
			String department_id,
			String job_info_name,
			String power_info_token
			);
	//查工作表信息
	public String queryJob(String oa_job_info, String page);
}
