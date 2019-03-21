package com.jcf.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jcf.interfaces.OaAdminAcitonInfaces;
import com.jcf.interfaces.OaRoleAcitonInfaces;
@Controller
public class OaAdminActionController {
	@Autowired
	OaAdminAcitonInfaces oaAdminAcitonInfaces;
	
	@ResponseBody
	@RequestMapping(value="/queryAdminList",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String queryAdminList(String admin_info_name,String page){	
		  return oaAdminAcitonInfaces.queryAdminList(admin_info_name,page);
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteAdminList",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String deleteAdminList(String admin_info_name){	
		
		  return oaAdminAcitonInfaces.deletAdminList(admin_info_name);
		}
	
	
	@ResponseBody
	@RequestMapping(value="/updateAdminList",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String updateAdminList(
		   @RequestParam("file") CommonsMultipartFile[] file, 
		   String admin_info_name,
 		   String admin_info_img,
 		   String admin_info_phone,
 		   String admin_info_birthday,
 		   String admin_info_idcard,
 		   String admin_info_pwd,
 		   String admin_info_flag,
 		   String department_id,
 		   String admin_info_role,
 		   String admin_info_master,
 		   String sub_department_id
 		   ){		
		  return oaAdminAcitonInfaces.updateAdminList(file, admin_info_name,admin_info_phone, admin_info_birthday, admin_info_idcard, admin_info_pwd, admin_info_flag, department_id, admin_info_role, admin_info_master,sub_department_id);
		}
	
	@ResponseBody
	@RequestMapping(value="/insertAdminList",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
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
  		   ){		
		  return oaAdminAcitonInfaces.insertAdminList(file,admin_info_name, admin_info_phone, admin_info_birthday, admin_info_idcard, admin_info_pwd, admin_info_flag, department_id, admin_info_role, admin_info_master,sub_department_id);
		}
	
	@ResponseBody
	@RequestMapping(value="/loginAdminList",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String loginAdminList
	(String admin_info_name,
     String admin_info_pwd)
	{	
    return oaAdminAcitonInfaces.loginAdminList(admin_info_name, admin_info_pwd);
    }
	
}
	
	

