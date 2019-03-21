package com.implement;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.interfaces.QueryShopInterface;

@Service
public class QueryShopImp implements QueryShopInterface {
	@Autowired
	JdbcTemplate jt;
	@Override
	public String queryShopBySchool(String schoolid, String shop_type) 
	{
		if (jt != null)
		{
			if (schoolid != null && shop_type!= null)
			{
				String sql = "";
				List<Map<String, Object>> shops = null;
				//如果传入的类型为空字符串就查询所有类型的店铺
				if (shop_type.equals(""))
				{
					sql = "select * from shopadminstor where schoolid = ?";
					shops = jt.queryForList(sql, new Object[]{schoolid});
				}
				else
				{
					sql = "select * from shopadminstor where schoolid = ? and shoptype = ?";
					shops = jt.queryForList(sql, new Object[]{schoolid,shop_type});
				}
				return shops.toString();
			}
		}
		else
		{
			JSONObject object = new JSONObject();
			object.put("status","3");
			object.put("info","dbfail!!!");
			return object.toString();
		}
		return null;
	}

	@Override
	public String queryShopByUserPhone(String user_phone) 
	{
		if (jt!=null)
		{
			//查询用户属于的学校
			String sql = "select schoolid from user where phone = ?";
			List<Map<String, Object>> school_list = jt.queryForList(sql,user_phone);
			String school_id = "";
			for (Map<String, Object> map : school_list)
			{
				for (Entry<String, Object> entry : map.entrySet())
				{
					if (entry.getKey().equals("schoolid"))
					{
						school_id = (String) entry.getValue();
						break;
					}
				}
			}
			//查询学校的名称
			String query_school_name = "select * from school where schoolId = ?";
			Map<String, Object> schoolmap = jt.queryForMap(query_school_name,school_id);
			String school_name = "";
			for (Entry<String, Object> entry : schoolmap.entrySet())
			{
				if (entry.getKey().equals("schoolName"))
				{
					school_name = (String) entry.getValue();
				}
			}
			//查询对应学校的信息
			String queryshop = "select * from shopadminstor where schoolid = ?";
			List<Map<String, Object>> shops = jt.queryForList(queryshop, new Object[]{school_id});
			
			JSONObject object = new JSONObject();
			object.put("status","1");
			object.put("shops",shops);
			object.put("school",school_name);
			object.put("schoolid",school_id);
			return object.toString();
		}
		else
		{
			JSONObject object = new JSONObject();
			object.put("status","3");
			object.put("info","dbfail!!!");
			return object.toString();
		}
	}

}
