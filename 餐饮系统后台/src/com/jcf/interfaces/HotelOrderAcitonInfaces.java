package com.jcf.interfaces;





/*
 *  @author:cht
 *  @time:2018/3/2
 *  @fun:分组(Hotel部分)
 *  @param:

 * 
 * */
public interface HotelOrderAcitonInfaces {

	public abstract String queryHotelOrder(
			String hotel_order_info_guest_name,
			String hotel_order_info_tuan_name,
			String hotel_order_info_guest_phone
			);

	public abstract String insertHotelOrder(
			String hotel_order_info_flag,
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
			);

	public abstract String deletHotelOrder(
			String hotel_order_info_guest_name,
			String hotel_order_info_tuan_name
			);

	public abstract String updateHotelOrder(
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
			);
}
