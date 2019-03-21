package com.jcf.implement;

import java.util.ArrayList;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.jcf.interfaces.HotelOrderAcitonInfaces;
import com.jcf.util.DBHelper;
import com.jcf.util.MyUtil;


@Service
public class HotelOrderActionImpments implements HotelOrderAcitonInfaces {

	@Autowired
	JdbcTemplate jdbcTemplate;
	private ArrayList<Map<String, Object>> list;
	private int rows;

	@Override
	public String queryHotelOrder(
			String hotel_order_info_guest_name,
			String hotel_order_info_tuan_name,
			String hotel_order_info_guest_phone) {
		if (jdbcTemplate != null) {
			
			if(hotel_order_info_guest_name==null&&hotel_order_info_tuan_name==null&&hotel_order_info_guest_phone==null){
				String sql="select * from snug_hotel_order_info";
				list = (ArrayList<Map<String, Object>>) jdbcTemplate.queryForList(sql);			
				return	DBHelper.ReturnList(list);

			}else if(hotel_order_info_guest_name!=null){
				String sql="select * from snug_hotel_order_info where hotel_order_info_guest_name=?";
				ArrayList<Map<String, Object>> list=(ArrayList<Map<String, Object>>) jdbcTemplate.queryForList(sql,new Object[]{hotel_order_info_guest_name});
				return	DBHelper.ReturnList(list);
			}else if(hotel_order_info_tuan_name!=null){
				
				String sql="select * from snug_hotel_order_info where hotel_order_info_tuan_name=?";
				ArrayList<Map<String, Object>> list=(ArrayList<Map<String, Object>>) jdbcTemplate.queryForList(sql,new Object[]{hotel_order_info_tuan_name});
				return	DBHelper.ReturnList(list);
				
			}else if(hotel_order_info_guest_phone!=null){
				String sql="select * from snug_hotel_order_info where hotel_order_info_guest_phone=?";
				ArrayList<Map<String, Object>> list=(ArrayList<Map<String, Object>>) jdbcTemplate.queryForList(sql,new Object[]{hotel_order_info_guest_phone});
				return	DBHelper.ReturnList(list);
			}
			
			
		}
		return "";
	}

	@Override
	public String insertHotelOrder(
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
			)
	   {
		
		if (jdbcTemplate != null) {
		String hotel_order_info_token = "company" + MyUtil.GetToken().substring(0,9);
		System.out.println(hotel_order_info_arrive_time0+hotel_order_info_leave_time0+hotel_order_info_leave_time1+hotel_order_info_arrive_time1);
		if(hotel_order_info_guest_name!=null){   //单人订单			
			String sql="insert into snug_hotel_order_info("+
					"hotel_order_info_flag,"+
					"hotel_order_info_token,"+
					"hotel_order_info_guest_name,"+
					"hotel_order_info_guest_phone,"+ 
					"hotel_order_info_arrive_time0,"+ 
					"hotel_order_info_leave_time0,"+ 
					"hotel_order_info_leave_room_number,"+ 
					"hotel_order_info_leave_room_info,"+ 
					"hotel_order_info_channel,"+ 
					"hotel_order_info_total_fee,"+ 
					"hotel_order_info_pre_fee,"+ 
					"hotel_order_info_status,"+ 
					"hotel_order_info_id_type,"+ 
					"hotel_order_info_id_info,"+ 
					"hotel_order_info_other_cast_info,"+ 
					"hotel_order_info_other_cast_fee,"+ 
					"department_id,"+ 
					"hotel_order_info_food_ticket)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			       
			   rows = jdbcTemplate.update(sql,new Object[]{
			        		hotel_order_info_flag,
			        		hotel_order_info_token,
			        		hotel_order_info_guest_name,
			        		hotel_order_info_guest_phone,
			        		hotel_order_info_arrive_time0, 
			    			hotel_order_info_leave_time0,
			        		hotel_order_info_leave_room_number, 
			        		hotel_order_info_leave_room_info, 
			        		hotel_order_info_channel, 
			        		hotel_order_info_total_fee, 
			        		hotel_order_info_pre_fee, 
			        		hotel_order_info_status, 
			        		hotel_order_info_id_type, 
			        		hotel_order_info_id_info, 
			        		hotel_order_info_other_cast_info, 
			        		hotel_order_info_other_cast_fee, 
			        		department_id, 
			        		hotel_order_info_food_ticket});
		     } else if(hotel_order_info_tuan_name!=null){
		    	 String sql="insert into snug_hotel_order_info("+
							"hotel_order_info_flag,"+
							"hotel_order_info_token,"+
							"hotel_order_info_tuan_name,"+
							"hotel_order_info_guest_phone,"+ 
							"hotel_order_info_leave_time1,"+ 
							"hotel_order_info_arrive_time1,"+ 
							"hotel_order_info_leave_room_number,"+ 
							"hotel_order_info_leave_room_info,"+ 
							"hotel_order_info_channel,"+ 
							"hotel_order_info_total_fee,"+ 
							"hotel_order_info_pre_fee,"+ 
							"hotel_order_info_status,"+ 
							"hotel_order_info_id_type,"+ 
							"hotel_order_info_id_info,"+ 
							"hotel_order_info_other_cast_info,"+ 
							"hotel_order_info_other_cast_fee,"+ 
							"department_id,"+ 
							"hotel_order_info_food_ticket)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					        rows = jdbcTemplate.update(sql,new Object[]{
					        		hotel_order_info_flag,
					        		hotel_order_info_token,
					        		hotel_order_info_tuan_name,
					        		hotel_order_info_guest_phone,
					        		hotel_order_info_leave_time1, 
					        		hotel_order_info_arrive_time1, 
					        		hotel_order_info_leave_room_number, 
					        		hotel_order_info_leave_room_info, 
					        		hotel_order_info_channel, 
					        		hotel_order_info_total_fee, 
					        		hotel_order_info_pre_fee, 
					        		hotel_order_info_status, 
					        		hotel_order_info_id_type, 
					        		hotel_order_info_id_info, 
					        		hotel_order_info_other_cast_info, 
					        		hotel_order_info_other_cast_fee, 
					        		department_id, 
					        		hotel_order_info_food_ticket});
			}
		}
		return DBHelper.ReturnRows(rows);

	}

	@Override
	public String deletHotelOrder(
			String hotel_order_info_guest_name,
			String hotel_order_info_tuan_name) {
		if (jdbcTemplate != null) {
			if (hotel_order_info_guest_name != null) {
				String sql = "update snug_hotel_order_info set hotel_order_info_flag=0 WHERE hotel_order_info_guest_name=? ";
				rows = jdbcTemplate.update(sql,
						new Object[] { hotel_order_info_guest_name });
				return DBHelper.ReturnRows(rows);
			}else if (hotel_order_info_tuan_name != null) {
				String sql = "update snug_hotel_order_info set hotel_order_info_flag=0 WHERE hotel_order_info_tuan_name=? ";
				rows = jdbcTemplate.update(sql,
						new Object[] { hotel_order_info_tuan_name });
				return DBHelper.ReturnRows(rows);

			}
	    }
		return DBHelper.ReturnRows(rows);
		
	}
	@Override
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
			) {
		if (jdbcTemplate != null) { 
			String sql="update snug_hotel_order_info set hotel_order_info_flag=?,hotel_order_info_guest_name=?,hotel_order_info_tuan_name=?,hotel_order_info_guest_phone=?,hotel_order_info_arrive_time0=?,hotel_order_info_leave_time0=?,hotel_order_info_leave_time1=?,hotel_order_info_arrive_time1=?,hotel_order_info_leave_room_number=?,hotel_order_info_leave_room_info=?,hotel_order_info_channel=?,hotel_order_info_total_fee=?,hotel_order_info_pre_fee=?,hotel_order_info_status=?,hotel_order_info_id_type=?,hotel_order_info_id_info=?,hotel_order_info_other_cast_info=?,hotel_order_info_other_cast_fee=?,hotel_order_info_food_ticket=? WHERE hotel_order_info_guest_name=? or hotel_order_info_tuan_name=?";
			rows=jdbcTemplate.update(sql,new Object[]{
					 hotel_order_info_flag,
					 hotel_order_info_guest_name1, 
					 hotel_order_info_tuan_name1,
					 hotel_order_info_guest_phone, 
					 hotel_order_info_arrive_time0, 
					 hotel_order_info_leave_time0, 
					 hotel_order_info_leave_time1, 
					 hotel_order_info_arrive_time1, 
					 hotel_order_info_leave_room_number, 
					 hotel_order_info_leave_room_info, 
					 hotel_order_info_channel, 
					 hotel_order_info_total_fee, 
					 hotel_order_info_pre_fee, 
					 hotel_order_info_status, 
					 hotel_order_info_id_type, 
					 hotel_order_info_id_info, 
					 hotel_order_info_other_cast_info, 
					 hotel_order_info_other_cast_fee,
					 hotel_order_info_food_ticket,
					 hotel_order_info_guest_name, 
					 hotel_order_info_tuan_name
					  }
					  
					  );
		}
		return DBHelper.ReturnRows(rows);
	}

}
