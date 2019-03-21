package com.jcf.implement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.jcf.interfaces.OaAdminAcitonInfaces;
import com.jcf.interfaces.OaRoleAcitonInfaces;
import com.jcf.util.DBHelper;
import com.jcf.util.Limit;
import com.jcf.util.MyUtil;

@Service
public class OaAdminActionImpments implements OaAdminAcitonInfaces {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	public String queryAdminList(String admin_info_name,String page) {
		if (jdbcTemplate != null) {
			int pagesize=2;
			int pageIndex=Limit.QueryLimit(page, pagesize);
			if (admin_info_name==null) {
          	String sqlString = "SELECT * FROM snug_admin_info limit ?,?";
				List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlString,new Object[]{
						pageIndex,pagesize			
				});
				return DBHelper.ReturnList(list);
			} else {
				String sqlString = "SELECT * FROM snug_admin_info where admin_info_name=? limit ?,?";
				List<Map<String, Object>> list = jdbcTemplate.queryForList(
						sqlString, new Object[] { admin_info_name,pageIndex,pagesize});
				return DBHelper.ReturnList(list);
			}
			
		} else {
			return DBHelper.FailConnectDatabase();
		}
	}

	
	public String deletAdminList(String admin_info_name) {
		if (jdbcTemplate != null) {
			System.out.println(admin_info_name);
			String sqlString = "update snug_admin_info set admin_info_flag=0 WHERE admin_info_name=?";
			int rows = jdbcTemplate.update(sqlString,new Object[] {admin_info_name});
			return DBHelper.ReturnRows(rows);
		} else {
			return DBHelper.ReturnRows(0);
		}

	}

	
	public String updateAdminList(
			@RequestParam("file") CommonsMultipartFile[] file, 
			String admin_info_name,
			String admin_info_phone,
			String admin_info_birthday,
			String admin_info_idcard,
			String admin_info_pwd,
			String admin_info_flag,
			String department_id,
			String admin_info_role,
			String admin_info_master,
			String sub_department_id

	) {
		String admin_info_pwds=MyUtil.ecodeByMD5(admin_info_pwd);
		String newfilename = new Date().getTime()+file[0].getOriginalFilename();
		String company_info_token = "company" + MyUtil.GetToken().substring(0,9);
		String result = MyUtil.Upimgs(file,company_info_token, jdbcTemplate);
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
		String sqlString = "update snug_admin_info set admin_info_name=?,admin_info_img=?,admin_info_phone=?,admin_info_birthday=?,admin_info_idcard=?,admin_info_pwd=?,admin_info_flag=?,department_id=?,admin_info_role=?,admin_info_master=?,sub_department_id=?  where admin_info_name=?";
		int rows = jdbcTemplate.update(sqlString, new Object[]
				{
				admin_info_name,
				newfilename,
				admin_info_phone,
				admin_info_birthday,
				admin_info_idcard,
				admin_info_pwds,
				admin_info_flag,
				department_id,
				admin_info_role,
				admin_info_master,
				sub_department_id,
				admin_info_name
				}
		);
		
		return DBHelper.ReturnRows(rows);
	}

	public String insertAdminList(
			@RequestParam("file") CommonsMultipartFile[] file,
			String admin_info_name,
			String admin_info_phone,
			String admin_info_birthday,
			String admin_info_idcard,
			String admin_info_pwd,
			String admin_info_flag,
			String department_id,
			String admin_info_role,
			String admin_info_master,
			String sub_department_id
			) {
		
		if (jdbcTemplate != null) {
			String admin_info_pwds=MyUtil.ecodeByMD5(admin_info_pwd);
			String newfilename = new Date().getTime()+file[0].getOriginalFilename();
			String company_info_token = "company" + MyUtil.GetToken().substring(0,9);
			String result = MyUtil.Upimgs(file,company_info_token, jdbcTemplate);
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
			
			
			String sqlString = "insert into snug_admin_info (admin_info_name,admin_info_img,admin_info_phone,admin_info_birthday,admin_info_idcard,admin_info_pwd,admin_info_flag,department_id,admin_info_role,admin_info_master,sub_department_id)values(?,?,?,?,?,?,?,?,?,?,?)";
			int rows = jdbcTemplate.update(sqlString, new Object[] {
			         admin_info_name,
			         newfilename,
					 admin_info_phone,
					 admin_info_birthday,
					 admin_info_idcard,
					 admin_info_pwds,
					 admin_info_flag,
					 department_id,
					 admin_info_role,
					 admin_info_master,
					 sub_department_id
					}
			);

			return DBHelper.ReturnRows(rows);
		}else {
			return DBHelper.ReturnRows(0);
		}
		

	}
	//登录
	public String loginAdminList(String admin_info_name, String admin_info_pwd) {
		if (jdbcTemplate != null)
		{
			String admin_info_pwds =MyUtil.ecodeByMD5(admin_info_pwd);
			List<Map<String, Object>> listName =jdbcTemplate.queryForList("SELECT * FROM snug_admin_info where admin_info_name=?",new Object[]{admin_info_name});
			List<Map<String, Object>> listPwds =jdbcTemplate.queryForList("SELECT * FROM snug_admin_info where admin_info_pwd=?",new Object[]{admin_info_pwds});
            

            if(listName.size()==0)
            {
            	JSONObject object1= new JSONObject();
        		object1.put("status", "1");
        		object1.put("msg", "账号错误或不存在");
        		return object1.toString();
			}
            else if(listPwds.size()==0) 
			{
              	JSONObject object1 = new JSONObject();
        		object1.put("status", "2");
        		object1.put("msg", "密码错误");
        		return object1.toString();
			}else {
				
				String sqlString = "SELECT * FROM snug_admin_info where admin_info_name=? and admin_info_pwd=?";
				List<Map<String, Object>> list = jdbcTemplate.queryForList(
						sqlString, new Object[] { admin_info_name ,admin_info_pwds});
				return DBHelper.ReturnList(list);	
			}
	
		} else {
			return DBHelper.FailConnectDatabase();
		}
	}
    
}
