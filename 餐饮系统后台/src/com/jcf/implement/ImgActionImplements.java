package com.jcf.implement;

import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jcf.interfaces.ImgActionInterfaces;
import com.jcf.util.DBHelper;
import com.jcf.util.MyUtil;
//http://localhost:8080/snug/upImg?token=100
@Service
public class ImgActionImplements implements ImgActionInterfaces {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public String upImg(
			CommonsMultipartFile[] file,
			String total_image_type,
			String department_id) 
	{
		if (jdbcTemplate != null) 
		{
			if(file.length==0)
			{
				JSONObject object = new JSONObject();
				try {
					object.put("status", "2");
					object.put("mes", "没有图片！！");
				}
				catch (JSONException e)
				{
					e.printStackTrace();
				}
				return object.toString();
			}
			else
			{
				//上传图片
				for (int i = 0; i < file.length; i++)
				{
					String image_name = MyUtil.Upimg(file[i]);
					String old_name = file[i].getOriginalFilename();
					System.out.println("old_name:" + old_name);
					//存数据库
					String sqlString = "insert snug_total_image(" +
							"total_image_name,total_image_old_name,total_image_type,department_id) values(?,?,?,?)";
					int rows = jdbcTemplate.update(sqlString, new Object[]{image_name,old_name,total_image_type,department_id});
					if (rows==0)
					{
						JSONObject object = new JSONObject();
						try {
							object.put("status", "2");
							object.put("mes", "该图片上传失败！！");
						} catch (JSONException e) {
							e.printStackTrace();
						}
						return object.toString();
					}
				}
				JSONObject object = new JSONObject();
				try {
					object.put("status", "1");
					object.put("mes", "success");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				return object.toString();
			}
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}

	@Override
	public String deleteImg(String image_name) {
		if (jdbcTemplate != null) 
		{
			String sqlString = "update snug_total_image set total_image_flag = '0' where total_image_name = ?";
			int rows = jdbcTemplate.update(sqlString,image_name);
			return DBHelper.ReturnRows(rows);
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}

	@Override
	public String queryImages() {
		if (jdbcTemplate != null) 
		{
			String sqlString = "select * from snug_total_image where total_image_flag = '1'";
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlString);
			return DBHelper.ReturnList(list);
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}
	
}
