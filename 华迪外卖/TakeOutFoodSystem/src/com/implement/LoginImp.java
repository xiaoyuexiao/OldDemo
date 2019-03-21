/**
 * 
 */
package com.implement;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.interfaces.LoginInterface;

/**
 * @author lee
 * @time:2018年6月11日
 */
@Service
public class LoginImp implements LoginInterface 
{
	@Autowired
	JdbcTemplate jt;
	@Override
	public String userLogin(String phone, String password)
	{
		if (jt!=null)
		{	
			//1.根据账号密码查询用户
			String queryUserPhone = "select * from user where phone = ? and password = ?";
			List<Map<String, Object>> list = jt.queryForList(queryUserPhone,new Object[]{phone,password});
			//判断用户是否存在，如果用户不存在
			if (list.size()!=1)
			{
				JSONObject object = new JSONObject();
				object.put("status","2");
				object.put("info","num is not exits");
				return object.toString();
			}
			//2.检测用户是否已经登录
			boolean flag = true;
			for (Map<String, Object> map : list)
			{
				for (Entry<String, Object> entry : map.entrySet())
				{
					if(entry.getKey().equals("userstatus") && entry.getValue().equals("1"))
					{
						flag = false;
						break;
					}
				}
			}
			if(flag == false)
			{
				JSONObject object = new JSONObject();
				object.put("status","3");
				object.put("info","login fail user had login in other ip!!!");
				return object.toString();
			}
			//3.改变用户状态，用户登陆成功
			String loginUser = "update user set userstatus = '1' where phone = ?";
			int row = jt.update(loginUser, new Object[]{phone});
			if (row==1)
			{
				JSONObject object = new JSONObject();
				object.put("status","1");
				object.put("info","login success");
				return object.toString();
			}
			else
			{
				JSONObject object = new JSONObject();
				object.put("status","4");
				object.put("info","login fail");
				return object.toString();
			}	
		}
		else
		{
			JSONObject object = new JSONObject();
			object.put("status","4");
			object.put("info","dbfail!!!");
			return object.toString();
		}
	}
	@Override
	public String shopLogin(String phone, String password)
	{
		if (jt!=null)
		{	
			//1.根据账号密码查询商家
			String queryUserPhone = "select * from shopadminstor where shopphone = ? and shoppwd = ?";
			List<Map<String, Object>> list = jt.queryForList(queryUserPhone,new Object[]{phone,password});
			//判断商家是否存在
			if (list.size()!=1)
			{
				JSONObject object = new JSONObject();
				object.put("status","2");
				object.put("info","num is not exits");
				return object.toString();
			}
			//2.判断商家是否已经登录
			boolean flag = true;
			for (Map<String, Object> map : list)
			{
				for (Entry<String, Object> entry : map.entrySet())
				{
					if(entry.getKey().equals("shopstatus") && entry.getValue().equals("1"))
					{
						flag = false;
						break;
					}
				}
			}
			if(flag == false)
			{
				JSONObject object = new JSONObject();
				object.put("status","3");
				object.put("info","login fail user had login in other ip!!!");
				return object.toString();
			}
			//3.改变商家状态，登陆成功
			String loginUser = "update shopadminstor set shopstatus = '1' where shopphone = ?";
			int row = jt.update(loginUser, new Object[]{phone});
			
			if (row==1)
			{
				JSONObject object = new JSONObject();
				object.put("status","1");
				object.put("info","login success");
				return object.toString();
			}
			else
			{
				JSONObject object = new JSONObject();
				object.put("status","4");
				object.put("info","login fail");
				return object.toString();
			}
			
		}
		else
		{
			JSONObject object = new JSONObject();
			object.put("status","4");
			object.put("info","dbfail!!!");
			return object.toString();
		}
	}
	@Override
	public String userQuite(String phone) 
	{
		if (jt!=null)
		{
			//修改用户状态
			String sql = "update user set userstatus = '0' where phone = ?";
			int row = jt.update(sql, phone);
			if (row==0)
			{
				//此处info返回值为0，说明退出登陆失败
				JSONObject object = new JSONObject();
				object.put("status","2");
				object.put("info","0");
				return object.toString();
			}
			//此处info返回值为1，说明退出登陆成功
			JSONObject object = new JSONObject();
			object.put("status","1");
			object.put("info","1");
			return object.toString();
		}
		else
		{
			JSONObject object = new JSONObject();
			object.put("status","3");
			object.put("info","db fail");
			return object.toString();
		}
	}
	@Override
	public String shopQuite(String phone) 
	{
		// TODO Auto-generated method stub
		return null;
	}

}
