package com.jcf.implement;


import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.jcf.interfaces.OaSchedulesActionlnfaces;
import com.jcf.util.DBHelper;
import com.jcf.util.Limit;
import com.jcf.util.MyUtil;
@Service
public class OaSchedulesActionImpments implements OaSchedulesActionlnfaces {
  
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public String OaInsertSchedules(
			String schedules_info_flag,
			String schedules_info_name,
			String schedules_info_work_time_type,
			String schedules_info_work_time_first_start,
			String schedules_info_work_time_first_end,
			String schedules_info_work_time_second_start,
			String schedules_info_work_time_second_end,
			String schedules_info_work_time_third_start,
			String schedules_info_work_time_third_end,
			String schedules_info_work_time_first_rest_start,
			String schedules_info_work_time_first_rest_end,
			String schedules_info_work_time_second_rest_start,
			String schedules_info_work_time_second_rest_end,
			String schedules_info_work_time_third_rest_start,
			String schedules_info_work_time_third_rest_end,
			String schedules_info_duty, 
			String schedules_info_delay_time,
			String operator_id,
			String department_id) {
		
		if (jdbcTemplate!=null)
		{   
			
			
			//得到token值
		    String company_info_token = "company" + MyUtil.GetToken().substring(0,9);
		    String sql="INSERT INTO `oa_schedules_info`(schedules_info_token,`schedules_info_flag`, `schedules_info_name`, `schedules_info_work_time_type`, `schedules_info_work_time_first_start`, `schedules_info_work_time_first_end`, `schedules_info_work_time_second_start`, `schedules_info_work_time_second_end`, `schedules_info_work_time_third_start`, `schedules_info_work_time_third_end`, `schedules_info_work_time_first_rest_start`, `schedules_info_work_time_first_rest_end`, `schedules_info_work_time_second_rest_start`, `schedules_info_work_time_second_rest_end`, `schedules_info_work_time_third_rest_start`, `schedules_info_work_time_third_rest_end`, `schedules_info_duty`, `schedules_info_delay_time`, `operator_id`, `department_id`)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
		int rows = jdbcTemplate.update(sql,new Object[]{
				 company_info_token,
				 schedules_info_flag,
				 schedules_info_name,
				 schedules_info_work_time_type,
				 schedules_info_work_time_first_start,
				 schedules_info_work_time_first_end,
				 schedules_info_work_time_second_start,
				 schedules_info_work_time_second_end,
				 schedules_info_work_time_third_start,
			     schedules_info_work_time_third_end,
				 schedules_info_work_time_first_rest_start,
				 schedules_info_work_time_first_rest_end,
				 schedules_info_work_time_second_rest_start,
				 schedules_info_work_time_second_rest_end,
				 schedules_info_work_time_third_rest_start,
				 schedules_info_work_time_third_rest_end,
				 schedules_info_duty, 
				 schedules_info_delay_time,
				 operator_id,
				 department_id}
		);	
		return DBHelper.ReturnRows(rows);  
		}else {
			
		}return  DBHelper.ReturnRows(0); 
	   
	}

	@Override
	public String OaUpdateSchedules(
			String schedules_info_token,
			String schedules_info_flag,
			String schedules_info_name,
			String schedules_info_work_time_type,
			String schedules_info_work_time_first_start,
			String schedules_info_work_time_first_end,
			String schedules_info_work_time_second_start,
			String schedules_info_work_time_second_end,
			String schedules_info_work_time_third_start,
			String schedules_info_work_time_third_end,
			String schedules_info_work_time_first_rest_start,
			String schedules_info_work_time_first_rest_end,
			String schedules_info_work_time_second_rest_start,
			String schedules_info_work_time_second_rest_end,
			String schedules_info_work_time_third_rest_start,
			String schedules_info_work_time_third_rest_end,
			String schedules_info_duty,
			String schedules_info_delay_time,
			String operator_id,
			String department_id
			) {
		if (jdbcTemplate!=null)
		{
			String sql="UPDATE oa_schedules_info SET schedules_info_flag=?," +
					"schedules_info_name=?," +
					"schedules_info_work_time_type=?," +
					"schedules_info_work_time_first_start=?," +
					"schedules_info_work_time_first_end=?," +
					"schedules_info_work_time_second_start=?," +
					"schedules_info_work_time_second_end=?," +
					"schedules_info_work_time_third_start=?," +
					"schedules_info_work_time_third_end=?," +
					"schedules_info_work_time_first_rest_start=?," +
					"schedules_info_work_time_first_rest_end=?," +
					"schedules_info_work_time_second_rest_start=?," +
					"schedules_info_work_time_second_rest_end=?," +
					"schedules_info_work_time_third_rest_start=?," +
					"schedules_info_work_time_third_rest_end=?," +
					"schedules_info_duty=?," +
					"schedules_info_delay_time=?," +
					"operator_id=?," +
					"department_id=? WHERE schedules_info_token=?";
			int rows = jdbcTemplate.update(sql,new Object[]{
					 schedules_info_flag,
					 schedules_info_name,
					 schedules_info_work_time_type,
					 schedules_info_work_time_first_start,
					 schedules_info_work_time_first_end,
					 schedules_info_work_time_second_start,
					 schedules_info_work_time_second_end,
					 schedules_info_work_time_third_start,
				     schedules_info_work_time_third_end,
					 schedules_info_work_time_first_rest_start,
					 schedules_info_work_time_first_rest_end,
					 schedules_info_work_time_second_rest_start,
					 schedules_info_work_time_second_rest_end,
					 schedules_info_work_time_third_rest_start,
					 schedules_info_work_time_third_rest_end,
					 schedules_info_duty, 
					 schedules_info_delay_time,
					 operator_id,
					 department_id,
					 schedules_info_token					 
			});
			return DBHelper.ReturnRows(rows);
		}else {
			return DBHelper.ReturnRows(0);
		}
		
	}

	@Override
	public  String OaQuerySchedules(String schedules_info_token,String page) {
		if(jdbcTemplate!=null)
		{
			int pagesize=2;
		    int pageIndext=	Limit.QueryLimit(page, pagesize);
			
			if(schedules_info_token==null){
				List<Map<String, Object>> list=jdbcTemplate.queryForList("select * from oa_schedules_info limit ?,?",new Object[]{pageIndext,pagesize});
				
				return DBHelper.ReturnList(list);
			}else {
	            List<Map<String, Object>> list=jdbcTemplate.queryForList("select * from oa_schedules_info where schedules_info_token=? limit ?,?",new Object[]{schedules_info_token,pageIndext,pagesize});
				
				return DBHelper.ReturnList(list);
			}
			
		}else {
			
			return DBHelper.ReturnRows(0);
		}
		
	}

	@Override
	public String OaDelSchedules(String schedules_info_token) {
		if(jdbcTemplate!=null)
		{
			int rows=jdbcTemplate.update("update  oa_schedules_info set schedules_info_flag=0 WHERE schedules_info_token = ?  ",new Object[]{schedules_info_token});
			
			return DBHelper.ReturnRows(rows);
		}
		return DBHelper.ReturnRows(0);
	}
}
