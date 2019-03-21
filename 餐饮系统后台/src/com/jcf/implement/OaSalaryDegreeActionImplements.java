package com.jcf.implement;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.jcf.interfaces.OaSalaryDegreeActionInfaces;
import com.jcf.util.DBHelper;
import com.jcf.util.Limit;

@Service
public class OaSalaryDegreeActionImplements implements
		OaSalaryDegreeActionInfaces {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public String insertSalary(String salary_degree_money,
			String salary_degree_name, String salary_degree_flag,
			String department_id) {
		if (jdbcTemplate != null) {
			String sql = "insert into oa_salary_degree(salary_degree_name,salary_degree_money,salary_degree_flag,department_id)values(?,?,?,?)";
			int rows = jdbcTemplate.update(sql, new Object[] {
					salary_degree_name, salary_degree_money,
					salary_degree_flag, department_id });
			System.out.println(salary_degree_name + salary_degree_money
					+ salary_degree_flag + department_id);
			return DBHelper.ReturnRows(rows);
		}
		return DBHelper.ReturnRows(0);
	}

	@Override
	public String deleteSalary(String salary_degree_name) {
		if (jdbcTemplate != null) {
			String sql = "update  oa_salary_degree salary_degree_flag=0  where salary_degree_name=?";
			int rows = jdbcTemplate.update(sql,
					new Object[] { salary_degree_name });
			return DBHelper.ReturnRows(rows);
		} else {
			return DBHelper.ReturnRows(0);
		}

	}

	@Override
	public String updateSalary(String salary_degree_name,
			String salary_degree_money, String salary_degree_flag,
			String department_id) {
		if (jdbcTemplate != null) {
			String sql = "update oa_salary_degree set salary_degree_name=?,salary_degree_money=?,salary_degree_flag=?,department_id=? where salary_degree_name=?";

			int rows = jdbcTemplate.update(sql, new Object[] {
					salary_degree_name, salary_degree_money,
					salary_degree_flag, department_id, salary_degree_name });
			return DBHelper.ReturnRows(rows);
		} else {
			return DBHelper.ReturnRows(0);
		}

	}

	@Override
	public String querySalary(String salary_degree_name,String page) {
		if (jdbcTemplate != null) {
			int pagesize=2;
			int pageIndex= Limit.QueryLimit(page, pagesize);
			if (salary_degree_name == null) {
				String sql = "select * from oa_salary_degree limit ?,?";
				List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,new Object[]{pageIndex,pagesize});
				return DBHelper.ReturnList(list);
			} else {
				String sql = "select * from oa_salary_degree where salary_degree_name=? limit ?,?";
				List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,
						new Object[] { salary_degree_name,pageIndex,pagesize});
				return DBHelper.ReturnList(list);
			}

		} else {

			return DBHelper.FailConnectDatabase();
		}

	}

}
