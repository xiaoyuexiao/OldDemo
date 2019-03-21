package com.implement;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.interfaces.OrderMangerInterface;
import com.util.MyUtil;
@Service
public class OrderMangerImp implements OrderMangerInterface {
	@Autowired
	JdbcTemplate jt;
	@Override
	public String addOrder(String clear_address, String allmoney, String userphone, String shopphone, String good_str) {
		if (jt != null)
		{
			//生成一个订单
			String ordertoken = "order" + MyUtil.GetToken().substring(0,10);//随机取得订单的唯一值，即订单编号
			String sql = "insert into `order` (`orderid`,`ordermoney`,`userphone`,`shopphone`,`detailaddress`) values(?,?,?,?,?)";
			int row = jt.update(sql,new Object[]{ordertoken,allmoney,userphone,shopphone,clear_address});
			if (row == 0)
			{
				JSONObject object = new JSONObject();
				object.put("status","2");
				object.put("info","add fail");
				return object.toString();
			}
			//分解商品信息
			String[] goods = good_str.split(";");
			for (int i = 0; i < goods.length; i++) 
			{
				String add_order_goods = "insert into good_order_middle(goodId,goodnum,orderId) values(?,?,?)";
				String[] good = goods[i].split(",");
				int goodrows = jt.update(add_order_goods, new Object[]{good[0],good[1],ordertoken});
				if (goodrows==0)
				{
					JSONObject object = new JSONObject();
					object.put("status","2");
					object.put("info","add fail");
					return object.toString();
				}
			}
			//添加一条支付记录
			String payid = "pay" + MyUtil.GetToken().substring(0, 9);
			String addPay = "insert into sellermoneylist(payid,payuserphone,paymoney,shopid,payflag) values(?,?,?,?,?)";
			int pay_row = jt.update(addPay,new Object[]{payid,userphone,allmoney,shopphone,"1"});
			if (pay_row == 0)
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
	public String queryOrdersByUserphone(String user_phone)
	{
		if (jt!=null)
		{
			//查询所有订单
			//String sql = "select * from `order` left join `good_order_middle` on order.orderid = good_order_middle.orderId where userphone = ? ORDER BY orderstarttime ASC";
			String sql = "select * from `order` inner join `shopadminstor` on order.shopphone = shopadminstor.shopphone where userphone = ? ORDER BY orderstarttime DESC";
			List<Map<String, Object>> list = jt.queryForList(sql, user_phone);
			//查询订单中的商品
			for (Map<String, Object> map : list)
			{
				String orderid = "";
				for (Entry<String, Object> entry : map.entrySet())
				{
					if (entry.getKey().equals("orderid"))
					{
						orderid = (String) entry.getValue();
						break;
					}
				}
				//查询订单中商品的具体信息
				String querygood = "select * from `good_order_middle` inner join`goods` on good_order_middle.goodId = goods.goodid where good_order_middle.orderId = ?";
				List<Map<String, Object>> list2 = jt.queryForList(querygood, orderid);
				JSONArray array = MyUtil.getJsonArray(list2);
				map.put("goods", array);
			}
			
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
	@Override
	public String queryShopNameAndStarByorderid(String orderid)
	{
		if (jt!=null)
		{
			String sql = "select shopadminstor.shopname from `shopadminstor` inner join `order` on shopadminstor.shopphone = order.shopphone where order.orderid = ?";
			Map<String, Object> map = jt.queryForMap(sql, orderid);
			JSONObject object = new JSONObject();
			object.put("status","1");
			object.put("info",map);
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
