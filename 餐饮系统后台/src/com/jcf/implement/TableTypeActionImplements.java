package com.jcf.implement;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.jcf.interfaces.TableTypeActionInterfaces;
import com.jcf.util.DBHelper;
import com.jcf.util.MyUtil;

import java.util.List;


@Service
public class TableTypeActionImplements implements TableTypeActionInterfaces {
  @Autowired
  JdbcTemplate jdbcTemplate;
  /*
	 * @author Lee
	 * @time 2018/1/12
	 * @fun 台桌分类查询(所有)
	 * testurl:http://localhost:8080/snug/queryTableType
	 * */
  	@Transactional(propagation=Propagation.REQUIRED)
  	@Override
	public String queryTableType() {
		if (jdbcTemplate!=null) {
			List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from snug_table_type where table_type_flag = 1");
			for (Map<String,Object> map : list)
			{
				JSONArray urlString = null;
				for (Map.Entry<String,Object> entry : map.entrySet())
				{
					if (entry.getKey().equals("table_type_token"))
					{
						String query ="select image_info_name,image_info_token_ref from snug_images_info where image_info_token_ref like '" + entry.getValue() +"%' and image_info_flag = '1'";
						List<Map<String,Object>> list2 = jdbcTemplate.queryForList(query);
						JSONArray  typeArray = MyUtil.getJsonArray(list2);
						if (list2.size()==0)
						{
							urlString = new JSONArray();
						}
						else
						{
							urlString = typeArray;
						}
					}
				}
				map.put("table_type_img",urlString);
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
			return DBHelper.FailConnectDatabase();
		}
	}
  
  /*
	 * @author Lee
	 * @time 2018/1/12
	 * @fun 台桌分类添加
	 * testUrl:
	  http:localhost:8080/snug/insertTableType?table_type_name=厅&table_type_sub_name=包间&
	  table_type_img=5.jpg&table_type_sort=大小&table_type_recommand=推荐&table_type_content=4564564&table_type_flag=1
	 * */
  /* (non-Javadoc)
 * @see com.jcf.service.TableTypeInterfaces#insertTableInfo(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
 */
  	@Override
	public String insertTableType(String table_type_name,String table_type_sub_name,
								  String table_type_sort,
								  String table_type_recommand,String table_type_content,
								  String table_type_flag,String table_type_images)
    {
	  	//得到项目唯一标识
	  	String takenString = "tabletype" + MyUtil.GetToken().substring(0,9);
	  	
		if (jdbcTemplate!=null)
		{
			//上传图片
			String result = MyUtil.addImgs(table_type_images, takenString, jdbcTemplate);
			if (result.equals("error")) {
				JSONObject object = new JSONObject();
				object.put("status", "2");
				object.put("mes", "图片参数错误，图片库没有所传图片！！！");
				return object.toString();
			}
			int rows =jdbcTemplate.update("insert into snug_table_type(" +
					"table_type_name,table_type_sub_name," +
					"table_type_token," +
					"table_type_sort,table_type_flag," +
					"table_type_recommand,table_type_content" +
					") values(?,?,?,?,?,?,?)", 
					new Object[]{table_type_name,table_type_sub_name,takenString,table_type_sort,table_type_flag,table_type_recommand,table_type_content});
		   return DBHelper.ReturnRows(rows);
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}
    /* 
	 * @author Lee
	 * @time 2018/1/12
	 * @fun 台桌分类隐藏(flag=0)
	 * testUrl:
	  http:localhost:8080/snug/deleteTableType?table_type_token=47897774
	 * */
  @Override
  public String deleteTableType(String table_type_token){
	  if (jdbcTemplate!=null) {
		String sql = "update snug_table_type set table_type_flag = 0 where table_type_token = ?";
		int rows = jdbcTemplate.update(sql, table_type_token);
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
	 * @fun 台桌分类修改
	 * testUrl:
	  http://localhost:8080/snug/updateTableType?
	  table_type_name=1&
	  table_type_sub_name=11&
	  table_type_sort=大小&
	  table_type_recommand=推荐&
	  table_type_content=没有描述&
	  table_type_flag =1&
	  table_type_image_token=1.jpg,2.jpg&
	  table_type_token=tableaddress75f633b78
	 * */

  @Override
  public String updateTableType(String table_type_name,String table_type_sub_name,
								  String table_type_sort,String table_type_recommand,
								  String table_type_token,String table_type_content,
								  String table_type_flag,String table_type_images,
								  String department_id)
  {
		if (jdbcTemplate!=null)
		{
			//删除以前的图片
			String deletesqlString = "update snug_images_info set image_info_flag = '0' where image_info_token_ref = ?";
			jdbcTemplate.update(deletesqlString,table_type_token);
			//添加新图片
			String result = MyUtil.addImgs(table_type_images, table_type_token, jdbcTemplate);
			System.out.println(result);
			if (result.equals("error")) {
				JSONObject object = new JSONObject();
				object.put("status", "2");
				object.put("mes", "图片参数错误，图片库没有所传图片！！！");
				return object.toString();
			}
			//修改信息
			System.out.println("table_type_token:"+table_type_token);
			System.out.println("department_id:"+department_id);
			//select * from snug_table_type where table_type_token = tabletype587cc7fdb and table_type_flag = '1' and department_id = 001
			String sqlString = "update snug_table_type set table_type_name=?,table_type_sub_name=?,table_type_sort=?,table_type_recommand=?,table_type_content=?,table_type_flag=? where table_type_token = ? and table_type_flag = '1' and department_id = ?";
			int rows = jdbcTemplate.update(sqlString, new Object[]{table_type_name,table_type_sub_name,table_type_sort,table_type_recommand,table_type_content,table_type_flag,table_type_token,department_id});
			return DBHelper.ReturnRows(rows);
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
  }
  
}
