package com.jcf.implement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.jcf.interfaces.StockActionInterfaces;
import com.jcf.util.DBHelper;
import com.jcf.util.MyUtil;

@Service
public class StockActionImplements implements StockActionInterfaces {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public String queryGoodsMes() 
	{
		if (jdbcTemplate!=null)
		{
			//查询商品下面的二级分类
			String query = "select category_info_name,category_info_token from snug_category_info where category_info_flag = '1' and category_info_father = '1'";
			List<Map<String, Object>> list = jdbcTemplate.queryForList(query);
			
			//查询一级分类下面是否有商品
			String queryfir = "select	food_material_name," +
										"food_material_number," +
										"food_material_unit," +
										"food_material_inprice," +
										"food_material_outprice," +
										"food_material_min_inventory," +
										"food_material_max_inventory," +
										"food_material_guarantee," +
										"food_material_token from snug_food_material where food_material_type = '1' and food_material_flag = '2'";
			List<Map<String, Object>> list2 = jdbcTemplate.queryForList(queryfir);
			if (list2.size()==0)
			{
				//返回二级分类的商品信息
				//得到第一个二级分类
//				Map<String,Object> firtype = list.get(0);
//				String firtypetoken = (String) firtype.get("category_info_token");
//				String sql = "select food_material_name," +
//						"food_material_number," +
//						"food_material_unit," +
//						"food_material_outprice," +
//						"food_material_guarantee," +
//						"food_material_token from snug_food_material where food_material_type = ?";
//				List<Map<String, Object>> list3 = jdbcTemplate.queryForList(sql,firtypetoken);
//				JSONArray array3 = MyUtil.getJsonArray(list3);
				
				JSONArray array = MyUtil.getJsonArray(list);
				JSONObject object = new JSONObject();
				object.put("status", "1");
				object.put("mes", "success");
				object.put("info", "[]");
				object.put("sec_type",array);
				return object.toString();
			}
			else
			{
				//返回一级（商品）下面的数据
				JSONArray array2 = MyUtil.getJsonArray(list2);
				//二级分类
				JSONArray array = MyUtil.getJsonArray(list);
				JSONObject object = new JSONObject();
				object.put("status", "1");
				object.put("mes", "success");
				object.put("info",array2);
				object.put("sec_type",array);
				return object.toString();
			}
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
		
	}

	@Override
	public String queryGoodsByFirAndTow(String firstToken, String Towtoken) 
	{
		if (jdbcTemplate!=null)
		{
			
			String table = null;
			if (firstToken.equals("1"))
			{
				if (Towtoken.equals(""))
				{
					//查询商品下面的二级分类
					String query = "select category_info_name,category_info_token from snug_category_info where category_info_flag = '1' and category_info_father = '1'";
					List<Map<String, Object>> list2 = jdbcTemplate.queryForList(query);
					JSONArray array2 = MyUtil.getJsonArray(list2);
					String sql = "select " +
							"food_material_name," +
							"food_material_number," +
							"food_material_unit," +
							"food_material_inprice," +
							"food_material_outprice," +
							"food_material_guarantee," +
							"food_material_min_inventory," +
							"food_material_max_inventory," +
							"food_material_token from snug_food_material where food_material_flag = '2'";
					List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
					JSONArray array = MyUtil.getJsonArray(list);
					JSONObject object = new JSONObject();
					object.put("status", "1");
					object.put("mes","success");
					object.put("info", array);
					object.put("sec_type", array2);
					return object.toString();
				}
				
				String sql = "select " +
						"food_material_name," +
						"food_material_number," +
						"food_material_unit," +
						"food_material_inprice," +
						"food_material_outprice," +
						"food_material_guarantee," +
						"food_material_min_inventory," +
						"food_material_max_inventory," +
						"food_material_token from snug_food_material where food_material_type = ? and food_material_flag = '2'";
				List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,Towtoken);
				JSONArray array = MyUtil.getJsonArray(list);
				JSONObject object = new JSONObject();
				object.put("status", "1");
				object.put("mes","success");
				object.put("info", array);
				return object.toString();
			}
			else if(firstToken.equals("3"))
			{
				if (Towtoken.equals(""))
				{
					//查询商品下面的二级分类
					String query = "select category_info_name,category_info_token from snug_category_info where category_info_flag = '1' and category_info_father = '3'";
					List<Map<String, Object>> list2 = jdbcTemplate.queryForList(query);
					JSONArray array2 = MyUtil.getJsonArray(list2);
					
					table = "snug_wine_info";
					String sql = "select " +
							"wine_info_name," +
							"wine_info_max_numbers," +
							"wine_info_unit," +
							"wine_info_price_low," +
							"wine_info_price_high," +
							"wine_info_guarantee," +
							"wine_info_token," +
							"wine_info_min_inventory," +
							"wine_info_max_inventory," +
							"wine_info_commission_price from "+ table +" where wine_info_flag = '2'";
					List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
					JSONArray array = MyUtil.getJsonArray(list);
					JSONObject object = new JSONObject();
					object.put("status", "1");
					object.put("mes","success");
					object.put("info", array);
					object.put("sec_type", array2);
					return object.toString();
				}
				
				table = "snug_wine_info";
				String sql = "select " +
						"wine_info_name," +
						"wine_info_max_numbers," +
						"wine_info_unit," +
						"wine_info_price_low," +
						"wine_info_price_high," +
						"wine_info_guarantee," +
						"wine_info_token," +
						"wine_info_min_inventory," +
						"wine_info_max_inventory," +
						"wine_info_commission_price from "+ table +" where wine_info_type = ? and wine_info_flag = '2'";
				List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,Towtoken);
				JSONArray array = MyUtil.getJsonArray(list);
				JSONObject object = new JSONObject();
				object.put("status", "1");
				object.put("mes","success");
				object.put("info", array);
				return object.toString();
			}
			else if(firstToken.equals("2"))
			{
				
				if (Towtoken.equals(""))
				{
					//查询商品下面的二级分类
					String query = "select category_info_name,category_info_token from snug_category_info where category_info_flag = '1' and category_info_father = '2'";
					List<Map<String, Object>> list2 = jdbcTemplate.queryForList(query);
					JSONArray array2 = MyUtil.getJsonArray(list2);
					
					table = "snug_equipment_info";
					String sql = "select " +
							"equipment_info_name," +
							"equipment_info_number," +
							"equipment_info_unit," +
							"equipment_info_inprice," +
							"equipment_info_servicelift," +
							"equipment_info_maintain," +
							"equipment_info_min_inventory," +
							"equipment_info_max_inventory," +
							"equipment_info_token from "+ table +" where equipment_info_flag = '1'";
					List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
					JSONArray array = MyUtil.getJsonArray(list);
					JSONObject object = new JSONObject();
					object.put("status", "1");
					object.put("mes","success");
					object.put("info", array);
					object.put("sec_type", array2);
					return object.toString();
				}
				
				table = "snug_equipment_info";
				String sql = "select " +
						"equipment_info_name," +
						"equipment_info_number," +
						"equipment_info_unit," +
						"equipment_info_inprice," +
						"equipment_info_servicelift," +
						"equipment_info_maintain," +
						"equipment_info_min_inventory," +
						"equipment_info_max_inventory," +
						"equipment_info_token from "+ table +" where equipment_info_type = ? and equipment_info_flag = '1'";
				List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,Towtoken);
				JSONArray array = MyUtil.getJsonArray(list);
				JSONObject object = new JSONObject();
				object.put("status", "1");
				object.put("mes","success");
				object.put("info", array);
				return object.toString();
			}
			else if(firstToken.equals("4"))
			{
				if (Towtoken.equals(""))
				{
					//查询商品下面的二级分类
					String query = "select category_info_name,category_info_token from snug_category_info where category_info_flag = '1' and category_info_father = '4'";
					List<Map<String, Object>> list2 = jdbcTemplate.queryForList(query);
					JSONArray array2 = MyUtil.getJsonArray(list2);	
					table = "snug_othersgoods_info";
					String sql = "select " +
							"othersgoods_info_name," +
							"othersgoods_info_max_numbers," +
							"othersgoods_info_unit," +
							"othersgoods_info_price_low," +
							"othersgoods_info_price_high," +
							"othersgoods_info_guarantee," +
							"othersgoods_info_token," +
							"othersgoods_info_min_inventory," +
							"othersgoods_info_max_inventory" +
							" from "+ table +" where othersgoods_info_flag = '2'";
					List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
					JSONArray array = MyUtil.getJsonArray(list);
					JSONObject object = new JSONObject();
					object.put("status", "1");
					object.put("mes","success");
					object.put("info", array);
					object.put("sec_type", array2);
					return object.toString();
				}
				
				table = "snug_othersgoods_info";
				String sql = "select " +
						"othersgoods_info_name," +
						"othersgoods_info_max_numbers," +
						"othersgoods_info_unit," +
						"othersgoods_info_price_low," +
						"othersgoods_info_price_high," +
						"othersgoods_info_guarantee," +
						"othersgoods_info_token," +
						"othersgoods_info_min_inventory," +
						"othersgoods_info_max_inventory" +
						" from "+ table +" where othersgoods_info_type = ? and othersgoods_info_flag = '2'";
				List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,Towtoken);
				JSONArray array = MyUtil.getJsonArray(list);
				JSONObject object = new JSONObject();
				object.put("status", "1");
				object.put("mes","success");
				object.put("info", array);
				return object.toString();
			}
			else
			{
				JSONObject object = new JSONObject();
				object.put("status", "2");
				object.put("mes","参数错误！！");
				return object.toString();
			}
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}

	@Override
	public String queryAllGoodsMes(String key_word) 
	{
		if (jdbcTemplate!=null)
		{
			JSONObject object = new JSONObject();
			//查询商品下面的所有二级分类的所有商品
			String goods_sec_types = "select category_info_name,category_info_token from snug_category_info where category_info_father = '1'";
			List<Map<String, Object>> goods_types_list = jdbcTemplate.queryForList(goods_sec_types);
			JSONArray array = new JSONArray();
			for (Map<String, Object> map : goods_types_list)
			{
				JSONObject jsonObject = new JSONObject();
				for (Map.Entry<String, Object> entry : map.entrySet()) 
				{
					if (entry.getKey().equals("category_info_name"))
					{
						jsonObject.put("sec_type_name", entry.getValue());
					}
					if (entry.getKey().equals("category_info_token"))
					{
						jsonObject.put("sec_type_token", entry.getValue());
						String queryString = "select " +
												"food_material_name," +
												"food_material_number," +
												"food_material_unit," +
												"food_material_inprice," +
												"food_material_outprice," +
												"food_material_guarantee," +
												"food_material_min_inventory," +
												"food_material_max_inventory," +
												"food_material_token " +
												"from snug_food_material where food_material_type = ? and food_material_name like '%"+ key_word +"%' and food_material_flag = '2'";
						System.out.println(queryString);
						List<Map<String, Object>> list = jdbcTemplate.queryForList(queryString,entry.getValue());
						JSONArray arr = MyUtil.getJsonArray(list);
						jsonObject.put("goods", arr);
					}
				}
				array.add(jsonObject);
			}
			//查询商品下面一级分类的商品
			String goods_first = "select " +
					"food_material_name," +
					"food_material_number," +
					"food_material_unit," +
					"food_material_inprice," +
					"food_material_outprice," +
					"food_material_guarantee," +
					"food_material_min_inventory," +
					"food_material_max_inventory," +
					"food_material_token" +
					" from snug_food_material where food_material_type = '1' and food_material_name like '%"+ key_word +"%' and food_material_flag = '2'";
			
			List<Map<String, Object>> fir_goods_list = jdbcTemplate.queryForList(goods_first);
			JSONArray array2 = MyUtil.getJsonArray(fir_goods_list);
			
			JSONObject goods_object = new JSONObject();
			goods_object.put("fir_goods", array2);
			goods_object.put("sec_goods", array);
			object.put("goods_array", goods_object);
			//模糊检索设备
			String equ_sec_types = "select category_info_name,category_info_token from snug_category_info where category_info_father = '2'";
			List<Map<String, Object>> equs_types_list = jdbcTemplate.queryForList(equ_sec_types);
			JSONArray equarray = new JSONArray();
			for (Map<String, Object> map : equs_types_list)
			{
				JSONObject jsonObject = new JSONObject();
				for (Map.Entry<String, Object> entry : map.entrySet()) 
				{
					if (entry.getKey().equals("category_info_name"))
					{
						jsonObject.put("sec_type_name", entry.getValue());
					}
					if (entry.getKey().equals("category_info_token"))
					{
						jsonObject.put("sec_type_token", entry.getValue());
						String queryString = "select " +
												"equipment_info_name," +
												"equipment_info_number," +
												"equipment_info_unit," +
												"equipment_info_inprice," +
												"equipment_info_outprice," +
												"equipment_info_guarantee," +
												"equipment_info_min_inventory," +
												"equipment_info_max_inventory," +
												"equipment_info_token " +
												"from snug_equipment_info where equipment_info_type = ? and equipment_info_name like '%"+ key_word +"%' and equipment_info_flag = '1'";
						System.out.println(queryString);
						List<Map<String, Object>> list = jdbcTemplate.queryForList(queryString,entry.getValue());
						JSONArray arr = MyUtil.getJsonArray(list);
						jsonObject.put("goods", arr);
					}
				}
				equarray.add(jsonObject);
			}
			//查询商品下面一级分类的商品
			String equs_first = "select " +
					"equipment_info_name," +
					"equipment_info_number," +
					"equipment_info_unit," +
					"equipment_info_inprice," +
					"equipment_info_outprice," +
					"equipment_info_guarantee," +
					"equipment_info_min_inventory," +
					"equipment_info_max_inventory," +
					"equipment_info_token " +
					" from snug_equipment_info where equipment_info_type = '1' and equipment_info_name like '%"+ key_word +"%' and equipment_info_flag = '2'";
			
			List<Map<String, Object>> fir_equs_list = jdbcTemplate.queryForList(equs_first);
			JSONArray arrayequ = MyUtil.getJsonArray(fir_equs_list);
			
			JSONObject equs_object = new JSONObject();
			equs_object.put("fir_goods", arrayequ);
			equs_object.put("sec_goods", equarray);
			object.put("equ_array", equs_object);
			
			//酒水模糊检索	
			String wines_sec_types = "select category_info_name,category_info_token from snug_category_info where category_info_father = '1'";
			List<Map<String, Object>> wines_types_list = jdbcTemplate.queryForList(wines_sec_types);
			JSONArray winearray = new JSONArray();
			for (Map<String, Object> map : wines_types_list)
			{
				JSONObject jsonObject = new JSONObject();
				for (Map.Entry<String, Object> entry : map.entrySet()) 
				{
					if (entry.getKey().equals("category_info_name"))
					{
						jsonObject.put("sec_type_name", entry.getValue());
					}
					if (entry.getKey().equals("category_info_token"))
					{
						jsonObject.put("sec_type_token", entry.getValue());
						String queryString = "select " +
								"wine_info_name," +
								"wine_info_max_numbers," +
								"wine_info_unit," +
								"wine_info_price_low," +
								"wine_info_price_high," +
								"wine_info_guarantee," +
								"wine_info_token," +
								"wine_info_min_inventory," +
								"wine_info_max_inventory," +
								"wine_info_commission_price from snug_wine_info where wine_info_type = ? and wine_info_name like '%"+ key_word +"%' and wine_info_flag = '2'";
						System.out.println(queryString);
						List<Map<String, Object>> list = jdbcTemplate.queryForList(queryString,entry.getValue());
						JSONArray arr = MyUtil.getJsonArray(list);
						jsonObject.put("goods", arr);
					}
				}
				winearray.add(jsonObject);
			}
			//查询商品下面一级分类的商品
			String wines_first = "select " +
					"wine_info_name," +
					"wine_info_max_numbers," +
					"wine_info_unit," +
					"wine_info_price_low," +
					"wine_info_price_high," +
					"wine_info_guarantee," +
					"wine_info_token," +
					"wine_info_min_inventory," +
					"wine_info_max_inventory," +
					"wine_info_commission_price from snug_wine_info where wine_info_name like '%"+ key_word +"%' and wine_info_flag = '2'";
			
			List<Map<String, Object>> fir_wines_list = jdbcTemplate.queryForList(wines_first);
			JSONArray arraywine = MyUtil.getJsonArray(fir_wines_list);
			
			JSONObject wines_object = new JSONObject();
			wines_object.put("fir_goods", arraywine);
			wines_object.put("sec_goods", winearray);
			object.put("wines_array", wines_object);
			
			
			
			//其他的模糊检索
			String othersgoodss_sec_types = "select category_info_name,category_info_token from snug_category_info where category_info_father = '1'";
			List<Map<String, Object>> othersgoodss_types_list = jdbcTemplate.queryForList(othersgoodss_sec_types);
			JSONArray othersgoodsarray = new JSONArray();
			for (Map<String, Object> map : othersgoodss_types_list)
			{
				JSONObject jsonObject = new JSONObject();
				for (Map.Entry<String, Object> entry : map.entrySet()) 
				{
					if (entry.getKey().equals("category_info_name"))
					{
						jsonObject.put("sec_type_name", entry.getValue());
					}
					if (entry.getKey().equals("category_info_token"))
					{
						jsonObject.put("sec_type_token", entry.getValue());
						String queryString = "select " +
								"othersgoods_info_name," +
								"othersgoods_info_max_numbers," +
								"othersgoods_info_unit," +
								"othersgoods_info_price_low," +
								"othersgoods_info_price_high," +
								"othersgoods_info_guarantee," +
								"othersgoods_info_token," +
								"othersgoods_info_min_inventory," +
								"othersgoods_info_max_inventory" +
								" from snug_othersgoods_info where othersgoods_info_type = ? and othersgoods_info_name like '%"+ key_word +"%' and othersgoods_info_flag = '2'";
						System.out.println(queryString);
						List<Map<String, Object>> list = jdbcTemplate.queryForList(queryString,entry.getValue());
						JSONArray arr = MyUtil.getJsonArray(list);
						jsonObject.put("goods", arr);
					}
				}
				othersgoodsarray.add(jsonObject);
			}
			//查询商品下面一级分类的商品
			String othersgoodss_first = "select " +
					"othersgoods_info_name," +
					"othersgoods_info_max_numbers," +
					"othersgoods_info_unit," +
					"othersgoods_info_price_low," +
					"othersgoods_info_price_high," +
					"othersgoods_info_guarantee," +
					"othersgoods_info_token," +
					"othersgoods_info_min_inventory," +
					"othersgoods_info_max_inventory" +
					" from snug_othersgoods_info where othersgoods_info_name like '%"+ key_word +"%' and othersgoods_info_flag = '2'";
			
			List<Map<String, Object>> fir_othersgoodss_list = jdbcTemplate.queryForList(othersgoodss_first);
			JSONArray arrayothersgoods = MyUtil.getJsonArray(fir_othersgoodss_list);
			
			JSONObject othersgoodss_object = new JSONObject();
			othersgoodss_object.put("fir_goods", arrayothersgoods);
			othersgoodss_object.put("sec_goods", othersgoodsarray);
			object.put("othersgoodss_array", othersgoodss_object);
			
			
			return object.toString();
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}

	@Override
	public String queryInGoodsMes(String status_flag) 
	{
		if (jdbcTemplate!=null)
		{
			if (status_flag.equals(""))
			{
				//查询申购单
				String query = "select attence_info_token,attence_info_series,department_id,examine_info_times,examine_info_flag from oa_examine_info where examine_info_flag in('3','4','5') group by id desc";
				List<Map<String, Object>> list = jdbcTemplate.queryForList(query);
				//查询申购单中的第一个下面的数据
				Map<String, Object> firobj = list.get(0);
				String token = null;
				for (Map.Entry<String, Object> entry : firobj.entrySet())
				{
					if (entry.getKey().equals("attence_info_token"))
					{
						token = (String) entry.getValue();
					}
				}
				String sql = "select attence_info_detail from oa_examine_info where attence_info_token = ?";
				List<Map<String, Object>> list2 = jdbcTemplate.queryForList(sql,token);
				JSONArray array = MyUtil.getJsonArray(list2);
				String goods = (String) ((JSONObject) array.get(0)).get("attence_info_detail");

				JSONArray arr = new JSONArray();
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				for (Map<String, Object> map : list) 
				{
					JSONObject object = new JSONObject();
					for (Map.Entry<String, Object> entry : map.entrySet())
					{
						if (entry.getKey().equals("examine_info_times")) 
						{
							entry.setValue(dateFormat.format(entry.getValue()));
						}
						try 
						{
							object.put(entry.getKey(), entry.getValue());
						} 
						catch (JSONException e) 
						{
							e.printStackTrace();
						}
					}
					arr.add(object);
				}
				
				JSONObject object = new JSONObject();
				object.put("status", "1");
				object.put("mes", "success");
				object.put("header",arr);
				object.put("goods", JSONArray.parseArray(goods));
				return object.toString();
			}
			else
			{
				String query = "select attence_info_token,attence_info_series,department_id,examine_info_times,examine_info_flag from oa_examine_info where examine_info_flag = "+ status_flag +" group by id desc";
				List<Map<String, Object>> list = jdbcTemplate.queryForList(query);
				
				//查询申购单中的第一个下面的数据
				Map<String, Object> firobj = list.get(0);
				String token = null;
				for (Map.Entry<String, Object> entry : firobj.entrySet())
				{
					if (entry.getKey().equals("attence_info_token"))
					{
						token = (String) entry.getValue();
					}
				}
				String sql = "select attence_info_detail from oa_examine_info where attence_info_token = ?";
				List<Map<String, Object>> list2 = jdbcTemplate.queryForList(sql,token);
				JSONArray array = MyUtil.getJsonArray(list2);
				String goods = (String) ((JSONObject) array.get(0)).get("attence_info_detail");

				JSONArray arr = MyUtil.getJsonArray(list);
				JSONObject object = new JSONObject();
				object.put("status", "1");
				object.put("mes", "success");
				object.put("header",arr);
				object.put("goods", JSONArray.parseArray(goods));
				return object.toString();
			}
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}

	@Override
	public String queryLikeNameAndTime(String key_word) {
		//模糊检索商品
		String sqlgoods = "select attence_info_token,attence_info_series,department_id,examine_info_times,examine_info_flag from oa_examine_info where attence_info_token like '%"+ key_word +"%' or examine_info_times like '%"+ key_word +"%' group by id desc";
		System.out.println(sqlgoods);
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlgoods);
		
		JSONArray arr = new JSONArray();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (Map<String, Object> map : list) 
		{
			JSONObject object = new JSONObject();
			for (Map.Entry<String, Object> entry : map.entrySet())
			{
				if (entry.getKey().equals("examine_info_times")) 
				{
					entry.setValue(dateFormat.format(entry.getValue()));
				}
				try 
				{
					object.put(entry.getKey(), entry.getValue());
				} 
				catch (JSONException e) 
				{
					e.printStackTrace();
				}
			}
			arr.add(object);
		}
		
		JSONObject object = new JSONObject();
		object.put("status", "1");
		object.put("mes", "success");
		object.put("info", arr);
		return object.toString();
	}
//[{"food_material_out_type":"分类的token","food_material_out_good_token":"商品的token","food_material_out_quantity":"20","food_material_out_unit":"瓶","food_material_out_to_department":"去处","food_material_out_to_people":"去处人","food_material_out_plus":"备注","food_material_out_time":"时间","food_material_out_sort":"序号，排序","food_material_out_good_token":"货物的token值"}]
	@Override
	public String addOutGoodsMes(String out_material_mes,String flag)
	{
		if (jdbcTemplate != null) 
		{
			JSONArray all_mes_arr = JSONArray.parseArray(out_material_mes);
			String food_material_out_second = "food_material_out" + MyUtil.GetToken().substring(0,9);//将多条记录联系起来，表示是这一次
			int rows = 0;
			for (int i = 0; i < all_mes_arr.size(); i++)
			{
				//修改数据库的数据
				JSONObject one_good = (JSONObject) all_mes_arr.get(i);
				String food_material_out_quantity = one_good.getString("food_material_out_quantity");
				String food_material_out_good_token = one_good.getString("food_material_out_good_token");
				String food_material_out_type = one_good.getString("food_material_out_type");
				String food_material_out_to_department = one_good.getString("food_material_out_to_department");
				String food_material_out_name = one_good.getString("food_material_out_name");
				String food_material_out_unit = one_good.getString("food_material_out_unit");
				String food_material_out_to_people = one_good.getString("food_material_out_to_people");
				String food_material_out_plus = one_good.getString("food_material_out_plus");
				String food_material_out_sort = one_good.getString("food_material_out_sort");
				String food_material_out_flag = one_good.getString("food_material_out_flag");
				System.out.println(food_material_out_flag);
				String department_id = one_good.getString("department_id");
				//String food_material_out_flag = one_good.getString("food_material_out_flag");
				String food_material_out_token = "out_goods" + MyUtil.GetToken().substring(0,9);
				if (food_material_out_type.equals(""))
				{
					JSONObject object = new JSONObject();
					object.put("status", "2");
					object.put("mes", "参数错误！！");
					return object.toString();
				}
				
				if (food_material_out_type.equals("1"))
				{
					//商品
					String sqlString = "insert into snug_food_material(food_material_token,food_material_flag,food_material_number,department_id) values(?,?,?,?)";
					jdbcTemplate.update(sqlString, new Object[]{food_material_out_good_token,food_material_out_flag,food_material_out_quantity,department_id});
					
				}
				else if(food_material_out_type.equals("2"))
				{
					//设备
					String sqlString = "insert into snug_equipment_info(equipment_info_token,equipment_info_flag,equipment_info_number,department_id) values(?,?,?,?)";
					jdbcTemplate.update(sqlString, new Object[]{food_material_out_good_token,food_material_out_flag,food_material_out_quantity,department_id});
				}
				else if(food_material_out_type.equals("3"))
				{
					String sqlString = "insert into snug_wine_info(wine_info_token,wine_info_flag,wine_info_max_numbers,department_id) values(?,?,?,?)";
					jdbcTemplate.update(sqlString, new Object[]{food_material_out_good_token,food_material_out_flag,food_material_out_quantity,department_id});
					//酒水
				}
				else if(food_material_out_type.equals("4"))
				{
					//其他
					String sqlString = "insert into snug_othersgoods_info(othersgoods_info_token,othersgoods_info_flag,othersgoods_info_number,department_id) values(?,?,?,?)";
					jdbcTemplate.update(sqlString, new Object[]{food_material_out_good_token,food_material_out_flag,food_material_out_quantity,department_id});
				}
				else
				{
					//二级分类
					//查询属于哪一个一级分类
					String queryString  = "select category_info_father from snug_category_info where category_info_token = ?";
					Map<String, Object> map = jdbcTemplate.queryForMap(queryString,food_material_out_type);
					if (map.isEmpty())
					{
						return "没有此分类，参数错误！！";
					}
					String table = (String) map.get("category_info_father");//得到二级分类属于哪一个一级分类（1,2,3,4）
					
					if (table.equals("1"))
					{
						//属于商品
						String sqlString = "insert into snug_food_material(food_material_token,food_material_flag,food_material_number,department_id) values(?,?,?,?)";
						jdbcTemplate.update(sqlString, new Object[]{food_material_out_good_token,food_material_out_flag,food_material_out_quantity,department_id});
					}
					else if(table.equals("2"))
					{
						//属于设备
						String sqlString = "insert into snug_equipment_info(equipment_info_token,equipment_info_flag,equipment_info_number,department_id) values(?,?,?,?)";
						jdbcTemplate.update(sqlString, new Object[]{food_material_out_good_token,food_material_out_flag,food_material_out_quantity,department_id});
					}
					else if (table.equals("3"))
					{
						//属于酒水
						String sqlString = "insert into snug_wine_info(wine_info_token,wine_info_flag,wine_info_max_numbers,department_id) values(?,?,?,?)";
						jdbcTemplate.update(sqlString, new Object[]{food_material_out_good_token,food_material_out_flag,food_material_out_quantity,department_id});
					}
					else if (table.equals("4"))
					{
						String sqlString = "insert into snug_othersgoods_info(othersgoods_info_token,othersgoods_info_flag,othersgoods_info_number,department_id) values(?,?,?,?)";
						jdbcTemplate.update(sqlString, new Object[]{food_material_out_good_token,food_material_out_flag,food_material_out_quantity,department_id});
					}
					else
					{
						JSONObject object = new JSONObject();
						object.put("status", "2");
						object.put("mes", "没有一级分类，参数错误！！！");
						return object.toString();
					}
				}
			//将数据都插入到出库单表
				String sqlString = "insert into snug_food_material_out(" +
						"food_material_out_name,"+
						"food_material_out_type,"+
						"food_material_out_quantity,"+
						"food_material_out_unit,"+
						"food_material_out_to_department,"+
						"food_material_out_to_people,"+
						"food_material_out_plus,"+
						"food_material_out_sort,"+
						"food_material_out_good_token,"+
						"food_material_out_token,"+
						"food_material_out_flag,"+
						"food_material_out_second,"+
						"department_id"+
						") values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
				Object[] objects = new Object[]{
						food_material_out_name,
						food_material_out_type,
						food_material_out_quantity,
						food_material_out_unit,
						food_material_out_to_department,
						food_material_out_to_people,
						food_material_out_plus,
						food_material_out_sort,
						food_material_out_good_token,
						food_material_out_token,
						flag,
						food_material_out_second,
						department_id
				};
					rows =	jdbcTemplate.update(sqlString,objects);
			}
			return DBHelper.ReturnRows(rows);
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}

	@Override
	public String queryInGoodMes(String attence_info_token) {
		if (jdbcTemplate != null)
		{
			String queryString = "select attence_info_detail from oa_examine_info where attence_info_token = ?";
			Map<String, Object> map = jdbcTemplate.queryForMap(queryString,attence_info_token);
			String mapString = (String) map.get("attence_info_detail");
			JSONArray array = JSONArray.parseArray(mapString);
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
	public String queryHistoryOutGoods()
	{
		if (jdbcTemplate != null) 
		{
			String queryString = "select * from snug_food_material_out";
			List<Map<String, Object>> list = jdbcTemplate.queryForList(queryString);
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
	public String addGoods(String name, String type_one, String type_sec,
			String unit, CommonsMultipartFile[] file, String in_price,
			String min_inventory, String max_inventory, String out_price,
			String guarantee, String maintain, String servicelife ,String department_id) 
	{
		if (jdbcTemplate != null)
		{
			String token = "goods" + MyUtil.GetToken().substring(0, 9);
			String type = null;
			
			if (!type_one.equals(""))
			{
				if (type_sec.equals(""))
				{
					type = type_one;
				}
				else
				{
					type = type_sec;
				}
			}
			
			String sqlString = null;
			if (type_one.equals("1"))
			{
				 sqlString = "insert into snug_food_material(" +
						"food_material_name,"+
						"food_material_type,"+
						"food_material_unit,"+
						"food_material_inprice,"+
						"food_material_outprice,"+
						"food_material_min_inventory,"+
						"food_material_max_inventory,"+
						"food_material_guarantee,"+
						"food_material_maintain,"+
						"food_material_servicelift,"+
						"food_material_token,"+
						"department_id"+
						") values(?,?,?,?,?,?,?,?,?,?,?,?)";
			}
			else if(type_one.equals("2"))
			{
				sqlString = "insert into snug_equipment_info(" +
						"equipment_info_name,"+
						"equipment_info_type,"+
						"equipment_info_unit,"+
						"equipment_info_inprice,"+
						"equipment_info_outprice,"+
						"equipment_info_min_inventory,"+
						"equipment_info_max_inventory,"+
						"equipment_info_guarantee,"+
						"equipment_info_maintain,"+
						"equipment_info_servicelift,"+
						"equipment_info_token,"+
						"department_id"+
						") values(?,?,?,?,?,?,?,?,?,?,?,?)";
			}
			else if(type_one.equals("3"))
			{
				 sqlString = "insert into snug_wine_info(" +
						"wine_info_name,"+
						"wine_info_type,"+
						"wine_info_unit,"+
						"wine_info_price_low,"+
						"wine_info_price_high,"+
						"wine_info_min_inventory,"+
						"wine_info_max_inventory,"+
						"wine_info_guarantee,"+
						"wine_info_maintain,"+
						"wine_info_servicelift,"+
						"wine_info_token,"+
						"department_id"+
						") values(?,?,?,?,?,?,?,?,?,?,?,?)";
			}
			else if(type_one.equals("4"))
			{
				 sqlString = "insert into snug_othersgoods_info(" +
						"othersgoods_info_name,"+
						"othersgoods_info_type,"+
						"othersgoods_info_unit,"+
						"othersgoods_info_price_low,"+
						"othersgoods_info_price_high,"+
						"othersgoods_info_min_inventory,"+
						"othersgoods_info_max_inventory,"+
						"othersgoods_info_guarantee,"+
						"othersgoods_info_maintain,"+
						"othersgoods_info_servicelift,"+
						"othersgoods_info_token,"+
						"department_id"+
						") values(?,?,?,?,?,?,?,?,?,?,?,?)";
			}
			else
			{
				JSONObject object = new JSONObject();
				object.put("status", "2");
				object.put("mes", "type param is error !!");
				return object.toString();
			}
			
			Object[] params = new Object[]{
					 name,
					 type,
					 unit,
					 in_price,
					 out_price,
					 min_inventory,
					 max_inventory,
					 guarantee,
					 maintain,
					 servicelife,
					 token,
					 department_id
			};
			int rows = jdbcTemplate.update(sqlString, params);
			
			//上传图片
			String result = MyUtil.Upimgs(file, token, jdbcTemplate);
			if (result.equals("big"))
			{
				JSONObject object = new JSONObject();
				object.put("status", "2");
				object.put("mes", "图片太大上传失败！！！");
				return object.toString();
			}
			else if(result.equals("type_error"))
			{
				JSONObject object = new JSONObject();
				object.put("status", "3");
				object.put("mes", "图片类型错误！！！");
				return object.toString();
			}
			
			return DBHelper.ReturnRows(rows);
		} 
		else 
		{
			return DBHelper.FailConnectDatabase();
		}
	}

	@Override
	public String queryByTimeAndName(String key_name, String key_time,
			String manage_type, String status_type)
	{
		String from_time = null;
		String to_time = null;
		String now_time = null;
		if (key_time.equals(""))
		{
			Date timeDate = new Date();
			long time = timeDate.getTime();
			SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd"); 
			now_time = format0.format(time);
		}
		else
		{
			String[] time = key_time.split(",");
			from_time = time[0];
			to_time = time[1];
		}
		if (jdbcTemplate != null) 
		{	//出库
			if (manage_type.equals("1"))
			{
				String queryString = null ;
				//待审核
				if(status_type.equals("4"))
				{
					if (now_time != null && from_time == null && to_time == null)
					{
						queryString  = "select food_material_out_name,food_material_out_token,food_material_out_type,food_material_out_flag,food_material_out_from_people,food_material_out_to_department,food_material_out_quantity,food_material_out_unit,food_material_out_time from snug_food_material_out where food_material_out_flag = '4' and  food_material_out_name like '%"+ key_name + "%' and food_material_out_time like '"+now_time+"%'";				
					}
					else
					{
						queryString  = "select food_material_out_name,food_material_out_token,food_material_out_type,food_material_out_flag,food_material_out_from_people,food_material_out_to_department,food_material_out_quantity,food_material_out_unit,food_material_out_time from snug_food_material_out where food_material_out_flag = '4' and  food_material_out_name like '%"+ key_name + "%' and food_material_out_time between '"+from_time+"' and '" + to_time +"'";
					}
					
				}
				//通过
				else if(status_type.equals("6"))
				{
					if (now_time != null && from_time == null && to_time == null)
					{
						queryString  = "select food_material_out_name,food_material_out_token,food_material_out_type,food_material_out_flag,food_material_out_from_people,food_material_out_to_department,food_material_out_quantity,food_material_out_unit,food_material_out_time from snug_food_material_out where food_material_out_flag = '6' and  food_material_out_name like '%"+ key_name + "%' and food_material_out_time like '"+now_time+"%'";				
					}
					else
					{
						queryString  = "select food_material_out_name,food_material_out_token,food_material_out_type,food_material_out_flag,food_material_out_from_people,food_material_out_to_department,food_material_out_quantity,food_material_out_unit,food_material_out_time from snug_food_material_out where food_material_out_flag = '6' and  food_material_out_name like '%"+ key_name + "%' and food_material_out_time between '"+from_time+"' and '" + to_time +"'";
					}				}
				//未通过
				else if(status_type.equals("5"))
				{
					if (now_time != null && from_time == null && to_time == null)
					{
						queryString  = "select food_material_out_name,food_material_out_token,food_material_out_type,food_material_out_flag,food_material_out_from_people,food_material_out_to_department,food_material_out_quantity,food_material_out_unit,food_material_out_time from snug_food_material_out where food_material_out_flag = '5' and  food_material_out_name like '%"+ key_name + "%' and food_material_out_time like '"+now_time+"%'";				
					}
					else
					{
						queryString  = "select food_material_out_name,food_material_out_token,food_material_out_type,food_material_out_flag,food_material_out_from_people,food_material_out_to_department,food_material_out_quantity,food_material_out_unit,food_material_out_time from snug_food_material_out where food_material_out_flag = '5' and  food_material_out_name like '%"+ key_name + "%' and food_material_out_time between '"+from_time+"' and '" + to_time+"'";
					}				}
				//全部
				else if(status_type.equals(""))
				{
					if (now_time != null && from_time == null && to_time == null)
					{
						queryString  = "select food_material_out_name,food_material_out_token,food_material_out_type,food_material_out_flag,food_material_out_from_people,food_material_out_to_department,food_material_out_quantity,food_material_out_unit,food_material_out_time from snug_food_material_out where food_material_out_flag in('4','5','6') and  food_material_out_name like '%"+ key_name + "%' and food_material_out_time like '"+now_time+"%'";				
					}
					else
					{
						queryString  = "select food_material_out_name,food_material_out_token,food_material_out_type,food_material_out_flag,food_material_out_from_people,food_material_out_to_department,food_material_out_quantity,food_material_out_unit,food_material_out_time from snug_food_material_out where food_material_out_flag in('4','5','6') and  food_material_out_name like '%"+ key_name + "%' and food_material_out_time between '"+from_time+"' and '" + to_time +"'";
						
					}
				}
				else
				{
					JSONObject object = new JSONObject();
					object.put("status", "2");
					object.put("mes", "参数错误");
					return object.toString();
				}
				System.out.println(queryString);
				List<Map<String, Object>> list = jdbcTemplate.queryForList(queryString);
				return DBHelper.ReturnList(list);
			}
			//入库
			else if (manage_type.equals("2"))
			{
				String queryString = null ;
				
				//物品待审核
				if(status_type.equals("1"))
				{
					if (now_time != null && from_time == null && to_time == null)
					{
						//从待审核的单子中查
						queryString  = "select attence_info_detail,examine_info_flag from oa_examine_info where examine_info_flag = '3' and examine_info_times like '%"+now_time+"%'";
						List<Map<String, Object>> list = jdbcTemplate.queryForList(queryString);
						System.out.println(list);
						JSONArray like_goods = new JSONArray();//用来存储模糊查询的结果
						
						for (Map<String, Object> map : list)
						{
							//每一个申购单的物品jsonarray
							JSONArray all_goods = null;
							for (Map.Entry<String, Object> entry : map.entrySet())
							{
								if (entry.getKey().equals("attence_info_detail"))
								{
									all_goods = JSONArray.parseArray((String) entry.getValue());
									for (int i = 0; i < all_goods.size(); i++) 
									{
										Map<String, Object> map2 = all_goods.getJSONObject(i);
										for (Map.Entry<String, Object> entry2 : map2.entrySet())
										{
											if (entry2.getKey().equals("food_material_name"))
											{
												if ((entry2.getValue().toString()).contains(key_name))
												{
													like_goods.add(map2);
												}
											}
										}
									}
								}
							}
						}
						JSONObject object = new JSONObject();
						object.put("status", "1");
						object.put("mes", "success");
						object.put("info", like_goods);
						return object.toString();
					}
					else
					{
						queryString  = "select attence_info_detail,examine_info_flag from oa_examine_info where examine_info_flag = '3' and examine_info_times between '"+from_time+"' and '" + to_time +"'";
						List<Map<String, Object>> list = jdbcTemplate.queryForList(queryString);
						
						JSONArray like_goods = new JSONArray();//用来存储模糊查询的结果
						
						for (Map<String, Object> map : list)
						{
							//每一个申购单的物品jsonarray
							JSONArray all_goods = null;
							for (Map.Entry<String, Object> entry : map.entrySet())
							{
								if (entry.getKey().equals("attence_info_detail"))
								{
									all_goods = JSONArray.parseArray((String) entry.getValue());
									for (int i = 0; i < all_goods.size(); i++) 
									{
										Map<String, Object> map2 = all_goods.getJSONObject(i);
										for (Map.Entry<String, Object> entry2 : map2.entrySet())
										{
											if (entry2.getKey().equals("food_material_name"))
											{
												if ((entry2.getValue().toString()).contains(key_name))
												{
													like_goods.add(map2);
												}
											}
										}
									}
								}
							}
						}
						JSONObject object = new JSONObject();
						object.put("status", "1");
						object.put("mes", "success");
						object.put("info", like_goods);
						return object.toString();
					}
								
				}
				//通过
				else if(status_type.equals("4"))
				{
					if (now_time != null && from_time == null && to_time == null)
					{
						//从待审核的单子中查
						queryString  = "select attence_info_detail,examine_info_flag from oa_examine_info where examine_info_flag = '4' and examine_info_times like '%"+now_time+"%'";
						List<Map<String, Object>> list = jdbcTemplate.queryForList(queryString);
						System.out.println(list);
						JSONArray like_goods = new JSONArray();//用来存储模糊查询的结果
						
						for (Map<String, Object> map : list)
						{
							//每一个申购单的物品jsonarray
							JSONArray all_goods = null;
							for (Map.Entry<String, Object> entry : map.entrySet())
							{
								if (entry.getKey().equals("attence_info_detail"))
								{
									all_goods = JSONArray.parseArray((String) entry.getValue());
									for (int i = 0; i < all_goods.size(); i++) 
									{
										Map<String, Object> map2 = all_goods.getJSONObject(i);
										for (Map.Entry<String, Object> entry2 : map2.entrySet())
										{
											if (entry2.getKey().equals("food_material_name"))
											{
												if ((entry2.getValue().toString()).contains(key_name))
												{
													like_goods.add(map2);
												}
											}
										}
									}
								}
							}
						}
						JSONObject object = new JSONObject();
						object.put("status", "1");
						object.put("mes", "success");
						object.put("info", like_goods);
						return object.toString();
					}
					else
					{
						queryString  = "select attence_info_detail,examine_info_flag from oa_examine_info where examine_info_flag = '4' and examine_info_times between '"+from_time+"' and '" + to_time +"'";
						List<Map<String, Object>> list = jdbcTemplate.queryForList(queryString);
						
						JSONArray like_goods = new JSONArray();//用来存储模糊查询的结果
						
						for (Map<String, Object> map : list)
						{
							//每一个申购单的物品jsonarray
							JSONArray all_goods = null;
							for (Map.Entry<String, Object> entry : map.entrySet())
							{
								if (entry.getKey().equals("attence_info_detail"))
								{
									all_goods = JSONArray.parseArray((String) entry.getValue());
									for (int i = 0; i < all_goods.size(); i++) 
									{
										Map<String, Object> map2 = all_goods.getJSONObject(i);
										for (Map.Entry<String, Object> entry2 : map2.entrySet())
										{
											if (entry2.getKey().equals("food_material_name"))
											{
												if ((entry2.getValue().toString()).contains(key_name))
												{
													like_goods.add(map2);
												}
											}
										}
									}
								}
							}
						}
						JSONObject object = new JSONObject();
						object.put("status", "1");
						object.put("mes", "success");
						object.put("info", like_goods);
						return object.toString();
					}
				}
				//未通过
				else if(status_type.equals("5"))
				{
					if (now_time != null && from_time == null && to_time == null)
					{
						//从待审核的单子中查
						queryString  = "select attence_info_detail,examine_info_flag from oa_examine_info where examine_info_flag = '5' and examine_info_times like '%"+now_time+"%'";
						List<Map<String, Object>> list = jdbcTemplate.queryForList(queryString);
						System.out.println(list);
						JSONArray like_goods = new JSONArray();//用来存储模糊查询的结果
						
						for (Map<String, Object> map : list)
						{
							//每一个申购单的物品jsonarray
							JSONArray all_goods = null;
							for (Map.Entry<String, Object> entry : map.entrySet())
							{
								if (entry.getKey().equals("attence_info_detail"))
								{
									all_goods = JSONArray.parseArray((String) entry.getValue());
									for (int i = 0; i < all_goods.size(); i++) 
									{
										Map<String, Object> map2 = all_goods.getJSONObject(i);
										for (Map.Entry<String, Object> entry2 : map2.entrySet())
										{
											if (entry2.getKey().equals("food_material_name"))
											{
												if ((entry2.getValue().toString()).contains(key_name))
												{
													like_goods.add(map2);
												}
											}
										}
									}
								}
							}
						}
						JSONObject object = new JSONObject();
						object.put("status", "1");
						object.put("mes", "success");
						object.put("info", like_goods);
						return object.toString();
					}
					else
					{
						queryString  = "select attence_info_detail,examine_info_flag from oa_examine_info where examine_info_flag = '5' and examine_info_times between '"+from_time+"' and '" + to_time +"'";
						List<Map<String, Object>> list = jdbcTemplate.queryForList(queryString);
						
						JSONArray like_goods = new JSONArray();//用来存储模糊查询的结果
						
						for (Map<String, Object> map : list)
						{
							//每一个申购单的物品jsonarray
							JSONArray all_goods = null;
							for (Map.Entry<String, Object> entry : map.entrySet())
							{
								if (entry.getKey().equals("attence_info_detail"))
								{
									all_goods = JSONArray.parseArray((String) entry.getValue());
									for (int i = 0; i < all_goods.size(); i++) 
									{
										Map<String, Object> map2 = all_goods.getJSONObject(i);
										for (Map.Entry<String, Object> entry2 : map2.entrySet())
										{
											if (entry2.getKey().equals("food_material_name"))
											{
												if ((entry2.getValue().toString()).contains(key_name))
												{
													like_goods.add(map2);
												}
											}
										}
									}
								}
							}
						}
						JSONObject object = new JSONObject();
						object.put("status", "1");
						object.put("mes", "success");
						object.put("info", like_goods);
						return object.toString();
					}
				}
				//全部
				else if(status_type.equals(""))
				{
					if (now_time != null && from_time == null && to_time == null)
					{
						//从待审核的单子中查
						queryString  = "select attence_info_detail,examine_info_flag from oa_examine_info where examine_info_flag in ('3','4','5') and examine_info_times like '%"+now_time+"%'";
						List<Map<String, Object>> list = jdbcTemplate.queryForList(queryString);
						System.out.println(list);
						JSONArray like_goods = new JSONArray();//用来存储模糊查询的结果
						
						for (Map<String, Object> map : list)
						{
							//每一个申购单的物品jsonarray
							JSONArray all_goods = null;
							for (Map.Entry<String, Object> entry : map.entrySet())
							{
								if (entry.getKey().equals("attence_info_detail"))
								{
									all_goods = JSONArray.parseArray((String) entry.getValue());
									for (int i = 0; i < all_goods.size(); i++) 
									{
										Map<String, Object> map2 = all_goods.getJSONObject(i);
										for (Map.Entry<String, Object> entry2 : map2.entrySet())
										{
											if (entry2.getKey().equals("food_material_name"))
											{
												if ((entry2.getValue().toString()).contains(key_name))
												{
													like_goods.add(map2);
												}
											}
										}
									}
								}
							}
						}
						JSONObject object = new JSONObject();
						object.put("status", "1");
						object.put("mes", "success");
						object.put("info", like_goods);
						return object.toString();
					}
					else
					{
						queryString  = "select attence_info_detail,examine_info_flag from oa_examine_info where examine_info_flag in ('3','4','5') and examine_info_times between '"+from_time+"' and '" + to_time +"'";
						List<Map<String, Object>> list = jdbcTemplate.queryForList(queryString);
						
						JSONArray like_goods = new JSONArray();//用来存储模糊查询的结果
						
						for (Map<String, Object> map : list)
						{
							//每一个申购单的物品jsonarray
							JSONArray all_goods = null;
							for (Map.Entry<String, Object> entry : map.entrySet())
							{
								if (entry.getKey().equals("attence_info_detail"))
								{
									all_goods = JSONArray.parseArray((String) entry.getValue());
									for (int i = 0; i < all_goods.size(); i++) 
									{
										Map<String, Object> map2 = all_goods.getJSONObject(i);
										for (Map.Entry<String, Object> entry2 : map2.entrySet())
										{
											if (entry2.getKey().equals("food_material_name"))
											{
												if ((entry2.getValue().toString()).contains(key_name))
												{
													like_goods.add(map2);
												}
											}
										}
									}
								}
							}
						}
						JSONObject object = new JSONObject();
						object.put("status", "1");
						object.put("mes", "success");
						object.put("info", like_goods);
						return object.toString();
					}
				}
				
				List<Map<String, Object>> list = jdbcTemplate.queryForList(queryString);
				return DBHelper.ReturnList(list);
			}
		} 
		else 
		{
			return DBHelper.FailConnectDatabase();
		}
		return "";
	}

}
