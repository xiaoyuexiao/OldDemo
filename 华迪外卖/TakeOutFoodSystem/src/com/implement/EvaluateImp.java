package com.implement;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.interfaces.EvaluateInterface;
@Service
public class EvaluateImp implements EvaluateInterface {
	@Autowired
	JdbcTemplate jt;
	@Override
	public String userEvaluate(String packing, String flavor, String distribution, String message, String orderid,
			String userphone) 
	{
		if (jt!=null)
		{
			System.out.println(packing);
			System.out.println(flavor);
			System.out.println(distribution);
			System.out.println(message);
			System.out.println(orderid);
			System.out.println(userphone);
			//1.向星级评价中添加评价
			String addstar = "insert into eva_star(Flavor,Packing,Distribution,Orderid) values(?,?,?,?)";
			int row = jt.update(addstar, new Object[]{flavor,packing,distribution,orderid});
			if (row == 0)
			{
				JSONObject object = new JSONObject();
				object.put("status","2");
				object.put("info","fail");
				return object.toString();
			}
			//2.通过订单id查询到商家的id
			String queryshopid = "select shopphone from `order` where orderid = ?";
			Map<String, Object> map = jt.queryForMap(queryshopid,orderid);
			String shopphone = "";
			for (Entry<String, Object> entry : map.entrySet())
			{
				if (entry.getKey().equals("shopphone"))
				{
					shopphone = (String) entry.getValue();
				}
			}
			//3.添加一条回复记录
				//查询此订单为第几条回复
				String query_eva_num = "select count(*) from evalutelist where orderid = ?";
				int eva_num = jt.queryForInt(query_eva_num, orderid);
				eva_num++;
				//添加记录
				String add_eva = "insert into evalutelist(evalutecontent,orderid,shopid,userid,evaluater,evaluatenum) values(?,?,?,?,?,?)";
				int eva_row = jt.update(add_eva, new Object[]{message,orderid,shopphone,userphone,"0",eva_num});
				if (eva_row == 0)
				{
					JSONObject object = new JSONObject();
					object.put("status","2");
					object.put("info","fail");
					return object.toString();
				}
				
				JSONObject object = new JSONObject();
				object.put("status","1");
				object.put("info","success");
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
	
	@Override
	public String queryEvlaluateListByOrderid(String orderid) {
		if (jt!=null)
		{
			String query_eva_list = "select * from evalutelist where orderid = ? order by evaluatenum ASC";
			List<Map<String, Object>> list = jt.queryForList(query_eva_list, orderid);
			JSONObject object = new JSONObject();
			object.put("status","1");
			object.put("info",list);
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
