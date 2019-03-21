package com.jcf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jcf.interfaces.DepartmentActionInterfaces;

@Controller
public class DepartmentActionController {
	@Autowired
	DepartmentActionInterfaces departmentActionInterfaces;
	//http://localhost:8080/snug/updateResturnant/?department_info_name=餐厅&department_info_token=&department_info_logo=5.jpg&department_info_detail=餐厅位居二楼&department_info_address=a栋3,4,5楼&department_info_phone=15700218161&department_info_plus=早市,8:00-10:00;晚市,16:00-18:00&department_info_logo=123&department_info_images_token=789
	@ResponseBody
	@RequestMapping(value="/updateResturnant",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String updateResturnant(String department_info_name,
			String department_info_detail,
			String department_info_address, String department_info_phone,
			String department_info_plus,String department_info_token,
			String department_info_logos,String department_info_images_token)
	{
		return	departmentActionInterfaces.updateResturnant(department_info_name, department_info_detail, department_info_address, department_info_phone, department_info_plus,department_info_token,department_info_logos,department_info_images_token);
	}
	
	//http://localhost:8080/snug/queryResMes
	@ResponseBody
	@RequestMapping(value="/queryResMes",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String queryResMes(String department_id)
	{
		return	departmentActionInterfaces.queryResMes(department_id);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/updateResturnantNoImgs",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public  String updateResturnantNoImgs(
			String department_info_name,
			String department_info_detail,
			String department_info_address,
			String department_info_phone,
			String department_info_plus,
			String department_info_token,
			String department_info_logo,
			String department_info_images_token
			)
	{
		return departmentActionInterfaces.updateResturnantNoImgs(department_info_name, department_info_detail, department_info_address, department_info_phone, department_info_plus, department_info_token, department_info_logo, department_info_images_token);
		
	};
	
}
