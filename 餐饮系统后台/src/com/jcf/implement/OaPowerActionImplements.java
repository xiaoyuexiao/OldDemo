package com.jcf.implement;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.jcf.interfaces.OaPowerInfoActionInfaces;
import com.jcf.interfaces.OaSalaryDegreeActionInfaces;
import com.jcf.util.DBHelper;
import com.jcf.util.Limit;

@Service
public class OaPowerActionImplements implements
		OaPowerInfoActionInfaces {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public String insertPower(	
			String power_info_name,
			String power_info_flag,
			String department_id) 
	{
		if (jdbcTemplate != null) {
			String sql = "insert into oa_power_info( power_info_name,power_info_flag,department_id)values(?,?,?)";
			int rows = jdbcTemplate.update(sql, new Object[] {
					power_info_name, power_info_flag,
					department_id });
			return DBHelper.ReturnRows(rows);
		}
		return DBHelper.ReturnRows(0);
	}

	@Override
	public String deletePower(String power_info_name) {
		if (jdbcTemplate != null) {
			String sql = "update  oa_power_info set power_info_flag=0 where power_info_name=?";
			int rows = jdbcTemplate.update(sql,
					new Object[] { power_info_name });
			return DBHelper.ReturnRows(rows);
		} else {
			return DBHelper.ReturnRows(0);
		}

	}

	@Override
	public String updatePower(
			Integer id,
			String power_info_name,
			String power_info_flag,
			String department_id) {
		if (jdbcTemplate != null) {
			String sql = "update oa_power_info set power_info_name=?,power_info_flag=?,department_id=? where id=?";

			int rows = jdbcTemplate.update(sql, new Object[] 
			{
				power_info_name,
				power_info_flag,
				department_id,
				id
			});
			return DBHelper.ReturnRows(rows);
		} else {
			return DBHelper.ReturnRows(0);
		}

	}

	@Override
	public String queryPower(String power_info_name,String page) {
		if (jdbcTemplate != null) {
			int pagesize=2;
            int pageIndex =Limit.QueryLimit(page, pagesize);
			if (power_info_name == null) {		
				String sql = "select * from oa_power_info limit ?,?";
				List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,new Object[]{pageIndex,pagesize});
				return DBHelper.ReturnList(list);
			} else {
				String sql = "select * from oa_power_info where power_info_name=? limit ?,?";
				List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,
						new Object[] { power_info_name,pageIndex,pagesize});
				return DBHelper.ReturnList(list);
			}

		} else {

			return DBHelper.FailConnectDatabase();
		}

	}

}
