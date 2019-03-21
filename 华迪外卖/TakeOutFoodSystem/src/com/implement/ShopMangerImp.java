package com.implement;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.interfaces.ShopMangerInterface;
@Service
public class ShopMangerImp implements ShopMangerInterface {
	@Autowired
	JdbcTemplate jt;
	@Override
	public String queryShopMesByPhone(String shop_phone) 
	{
		if (jt != null)
		{
			//查询店铺基本信息
			String queryShop = "select * from shopadminstor where shopphone = ?";
			Map<String, Object> shopmap = jt.queryForMap(queryShop,shop_phone);
			if (shopmap.size() == 0)
			{
				JSONObject object = new JSONObject();
				object.put("status","2");
				object.put("info","no data");
				return object.toString();
			}
			//查询店铺有的商品信息
			String queryShopGoods = "select * from goods where shopphone = ?";
			List<Map<String, Object>> goodlist = jt.queryForList(queryShopGoods,shop_phone);
			//返回数据
			JSONObject object = new JSONObject();
			object.put("status","1");
			object.put("shopmes",shopmap);
			object.put("shopgoods",goodlist);
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

}
