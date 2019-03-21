package com.jcf.implement;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.jcf.interfaces.FoodActionInterfaces;
import com.jcf.util.DBHelper;
import com.jcf.util.MyUtil;
@Service
public class FoodActionImplements implements FoodActionInterfaces {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	DataSourceTransactionManager  txManager;
	@Override
	public String insertFood( 
			 String food_info_name,String food_info_images,
			 String food_info_sig,String food_info_content,
			 String food_info_quantity,  String food_info_unit,
			 String food_info_sort,  String food_info_order,
			 String food_info_material, String food_info_sold,
			 String food_info_type, String food_info_price,
			 String department_id,String food_info_material_cost,
			 String food_info_image_word_content) {
		
			String token = "foods" + MyUtil.GetToken().substring(0,9);
			//图片上传
			String result = MyUtil.addImgs(food_info_images, token, jdbcTemplate);
			if (result.equals("error"))
			{
				JSONObject object = new JSONObject();
				object.put("status", "2");
				object.put("mes", "图片参数错误，图片库没有所传图片！！！");
				return object.toString();
			}
			
			if (jdbcTemplate!=null)
			{
				//存菜品
				String sqlString = "insert into snug_food_info(" +
						"food_info_name," +
						"food_info_token," +
						"food_info_sig," +
						"food_info_content," +
						"food_info_quantity," +
						"food_info_unit," +
						"food_info_sort," +
						"food_info_order," +
						"food_info_material," +
						"food_info_sold," +
						"food_info_type," +
						"food_info_price," +
						"department_id," +
						"food_info_material_cost," +
						"food_info_image_word_content) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				
				
				
				Object[] params = new Object[]{
						food_info_name,
						token,
						food_info_sig,
						food_info_content,
						food_info_quantity,
						food_info_unit,
						food_info_sort,
						food_info_order,
						food_info_material,
						food_info_sold,
						food_info_type,
						food_info_price,
						department_id,
						food_info_material_cost,
						food_info_image_word_content
						};
				jdbcTemplate.update(sqlString,params);
				//存标签
				return MyUtil.addSigs(food_info_sig, food_info_type, jdbcTemplate, department_id);
			}
			else
			{
				return DBHelper.FailConnectDatabase();
			}
	}

	@Override
	public String deleteFood(String token) {
		if (jdbcTemplate!=null) {
			String sqlString = "update snug_food_info set food_info_flag = 0 where food_info_token = ?";
			int rows = jdbcTemplate.update(sqlString, new Object[]{token});
			return DBHelper.ReturnRows(rows);
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}

	@Override
	public String queryFood() {
		if (jdbcTemplate!=null) 
		{
			String sqlString = "select * from snug_food_info where food_info_flag = 1";
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlString);
			return DBHelper.ReturnList(list);
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}
	//http://localhost:8080/snug/updateFoodInfo?food_info_name=111111111&food_info_sig=%E5%BE%AE%E8%BE%A3%EF%BC%8C%E7%BE%8E%E5%91%B3&food_info_content=%E5%BE%88%E5%A5%BD%E5%90%83&food_info_quantity=100
	//&food_info_unit=%E4%BB%BD&food_info_sort=003&food_info_order=%E5%A4%A7%E4%BB%BD&food_info_material=%E9%B1%BC
	//&food_info_sold=1500&food_info_type=%E5%AE%B6%E5%B8%B8%E8%8F%9C&food_info_price=100%E5%85%83&department_id=001
	//&food_info_token=foods649301f12&food_info_material_cost=123&food_info_image_word_content=5456&food_info_images=15204146980616.png,15204146980626.png
	@Override
	public String updateFoodInfo(
			String food_info_name, String food_info_sig,
			String food_info_content, String food_info_quantity,
			String food_info_unit, String food_info_sort,
			String food_info_order, String food_info_material,
			String food_info_sold, String food_info_type,
			String food_info_price, String department_id,
			String food_info_token,String food_info_material_cost,
			String food_info_image_word_content,String food_info_images)
	{
		System.out.println("parames:");
		System.out.println("food_info_name:"+food_info_name);
		System.out.println("food_info_sig:"+food_info_sig);
		System.out.println("food_info_content:"+food_info_content);
		System.out.println("food_info_quantity:"+food_info_quantity);
		System.out.println("food_info_unit:"+food_info_unit);
		System.out.println("food_info_sort:"+food_info_sort);
		System.out.println("food_info_order:"+food_info_order);
		System.out.println("food_info_material:"+food_info_material);
		System.out.println("food_info_sold:"+food_info_sold);
		System.out.println("food_info_type:"+food_info_type);
		System.out.println("food_info_sold:"+food_info_sold);
		System.out.println("food_info_price:"+food_info_price);
		System.out.println("department_id:"+department_id);
		System.out.println("food_info_token:"+food_info_token);
		System.out.println("food_info_material_cost:"+food_info_material_cost);
		System.out.println("food_info_image_word_content:"+food_info_image_word_content);
		System.out.println("food_info_images:"+food_info_images);
		//删除以前的图片
		String deleteString = "update snug_images_info set image_info_flag = '0' where image_info_token_ref = ?";
		jdbcTemplate.update(deleteString, food_info_token);
		//添加新的图片
		String result = MyUtil.addImgs(food_info_images, food_info_token, jdbcTemplate);
		if (result.equals("error"))
		{
			JSONObject object = new JSONObject();
			object.put("status", "2");
			object.put("mes", "图片参数错误，图片库没有所传图片！！！");
			return object.toString();
		}
		
		if (jdbcTemplate!=null) {
			String sqlString = "update snug_food_info set" +
					" food_info_name=?,"+
					"food_info_sig=?,"+
					"food_info_content=?,"+
					"food_info_quantity=?,"+
					"food_info_unit=?,"+
					"food_info_sort=?,"+
					"food_info_order=?,"+
					"food_info_material=?,"+
					"food_info_sold=?,"+
					"food_info_type=?,"+
					"food_info_price=?,"+
					"food_info_material_cost = ?," +
					"food_info_image_word_content = ? where " +
					"food_info_token =? and department_id = ? and food_info_flag = '1'";
//			try {
//				GetDataSource ds = new GetDataSource();
//				Connection connection = ds.getConnnection();
//				PreparedStatement pstm;
//				pstm = (PreparedStatement) connection.prepareStatement(sqlString);
//				pstm.setString(1, food_info_name);
//				pstm.setString(2, food_info_sig);
//				pstm.setString(3, food_info_content);
//				pstm.setString(4, food_info_quantity);
//				pstm.setString(5, food_info_unit);
//				pstm.setString(6, food_info_sort);
//				pstm.setString(7, food_info_order);
//				pstm.setString(8, food_info_material);
//				pstm.setString(9, food_info_sold);
//				pstm.setString(10, food_info_type);
//				pstm.setString(11, food_info_price);
//				pstm.setString(12, food_info_material_cost);
//				pstm.setString(13, food_info_image_word_content);
//				pstm.setString(14, food_info_token);
//				pstm.setString(15, department_id);
//				System.out.println(pstm);
//				pstm.execute();
//				
//			}
//			catch (SQLException e)
//			{
//				e.printStackTrace();
//			}
			
			int rows = jdbcTemplate.update(sqlString, new Object[]{
					food_info_name,
					food_info_sig,
					food_info_content,
					food_info_quantity,
					food_info_unit,
					food_info_sort,
					food_info_order,
					food_info_material,
					food_info_sold,
					food_info_type,
					food_info_price,
					food_info_material_cost,
					food_info_image_word_content,
					food_info_token,
					department_id});
			return DBHelper.ReturnRows(rows);
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}

	@Override
	public String queryDefaultFood() {
		if (jdbcTemplate!=null)
		{
			//返回的JSONArray
			JSONArray arr = new JSONArray();
			//查询所有一级分类
			String sqlString = "select food_type_name,food_type_token from snug_food_type where food_type_father = '-1' and food_type_flag!='0'";
			List<Map<String, Object>> list2 = jdbcTemplate.queryForList(sqlString);
			
			//查询一级分类下面是否有二级分类
			for (Map<String, Object> map : list2)
			{
				JSONObject object1 = new JSONObject();
				String fir_name = null;
				String fir_token = null;
				
				for (Map.Entry<String, Object> entry : map.entrySet())
				{
					if (entry.getKey().equals("food_type_name"))
					{
						fir_name = (String) entry.getValue();
						object1.put("fir_name", entry.getValue());
					}
					
					if (entry.getKey().equals("food_type_token"))
					{
						fir_token = (String) entry.getValue();
						object1.put("fir_token", entry.getValue());
					}
				}
				
				
				String sqlString3 = "select food_type_name,food_type_token from snug_food_type where food_type_father = ? and food_type_flag !='0'";
				List<Map<String, Object>> list3 = jdbcTemplate.queryForList(sqlString3,fir_token);
				
				if (list3.size()>0)
				{
					System.out.println(fir_name +"有二级分类："+list3);
					JSONArray array2 = new JSONArray();
					JSONObject object2 = null;
					//有二级分类，查询二级分类下面的菜品信息
					for (Map<String, Object> map2 : list3)
					{
						object2 = new JSONObject();
						String sec_name = null;
						String sec_token = null;
						for (Map.Entry<String, Object> entry2 : map2.entrySet())
						{
							if (entry2.getKey().equals("food_type_name"))
							{
								
								sec_name = (String) entry2.getValue();
							}
							if (entry2.getKey().equals("food_type_token"))
							{
								sec_token = (String) entry2.getValue();
							}
						}
						
						object2.put("sec_name", sec_name);
						object2.put("sec_token", sec_token);
						
						System.out.println(sec_name);
						String sqlString4 = "select food_info_name,food_info_token,food_info_content,food_info_sig,food_info_unit,food_info_quantity,food_info_price,food_info_flag,food_info_sold,food_info_material,food_info_sort,food_info_image_word_content from snug_food_info where food_info_type = ? and food_info_flag !='0'";
						List<Map<String, Object>> list4 = jdbcTemplate.queryForList(sqlString4,sec_token);
						for (Map<String, Object> map3 : list4)
						{
							List<Map<String,Object>> list = null;
							for (Map.Entry<String, Object> entry : map3.entrySet())
							{
								if (entry.getKey().equals("food_info_token"))
								{
									String query_imagesString = "select * from snug_images_info where image_info_flag !='0' and image_info_token_ref = ?";
									list = jdbcTemplate.queryForList(query_imagesString,entry.getValue());
								}
							}
							map3.put("food_info_imgs",list);
						}
						System.out.println("list4"+list4);
						JSONArray array = MyUtil.getJsonArray(list4);
						object2.put("sec_foods", array);
						array2.add(object2);
					}
					object1.put("sec_info", array2);
				}
				else
				{
					System.out.println(fir_name + "没有二级分类："+ list3);
					//没有二级分类，直接查询一级分类下面的菜品信息
					String sqlString5 = "select * from snug_food_info where food_info_type = ? and food_info_flag !='0'";
					List<Map<String, Object>> list5 = jdbcTemplate.queryForList(sqlString5,fir_token);
					JSONArray array = MyUtil.getJsonArray(list5);
					object1.put("fir_foods", array);
				}
				arr.add(object1);
			}
			
			JSONObject object = new JSONObject();
			object.put("status", "1");
			object.put("mes", "success");
			object.put("info", arr);
			return object.toString();
			
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}

	@Override
	public String turnFood(String bookdinner,String turntobookdinner, String foodInfo) {
		
			String foodtoken = null;
			String foodname = null;
			String foodnum = null;
			String foodprice = null;
		
			//分解转菜信息foodInfo：token123,清蒸肉,10,123元;token456,凤尾,10,13元	
//				String[] foods =  foodInfo.split(";");
//				for (int i = 0; i < foods.length; i++) {
//					System.out.println("========转菜具体清单=======");
//					String[] food = foods[i].split(",");
//					
//					 foodtoken = food[0];
//					 foodname = food[1];
//					 foodnum = food[2];
//					 foodprice = food[3];
//					
//					System.out.println("token:" + foodtoken);
//					System.out.println("名称:"+ foodname);
//					System.out.println("份数:" + foodnum);
//					System.out.println("单价:"+ foodprice);
//				}
		
			//得到订单表的table和对应food
			if (jdbcTemplate!=null)
			{
//==================================查询转入餐桌菜品信息================================================
				//查询需要转菜的桌子的菜品信息
				String querybookdinner = "select bookdinner_info_food,bookdinner_info_table from snug_bookdinner_info where bookdinner_info_token = ?";
				List<Map<String, Object>> list = jdbcTemplate.queryForList(querybookdinner,new Object[]{bookdinner});
				//判断菜品信息是否为空
				if (list.size()<=0)
				{
					JSONObject object = new JSONObject();
					object.put("status", "2");
					object.put("mes", "被转入单没数据");
					return object.toString();
				}
				//得到具体的菜品信息数组和桌子token值
				Map<String, Object> map = list.get(0);
				JSONObject foodsobject = new JSONObject();
				for (Map.Entry<String, Object> entry : map.entrySet()) {
						try {
							foodsobject.put(entry.getKey(),entry.getValue());
						} catch (JSONException e) {
							e.printStackTrace();
						}
				}
				String str = (String) foodsobject.get("bookdinner_info_food");
				//菜品数组
				JSONArray foodsarray = (JSONArray) JSONArray.parse(str);
//===============================遍历菜品数组，得到具体菜品信息===============================================
				String dbfoodtoken = null;
				String dbfoodnums = null;
				for (int i = 0; i < foodsarray.size(); i++) {
					JSONObject foodObject = foodsarray.getJSONObject(i);
//					System.out.println(foodObject.get("foodname"));
//					System.out.println(foodObject.get("foodprice"));
//					System.out.println(foodObject.get("foodnums"));
//					System.out.println(foodObject.get("foodtoken"));
//					System.out.println(foodObject.get("foodplus"));
					dbfoodtoken = (String) foodObject.get("foodtoken");
					dbfoodnums = (String) foodObject.get("foodnums");
					//需要转菜的具体信息
					String[] foods =  foodInfo.split(";");
					//转入订单数据库应该存的jsonarray
					JSONArray turnarray = new JSONArray();
//===============================遍历前台传过来的转菜字符串，并且判断是否和上面遍历的数据库中菜品的token值一样===============================================					
					for (int j = 0; j < foods.length; j++) {
						
						String[] food = foods[j].split(",");
						foodtoken = food[0];
						foodname = food[1];
						foodnum = food[2];
						foodprice = food[3];						
						if(foodtoken.equals(dbfoodtoken)){
						//进行转菜数据库操作
							//修改转菜订单的食物数据
							dbfoodnums = (Integer.parseInt(dbfoodnums) - Integer.parseInt(foodnum))+"";
							
							for (Map.Entry<String, Object> entry : foodObject.entrySet()) 
							{
								if (entry.getKey().equals("foodnums"))
								{
									entry.setValue(dbfoodnums);
								}
							}
							
							System.out.println("==============修改被转菜品订单后的数据==============="+foodsarray.toString());
							//修改被转入订单的食品数据
							String updatebeforefoodnum = foodsarray.toString();
							int rows = jdbcTemplate.update("update snug_bookdinner_info set bookdinner_info_food = ? where bookdinner_info_token = ?",new Object[]{updatebeforefoodnum,bookdinner});
							if (rows==0)
							{
								return "修改转入订单失败！！！";
							}
						}
						
						//修改转入订单的数据=======================================================================
						String queryturnbookdinner = "select bookdinner_info_food,bookdinner_info_table from snug_bookdinner_info where bookdinner_info_token = ?";
						List<Map<String, Object>> turnlist = jdbcTemplate.queryForList(queryturnbookdinner,new Object[]{turntobookdinner});
						//转入桌没有点菜信息
						JSONArray turnselectarray = MyUtil.getJsonArray(turnlist);
						if (turnselectarray.size()<=0) 
						{
							return "不能转向没有的桌子！！！！！！！！！！！";
						}
						JSONObject turnselectobject = (com.alibaba.fastjson.JSONObject) turnselectarray.get(0);
						String turnselectfoods = turnselectobject.get("bookdinner_info_food").toString();
						//System.out.println(turnselectobject.toString().length());
						if (turnselectfoods.length()==0)
						{
							JSONObject turnfood = new JSONObject();
							turnfood.put("foodname",foodname);
							turnfood.put("foodtoken",foodtoken);
							turnfood.put("foodprice", foodprice);
							turnfood.put("foodnums",foodnum);
							turnfood.put("foodplus","");
							turnarray.add(turnfood);
							String sql = "update snug_bookdinner_info set bookdinner_info_food = ? where bookdinner_info_token = ?";
							int rows = jdbcTemplate.update(sql,new Object[]{turnarray.toString(),turntobookdinner});
							return DBHelper.ReturnRows(rows);
						}
						else
						{
							//遍历注入卓的菜品信息
							String queryturnfood = "select bookdinner_info_food,bookdinner_info_table from snug_bookdinner_info where bookdinner_info_token = ?";
							if(jdbcTemplate!=null)
							{
								List<Map<String, Object>> list2 = jdbcTemplate.queryForList(queryturnfood,turntobookdinner);
								
								//System.out.println(list2.get(0).toString());
								JSONArray array = MyUtil.getJsonArray(list2);
								JSONObject foodturns = array.getJSONObject(0);
								String turnfoods = (String) foodturns.get("bookdinner_info_food");
								JSONArray foodsturnArray = (JSONArray) JSONArray.parse(turnfoods);
								
								for (int k = 0; k < foodsturnArray.size(); k++) {
									JSONObject object = (JSONObject) foodsturnArray.get(k);
									String turnfoodtoken = (String) object.get("foodtoken");
									String turnfoodnums = (String) object.get("foodnums");
									if(turnfoodtoken.equals(foodtoken))
									{
										//修改转菜订单的食物数据
										dbfoodnums = (Integer.parseInt(turnfoodnums) + Integer.parseInt(foodnum)) + "";
										
										for (Map.Entry<String, Object> entry : object.entrySet()) 
										{
											if (entry.getKey().equals("foodnums"))
											{
												entry.setValue(dbfoodnums);
											}
										}
										
										System.out.println("==============修改被转菜品订单后的数据==============="+foodsturnArray.toString());
										//修改被转入订单的食品数据
										String updatebeforefoodnum = foodsturnArray.toString();
										int rows = jdbcTemplate.update("update snug_bookdinner_info set bookdinner_info_food = ? where bookdinner_info_token = ?",new Object[]{updatebeforefoodnum,turntobookdinner});
										if (rows==0)
										{
											return "修改被转入订单失败！！！";
										}
									}
								}
							}
							else
							{
								return DBHelper.FailConnectDatabase();
							}
							
						}
						
					}
				}
				
				return "success";
				
				
				//System.out.println(object.get("bookdinner_info_table"));
				
			/*
				for (Map<String, Object> map : list) {
					JSONObject object = new JSONObject();
					for (Map.Entry<String, Object> entry : map.entrySet()) {
							try {
								object.put(entry.getKey(),entry.getValue());
							} catch (JSONException e) {
								e.printStackTrace();
							}
					}
					//array.add(object);
					try {
						String str = object.get("bookdinner_info_table").toString();
						JSONArray json = (JSONArray) JSONArray.parse(str);
						//遍历每一个餐桌
						for (int i = 0; i < json.size(); i++) {
							//将所有桌子转化成jsonObject
							JSONObject tables = json.getJSONObject(i);
							//得到桌子的token值
							String tabletoken = (String) tables.get("table");
							System.out.println("==============================================");
							System.out.println("tabletoken值："+ tabletoken);
							//得到桌子所点的食物信息,数组字符串
							String tablefoods = tables.getString("table_foods");
							System.out.println("====table_foods值:=====");
							//将食物信息字符串转化成jsonObject
							JSONArray foods = (JSONArray) JSONArray.parse(tablefoods);
							for (int j = 0; j < foods.size(); j++) {
								JSONObject food = foods.getJSONObject(j);
								System.out.println("foodtoken:"+food.get("foodtoken"));
								System.out.println("foodname:"+food.get("foodname"));
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
		*/
		
		//修改转菜信息
				
		//根据转菜信息转菜
			}
			else
			{
				return DBHelper.FailConnectDatabase();
			}
		
	}

	@Override
	public String getFoodCost(String material_mes)
	{
		
		if (jdbcTemplate!=null)
		{
			String[] materials = material_mes.split(";");
			Double total_price = 0.00;
			for (int i = 0; i < materials.length; i++)
			{
				String[] material = materials[i].split(",");
				String material_token = material[0];
				String material_nums = material[1];//单位：克
				//查询价格
				String query_price = "select food_material_inprice from snug_food_material where food_material_token = ?";
				Map<String, Object> price_map = jdbcTemplate.queryForMap(query_price,material_token);
				String price = (String) price_map.get("food_material_inprice");
				//计算总价
				total_price+=(Double.parseDouble(price) * Double.parseDouble(material_nums));
			}
			JSONObject object = new JSONObject();
			object.put("status","1");
			object.put("mes", "success");
			object.put("cost",String.format("%.2f", total_price));
			return object.toString();
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}

	@Override
	public String updateFoodInfoNoImg(String food_info_name,
			String food_info_sig, String food_info_content,
			String food_info_quantity, String food_info_unit,
			String food_info_sort, String food_info_order,
			String food_info_material, String food_info_sold,
			String food_info_type, String food_info_price,
			String department_id, String food_info_token,
			String food_info_material_cost,
			String food_info_image_word_content,String images_token_ref)
	{
				
		
		
		
				//分解要修改图片的token_ref，隐藏要修改的图片
				String[] imgtoken = images_token_ref.split(",");
				for (int i = 0; i < imgtoken.length; i++) {
					String sqlString = "update snug_images_info set image_info_flag = '0' where image_info_token_ref = ?";
					jdbcTemplate.update(sqlString, imgtoken[i]);
				}
				
				if (jdbcTemplate!=null) {
					String sqlString = "update snug_food_info set" +
							" food_info_name=?,"+
							"food_info_sig=?,"+
							"food_info_content=?,"+
							"food_info_quantity=?,"+
							"food_info_unit=?,"+
							"food_info_sort=?,"+
							"food_info_order=?,"+
							"food_info_material=?,"+
							"food_info_sold=?,"+
							"food_info_type=?,"+
							"food_info_price=?,"+
							"department_id = ?," +
							"food_info_material_cost = ?," +
							"food_info_image_word_content = ? where food_info_token =? and food_info_flag = 1";
					System.out.println(sqlString);
					int rows = jdbcTemplate.update(sqlString, new Object[]{food_info_name,food_info_sig,food_info_content,food_info_quantity,food_info_unit,food_info_sort,food_info_order,food_info_material,food_info_sold,food_info_type,food_info_price,department_id,food_info_token,food_info_material_cost,food_info_image_word_content});
					
					return DBHelper.ReturnRows(rows);
				}
				else
				{
					return DBHelper.FailConnectDatabase();
				}
		
	}

	@Override
	public String deleteFoods(String foods_token) {
		if (jdbcTemplate != null)
		{
			String[] tokens = foods_token.split(",");
			for (int i = 0; i < tokens.length; i++) {
				String sqlString = "update snug_food_info set food_info_flag = '0' where food_info_token = ?";
				jdbcTemplate.update(sqlString, tokens[i]);
			}
			
			JSONObject object = new JSONObject();
			object.put("status", "1");
			object.put("mes", "success");
			return object.toString();
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}

	@Override
	public String queryRuntime(String department_info_token) {
		if (jdbcTemplate != null) 
		{
			String sqlString = "select department_info_plus from snug_department_info where department_info_token = ?";
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlString, department_info_token);
			JSONArray array = MyUtil.getJsonArray(list);
			JSONObject object = new JSONObject();
			object.put("status", "1");
			object.put("mes", "success");
			object.put("info", array);
			return object.toString();
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}

	@Override
	public String queryFoodMaterial()
	{
		if (jdbcTemplate != null) 
		{
			String sqlString = "select food_material_name,food_material_token from snug_food_material where food_material_flag = '2'";
			jdbcTemplate.queryForList(sqlString);
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
		return null;
	}

	@Override
	public String orderFoods(String bookdinner_token, String foods_mes) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
