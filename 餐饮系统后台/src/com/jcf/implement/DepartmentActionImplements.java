package com.jcf.implement;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jcf.interfaces.DepartmentActionInterfaces;
import com.jcf.util.DBHelper;
import com.jcf.util.MyUtil;
@Service
public class DepartmentActionImplements implements DepartmentActionInterfaces {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Override
	public String updateResturnant(
			String department_info_name,String department_info_detail,
			String department_info_address, String department_info_phone,
			String department_info_plus,String department_info_token,
			String department_info_logos,String department_info_images_token) {
		if (jdbcTemplate!=null)
		{
			//根据传过来的token数值是否为为空判断该部门是否创建。
			if (department_info_token.isEmpty())
			{
				//分别得到token值
				String token = "resturnant" + MyUtil.GetToken().substring(0,9);//餐厅的token值,以及图片的名称前缀
				String tokenlogo = token +"_logo";//logo的名称
				//分别上传图片
				String result = MyUtil.addImgs(department_info_images_token, token, jdbcTemplate);
				String result1 = MyUtil.addImgs(department_info_logos, tokenlogo, jdbcTemplate);
				if (result.equals("error"))
				{
					JSONObject object = new JSONObject();
					object.put("status", "2");
					object.put("mes", "图片参数错误，图片库没有所传图片！！！");
					return object.toString();
				}
				if (result1.equals("error"))
				{
					JSONObject object = new JSONObject();
					object.put("status", "2");
					object.put("mes", "logo参数错误，图片库没有所传图片！！！");
					return object.toString();
				}
				String sql = "insert into snug_department_info(department_info_name,department_info_logo," +
						"department_info_detail,department_info_address," +
						"department_info_phone,department_info_plus," +
						"department_info_token) values(?,?,?,?,?,?,?)";
				
				int rows = jdbcTemplate.update(sql,new Object[]{
						department_info_name,tokenlogo,
						department_info_detail,department_info_address,
						department_info_phone,department_info_plus,
						token
						});
				return DBHelper.ReturnRows(rows);
			}
			else
			{
				//判断是否存在此部门
				
				//删除以前的logn和images
				//String logo[] = department_info_logos.split(",");
				//String images[] = department_info_images_token.split(",");
				String delete_logo_or_images = "update snug_images_info set image_info_flag = '0' where image_info_token_ref = ?";
				jdbcTemplate.update(delete_logo_or_images,department_info_token);
				jdbcTemplate.update(delete_logo_or_images,department_info_token +"_logo");

				MyUtil.addImgs(department_info_images_token, department_info_token, jdbcTemplate);
				MyUtil.addImgs(department_info_logos, department_info_token+"_logo", jdbcTemplate);
				
				String sql = "update snug_department_info set department_info_name =?," +
						"department_info_detail=?,department_info_address=?," +
						"department_info_phone=?,department_info_plus=?" +
						" where department_info_token =?";
				
				int rows = jdbcTemplate.update(sql,new Object[]{
						department_info_name,
						department_info_detail,department_info_address,
						department_info_phone,department_info_plus,department_info_token
						});
				return DBHelper.ReturnRows(rows);
			}
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}
	@Override
	public String queryResMes(String department_id) {
		if (jdbcTemplate!=null)
		{
			String sqlString = "select " +
					"department_info_name," +
					"department_info_detail," +
					"department_info_address," +
					"department_info_phone," +
					"department_info_plus, " +
					"department_info_token" +
					" from snug_department_info where department_info_flag='1' and company_id = ?";
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlString,department_id);
			JSONArray array =  MyUtil.getJsonArray(list);
			//得到餐厅的token值
			String restoken = null;
			for (Map<String, Object> map : list)
			{
				if (restoken!=null)
				{
					break;
				}
				for (Map.Entry<String, Object> entry : map.entrySet())
				{
					if (entry.getKey().equals("department_info_token"))
					{
						String valueString = (String) entry.getValue();
						//System.out.println(valueString.substring(0,valueString.length()-9));
						if (valueString.substring(0,valueString.length()-9).equals("resturnant")) {
							restoken = (String) entry.getValue();
							break;
						}
					}
				}
			}
			//查询图片
			String getimg = "select image_info_name,image_info_token_ref from snug_images_info where image_info_token_ref = ? and image_info_flag = '1'";
			List<Map<String, Object>> imglist = jdbcTemplate.queryForList(getimg,restoken);
			JSONArray imgArray = MyUtil.getJsonArray(imglist);
			//查询logo
			String getlogo = "select image_info_name,image_info_token_ref from snug_images_info where image_info_token_ref = ? and image_info_flag = '1'";
			List<Map<String, Object>> reslogo = jdbcTemplate.queryForList(getlogo,restoken+"_logo");
			JSONArray logoArray = MyUtil.getJsonArray(reslogo);
			
			
			JSONObject object = new JSONObject();
			object.put("status", "1");
			object.put("mes", "success");
			object.put("info", array);
			object.put("imgs",imgArray);
			object.put("logo", logoArray);
			return object.toString();
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}
	@Override
	public String updateResturnantNoImgs(
			String department_info_name,
			String department_info_detail, String department_info_address,
			String department_info_phone, String department_info_plus,
			String department_info_token, String department_info_logo,
			String department_info_images_token){
		
		if (jdbcTemplate!=null)
		{
			System.out.println("department_info_name:"+department_info_name);
			System.out.println("department_info_detail:"+department_info_detail);
			System.out.println("department_info_address:"+department_info_address);
			System.out.println("department_info_phone:"+department_info_phone);
			System.out.println("department_info_plus:"+department_info_plus);
			System.out.println("department_info_token:"+department_info_token);
			System.out.println("department_info_logo:"+department_info_logo);
			System.out.println("department_info_images_token:"+department_info_images_token);
			//查询是否有餐厅的基础设置
			if (department_info_token.isEmpty())
			{
				//分别得到token值
				String token = "resturnant" + MyUtil.GetToken().substring(0,9);//餐厅的token值,以及图片的名称前缀
				String tokenlogo = "resturnant_logo" + MyUtil.GetToken().substring(0,9);//logo的名称
				//分别上传图片
				
				String sql = "insert into snug_department_info(department_info_name,department_info_logo," +
						"department_info_detail,department_info_address," +
						"department_info_phone,department_info_plus," +
						"department_info_token) values(?,?,?,?,?,?,?)";
				
				int rows = jdbcTemplate.update(sql,new Object[]{
						department_info_name,tokenlogo,
						department_info_detail,department_info_address,
						department_info_phone,department_info_plus,
						token
						});
				return DBHelper.ReturnRows(rows);
			}
			else
			{
				//修改logo图片,删除以前的logn
				//String deletelogn = "update snug_images_info set image_info_flag = '0' where image_info_token_ref = ?";
				//jdbcTemplate.update(deletelogn,department_info_logo);
				
				String logn[] = department_info_logo.split(",");
				for (int i = 0; i < logn.length; i++) {
					String deleteimages = "update snug_images_info set image_info_flag = '0' where image_info_token_ref = ?";
					jdbcTemplate.update(deleteimages,logn[i]);
				}
				
				
				//String tokenlogo = "resturnant_logo" + MyUtil.GetToken().substring(0,9);//logo的名称
				//修改图片，删除要修改的图片
				String imgs[] = department_info_images_token.split(",");
				for (int i = 0; i < imgs.length; i++)
				{
					String deleteimages = "update snug_images_info set image_info_flag = '0' where image_info_token_ref = ?";
					jdbcTemplate.update(deleteimages,imgs[i]);
				}
				
				
				String sql = "update snug_department_info set department_info_name =?," +
						"department_info_detail=?,department_info_address=?," +
						"department_info_phone=?,department_info_plus=?" +
						" where department_info_token =?";
				
				int rows = jdbcTemplate.update(sql,new Object[]{
						department_info_name,
						department_info_detail,department_info_address,
						department_info_phone,department_info_plus,department_info_token
						});
				
				return DBHelper.ReturnRows(rows);
			}
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}

}
