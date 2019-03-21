package com.jcf.implement;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.jcf.interfaces.OaJobInfoActionInfaces;
import com.jcf.interfaces.OaPowerInfoActionInfaces;
import com.jcf.interfaces.OaSalaryDegreeActionInfaces;
import com.jcf.util.DBHelper;
import com.jcf.util.Limit;
import com.jcf.util.MyUtil;

@Service
public class OaJobActionImplements implements
		OaJobInfoActionInfaces {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public String insertJob(	
			String basic_salary,
			String salary_degree_token,
			String years_salary,
			String years_ultimate,
			String job_info_flag,
			String department_id,
			String job_info_name,
			String power_info_token
			) 
	{
		if (jdbcTemplate != null) {
			String job_info_token="company" + MyUtil.GetToken().substring(0,9);
			
			String sql = "insert into oa_job_info( job_info_token,basic_salary,salary_degree_token,years_salary,years_ultimate,job_info_flag,department_id,job_info_name,power_info_token)values(?,?,?,?,?,?,?,?,?)";
			int rows = jdbcTemplate.update(sql, new Object[] {
					 job_info_token,
					 basic_salary,
					 salary_degree_token,
					 years_salary,
					 years_ultimate,
					 job_info_flag,
					 department_id,
					 job_info_name,
					 power_info_token
			});
			return DBHelper.ReturnRows(rows);
		}
		return DBHelper.ReturnRows(0);
	}

	@Override
	public String deleteJob(String job_info_token) {
		if (jdbcTemplate != null) {
			String sql = "update  oa_job_info set job_info_flag=0 where job_info_token=?";
			int rows = jdbcTemplate.update(sql,
					new Object[] { job_info_token });
			return DBHelper.ReturnRows(rows);
		} else {
			return DBHelper.ReturnRows(0);
		}

	}

	@Override
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
			String power_info_token) {
		if (jdbcTemplate != null) {
			String sql = "update oa_job_info set basic_salary=?,salary_degree_token=?,years_salary=?,years_ultimate=?,job_info_flag=?,department_id=?,job_info_name=?,power_info_token=? where job_info_token=?";
			int rows = jdbcTemplate.update(sql, new Object[] 
			{
					
					 basic_salary,
					 salary_degree_token,
					 years_salary,
					 years_ultimate,
					 job_info_flag,
					 department_id,
					 job_info_name,
					 power_info_token,
					 job_info_token
			});
			return DBHelper.ReturnRows(rows);
		} else {
			return DBHelper.ReturnRows(0);
		}

	}

	@Override
	public String queryJob(String job_info_token,String page) {
		if (jdbcTemplate != null) {
			int pagesize=2;
			int pageIndex=Limit.QueryLimit(page, pagesize);
			if (job_info_token == null) {

				String sql = "select * from oa_job_info limit ?,?";
				List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,new Object[]{pageIndex,pagesize});
				return DBHelper.ReturnList(list);
			} else {
				String sql = "select * from oa_job_info where job_info_token=? limit ?,?";
				List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,
						new Object[] { job_info_token,pageIndex,pagesize});
				return DBHelper.ReturnList(list);
			}

		} else {

			return DBHelper.FailConnectDatabase();
		}

	}


}
