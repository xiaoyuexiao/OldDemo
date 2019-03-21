package com.jcf.interfaces;

import java.util.ArrayList;
import java.util.Map;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;

/*
 *  @author:cht
 *  @time:2018/3/2
 *  @fun:分组(Hotel部分)
 *  @param:

 * 
 * */
public interface HotelRoomAcitonInfaces {

	public abstract String queryHotelRoom(String hotel_room_info_name);

	public abstract String insertHotelRoom(
			String hotel_room_info_name,
			String hotel_room_info_flag,
			String hotel_room_info_type,
			String hotel_room_info_floor,
			String hotel_room_info_numbers,
			String hotel_room_info_standard,
			String hotel_building_info_token,
			String department_id	
			);

	public abstract String deletHotelRoomList(String hotel_room_info_name);

	public abstract String updateHotelRoomList(
			String hotel_room_info_name1,
			String hotel_room_info_flag,
			String hotel_room_info_type,
			String hotel_room_info_floor,
			String hotel_room_info_numbers,
			String hotel_room_info_standard,
			String hotel_building_info_token,
			String department_id,
			String hotel_room_info_name
			);
}
