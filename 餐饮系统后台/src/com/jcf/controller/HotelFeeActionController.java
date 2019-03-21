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
import com.jcf.interfaces.HotelFeeAcitonInfaces;
import com.jcf.interfaces.OaAdminAcitonInfaces;
import com.jcf.interfaces.OaAttenceActionInfaces;
import com.jcf.interfaces.OaRoleAcitonInfaces;
@Controller
public class HotelFeeActionController {
	@Autowired
	 HotelFeeAcitonInfaces hotelFee;
	
	@ResponseBody
	@RequestMapping(value="/queryHotelRoomFeeList",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String queryHotelRoomFeeList(String hotel_room_info_name){	
		
		  return hotelFee.queryHotelFee(hotel_room_info_name);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/updateHotelFeeList",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String updateHotelFeeList(
			String hotel_room_info_name1,
			String hotel_fee_basic_money,
			String hotel_fee_money0,
			String hotel_fee_money1,
			String hotel_fee_money2,
			String hotel_room_info_name
			){	
		
		  return hotelFee.updateHotelFeeList(
				  hotel_room_info_name1,
				  hotel_fee_basic_money,
				  hotel_fee_money0, 
				  hotel_fee_money1, 
				  hotel_fee_money2, 
				  hotel_room_info_name);
	}
/*	@ResponseBody
	@RequestMapping(value="/insertHotelList",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String insertHotelList(String hotel_info_name,
			                      String  hotel_info_flag,
			                      String department_id
			){	
		
		  return hotel.queryinsertHotel(hotel_info_name,hotel_info_flag,department_id);
	}
	
	@ResponseBody
	@RequestMapping(value="/deletHotelFeeList",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String deletHotelFeeList(String hotel_info_name                  
			){	
		
		  return hotel.deletHotelList(hotel_info_name);
	}
	
	

	*/
}
	
	

