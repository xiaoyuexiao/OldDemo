package com.jcf.implement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.jcf.interfaces.HotelAcitonInfaces;
import com.jcf.interfaces.HotelRoomAcitonInfaces;
import com.jcf.interfaces.OaAdminAcitonInfaces;
import com.jcf.interfaces.OaRoleAcitonInfaces;
import com.jcf.util.DBHelper;
import com.jcf.util.Limit;
import com.jcf.util.MyUtil;

@Service
public class HotelRoomActionImpments implements HotelRoomAcitonInfaces {

	@Autowired
	JdbcTemplate jdbcTemplate;
	private ArrayList<Map<String, Object>> list;
	private int rows;

	@Override
	public String queryHotelRoom(String hotel_room_info_name) {
		if (jdbcTemplate != null) {
			if(hotel_room_info_name==null){
				String sql="select * from snug_hotel_room_info";
				list = (ArrayList<Map<String, Object>>) jdbcTemplate.queryForList(sql);
			return	DBHelper.ReturnList(list);

			}else{
				String sql="select * from snug_hotel_room_info where hotel_room_info_name=?";
				ArrayList<Map<String, Object>> list=(ArrayList<Map<String, Object>>) jdbcTemplate.queryForList(sql,new Object[]{hotel_room_info_name});
				return	DBHelper.ReturnList(list);
			}
			
			
		}
		
		
		
		return "";
	}

	@Override
	public String insertHotelRoom(
			String hotel_room_info_name,
			String hotel_room_info_flag, 
			String hotel_room_info_type,
			String hotel_room_info_floor,
			String hotel_room_info_numbers,
			String hotel_room_info_standard,
			String hotel_building_info_token,
			String department_id
			)
	   {
		
		if (jdbcTemplate != null) {
			String sql="insert into snug_hotel_room_info (hotel_room_info_name,hotel_room_info_flag,hotel_room_info_type,hotel_room_info_floor,hotel_room_info_numbers,hotel_room_info_standard,hotel_building_info_token,department_id)values(?,?,?,?,?,?,?,?)";
			rows = jdbcTemplate.update(sql,new Object[]{hotel_room_info_name, hotel_room_info_flag, hotel_room_info_type, hotel_room_info_floor, hotel_room_info_numbers, hotel_room_info_standard, hotel_building_info_token, department_id});
			
		}
		return DBHelper.ReturnRows(rows);

	}

	@Override
	public String deletHotelRoomList(String hotel_room_info_name) {
		if (jdbcTemplate != null) {
			String sql="update snug_hotel_room_info set hotel_room_info_flag=0 WHERE hotel_room_info_name=? ";
			
			rows=jdbcTemplate.update(sql,new Object[]{hotel_room_info_name});

		}
		return DBHelper.ReturnRows(rows);
	
	}

	@Override
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
			
			) {
		if (jdbcTemplate != null) {
			String sql="update snug_hotel_room_info set hotel_room_info_name=?,hotel_room_info_flag=?,hotel_room_info_type=?,hotel_room_info_floor=?,hotel_room_info_numbers=?,hotel_room_info_standard=?,hotel_building_info_token=?,department_id=? WHERE hotel_room_info_name=? ";
			rows=jdbcTemplate.update(sql,new Object[]{
					  hotel_room_info_name1,
					  hotel_room_info_flag,
					  hotel_room_info_type,
					  hotel_room_info_floor,
					  hotel_room_info_numbers,
					  hotel_room_info_standard,
					  hotel_building_info_token,
					  department_id,
					  hotel_room_info_name
					  }
					  
					  );
		}
		return DBHelper.ReturnRows(rows);
	}

}
