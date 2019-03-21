package com.jcf.implement;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.jcf.interfaces.OaRoleAcitonInfaces;
import com.jcf.util.DBHelper;
import com.jcf.util.Limit;
import com.jcf.util.MyUtil;
@Service
public class OaRoleActionImpments implements OaRoleAcitonInfaces {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public String queryRoleList(String sub_department_id,String page) {
		if (jdbcTemplate!=null)
		{   
			
			int pagesize=2;
	        int pageIndex= Limit.QueryLimit(page, pagesize);
			if(sub_department_id==null){
				String sqlString = "SELECT * FROM oa_role_info limit ?,?";
				List<Map<String, Object>>  list=jdbcTemplate.queryForList(sqlString,new Object[]
						{pageIndex,pagesize});
				return DBHelper.ReturnList(list);
			}else {
				
				String sqlString = "SELECT * FROM oa_role_info where sub_department_id=? limit ?,?";
				List<Map<String, Object>>  list=jdbcTemplate.queryForList(sqlString,new Object[]
						{sub_department_id,pageIndex,pagesize});
				return DBHelper.ReturnList(list);
			}
			
		
		}else {
			return DBHelper.FailConnectDatabase();
		}
	}
	
	public String deletRoleList(String role_info_token) {
		if (jdbcTemplate!=null)
		{ 
			//DELETE * FROM oa_role_info WHERE role_info_token=?
			String sqlString = "update  oa_role_info role_info_flag=0 WHERE role_info_token=?";	
			int rows=jdbcTemplate.update(sqlString,new Object[]{role_info_token});
			return DBHelper.ReturnRows(rows);
		}else {
			return DBHelper.ReturnRows(0);
		}
		
	
		
	}
	
	public String updateRoleList(
			String role_info_token,
			String role_info_flag,
			String role_info_type,
			String role_info_name,
			String operator_id,
			String department_id,
			String sub_department_id
			) {
		if (jdbcTemplate!=null){
			String sqlString = "update oa_role_info set role_info_flag=?,role_info_type=?,role_info_name=?,operator_id=?,department_id=?,sub_department_id=?  where role_info_token=?";
			int rows=jdbcTemplate.update(sqlString,new Object[]{
					role_info_flag,
					role_info_type,
					role_info_name,
					operator_id,
					department_id,
					sub_department_id,
					role_info_token,
					}
			
					);
			
			return DBHelper.ReturnRows(rows);
		}else {
			return DBHelper.ReturnRows(0);
		}
		
	}
	
	
	
	public String insertRoleList(String role_info_flag,
			String role_info_type, String role_info_name, String operator_id,
	String department_id,String sub_department_id) {  
		if (jdbcTemplate!=null){
			//得到token值
		    String company_info_token = "company" + MyUtil.GetToken().substring(0,9);
    	    
			String sqlString = "insert into oa_role_info (role_info_token,role_info_flag,role_info_type,role_info_name,operator_id,department_id,sub_department_id)values(?,?,?,?,?,?,?)";
			int rows=jdbcTemplate.update(sqlString,new Object[]{
					company_info_token,
					role_info_flag,
					role_info_type,
					role_info_name,
					operator_id,
					department_id,
					sub_department_id
					}
			);
			return DBHelper.ReturnRows(rows);
		}else {
			return DBHelper.ReturnRows(0);
		}
	}

	
	@Override
	public String queryAdmins(
			String department_id,
			String sub_department_id,
			String role_info_token
			)
	{
		//String query = "select * from";
		return role_info_token;
	}
	
	
	

}
