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
import com.jcf.interfaces.HotelRoomAcitonInfaces;
import com.jcf.interfaces.OaAdminAcitonInfaces;
import com.jcf.interfaces.OaAttenceActionInfaces;
import com.jcf.interfaces.OaRoleAcitonInfaces;
@Controller
public class HotelRoomActionController {
	@Autowired
	HotelRoomAcitonInfaces hotelRoom;
	
	@ResponseBody
	@RequestMapping(value="/queryHotelRoomList",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String queryHotelRoomList(String hotel_room_info_name){	
		
		  return hotelRoom.queryHotelRoom(hotel_room_info_name);
	}
	
	
	
	@ResponseBody
	@RequestMapping(value="/deletHotelRoomList",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String deletHotelRoomList(String hotel_room_info_name                  
			){	
		
		  return hotelRoom.deletHotelRoomList(hotel_room_info_name);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/insertHotelRoomList",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String insertHotelRoomList(
			String hotel_room_info_name,
			String hotel_room_info_flag, 
			String hotel_room_info_type, 
			String hotel_room_info_floor,
			String hotel_room_info_numbers, 
			String hotel_room_info_standard,
			String hotel_building_info_token,
			String department_id
			){	
		
		  return hotelRoom.insertHotelRoom(hotel_room_info_name, hotel_room_info_flag, hotel_room_info_type, hotel_room_info_floor, hotel_room_info_numbers, hotel_room_info_standard, hotel_building_info_token, department_id);
	}
	
	
	
	@ResponseBody
	@RequestMapping(value="/updateHotelRoomList",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String updateHotelRoomList(
			String hotel_room_info_name1,
			String hotel_room_info_flag,
			String hotel_room_info_type,
			String hotel_room_info_floor,
			String hotel_room_info_numbers,
			String hotel_room_info_standard,
			String hotel_building_info_token,
			String department_id,
			String hotel_room_info_name
			){	
		
		  return hotelRoom.updateHotelRoomList(
				  hotel_room_info_name1,
				  hotel_room_info_flag,
				  hotel_room_info_type,
				  hotel_room_info_floor,
				  hotel_room_info_numbers,
				  hotel_room_info_standard,
				  hotel_building_info_token,
				  department_id,
				  hotel_room_info_name
				  );
	}
	
}
	
	

