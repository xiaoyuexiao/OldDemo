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
import com.jcf.interfaces.OaAdminAcitonInfaces;
import com.jcf.interfaces.OaAttenceActionInfaces;
import com.jcf.interfaces.OaRoleAcitonInfaces;
@Controller
public class HotelActionController {
	@Autowired
	HotelAcitonInfaces hotel;
	
	@ResponseBody
	@RequestMapping(value="/queryHotelList",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String queryHotelList(String hotelName){	
		
		  return hotel.queryHotel(hotelName);
	}
	
	@ResponseBody
	@RequestMapping(value="/insertHotelList",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String insertHotelList(String hotel_info_name,
			                      String  hotel_info_flag,
			                      String department_id
			){	
		
		  return hotel.queryinsertHotel(hotel_info_name,hotel_info_flag,department_id);
	}
	
	@ResponseBody
	@RequestMapping(value="/deletHotelList",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String deletHotelList(String hotel_info_name                  
			){	
		
		  return hotel.deletHotelList(hotel_info_name);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/updateHotelList",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String updateHotelList(String hotel_info_name, 
			                      String hotel_info_flag,
			                      String hotel_info_name1
			){	
		
		  return hotel.updateHotelList(hotel_info_name,hotel_info_flag, hotel_info_name1);
	}
	
}
	
	

