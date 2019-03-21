package com.jcf.implement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jcf.interfaces.TableActionInterfaces;
import com.jcf.interfaces.TableAddressActionInterfaces;
import com.jcf.util.DBHelper;
import com.jcf.util.MyUtil;
import com.jcf.util.TwoDimensionCode;
@Service
public class TableActionImplements implements TableActionInterfaces {
	  @Autowired
	  JdbcTemplate jdbcTemplate;
	  @Autowired
	  TwoDimensionCode twoDimensionCode;
	  @Autowired
	  DataSourceTransactionManager dstm;
	  @Autowired
	  TableAddressActionInterfaces tableAddressActionInterfaces;
	  /*
		 * @author Lee
		 * @time 2018/1/13
		 * @fun 餐桌添加
		 * */
	  	@Override
		public String insertTable(		
										 String table_info_name,
										 String table_info_content,
										 String table_info_quantity,
										 String table_info_type,
										 String table_info_numbers,
										 String table_info_fee_own,
										 String table_info_fee_over,
										 String table_info_fee_low,
										 String table_type_sort,
										 String table_type_sig,
										 String table_info_order_rule,
										 String department_id,
										 String table_info_address,
										 String table_info_flag,
										 String table_info_sort_rule,
										 String table_last_num,
										 String table_fiter_num,
										 String table_info_images
								 )
	    {
			if (table_last_num.length()<=0)
			{
			  	String takenString = "tableaddress" + MyUtil.GetToken().substring(0,9);
			  	//生成对应的二维码
			  	Properties pro = new Properties();
				try {
					pro.load(TableActionImplements.class.getClassLoader().getResourceAsStream("com/jcf/config/db.properties"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			  	String content = pro.getProperty("QRcodeurl") + "/queryTable?token=" + takenString;
			  	//String imgPath = System.getProperty("user.dir"); //得到项目所在服务器绝对路径
			  	//String realpath = imgPath.substring(0, imgPath.lastIndexOf("\\")) + "\\webapps\\snug\\view\\qrcode\\"+takenString+".png";
			  	String realpath = "../webapps/snug/view/qrcode/"+takenString+".png";
			  	twoDimensionCode.encoderQRCode(content, realpath,"png");
			  	String pageurlString = "view/" + takenString + ".png";
			  	//删除以前图片
			  	String deleteString = "update snug_images_info set image_info_flag = 0 where image_info_token_ref =?";
			  	jdbcTemplate.update(deleteString,takenString);
			  	//添加图片
			  	String result = MyUtil.addImgs(table_info_images, takenString, jdbcTemplate);
				if (result.equals("error")) {
					JSONObject object = new JSONObject();
					object.put("status", "2");
					object.put("mes", "图片参数错误，图片库没有所传图片！！！");
					return object.toString();
				}
			  	if (jdbcTemplate!=null) {
					String sql = "insert into snug_table_info(" +
							"table_info_name," +
							"table_info_token," +
							"table_info_content," +
							"table_info_quantity," +
							"table_info_type," +
							"table_info_numbers," +
							"table_info_fee_own," +
							"table_info_fee_over, " +
							"table_info_fee_low," +
							"table_info_url," +
							"table_type_sort," +
							"table_type_sig," +
							"table_info_order_rule," +
							"department_id," +
							"table_info_address," +
							"table_info_flag,"+
							"table_info_sort_rule) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					int rows = jdbcTemplate.update(sql, new Object[]{
							table_info_name,
							takenString,
							table_info_content,
							table_info_quantity,
							table_info_type,
							table_info_numbers,
							table_info_fee_own,
							table_info_fee_over, 
							table_info_fee_low,
							pageurlString,
							table_type_sort,
							table_type_sig,
							table_info_order_rule,
							department_id,
							table_info_address,
							table_info_flag,
							table_info_sort_rule
							});
					
					return DBHelper.ReturnRows(rows);
				}
				else
				{
					return DBHelper.FailConnectDatabase();
				}
			}
			else
			{
				//批量添加
				ArrayList<Integer> table_nums = MyUtil.getlist(table_last_num,table_fiter_num);
				System.out.println("last====="+table_last_num);
				System.out.println("fiter======"+table_fiter_num);
				System.out.println(table_nums);
				int count = 0;
				for (int i = 0; i < table_nums.size(); i++)
				{
					//得到唯一标识
				  	String takenString = "tableaddress" + MyUtil.GetToken().substring(0,9);
				  	//生成对应的二维码
				  	Properties pro = new Properties();
					try {
						pro.load(TableActionImplements.class.getClassLoader().getResourceAsStream("com/jcf/config/db.properties"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				  	String content = pro.getProperty("QRcodeurl") + "/queryTable?token=" + takenString;
				  	//String imgPath = System.getProperty("user.dir"); //得到项目所在服务器绝对路径
				  	//String realpath = imgPath.substring(0, imgPath.lastIndexOf("\\")) + "\\webapps\\snug\\view\\qrcode\\"+takenString+".png";
				  	String realpath = "../webapps/snug/view/qrcode/"+takenString+".png";
				  	twoDimensionCode.encoderQRCode(content, realpath,"png");
				  	String pageurlString = "view/" + takenString + ".png";
				  	//添加图片
				  	String result = MyUtil.addImgs(table_info_images, takenString, jdbcTemplate);
					if (result.equals("error")) {
						JSONObject object = new JSONObject();
						object.put("status", "2");
						object.put("mes", "图片参数错误，图片库没有所传图片！！！");
						return object.toString();
					}
				  	if (jdbcTemplate!=null) {
						String sql = "insert into snug_table_info(" +
								"table_info_name," +
								"table_info_token," +
								"table_info_content," +
								"table_info_quantity," +
								"table_info_type," +
								"table_info_numbers," +
								"table_info_fee_own," +
								"table_info_fee_over, " +
								"table_info_fee_low," +
								"table_info_url," +
								"table_type_sort," +
								"table_type_sig," +
								"table_info_order_rule," +
								"department_id," +
								"table_info_address," +
								"table_info_flag,"+
								"table_info_sort_rule) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
						int rows = jdbcTemplate.update(sql, new Object[]{
								table_info_name + " " + table_nums.get(i),
								takenString,
								table_info_content,
								table_info_quantity,
								table_info_type,
								table_info_numbers,
								table_info_fee_own,
								table_info_fee_over, 
								table_info_fee_low,
								pageurlString,
								table_type_sort,
								table_type_sig,
								table_info_order_rule,
								department_id,
								table_info_address,
								table_info_flag,
								table_info_sort_rule
								});
						if (rows==0)
						{
							JSONObject object = new JSONObject();
							object.put("status","2");
							object.put("mes","添加 失败");
							return object.toString();
						}
						count++;
					}
					else
					{
						return DBHelper.FailConnectDatabase();
					}
				}
				System.out.println("成功添加"+count+"张桌子");
			}
			JSONObject object = new JSONObject();
			object.put("status", "1");
			object.put("mes", "success");
			return object.toString();
		}
	  
	  /*
		 * @author Lee
		 * @time 2018/1/13
		 * @fun 餐桌隐藏
		 * url:http://localhost:8080/snug/deleteTable?token=tableaddress876241a0d
		 * */
	  @Override
	  public String deleteTable(String token){
			  if(jdbcTemplate!=null){
					 String sql = "update snug_table_info set table_info_flag = '0' where table_info_token = ?";
					 
					 int rows = jdbcTemplate.update(sql,new Object[]{token});
					 
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
		 * @fun 餐桌查询
		 * url:http://localhost:8080/snug/queryTable
		 * */
	  @Override
	  public String queryTable(){
		if (jdbcTemplate!=null) 
		{
			String sqlString  = "select * from snug_table_info where table_info_flag = '1'";
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlString);
			//list相当于是一个数组，现在是把list中的对象转换成JSON的对象
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
		 * @fun 餐桌信息修改
		 */
	  @Override
	  public String updateTableInfoNoImg(
			  	 String table_info_name,
				 String table_info_content,
				 String table_info_quantity,
				 String table_info_type,
				 String table_info_numbers,
				 String table_info_fee_own,
				 String table_info_fee_over,
				 String table_info_fee_low,
				 String table_info_url,
				 String table_type_sort,
				 String table_type_sig,
				 String table_info_order_rule,
				 String department_id,
				 String table_info_address,
				 String table_info_token,
				 String table_images_token_ref
			  ) 
	  {
			if (jdbcTemplate!=null) {
				String sqlString = "update snug_table_info set table_info_name = ?," +
						"table_info_content=?," +
						"table_info_quantity=?," +
						"table_info_type=?," +
						"table_info_numbers=?," +
						"table_info_fee_own=?," +
						"table_info_fee_over=?," +
						"table_info_fee_low=?," +
						"table_info_url=?," +
						"table_type_sort=?," +
						"table_type_sig=?," +
						"table_info_order_rule=?," +
						"department_id=?," +
						"table_info_address=?" +
						" where table_info_token = ? and table_info_flag = 1";
				int rows = jdbcTemplate.update(sqlString, new Object[]{
						  table_info_name,
						  table_info_content,
						  table_info_quantity,
						  table_info_type,
						  table_info_numbers,
						  table_info_fee_own,
						  table_info_fee_over,
						  table_info_fee_low,
						  table_info_url,
						  table_type_sort,
						  table_type_sig,
						  table_info_order_rule,
						  department_id,
						  table_info_address,
						  table_info_token
				});
			    return DBHelper.ReturnRows(rows);
			}
			else
			{
				return DBHelper.FailConnectDatabase();
			}
		}

	@Override
	public String queryTableByToken(String table_info_token) {
		if (jdbcTemplate!=null) 
		{
			String sqlString  = "select * from snug_table_info where table_info_token = ?";
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlString,table_info_token);
			for (Map<String, Object> map : list)
			{
				List<Map<String,Object>> list2 = null;
				for (Map.Entry<String, Object> entry : map.entrySet())
				{
					if (entry.getKey().equals("table_info_token"))
					{
						String queryimages = "select image_info_name from snug_images_info where image_info_flag!='0' and image_info_token_ref = ?";
						list2 = jdbcTemplate.queryForList(queryimages,entry.getValue());	
					}
				}
				map.put("table_info_img",list2);
			}
			return DBHelper.ReturnList(list);
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}
	//查询分区名称、分类名称、台桌http://localhost:8080/queryTableDefault
	@Override
	public String queryTableDefault() {
		if (jdbcTemplate!=null) 
		{
			//查询所有根目录分区1
			String sqlfatheraddress = "select department_address_name,department_address_token from snug_department_address where department_address_parent = '根目录' and department_address_flag!='0'";
			List<Map<String,Object>> list = jdbcTemplate.queryForList(sqlfatheraddress);
			System.out.println("======list====="+list.toString());
			JSONArray array = MyUtil.getJsonArray(list);
			JSONObject fatheraddress = (JSONObject) array.get(0);
			
			//查询所有子分区及分类
			JSONArray addressarray = new JSONArray();
			for (int i = 0; i < array.size(); i++)
			{
				JSONObject object = (JSONObject) array.get(i);
				String firaddress_token = object.getString("department_address_token");
				String firaddress_name = object.getString("department_address_name");
				//得到分区下面的所有子分区和分类
				String child_address = tableAddressActionInterfaces.queryTableChildAddress(firaddress_token);
				
				JSONArray child_array = JSONArray.parseArray(child_address);
				JSONObject object2 = new JSONObject();
				object2.put("firaddress_name", firaddress_name);
				object2.put("firaddress_token", firaddress_token);
				object2.put("child_address_info", child_array);
				addressarray.add(object2);
			}
			
			
			//得到更目录分区的token值和name值1.1
			String fathertoken = (String) fatheraddress.get("department_address_token");
			String fathername = (String) fatheraddress.get("department_address_name");
			//查询根分区下面的子分区及分类2
			String addresss = tableAddressActionInterfaces.queryTableChildAddress(fathertoken);
			//得到所有分区下面所有分类的所有桌子3
			JSONArray addresssarr = JSONArray.parseArray(addresss);
			
			//存储每一组的桌子和分区分类
			JSONArray address_table_type = new JSONArray();
			
			String department_address_name = null;
			String table_info_type = null;
			JSONObject oneJsonObject = null;
			//遍历分区
			for (int i = 0; i < addresssarr.size(); i++)
			{
 				//得到分区的名称
				JSONObject address = addresssarr.getJSONObject(i);
 				department_address_name = (String) address.get("department_address_name").toString();
 				String department_address_token = (String) address.get("department_address_token").toString();
				JSONArray department_address_type = (JSONArray) JSONArray.parse((String)address.get("department_address_type").toString());
				//遍历分类
				for (int j = 0; j < department_address_type.size(); j++)
				{
					//得到分类
					JSONObject typeobject = (JSONObject) department_address_type.get(j);
					table_info_type = (String) typeobject.get("table_info_type");
					//查询分类名称
					String type_nameString = "select table_type_name from snug_table_type where table_type_token =? and table_type_flag !='0'";
					Map<String, Object> map = jdbcTemplate.queryForMap(type_nameString,table_info_type);
					String type_name = (String) map.get("table_type_name");
					//查询桌子
					String query = "select table_info_name," +
							"table_info_type," +
							"table_info_numbers," +
							"table_info_fee_own," +
							"table_info_fee_low," +
							"table_info_order_rule," +
							"table_info_token," +
							"table_info_orderable," +
							"table_type_sig," +
							"table_info_flag" +
							" from snug_table_info " +
							"where table_info_address = ?" +
							"and table_info_type = ? and table_info_flag != '0'";
					List<Map<String, Object>> list2 = jdbcTemplate.queryForList(query,new Object[]{department_address_token,table_info_type});
					for (Map<String, Object> map2 : list2)
					{
						List<Map<String, Object>> list3 = null;
						for (Map.Entry<String, Object> entry : map2.entrySet())
						{
							if (entry.getKey().equals("table_info_token"))
							{
								//查询桌子对应图片
								String queryimages = "select image_info_name from snug_images_info where image_info_token_ref = ? and image_info_flag!='0'";
								list3 = jdbcTemplate.queryForList(queryimages, entry.getValue());
								break;
							}
						}
						map2.put("image_info_names",list3);
					}
					JSONArray alltables = MyUtil.getJsonArray(list2);
					
					
					oneJsonObject = new JSONObject();
					oneJsonObject.put("house_name",fathername);
					oneJsonObject.put("department_address_name",department_address_name);
					oneJsonObject.put("table_type_name", type_name);
					oneJsonObject.put("table_type_token", table_info_type);
					oneJsonObject.put("tables", alltables);
					address_table_type.add(oneJsonObject);
					
					
				}
			}
			
			JSONObject object = new JSONObject();
			object.put("status", "1");
			object.put("mes", "success");
			object.put("info", address_table_type);
			object.put("address_info", addressarray);
			return object.toString();
			
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
				
	}

	@Override
	public String updateTableOrderable(String table_info_token,String table_info_orderable) {
		if (jdbcTemplate!=null) 
		{
			String sqlString = "update snug_table_info set table_info_orderable = ? where table_info_token = ?";
			int rows = jdbcTemplate.update(sqlString, new Object[]{table_info_orderable,table_info_token});
			return DBHelper.ReturnRows(rows);
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}

	@Override
	public String updateTableFlag(String table_info_token,String table_info_flag) {
		if (jdbcTemplate!=null) 
		{
			String sqlString = "update snug_table_info set table_info_flag = ? where table_info_token = ?";
			int rows = jdbcTemplate.update(sqlString, new Object[]{table_info_flag,table_info_token});
			return DBHelper.ReturnRows(rows);
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}

	@Override
	public String downloadQrCode(String table_info_token,HttpServletResponse response) {
		MyUtil.downloadQrCode(table_info_token, response);
		return "";
	}

	@Override
	public String queryTableByAddressAndTime(String table_info_address,String time, String table_info_type)
	{
		if (jdbcTemplate!=null)
		{
			//查询所有桌子
			String queryalltable = "select table_info_name,table_info_numbers,table_info_flag,table_info_token from snug_table_info where table_info_address = ? and table_info_type = ?";
			List<Map<String, Object>> list = jdbcTemplate.queryForList(queryalltable, new Object[]{table_info_address,table_info_type});
			String json_array_tables = DBHelper.ReturnList(list);
			//查询账单中的对应时间段的桌子信息
			String bookdinnertabgles = "select bookdinner_info_flag,bookdinner_info_costom,bookdinner_info_phone,bookdinner_info_table,bookdinner_info_address,bookdinner_info_times,bookdinner_info_ordertime from snug_bookdinner_info where ? between bookdinner_info_times and bookdinner_info_ordertime";
			List<Map<String, Object>> list2 = jdbcTemplate.queryForList(bookdinnertabgles,time);
			String json_array_ordertables = DBHelper.ReturnList(list2);
			//
			JSONObject object = new JSONObject();
			object.put("alltable",JSONArray.parse(json_array_tables));
			object.put("ordertables", JSONArray.parse(json_array_ordertables));
			return object.toString();
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
		
	}

	@Override
	public String queryTableDefaultByTime(String time) {
		if (jdbcTemplate!=null)
		{
			//查询所有桌子
			String queryalltable = "select table_info_name,table_info_numbers,table_info_flag," +
					"table_info_token,table_info_address,table_info_type from snug_table_info where table_info_flag !='0' ";	
			List<Map<String, Object>> list = jdbcTemplate.queryForList(queryalltable);
			JSONArray array = new JSONArray();
			for (Map<String, Object> map : list)
			{
				String department_address_name = null;
				String department_address_token = null;
				for (Map.Entry<String,Object> entry : map.entrySet())
				{
					if (entry.getKey().equals("table_info_address"))
					{
						//所属分区的token值
						String table_address = (String) entry.getValue();
						//查询所属分区的的父级token
						String address_father = "select department_address_parent from " +
								"snug_department_address where department_address_token = ?";
						System.out.println(table_address);
						Map<String, Object> map2 = jdbcTemplate.queryForMap(address_father, new Object[]{ table_address});
						
						if (map2!=null && map2.size()==0)
						{
							JSONObject object = new JSONObject();
							object.put("status", "2");
							object.put("mes", "没有对应二级分区，数据库数据错误！！！");
							return object.toString();
						}
						String father_token = (String) map2.get("department_address_parent");
						
						//查询顶级分区（栋）
						String fir_table_address = "select department_address_name,department_address_token from snug_department_address where department_address_token = ? and department_address_flag != '0'";
						Map<String, Object> map3 = jdbcTemplate.queryForMap(fir_table_address, father_token);
						if (map3.size() == 0)
						{
							JSONObject object = new JSONObject();
							object.put("status", "2");
							object.put("mes", "没有对应一级分区，数据库数据错误！！！");
							return object.toString();
						}
						 department_address_name = (String) map3.get("department_address_name");
						 department_address_token = (String) map3.get("department_address_token");
					}
				}
				map.put("first_address_name", department_address_name);
				map.put("first_address_token", department_address_token);
				array.add(map);
			}
			//String json_array_tables = DBHelper.ReturnList(list);
			//查询账单中的对应时间段的桌子信息
			String bookdinnertabgles = "select bookdinner_info_flag,bookdinner_info_costom,bookdinner_info_phone,bookdinner_info_table,bookdinner_info_address,bookdinner_info_times,bookdinner_info_ordertime,bookdinner_info_token from snug_bookdinner_info where ? between bookdinner_info_times and bookdinner_info_ordertime and bookdinner_info_flag !='0'";
			List<Map<String, Object>> list2 = jdbcTemplate.queryForList(bookdinnertabgles,time);
			String json_array_ordertables = DBHelper.ReturnList(list2);
			//查询外卖单
			String takeoutfood= "select bookdinner_info_food,bookdinner_info_flag from snug_bookdinner_info where bookdinner_info_type = '2'";
			List<Map<String, Object>> list3 = jdbcTemplate.queryForList(takeoutfood);
			JSONArray outfoodArray = new JSONArray();
			
			for (Map<String, Object> map : list3) {
				JSONObject object = new JSONObject();
				for (Map.Entry<String, Object> entry : map.entrySet())
				{
					String key = null;
					String value = null;
					if (entry.getKey().equals("bookdinner_info_food"))
					{
						key = entry.getKey();
						value = (String) entry.getValue();
						object.put(key,JSONArray.parse(value));
					}
					else
					{
						object.put(entry.getKey(),entry.getValue());
					}
					
				}
				outfoodArray.add(object);
			}
			//查询所有分区
			//List<Map<String,Object>> list4 = jdbcTemplate.queryForList("select * from snug_department_address where department_address_flag = '1'");
		    //查询一级分区
			String fir_address = "select department_address_name,department_address_token,id " +
					"from snug_department_address where department_address_parent = '根目录' and department_address_flag !='0' order by id";
			List<Map<String, Object>> list5 = jdbcTemplate.queryForList(fir_address);
			JSONArray address_arr = new JSONArray();
			//遍历一级分区
			for (Map<String, Object> map : list5)
			{
				JSONObject object = new JSONObject();
				for (Map.Entry<String, Object> entry : map.entrySet())
				{
					if (entry.getKey().equals("id"))
					{
						object.put("id",entry.getValue());
					}
					if (entry.getKey().equals("department_address_name"))
					{
						object.put("fir_address_name",entry.getValue());
					}
					if (entry.getKey().equals("department_address_token"))
					{
						object.put("fir_address_token",entry.getValue());
						//查询二级分区
						String address_sec = "select department_address_name,department_address_token from snug_department_address where department_address_parent = ? and department_address_flag != '0'";
						List<Map<String, Object>> list6 = jdbcTemplate.queryForList(address_sec,entry.getValue());
						JSONArray sec_array = new JSONArray();
						//遍历二级分区
						for (Map<String, Object> map2 : list6)
						{
							JSONObject object2 = new JSONObject();
							for (Map.Entry<String, Object> entry2 : map2.entrySet()) 
							{
								if (entry2.getKey().equals("department_address_name"))
								{
									object2.put("sec_name", entry2.getValue());
								}
								if (entry2.getKey().equals("department_address_token"))
								{
									object2.put("sec_token", entry2.getValue());
									//查询二级分区下面分类
									String sec_type = "select distinct table_type_name,table_type_token from snug_table_info,snug_table_type where snug_table_info.table_info_type = snug_table_type.table_type_token and table_info_address = ? and table_type_flag != '0'";
									List<Map<String, Object>> list7 = jdbcTemplate.queryForList(sec_type,entry2.getValue());
									JSONArray sec_arr = MyUtil.getJsonArray(list7);
									object2.put("sec_type",sec_arr);
								}
								
							}
							sec_array.add(object2);
						}
						object.put("sec_address",sec_array);
					}
				}
				address_arr.add(object);
			}
			// String tableaddres = DBHelper.ReturnList(list4);
			
		   
		    
		    //查询所有分类
		    List<Map<String,Object>> list7 = jdbcTemplate.queryForList("select * from snug_table_type where table_type_flag = 1");
		    String tableaddrestype = DBHelper.ReturnList(list7);
		    
		    
		    //查询排队
		    JSONArray sortarr = new JSONArray();
		    
		    JSONObject sort1 = new JSONObject();
		    sort1.put("phone", "15700218161");
		    sort1.put("table_type", "大桌");
		    sort1.put("people_nums", "10");
		    sortarr.add(sort1);
		    
		    JSONObject sort2 = new JSONObject();
		    sort2.put("phone", "18235687144");
		    sort2.put("table_type", "小桌");
		    sort2.put("people_nums", "5");
		    sortarr.add(sort2);
		     
		    
			JSONObject object = new JSONObject();
			object.put("alltable",array);
			object.put("ordertables", JSONArray.parse(json_array_ordertables));
			object.put("takeoutfood", outfoodArray);
			object.put("address_type", address_arr);
			//object.put("alltableaddress", JSONArray.parse(tableaddres));
			object.put("tableaddrestype", JSONArray.parse(tableaddrestype));
			object.put("table_sort", sortarr);
			return object.toString();
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}

	@Override
	public String orderTablesDefault(String TableAddressToken,String table_type_token) {

		if (jdbcTemplate!=null)
		{
			//查询所有空桌子
			String queryalltable = "select table_info_name,table_info_numbers,table_info_flag,table_info_token from snug_table_info where table_info_address = ? and table_info_type = ? and table_info_flag = '1'";
			List<Map<String, Object>> list = jdbcTemplate.queryForList(queryalltable, new Object[]{TableAddressToken,table_type_token});
			String json_array_tables = DBHelper.ReturnList(list);
			
			JSONObject object = new JSONObject();
			object.put("alltable",JSONArray.parse(json_array_tables));
			return object.toString();
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}

	@Override
	public String dealOrder(String bookdinner_info_setnumber,
			String bookdinner_info_ordertime, String bookdinner_info_sailer,
			String bookdinner_info_channel, String operator_id,
			String bookdinner_info_plus, String tables,
			String bookdinner_info_costom,
			String bookdinner_info_phone,
			String department_id) {
		 
		if (jdbcTemplate!=null)
		{
			//每张桌子一张订单
			String[] tablearr = tables.split(",");
			for (int i = 0; i < tablearr.length; i++)
			{
				String token = "bookdinner" + MyUtil.GetToken().substring(0, 9);
				String sql = "insert snug_bookdinner_info(" +
						"bookdinner_info_setnumber," +
						"bookdinner_info_ordertime," +
						"bookdinner_info_sailer," +
						"bookdinner_info_channel," +
						"operator_id," +
						"bookdinner_info_plus," +
						"bookdinner_info_table," +
						"bookdinner_info_costom," +
						"bookdinner_info_phone," +
						"bookdinner_info_flag," +
						"bookdinner_info_token," +
						"department_id) values(?,?,?,?,?,?,?,?,?,?,?,?)";
				int rows = jdbcTemplate.update(sql, new Object[]{
							bookdinner_info_setnumber,
							bookdinner_info_ordertime,
							bookdinner_info_sailer,
							bookdinner_info_channel,
							operator_id,
							bookdinner_info_plus,
							tablearr[i],
							bookdinner_info_costom,
							bookdinner_info_phone,
							"3",
							token,
							department_id
						});
				if (i==(tablearr.length-1))
				{
					return DBHelper.ReturnRows(rows);
				}
			}
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
		return "error";
	}

	@Override
	public String getOrderMes(String bookdinner_info_token) {
		if(jdbcTemplate!=null)
		{
			String query = "select bookdinner_info_food from snug_bookdinner_info where bookdinner_info_token = ?";
			List<Map<String, Object>> list = jdbcTemplate.queryForList(query,bookdinner_info_token);
			
			JSONArray json = MyUtil.getJsonArray(list);
			JSONArray array = new JSONArray();
			if (json.size()>0) 
			{
				JSONObject foodobj = json.getJSONObject(0);
				String food = (String) foodobj.get("bookdinner_info_food");
				array = JSONArray.parseArray(food);
			}
			
			JSONObject object = new JSONObject();
			object.put("status", 1);
			object.put("mes", "success");
			object.put("info",array);
			return object.toString();
			
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}

	@Override
	public String queryTablesByAddressToken(String address_token) {
		if (jdbcTemplate != null)
		{
			//判断是几级分区
			String queryString = "select * from snug_department_address where department_address_token = ?";
		
			Map<String, Object> map = jdbcTemplate.queryForMap(queryString, address_token);
			String department_address_parent = (String) map.get("department_address_parent");
			String department_address_name = (String) map.get("department_address_name");
			String department_address_token = (String) map.get("department_address_token");
			
			
			//查询该分区的type
/*			String fir_address_type = "select distinct table_info_type,table_type_name from snug_table_info,snug_table_type where snug_table_info.table_info_type = snug_table_type.table_type_token and table_info_address = ?";
			List<Map<String, Object>> list_fir = jdbcTemplate.queryForList(fir_address_type, address_token);
			JSONArray arrayfir = null;
			JSONArray all_type =new JSONArray();
			for (Map<String, Object> map7 : list_fir) 
			{
				arrayfir =new JSONArray();
				for (Map.Entry<String,Object> entry : map7.entrySet())
				{
					if (entry.getKey().equals("table_info_type"))
					{
						String query_tables = "select * from snug_table_info where table_info_address = ? and table_info_type = ?";
						List<Map<String, Object>> list2  = jdbcTemplate.queryForList(query_tables,new Object[]{address_token,entry.getValue()});
						arrayfir = MyUtil.getJsonArray(list2);
					}
				}
				map7.put("tables", arrayfir);
				all_type.add(map7);
			}
			fir_table.put("fir_type", all_type);
			fir_table.put("fir_address_name", department_address_name);
			fir_table.put("fir_address_token", department_address_token);
			tables.add(fir_table);
			
*/			
 			//判断是否是根目录分区
			if (department_address_parent.equals("根目录"))
			{
				JSONArray tables = new JSONArray();;
				JSONObject fir_table = null;
				//查询根分区的所有桌子1
				//查询所有子分区1.1
				String query_child_address = "select department_address_token,department_address_name from snug_department_address where department_address_parent =?";
				List<Map<String, Object>> list = jdbcTemplate.queryForList(query_child_address, address_token);
				if (list.size()>0)
				{
					//是根目录，有子分区
					String add_name = null;
					String add_token = null;
					//查询子分区下面的所有桌子1.2
					for (Map<String, Object> map2 : list)
					{
						for (Map.Entry<String,Object> entry : map2.entrySet())
						{
							if (entry.getKey().equals("department_address_name"))
							{
								add_name = (String) entry.getValue();
								
							}
							
							if (entry.getKey().equals("department_address_token"))
							{
								add_token = (String) entry.getValue();
							}
						}
						
						//查询该分区的分类
						String address_type = "select distinct table_info_type,table_type_name from snug_table_info,snug_table_type where snug_table_info.table_info_type = snug_table_type.table_type_token and table_info_address = ?";
						List<Map<String, Object>> list3 = jdbcTemplate.queryForList(address_type, add_token);
						
						//遍历分类
						for (Map<String, Object> map3 : list3)
						{
							fir_table = new JSONObject();
							for (Map.Entry<String, Object> entry2 : map3.entrySet())
							{
								if (entry2.getKey().equals("table_info_type"))
								{
									//查询分类的名称
									String query_type_name = "select table_type_name from snug_table_type where table_type_token = ?";
									Map<String, Object> map4 = jdbcTemplate.queryForMap(query_type_name,entry2.getValue());
									String table_type_name = (String) map4.get("table_type_name");
									
									String query_tables = "select * from snug_table_info where table_info_address = ? and table_info_type = ?";
									List<Map<String, Object>> list2  = jdbcTemplate.queryForList(query_tables,new Object[]{add_token,entry2.getValue()});
									for (Map<String, Object> map5 : list2)
									{
										List<Map<String,Object>> list5 = null;
										for (Map.Entry<String, Object> entry : map5.entrySet())
										{
											if (entry.getKey().equals("table_info_token"))
											{
												String queryimages = "select image_info_name from snug_images_info where image_info_flag!='0' and image_info_token_ref = ?";
												list5 = jdbcTemplate.queryForList(queryimages,entry.getValue());	
											}
										}
										map5.put("table_info_img",list5);
									}
									System.out.println("1111111111111111111111");
									JSONArray array_tables = MyUtil.getJsonArray(list2);
									System.out.println(add_name);
									fir_table.put("sec_address_name", add_name);
									fir_table.put("sec_address_token", add_token);
									fir_table.put("first_address_name", department_address_name);
									fir_table.put("first_address_token", department_address_token);
									fir_table.put("table_type_name", table_type_name);
									fir_table.put("table_type_token", entry2.getValue());
									fir_table.put("tables", array_tables);
									tables.add(fir_table);
								}
							}
							
						}
					}
					JSONObject object = new JSONObject();
					object.put("status", "1");
					object.put("mes", "success");
					object.put("info", tables);
					return object.toString();
				}
				else
				{
					//是根目录，但没有子分区
					//查询名称
					String queryString2 = "select department_address_name from snug_department_address where department_address_token = ?";
					Map<String, Object> map2 = jdbcTemplate.queryForMap(queryString2,address_token);
					String department_address_name2 = (String) map2.get("department_address_name");
					//查询分类
					String sqlString = "select distinct table_info_type,table_type_name from snug_table_info,snug_table_type where snug_table_info.table_info_type = snug_table_type.table_type_token and table_info_address = ?";;
					List<Map<String, Object>> list2 = jdbcTemplate.queryForList(sqlString,address_token);
					JSONArray type_tables = new JSONArray();
					JSONObject type_table = null;
					
					for (Map<String, Object> map3 : list2)
					{
						String tn = null;
						String tt = null;
						type_table = new JSONObject();
						for (Map.Entry<String, Object> entry : map3.entrySet())
						{
							if (entry.getKey().equals("table_info_type"))
							{
								tt = (String) entry.getValue();
							}
							
							if (entry.getKey().equals("table_type_name"))
							{
								tn = (String) entry.getValue();
							}
							
						}
						//查询桌子
						String query_tables = "select * from snug_table_info where table_info_address = ? and table_info_type = ?";
						List<Map<String, Object>> list4  = jdbcTemplate.queryForList(query_tables,new Object[]{address_token,tt});
						for (Map<String, Object> map6 : list4)
						{
							List<Map<String,Object>> list6 = null;
							for (Map.Entry<String, Object> entry : map6.entrySet())
							{
								if (entry.getKey().equals("table_info_token"))
								{
									String queryimages = "select image_info_name from snug_images_info where image_info_flag!='0' and image_info_token_ref = ?";
									list6 = jdbcTemplate.queryForList(queryimages,entry.getValue());	
								}
							}
							map6.put("table_info_img",list6);
						}
						
						JSONArray array_tables = MyUtil.getJsonArray(list4);
						type_table.put("first_address_name",department_address_name2);
						type_table.put("first_address_token", address_token);
						type_table.put("table_type_name", tn);
						type_table.put("table_type_token", tt);
						type_table.put("tables", array_tables);
						type_tables.add(type_table);
					}
					JSONObject object = new JSONObject();
					object.put("status", "1");
					object.put("mes", "success");
					object.put("info", type_tables);
					return object.toString();
				}
			}
			else
			{
				//不是根目录
				//查询到所属根目录分区的名称
				String sqlString = "select department_address_parent,department_address_name from snug_department_address where department_address_token = ?";
				Map<String , Object> map2 = jdbcTemplate.queryForMap(sqlString, address_token);
				String  selfName = (String) map2.get("department_address_parent");//分区的父级token值
				String department_address_name2 = (String) map2.get("department_address_name");//自己的名称
				//查询父分区的名字
				String querySelfName = "select department_address_name from snug_department_address where department_address_token = ?";
				Map<String, Object> map5 = jdbcTemplate.queryForMap(querySelfName, selfName);
				String department_address_father = (String) map5.get("department_address_name");
				//查询该分区下面的分类
				String sqlString2 = "select distinct table_info_type,table_type_name from snug_table_info,snug_table_type where snug_table_info.table_info_type = snug_table_type.table_type_token and table_info_address = ?";;
				List<Map<String, Object>> list2 = jdbcTemplate.queryForList(sqlString2,address_token);
				System.out.println(list2);
				//遍历分类
				String tt = null;
				String tn = null;
				JSONObject object = null;
				JSONArray array = new JSONArray();
				for (Map<String, Object> map3 : list2) 
				{
					object = new JSONObject();
					//得到分类的名称和token值
					for (Map.Entry<String, Object> entry : map3.entrySet())
					{
						if (entry.getKey().equals("table_info_type"))
						{
							tt = (String) entry.getValue();
						}
						
						if (entry.getKey().equals("table_type_name"))
						{
							tn = (String) entry.getValue();
						}
					}
					//查询分类，分区的桌子
					String query_tables = "select * from snug_table_info where table_info_address = ? and table_info_type = ?";
					List<Map<String, Object>> list4  = jdbcTemplate.queryForList(query_tables,new Object[]{address_token,tt});
					for (Map<String, Object> map7 : list4)
					{
						List<Map<String,Object>> list5 = null;
						for (Map.Entry<String, Object> entry : map7.entrySet())
						{
							if (entry.getKey().equals("table_info_token"))
							{
								String queryimages = "select image_info_name from snug_images_info where image_info_flag!='0' and image_info_token_ref = ?";
								list5 = jdbcTemplate.queryForList(queryimages,entry.getValue());	
							}
						}
						map7.put("table_info_img",list5);
						System.out.println("map7:"+map7);
					}
					
					JSONArray array_tables = MyUtil.getJsonArray(list4);
					object.put("first_address_name",department_address_father);
					object.put("first_address_token",selfName);
					object.put("selfName", department_address_name2);
					object.put("selfToken", address_token);
					object.put("table_type_name", tn);
					object.put("table_type_token", tt);
					object.put("tables", array_tables);
					array.add(object);
				}
				JSONObject object2 = new JSONObject();
				object2.put("status", "1");
				object2.put("mes", "success");
				object2.put("info",array);
				return object2.toString();
			}
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
		
	}

	@Override
	public String updateTableInfo(String table_info_name,
			String table_info_content, String table_info_quantity,
			String table_info_type, String table_info_numbers,
			String table_info_fee_own, String table_info_fee_over,
			String table_info_fee_low, String table_info_url,
			String table_type_sort, String table_type_sig,
			String table_info_order_rule, String department_id,
			String table_info_address, String table_info_token,
			String table_info_images)
	{
		if (jdbcTemplate!=null) {
			//删除旧图片
				String delete_imgsString = "update snug_images_info set image_info_flag = '0' where image_info_token_ref = ?";
				jdbcTemplate.update(delete_imgsString,table_info_token);
			//上传新图片
			String result = MyUtil.addImgs(table_info_images, table_info_token, jdbcTemplate);
			if (result.equals("error"))
			{
				JSONObject object = new JSONObject();
				object.put("status", "2");
				object.put("mes", "图片参数错误，图片库没有所传图片！！！");
				return object.toString();
			}
			
			
			String sqlString = "update snug_table_info set " +
					"table_info_name = ?," +
					"table_info_content=?," +
					"table_info_quantity=?," +
					"table_info_type=?," +
					"table_info_numbers=?," +
					"table_info_fee_own=?," +
					"table_info_fee_over=?," +
					"table_info_fee_low=?," +
					"table_info_url=?," +
					"table_type_sort=?," +
					"table_type_sig=?," +
					"table_info_order_rule=?," +
					"table_info_address=?" +
					" where table_info_token = ? and table_info_flag = '1' and department_id=?";
			int rows = jdbcTemplate.update(sqlString, new Object[]{
					  table_info_name,
					  table_info_content,
					  table_info_quantity,
					  table_info_type,
					  table_info_numbers,
					  table_info_fee_own,
					  table_info_fee_over,
					  table_info_fee_low,
					  table_info_url,
					  table_type_sort,
					  table_type_sig,
					  table_info_order_rule,
					  table_info_address,
					  table_info_token,
					  department_id
			});
		    return DBHelper.ReturnRows(rows);
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}
}
