package com.jcf.implement;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.jcf.interfaces.SigActionInterfaces;
import com.jcf.util.DBHelper;
import com.jcf.util.MyUtil;
@Service
public class SigActionImplemnets implements SigActionInterfaces {
	@Autowired
	JdbcTemplate jdbcTemplate;
	/*
	 * @author Lee
	 * @time 2018/1/12
	 * @fun 标签插入接口
	 * */
	public String insertSig(String sig_name,String sig_type,String department_id,String sig_token)
	{
		if(jdbcTemplate!=null)
		{
			 String token = "sig" + MyUtil.GetToken().substring(0,9);
			 int rows =	jdbcTemplate.update("insert into snug_sig_info(sig_name,sig_type,sig_flag,department_id,sig_token) values(?,?,?,?,?)", 
						new Object[]{ sig_name, sig_type,"1", department_id,token});
			 return DBHelper.ReturnRows(rows);
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}
	
	/*
	 * @author Lee
	 * @time 2018/1/15
	 * @fun 标签插入查询
	 * http://localhost:8080/snug/getsiglist
	 * */
	@Override
	public String querySigList(String department_id)
	{
		if(jdbcTemplate!=null)
		{
		  List<Map<String,Object>> list = jdbcTemplate.queryForList("select * " +
		  		"from snug_sig_info where sig_flag !='0' and department_id=?",department_id);
		  if(list.size()==0)
		  {
				JSONObject object = new JSONObject();
				object.put("status", "");
				object.put("mes", "[]");
				return object.toString();
		  }
		  return DBHelper.ReturnList(list);
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}
	/*
	 * @author Lee
	 * @time 2018/1/15
	 * @fun 标签修改接口
	 * */
	@Override
	public String updateSig(String sig_name,String sig_type,String department_id,String token)
	{
		if(jdbcTemplate!=null)
		{
			String sqlString = "update snug_sig_info set sig_name = ?,sig_type = ? where sig_token=? and sig_flag != '0',department_id = ?";
			int rows = jdbcTemplate.update(sqlString, new Object[]{sig_name,sig_type,token,department_id});
			return 	DBHelper.ReturnRows(rows);
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}

	@Override
	public String deleteSig(String token) {
		 if (jdbcTemplate!=null) 
		 {
			String sql = "update snug_sig_info set sig_flag = 0 where sig_token = ?";
			int rows = jdbcTemplate.update(sql,token);
			return DBHelper.ReturnRows(rows);
		 }
		 else
		 {
		    return DBHelper.FailConnectDatabase();
		 }
	}
	
	@Override
	public String insertSigs(String sigList,String sig_type,String department_id) {
		if (jdbcTemplate!=null)
		{
			String sqlString = "insert snug_sig_info (sig_name,sig_type,department_id,sig_token) values(?,?,?,?)";
			//ArrayList<String> siglist = new ArrayList<String>();
			String[] arr = sigList.split(",");
			for (int i = 0; i < arr.length; i++) {
				String token = "sig" + MyUtil.GetToken().substring(0,9);
				Object[] params = new Object[]{arr[i],sig_type,department_id,token};
				jdbcTemplate.update(sqlString, params);
			}
			
			JSONObject object = new JSONObject();
			try 
			{
				object.put("status", "1");
				object.put("mes","success");
			} 
			catch (JSONException e)
			{
				e.printStackTrace();
			}
			return object.toString();
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}
}
