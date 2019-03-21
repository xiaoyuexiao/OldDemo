package com.jcf.implement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.jcf.interfaces.OaAdminAcitonInfaces;
import com.jcf.interfaces.OaAttenceActionInfaces;
import com.jcf.interfaces.OaRoleAcitonInfaces;
import com.jcf.util.DBHelper;
import com.jcf.util.Limit;
import com.jcf.util.MyUtil;

@Service
public class OaAttenceActionImpments implements OaAttenceActionInfaces {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	public String queryAttenceList(String attence_group_name,String page) {
		if (jdbcTemplate != null) {
			int pagesize=2;
			int pageIndex=Limit.QueryLimit(page, pagesize);
		    if(attence_group_name==null){
		    	String sql="select * from oa_attence_group limit ?,?";
				List<Map<String, Object>> list= jdbcTemplate.queryForList(sql,new Object[]{pageIndex,pagesize});
				return DBHelper.ReturnList(list);
		    }else {
		    	String sql="select * from oa_attence_group where attence_group_name=? limit ?,?";
				List<Map<String, Object>> list= jdbcTemplate.queryForList(sql,new Object[]{attence_group_name,pageIndex,pagesize});
				return DBHelper.ReturnList(list);
			}
		
		} else {
			return DBHelper.FailConnectDatabase();
		}
		
	}


	@Override
	public String deleteAttenceList(String attence_group_name) {
	
		if (jdbcTemplate != null) {
			String sql="update  oa_attence_group set attence_group_flag=0 where attence_group_name=?";
			int rows= jdbcTemplate.update(sql,attence_group_name);
			return DBHelper.ReturnRows(rows);
		} else {
			return DBHelper.FailConnectDatabase();
		}
	}


	@Override
	public String insertAttenceList(
			String attence_group_name,
			String attence_group_flag,
			String attence_group_type,
			String attence_group_enclude,
			String attence_group_exclude,
			String attence_group_master,
			String attence_group_workday,
			String attence_group_restday,
			String attence_group_wifilist,
			String department_id) {
		if (jdbcTemplate != null) {
			String sql="insert into `oa_attence_group` ( " +
					"`attence_group_name`," +
					" `attence_group_flag`," +
					" `attence_group_type`, " +
					"`attence_group_enclude`, " +
					"`attence_group_exclude`, " +
					"`attence_group_master`," +
					" `attence_group_workday`," +
					" `attence_group_restday`, " +
					"`attence_group_wifilist`," +
					"`department_id`) values(?,?,?,?,?,?,?,?,?,?)";
			
			int rows=jdbcTemplate.update(sql,new Object[]{attence_group_name,attence_group_flag,
					attence_group_type,attence_group_enclude,
					attence_group_exclude,attence_group_master,
					attence_group_workday,attence_group_restday,
					attence_group_wifilist,department_id
			});
			
			return DBHelper.ReturnRows(rows);
		}
		    return DBHelper.ReturnRows(0);
	}


	@Override
	public String updateAttenceList(String attence_group_name,
			String attence_group_flag, String attence_group_type,
			String attence_group_enclude, String attence_group_exclude,
			String attence_group_master, String attence_group_workday,
			String attence_group_restday, String attence_group_wifilist,
			String department_id) {
		
		if(jdbcTemplate!=null)
		{
			String sql=
					"update oa_attence_group set attence_group_name=?,attence_group_flag=?," +
							"attence_group_type=?," +
							"attence_group_enclude=?, " +
							"attence_group_exclude=?, " +
							"attence_group_master=?," +
							"attence_group_workday=?," +
							"attence_group_restday=?, " +
							"attence_group_wifilist=?," +
							"department_id=? where attence_group_name=?";

			int rows=jdbcTemplate.update(sql,new Object[]{
					attence_group_name,attence_group_flag,
					attence_group_type,attence_group_enclude,
					attence_group_exclude,attence_group_master,
					attence_group_workday,attence_group_restday,
					attence_group_wifilist,department_id,
					attence_group_name});
			
		return DBHelper.ReturnRows(rows);
		}else 
		{
		return DBHelper.ReturnRows(0);
		}

	}




    
}
