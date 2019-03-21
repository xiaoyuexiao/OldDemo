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
import com.jcf.interfaces.OaAttenceActionInfaces;
import com.jcf.interfaces.OaRoleAcitonInfaces;
@Controller
public class OaAttenceActionController {
	@Autowired
	OaAttenceActionInfaces attence;
	
	@ResponseBody
	@RequestMapping(value="/queryAttenceList",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String queryAttenceList(String attence_group_name,String page){	
		
		  return attence.queryAttenceList(attence_group_name,page);
	}
	@ResponseBody
	@RequestMapping(value="/deleteAttenceList",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String deleteAttenceList(String attence_group_name){	
		
		  return attence.deleteAttenceList(attence_group_name);
	}
	
	@ResponseBody
	@RequestMapping(value="/insertAttenceList",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String insertAttenceList(
			String attence_group_name,
			String attence_group_flag, 
			String attence_group_type, 
			String attence_group_enclude,
			String attence_group_exclude, 
			String attence_group_master,
			String attence_group_workday, 
			String attence_group_restday, 
			String attence_group_wifilist,
			String department_id
			
			){	
		  return attence.insertAttenceList(
				  attence_group_name,
				  attence_group_flag, 
				  attence_group_type, 
				  attence_group_enclude,
				  attence_group_exclude,
				  attence_group_master,
				  attence_group_workday, 
				  attence_group_restday,
				  attence_group_wifilist,
				  department_id
				  );
	}
	@ResponseBody
	@RequestMapping(value="/updateAttenceList",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String updateAttenceList(
			String attence_group_name,
			String attence_group_flag, 
			String attence_group_type, 
			String attence_group_enclude,
			String attence_group_exclude, 
			String attence_group_master,
			String attence_group_workday, 
			String attence_group_restday, 
			String attence_group_wifilist,
			String department_id
			){	
		  return attence.updateAttenceList
				  (attence_group_name,
				  attence_group_flag,
				  attence_group_type,
				  attence_group_enclude,
				  attence_group_exclude,
				  attence_group_master,
				  attence_group_workday, 
				  attence_group_restday,
				  attence_group_wifilist,
				  department_id);
	}
	
	
}
	
	

