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
import com.jcf.interfaces.HotelAcitonInfaces;
import com.jcf.interfaces.HotelBuildAcitonInfaces;
import com.jcf.interfaces.OaAdminAcitonInfaces;
import com.jcf.interfaces.OaAttenceActionInfaces;
import com.jcf.interfaces.OaRoleAcitonInfaces;
@Controller
public class HotelBuildActionController {
	@Autowired
	HotelBuildAcitonInfaces hotelBuild;
	
	@ResponseBody
	@RequestMapping(value="/queryHotelBuildList",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String queryHotelBuildList(String hotel_building_info_name){	
		
		  return hotelBuild.queryHotelBuild(hotel_building_info_name);
	}
	
	@ResponseBody
	@RequestMapping(value="/insertHotelBuildList",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String insertHotelBuildList(
			String hotel_building_info_name,
			String hotel_building_info_flag,
			String hotel_info_name,
			String hotel_info_token,
			String department_id
			){	
		
		  return hotelBuild.insertHotelBuild(hotel_building_info_name, hotel_building_info_flag, hotel_info_name, hotel_info_token, department_id);
	}	
	
	@ResponseBody
	@RequestMapping(value="/deletHotelBuildList",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String deletHotelBuildList(String hotel_building_info_name                  
			){	
		
		  return hotelBuild.deletHotelBuild(hotel_building_info_name);
	}
	@ResponseBody
	@RequestMapping(value="/updateHotelBuildList",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String updateHotelBuildList(
			String hotel_building_info_name,
			String hotel_building_info_flag,
			String hotel_building_info_name1
			){	

		  return hotelBuild.updateHotelBuild(hotel_building_info_name, hotel_building_info_flag, hotel_building_info_name1);
	}
	
	
}
	
	

