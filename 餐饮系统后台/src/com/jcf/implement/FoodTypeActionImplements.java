package com.jcf.implement;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jcf.interfaces.FoodTypeActionInterfaces;
import com.jcf.util.DBHelper;
import com.jcf.util.MyUtil;
@Service
public class FoodTypeActionImplements implements FoodTypeActionInterfaces {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Override
	public String insertFoodType(
			String food_type_name,
			String food_type_father,
			String food_type_sort,
			String food_type_runtime,
			String food_type_flag,
			String department_id,
			String food_type_images)
	{
		System.out.println("food_type_images:"+food_type_images);
		if(jdbcTemplate!=null)
		{
			String token = "foodtype" + MyUtil.GetToken().substring(0, 9);
			String query = "insert into snug_food_type(food_type_name,food_type_father,food_type_sort,food_type_runtime,food_type_flag,food_type_token,department_id) " +
					"values(?,?,?,?,?,?,?)";
			int rows = jdbcTemplate.update(query, new Object[]{food_type_name,food_type_father,food_type_sort,food_type_runtime,food_type_flag,token,department_id});
			//上传图片
			String result = MyUtil.addImgs(food_type_images, token, jdbcTemplate);
			if (result.equals("error"))
			{
				JSONObject object = new JSONObject();
				object.put("status", "2");
				object.put("mes", "参数错误，图片库没有所传图片！！！");
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
	public String deleteFoodType(String food_type_token) {
		if(jdbcTemplate!=null)
		{
			//查询是否有子分类
			String sqlString = "select * from snug_food_type where food_type_father = ?";
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlString,food_type_token);
			if (list.size()==0)
			{
				//隐藏当前分区
				String sqlString2 = "update snug_food_type set food_type_flag = '0' where food_type_token = ?";
				int rows = jdbcTemplate.update(sqlString2, food_type_token);
				return DBHelper.ReturnRows(rows);
			}
			else
			{
				//隐藏当前分区
				String sqlString2 = "update snug_food_type set food_type_flag = '0' where food_type_token = ?";
				jdbcTemplate.update(sqlString2, food_type_token);
				//return DBHelper.ReturnRows(rows);
				//隐藏子分区
				for (Map<String, Object> map : list) {
					for (Map.Entry<String, Object> entry : map.entrySet())
					{
						if (entry.getKey().equals("food_type_token"))
						{
							String sqlString3 = "update snug_food_type set food_type_flag = '0' where food_type_token = ?";
							jdbcTemplate.update(sqlString3,entry.getValue());
						}
					}
				}
				JSONObject object = new JSONObject();
				object.put("status", "1");
				object.put("mes","success");
				return object.toString();
			}
		}
		else
		{
			
		}
		return null;
	}

	@Override
	public String updateFoodType(String food_type_name,String food_type_father,String food_type_sort,String food_type_runtime,String food_type_flag,String department_id,String food_type_images,String food_type_token)
	{
		if (jdbcTemplate!=null) 
		{
			//删除以前的图片
			String sqlString = "update snug_images_info set image_info_flag = '0' where image_info_token_ref = ?";
			jdbcTemplate.update(sqlString, food_type_token);
			//添加现在的图片
			MyUtil.addImgs(food_type_images, food_type_token, jdbcTemplate);
			//修改基本信息
			String sqlString1 = "update snug_food_type set food_type_name = ? , food_type_father = ?,food_type_sort = ?,food_type_runtime = ?,food_type_flag = ? where food_type_token=? and department_id = ? and food_type_flag = '1'";
					jdbcTemplate.update(sqlString1, new Object[]{
							food_type_name,
							food_type_father,
							food_type_sort,
							food_type_runtime,
							food_type_flag,
							food_type_token,
							department_id
			});
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
	public String queryFoodType() {
		if (jdbcTemplate!=null)
		{
			//查询所有一级分类
			String sqlString = "select food_type_name,food_type_runtime,food_type_token,food_type_sort,food_type_flag,food_type_content from snug_food_type where food_type_father = '-1' and food_type_flag = '1'";
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlString);
			
			for (Map<String, Object> map : list)
			{
				JSONArray array = null;
				JSONArray fir_imgArray = null;
				for (Map.Entry<String, Object> entry : map.entrySet())
				{
					
					if (entry.getKey().equals("food_type_token"))
					{
						//查询一级分类下面的二级分类
						String sqlString2 = "select food_type_name,food_type_runtime,food_type_token,food_type_sort,food_type_flag,food_type_content from snug_food_type where food_type_father = '"+ entry.getValue() +"' and food_type_flag = '1'";
						List<Map<String, Object>> list2 = jdbcTemplate.queryForList(sqlString2);
						for (Map<String, Object> map2 : list2) {
							JSONArray sec_imgArray = null;
							for (Map.Entry<String, Object> entry2 : map2.entrySet()) {
								if (entry2.getKey().equals("food_type_token"))
								{
									String sqlString3 = "select image_info_name from snug_images_info where image_info_token_ref = ? and image_info_flag !='0'";
									List<Map<String, Object>> list3 = jdbcTemplate.queryForList(sqlString3,entry2.getValue());
									sec_imgArray = MyUtil.getJsonArray(list3);
								}
							}
							map2.put("imgs",sec_imgArray);
						}
						//查询一级分类下面的图片
						String sqlString4 = "select image_info_name from snug_images_info where image_info_token_ref = ? and image_info_flag !='0'";
						List<Map<String, Object>> list4 = jdbcTemplate.queryForList(sqlString4,entry.getValue());
						fir_imgArray = MyUtil.getJsonArray(list4);
						array = MyUtil.getJsonArray(list2);
					}
				}
				map.put("sec_type",array);
				map.put("imgs", fir_imgArray);
			}
			System.out.println(list.toString());
			JSONArray all_type = MyUtil.getJsonArray(list);
			
			JSONObject object = new JSONObject();
			object.put("status", "1");
			object.put("mes", "success");
			object.put("all_type",all_type);
			return object.toString();
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
		
	}

	@Override
	public String updateFoodTypeNoImg(String food_type_name,
			String food_type_father, String food_type_sort,
			String food_type_runtime, String food_type_flag,
			String department_id, String food_type_images_ref,
			String food_type_token) 
	{
		if (jdbcTemplate!=null) 
		{
			//分解要修改图片的token_ref
			String[] imgtoken = food_type_images_ref.split(",");
			for (int i = 0; i < imgtoken.length; i++) {
				String sqlString = "update snug_images_info set image_info_flag = '0' where image_info_token_ref = ?";
				jdbcTemplate.update(sqlString, imgtoken[i]);
			}
			
			String sqlString = "update snug_food_type set food_type_name = ? , food_type_father = ?,food_type_sort = ?,food_type_runtime = ?,food_type_flag = ?,department_id = ? where food_type_token=? and food_type_flag = '1'";
					jdbcTemplate.update(sqlString, new Object[]{
							food_type_name,
							food_type_father,
							food_type_sort,
							food_type_runtime,
							food_type_flag,
							department_id,
							food_type_token
			});
					
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

}
