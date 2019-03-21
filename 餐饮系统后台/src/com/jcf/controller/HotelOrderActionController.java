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
import com.jcf.interfaces.HotelOrderAcitonInfaces;
import com.jcf.interfaces.OaAdminAcitonInfaces;
import com.jcf.interfaces.OaAttenceActionInfaces;
import com.jcf.interfaces.OaRoleAcitonInfaces;
@Controller
public class HotelOrderActionController {
	@Autowired
	HotelOrderAcitonInfaces hotelOrder;
	
	
	
	
	
	
	
	@ResponseBody
	@RequestMapping(value="/queryHotelOrderList",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String queryHotelOrderList(String hotel_order_info_guest_name,
			                          String hotel_order_info_tuan_name,
			                          String hotel_order_info_guest_phone
			){	
		  return hotelOrder.queryHotelOrder(hotel_order_info_guest_name, hotel_order_info_tuan_name, hotel_order_info_guest_phone);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/deletHotelOrder",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String deletHotelOrder(
			String hotel_order_info_guest_name,
			String hotel_order_info_tuan_name                 
			){	
		  System.out.println(hotel_order_info_guest_name);
		  System.out.println(hotel_order_info_tuan_name);
		  return hotelOrder.deletHotelOrder(hotel_order_info_guest_name,hotel_order_info_tuan_name);
	}
	
	@ResponseBody
	@RequestMapping(value="/insertHotelOrder",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String insertHotelOrder(String hotel_order_info_flag,
			String hotel_order_info_guest_name, 
			String hotel_order_info_tuan_name,
			String hotel_order_info_guest_phone,
			String hotel_order_info_arrive_time0, 
			String hotel_order_info_leave_time0, 
			String hotel_order_info_leave_time1, 
			String hotel_order_info_arrive_time1, 
			String hotel_order_info_leave_room_number,
			String hotel_order_info_leave_room_info,
			String hotel_order_info_channel,
			String hotel_order_info_total_fee,
			String hotel_order_info_pre_fee, 
			String hotel_order_info_status,
			String hotel_order_info_id_type,
			String hotel_order_info_id_info,
			String hotel_order_info_other_cast_info, 
			String hotel_order_info_other_cast_fee,
			String department_id,
			String hotel_order_info_food_ticket
			){	
		
		  return hotelOrder.insertHotelOrder(hotel_order_info_flag, hotel_order_info_guest_name, hotel_order_info_tuan_name, hotel_order_info_guest_phone, hotel_order_info_arrive_time0, hotel_order_info_leave_time0, hotel_order_info_leave_time1, hotel_order_info_arrive_time1, hotel_order_info_leave_room_number, hotel_order_info_leave_room_info, hotel_order_info_channel, hotel_order_info_total_fee, hotel_order_info_pre_fee, hotel_order_info_status, hotel_order_info_id_type, hotel_order_info_id_info, hotel_order_info_other_cast_info, hotel_order_info_other_cast_fee, department_id, hotel_order_info_food_ticket);
	}
	
	@ResponseBody
	@RequestMapping(value="/updateHotelOrder",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String updateHotelOrder(
			String hotel_order_info_flag,
			String hotel_order_info_guest_name1, 
			String hotel_order_info_tuan_name1,
			String hotel_order_info_guest_phone, 
			String hotel_order_info_arrive_time0, 
			String hotel_order_info_leave_time0, 
			String hotel_order_info_leave_time1, 
			String hotel_order_info_arrive_time1, 
			String hotel_order_info_leave_room_number, 
			String hotel_order_info_leave_room_info, 
			String hotel_order_info_channel, 
			String hotel_order_info_total_fee, 
			String hotel_order_info_pre_fee, 
			String hotel_order_info_status, 
			String hotel_order_info_id_type, 
			String hotel_order_info_id_info, 
			String hotel_order_info_other_cast_info, 
			String hotel_order_info_other_cast_fee,
			String hotel_order_info_food_ticket,
			String hotel_order_info_guest_name, 
			String hotel_order_info_tuan_name
			){	
		
		  return hotelOrder.updateHotelOrder(hotel_order_info_flag, hotel_order_info_guest_name1, hotel_order_info_tuan_name1, hotel_order_info_guest_phone, hotel_order_info_arrive_time0, hotel_order_info_leave_time0, hotel_order_info_leave_time1, hotel_order_info_arrive_time1, hotel_order_info_leave_room_number, hotel_order_info_leave_room_info, hotel_order_info_channel, hotel_order_info_total_fee, hotel_order_info_pre_fee, hotel_order_info_status, hotel_order_info_id_type, hotel_order_info_id_info, hotel_order_info_other_cast_info, hotel_order_info_other_cast_fee, hotel_order_info_food_ticket, hotel_order_info_guest_name, hotel_order_info_tuan_name);
	}

}
	
	

