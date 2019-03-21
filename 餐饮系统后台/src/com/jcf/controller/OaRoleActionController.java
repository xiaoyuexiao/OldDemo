package com.jcf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jcf.interfaces.OaRoleAcitonInfaces;
@Controller
public class OaRoleActionController {
	@Autowired
	OaRoleAcitonInfaces oaRoleAcitonInfaces;
	
	@ResponseBody
	@RequestMapping(value="/queryOaRoleList",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String queryOaRoleList(String sub_department_id,String page){	
		  return oaRoleAcitonInfaces.queryRoleList(sub_department_id,page);
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteRoleList",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String deleteRoleList(String role_info_token){		
		  return oaRoleAcitonInfaces.deletRoleList(role_info_token);
		}
	
	
	@ResponseBody
	@RequestMapping(value="/updateRoleList",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String updateRoleList(String role_info_token, String role_info_flag,
			String role_info_type, String role_info_name, String operator_id,
			String department_id,String sub_department_id){		
		  return oaRoleAcitonInfaces.updateRoleList(role_info_token, role_info_flag, role_info_type, role_info_name, operator_id, department_id, sub_department_id);
		}
	
	@ResponseBody
	@RequestMapping(value="/insertRoleList",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String insertRoleList(
			String role_info_token,
			String role_info_flag,
			String role_info_type,
			String role_info_name,
			String operator_id,
			String department_id,
			String sub_department_id
			){		
		  return oaRoleAcitonInfaces.insertRoleList(role_info_flag, role_info_type, role_info_name, operator_id, department_id, sub_department_id);
		}
	
	}
	
	

