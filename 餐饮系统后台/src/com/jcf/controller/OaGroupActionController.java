package com.jcf.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jcf.interfaces.OaAdminAcitonInfaces;
import com.jcf.interfaces.OaGroupAcitonInfaces;
import com.jcf.interfaces.OaRoleAcitonInfaces;
@Controller
public class OaGroupActionController {
	@Autowired
	OaGroupAcitonInfaces oaGroupAcitonInfaces;
	
	@ResponseBody
	@RequestMapping(value="/queryGroupAdminList",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String queryGroupAdminList(String department_id,String role_info_type,String sub_department_id){	
	   System.out.println(department_id+role_info_type+sub_department_id);
		return oaGroupAcitonInfaces.queryGroupList(department_id,role_info_type,sub_department_id);
	
	
	}

}
	
	

