package com.jcf.implement;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.jcf.interfaces.TableAddressActionInterfaces;
import com.jcf.util.DBHelper;
import com.jcf.util.MyUtil;
import com.jcf.util.TwoDimensionCode;

@Service
public class TableAddressActionImplements implements TableAddressActionInterfaces {
	 @Autowired
	  JdbcTemplate jdbcTemplate;
	 @Autowired
	 TwoDimensionCode handler;
	  /*
		 * @author Lee
		 * @time 2018/1/12
		 * @fun 台桌分区添加
		 * testurl:http://localhost:8080/snug/insertTableAddress?
		  department_address_name=c栋
		  &department_address_content=001&department_address_sort=001
		  &department_address_recommand=001&department_address_flag=1&department_address_parent=12543&department_id=001
		 * */
	
	 	@Override
		public String insertTableAddress(String department_address_name,
										 String department_address_content,
										 String department_address_sort,
										 String department_address_recommand,
										 String department_address_flag,
										 String department_address_parent,
										 String department_id,String department_address_images)
	    {
		  	//得到项目唯一标识
		  	String tokenString = "tableaddress" + MyUtil.GetToken().substring(0,9);
		  	//添加图片
		  	String result = MyUtil.addImgs(department_address_images, tokenString, jdbcTemplate);
			if (result.equals("error")) {
				JSONObject object = new JSONObject();
				object.put("status", "2");
				object.put("mes", "图片参数错误，图片库没有所传图片！！！");
				return object.toString();
			}
		  	if (jdbcTemplate!=null) {
				String sql = "insert into snug_department_address(department_address_name," +
																"department_address_content," +
																"department_address_sort," +
																"department_address_recommand," +
																"department_address_flag," +
																"department_address_parent," +
																"department_address_token," +
																"department_id) values(?,?,?,?,?,?,?,?)";
				 	int rows = 	jdbcTemplate.update(sql, new Object[]{	department_address_name,
																department_address_content,
																department_address_sort,
																department_address_recommand,
																department_address_flag,
																department_address_parent,
																tokenString,
																department_id});
				return DBHelper.ReturnRows(rows);
				
			}
			else
			{
				return DBHelper.FailConnectDatabase();
			}
		}
	  
	  /*
		 * @author Lee
		 * @time 2018/1/13
		 * @fun 获取分区
		 * http://localhost:8080/snug/queryTableAddress
		 * 
		 * */
	 	@Override
		public String queryTableAddress() {
			if (jdbcTemplate!=null) {
				List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from snug_department_address where department_address_flag = '1' order by id");
				for (Map<String,Object> map : list)
				{
					JSONArray urlString = null;
					String fathernameString = null;
					for (Map.Entry<String,Object> entry : map.entrySet())
					{
						if (entry.getKey().equals("department_address_token"))
						{
							//查询分区图片
							String query ="select image_info_name,image_info_token_ref from snug_images_info where image_info_token_ref like '" + entry.getValue() + "%' and image_info_flag ='1'";
							List<Map<String,Object>> list2 = jdbcTemplate.queryForList(query);
							JSONArray imgArray = MyUtil.getJsonArray(list2);
							if (list2.size()==0)
							{
								urlString = new JSONArray();
							}
							else
							{
								urlString = imgArray;
							}
						}
						
						if (entry.getKey().equals("department_address_parent")) 
						{
							System.out.println(entry.getValue());
							if (!entry.getValue().equals("根目录")) {
								//查询分区的父分区名字
								String query ="select department_address_name from snug_department_address where department_address_token = ?";
								List<Map<String, Object>> list2 = jdbcTemplate.queryForList(query,entry.getValue());
								if (list2.size()==0)
								{
									JSONObject object3 = new JSONObject();
									try {
										object3.put("status", "2");
										object3.put("mes", "数据库数据错误！！！！！");
									} catch (JSONException e) {
										e.printStackTrace();
									}
									return object3.toString();
								}
								Map<String, Object> object = list2.get(0);
								
								fathernameString = (String) object.get("department_address_name");
								
							}
						}
					}
					map.put("department_address_img",urlString);
					map.put("father_name", fathernameString);
				}
				
				JSONArray array = MyUtil.getJsonArray(list);
				
				JSONObject object = new JSONObject();
				try {
					object.put("status", "1");
					object.put("mes", "success");
					object.put("info",array);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			    return object.toString();
			}
			else
			{
				System.out.println("end");
				return DBHelper.FailConnectDatabase();
			}
		}
	  /*
		 * @author Lee
		 * @time 2018/1/15
		 * @fun 分区隐藏
		 * http://localhost:8080/snug/deleteTableAddress?token=tableaddress2985209d1
		 * */
	  @Override
	  public String deleteTableAddress(String token){
		  Connection connection=null;
		  if (jdbcTemplate!=null) {
			    try {
			    	connection = jdbcTemplate.getDataSource().getConnection();
			    	connection.setAutoCommit(false);
				} 
			    catch (SQLException e2)
			    {
					e2.printStackTrace();
				}
			    //判断分区是否是根目录分区，如果是则子分区也删除
			  	String queryparant = "select department_address_parent from snug_department_address where department_address_token = ?";
			  	List<Map<String, Object>> list = jdbcTemplate.queryForList(queryparant,token);
			  	try
			  	{
			  		String parent = null;
			  		if(list.size()>0){
			  			 parent = new String(list.get(0).get("department_address_parent").toString().getBytes("iso-8859-1"),"utf-8");
			  		}
			  		else
			  		{
			  			 parent = "根目录不存在";
			  		}
					if (parent.equals("根目录"))
					{
						String deletechildren = "update snug_department_address set department_address_flag = 0 where department_address_parent = ?";
						jdbcTemplate.update(deletechildren, token);
						
						String deleteself = "update snug_department_address set department_address_flag = 0 where department_address_token = ?";
						jdbcTemplate.update(deleteself, token);
						
						JSONObject object = new JSONObject();
				    	try 
				    	{
				    		object.put("ststus","1");
							object.put("mes","success delete all address");
						}
				    	catch (JSONException e)
				    	{
							e.printStackTrace();
						}
				    	return object.toString();
					}
					else
					{
						//不是根目录则直接删除该分区
						String sql = "update snug_department_address set department_address_flag = 0 where department_address_token = ?";
						int rows = jdbcTemplate.update(sql, token);
						return DBHelper.ReturnRows(rows);
					}
					
				}
			  	catch (UnsupportedEncodingException e1)
				{
					e1.printStackTrace();
				}
			  	
			  }
		  
		  try
		  {
			connection.commit();
		  }
		  catch (Exception e) 
		  {
			
		  }
			  return "";
	  }
	  
	  /*
		 * @author Lee
		 * @time 2018/1/15
		 * @fun 台桌分区修改
	   */
	  @Override
	  public String updateTableAddress(String department_address_name,
				 String department_address_content,String department_address_sort,
				 String department_address_recommand,String department_address_parent,
				 String department_id,String token,String department_address_images)
	  {
			
		  System.out.println(department_address_name);
		  System.out.println(department_address_content);
		  System.out.println(department_address_sort);
		  System.out.println(department_address_recommand);
		  System.out.println(department_address_parent);
		  System.out.println(department_id);
		  System.out.println(token);
		  System.out.println(department_address_images);
		  if (jdbcTemplate!=null) {
				//删除以前的图片
			    String deletesqlString = "update snug_images_info set image_info_flag = '0' where image_info_token_ref = ?";
				jdbcTemplate.update(deletesqlString,token);
				//添加新图片
				String result = MyUtil.addImgs(department_address_images, token, jdbcTemplate);
				if (result.equals("error")) {
					JSONObject object = new JSONObject();
					object.put("status", "2");
					object.put("mes", "图片参数错误，图片库没有所传图片！！！");
					return object.toString();
				}
				
				String sqlString = "update snug_department_address set department_address_name=?," +
						"department_address_content=?," +
						"department_address_sort=?," +
						"department_address_recommand=?," +
						"department_address_parent=?" +
						" where department_address_token=? and department_address_flag = '1' and department_id=?";
						int rows = jdbcTemplate.update(sqlString, new Object[]{
						department_address_name,
						department_address_content,
						department_address_sort,
						department_address_recommand,
						department_address_parent,
						token,
						department_id
					});
				return DBHelper.ReturnRows(rows);
			}
			else
			{
				return DBHelper.FailConnectDatabase();
			}
		}
	
	@Override
	public String queryTableChildAddress(String token)
	{
		if (jdbcTemplate!=null)
		{	
			if(token.equals(""))
			{
				//如果参数token值为空就查询所有的分区及子分区
				String sqlString = "select department_address_name,department_address_token,department_address_recommand,department_address_content,department_address_flag from snug_department_address where department_address_parent = '根目录' and department_address_flag = '1' order by id";
				List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlString);
				JSONArray array = new JSONArray();;
				for (Map<String, Object> map : list)
				{
					JSONArray childaddressarr = null;
					for (Map.Entry<String, Object> entry : map.entrySet())
					{
						if (entry.getKey().equals("department_address_token")) 
						{
							String fatheraddress = (String) entry.getValue();
							String sqlString2 = "select department_address_name,department_address_token,department_address_recommand,department_address_content,department_address_flag from snug_department_address where department_address_parent = ? and department_address_flag = '1' order by id";
							List<Map<String, Object>> list2 = jdbcTemplate.queryForList(sqlString2, fatheraddress);
							childaddressarr = MyUtil.getJsonArray(list2);
						}
					}
					map.put("child_address",childaddressarr);
					array.add(map);
				}
				
				return array.toString();
				
			}
			else
			{
				//查询所有子分区
				String sql = "select department_address_name,department_address_token from snug_department_address where department_address_parent = ? and department_address_flag = '1' order by id";
				List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,token);
				JSONArray arrayaddress = new JSONArray();
				//遍历分区，得到token值然后查询查询下面有哪些分类（通过桌台的查询）
				for (Map<String, Object> map : list)
				{
					for (Map.Entry<String, Object> entry : map.entrySet()) {
						String addresstoken =  null; 
						if (entry.getKey().equals("department_address_token"))
						{
							addresstoken = (String) entry.getValue();
							//查询子分区中的餐桌，通过餐桌看有哪些分区类型
							String query = "select distinct table_info_type,table_type_name from snug_table_info,snug_table_type where snug_table_info.table_info_type=snug_table_type.table_type_token and table_info_address = ? and table_type_flag = '1'";
							List<Map<String, Object>> list2 =jdbcTemplate.queryForList(query, addresstoken);
							JSONArray arraytype = MyUtil.getJsonArray(list2);
							//给map中添加一个entry（存储该分区下面的所有分类）
							map.put("department_address_type",arraytype);
						}
						
					}
					arrayaddress.add(map);
				}
				return arrayaddress.toString();
			}
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}

	@Override
	public String updateTableAddressNoImg(String department_address_name,
			String department_address_content, String department_address_sort,
			String department_address_recommand,
			String department_address_parent, String department_id,
			String token, String department_address_images)
	{
		if (jdbcTemplate!=null) {
			//分解要修改图片的token_ref
			String[] imgtoken = department_address_images.split(",");
			for (int i = 0; i < imgtoken.length; i++) {
				String sqlString = "update snug_images_info set image_info_flag = '0' where image_info_token_ref = ?";
				jdbcTemplate.update(sqlString, imgtoken[i]);
			}
			
			String sqlString = "update snug_department_address set department_address_name=?," +
					"department_address_content=?," +
					"department_address_sort=?," +
					"department_address_recommand=?," +
					"department_address_parent=?," +
					"department_id=? where department_address_token=? and department_address_flag = 1";
					int rows = jdbcTemplate.update(sqlString, new Object[]{
					department_address_name,
					department_address_content,
					department_address_sort,
					department_address_recommand,
					department_address_parent,
					department_id,
					token});
			

			return DBHelper.ReturnRows(rows);
			
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}


	  
}
